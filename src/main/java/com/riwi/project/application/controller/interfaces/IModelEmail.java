package com.riwi.project.application.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.riwi.project.application.dto.request.EmailRequest;

public interface IModelEmail {
  ResponseEntity<String> sendEmail(EmailRequest request);

}
