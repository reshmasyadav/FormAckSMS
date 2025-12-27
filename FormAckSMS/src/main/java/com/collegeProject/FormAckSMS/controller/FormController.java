package com.collegeProject.FormAckSMS.controller;

import com.collegeProject.FormAckSMS.model.Submission;
import com.collegeProject.FormAckSMS.repository.SubmissionRepository;
import com.collegeProject.FormAckSMS.service.SmsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class FormController {

    @Autowired
    private SubmissionRepository repo;

    @Autowired
    private SmsService smsService;

    @GetMapping("/viewAllData")
    public String viewAllData(Model model) {
        List<Submission> allSubmissions = repo.findAll();
        model.addAttribute("submissions", allSubmissions);
        return "viewAllData";
    }

    @GetMapping("/searchByPrn")
    public String searchByPrn(@RequestParam("prn") String prn, Model model) {
        try {
            Submission submission = repo.findByPrnNumber(prn);
            if (submission != null) {
                model.addAttribute("submission", submission);
            } else {
                model.addAttribute("message", "No data found for PRN: " + prn);
            }
        } catch (Exception e) {
            model.addAttribute("message", "An error occurred while searching for PRN: " + prn);
            e.printStackTrace();
        }
        return "admissionData";
    }

    @GetMapping("/deleteSubmission")
    @Transactional
    public String deleteSubmission(@RequestParam("prn") String prn, Model model) {
        try {
            Submission submission = repo.findByPrnNumber(prn);
            if (submission != null) {
                repo.deleteByPrnNumber(prn);
                model.addAttribute("message", "Data for PRN: " + prn + " has been deleted.");
            } else {
                model.addAttribute("message", "No data found for PRN: " + prn);
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error occurred while deleting data for PRN: " + prn);
            e.printStackTrace();
        }
        return "admissionData";
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("submission", new Submission());
        return "form";
    }

    @RequestMapping("/admissionData")
    public String showAdmissionData() {
        return "admissionData";
    }

    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute Submission submission,
                             BindingResult br,
                             @RequestParam("submitType") String submitType,
                             Model model) {

        // Check if PRN Number already exists
        if (repo.existsByPrnNumber(submission.getPrnNumber())) {
            br.rejectValue("prnNumber", "error.submission", "PRN Number already exists");
        }

        if (br.hasErrors()) {
            return "form";
        }

        // Save submission
        repo.save(submission);

        // Send SMS if acknowledgment selected
        if ("acknowledgment".equals(submitType)) {
            try {
                smsService.sendAcknowledgment(
                        "+91"+submission.getContact(),
                        "Hi, " + submission.getFirstName() + ", we received your submission."
                );
            } catch (Exception e) {
                model.addAttribute("message", "Failed to send SMS: " + e.getMessage());
                e.printStackTrace(); // Log detailed error
                return "admissionData"; // Optional: show user-friendly message
            }
        }

        return "success";
    }
}
