package com.riwi.project.application.service.IModel;

import sendinblue.ApiException;

public interface IModelEmail {
  oid sendEmail(String toEmail, String subject, String content) throws ApiException;

}
