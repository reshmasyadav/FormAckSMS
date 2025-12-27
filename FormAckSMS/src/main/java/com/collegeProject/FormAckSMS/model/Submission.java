package com.collegeProject.FormAckSMS.model;

import jakarta.persistence.*;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "prnNumber", unique = true)
    private String prnNumber;
    private String collegeName;
    private String gender;
    private String contact;
    private String degree;
    private String course;
    private String address;

    // Getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPrnNumber() { return prnNumber; }
    public void setPrnNumber(String prnNumber) { this.prnNumber = prnNumber; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Override toString method to print Submission details nicely
    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", prnNumber='" + prnNumber + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", gender='" + gender + '\'' +
                ", contact='" + contact + '\'' +
                ", degree='" + degree + '\'' +
                ", course='" + course + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
