package com.collegeProject.FormAckSMS.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    // Twilio credentials (replace with your own Twilio account SID, Auth Token, and phone number)
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String FROM_PHONE_NUMBER = "+19789459132";  // Your Twilio phone number

    // Method to send acknowledgment SMS
    public void sendAcknowledgment(String contact, String msg) {
        // Initialize Twilio with account SID and Auth Token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send SMS
        Message message = Message.creator(
                        new PhoneNumber(contact),  // Recipient's phone number
                        new PhoneNumber(FROM_PHONE_NUMBER),  // Your Twilio phone number
                        msg)  // Message content
                .create();

        // Log the message SID for confirmation
        System.out.println("Message sent: " + message.getSid());
    }
}
