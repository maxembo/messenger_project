package ru.vsu.cs.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.api.services.EmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send_email")
    public ResponseEntity<String> sendEmail(@RequestParam("email") String email) {
        try {
            return new ResponseEntity<>(emailService.sendSimpleEmail(email), HttpStatus.OK);
        } catch (MailException mailException) {
            System.out.println(mailException.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
