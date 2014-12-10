package pl.java.scalatech.rest.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Slf4j
final class ControllerUtils {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            messages.add(String.format("%s value '%s' %s", constraintViolation.getPropertyPath(), constraintViolation.getInvalidValue(),
                    constraintViolation.getMessage()));
        }

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public VndErrors onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{}", e);
        return new VndErrors("HttpStatus.BAD_REQUEST error", e.getMessage());
    }

    /**
     * Handles the general error case. Report server-side error.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public VndErrors onException(Exception e) {
        log.error("{}", e);
        String msg = StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
        return new VndErrors("HttpStatus.INTERNAL_SERVER_ERROR error ", msg);
    }

}
