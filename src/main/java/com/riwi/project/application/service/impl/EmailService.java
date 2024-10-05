
package com.riwi.project.application.service.impl;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailService {

  Dotenv dotenv = Dotenv.load();
  String apiKey = dotenv.get("SENDINBLUE_API_KEY");

  public void sendEmail(String toEmail, String subject, String content) throws ApiException {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    ApiKeyAuth apiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
    apiKeyAuth.setApiKey(apiKey);

    TransactionalEmailsApi api = new TransactionalEmailsApi();
    SendSmtpEmailSender sender = new SendSmtpEmailSender();
    sender.setEmail("diegomejiasobsu@gmail.com");
    sender.setName("Diego Ramirez");

    List<SendSmtpEmailTo> toList = Arrays.asList(new SendSmtpEmailTo().email(toEmail));

    SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
    sendSmtpEmail.setSender(sender);
    sendSmtpEmail.setTo(toList);
    sendSmtpEmail.setSubject(subject);
    sendSmtpEmail.setHtmlContent(content);

    CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
    System.out.println(response.toString());
  }
}
