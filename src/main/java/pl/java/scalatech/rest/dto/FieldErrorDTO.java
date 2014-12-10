package pl.java.scalatech.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDTO {

    @Getter
    private String field;
    @Getter
    private String errorCode;
    @Getter
    private String message;
}
