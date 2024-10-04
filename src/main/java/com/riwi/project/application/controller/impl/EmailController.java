package com.riwi.project.application.controller.impl;

import com.riwi.project.application.service.impl.EmailService;
import com.riwi.project.application.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sendinblue.ApiException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @PostMapping("/send")
  public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
    try {
      emailService.sendEmail(request.getTo(), request.getSubject(), request.getContent());
      return ResponseEntity.ok("Email sent successfully");
    } catch (ApiException e) {
      return ResponseEntity.badRequest().body("Failed to send email: " + e.getMessage());
    }
  }
}