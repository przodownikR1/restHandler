package pl.java.scalatech.rest.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class NotFoundExceptionHandler {
    private final static String CATEGORY_NOT_FOUND_ID = "category_404_id_error";
    private final static String CATEGORY_NOT_FOUND_NAME = "category_404_name_error";
    private MessageSource messageSource;

    protected Locale locale = Locale.getDefault();

    @Autowired
    public NotFoundExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

   /* @ExceptionHandler({ CategoryException.class })
    public ResponseEntity<?> handlePersonNotFound(CategoryException ce) {

        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), messageSource.getMessage(CATEGORY_NOT_FOUND_ID, new Object[] { ce.getId() },
                locale)), HttpStatus.NOT_FOUND);

    }*/

}
