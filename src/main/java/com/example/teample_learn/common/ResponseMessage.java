package com.example.teample_learn.common;

public interface ResponseMessage {

    String SUCCESS = "Success";
    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATE_ID = "Duplicate Id.";
    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "Certification failed";

    String MAIL_FAIL = "Mail send failed.";
    String MAIL_CHECK_FAIL = "Mail Check failed.";
    String DATABASE_ERROR = "Database error.";
}
