package pl.java.scalatech.rest.dto;

import java.util.List;

import com.google.common.collect.Lists;


public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = Lists.newArrayList();

    public void addFieldError(String field, String errorCode, String message) {
        FieldErrorDTO error = new FieldErrorDTO(field, errorCode, message);
        fieldErrors.add(error);
    }
}
