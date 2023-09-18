package in.das.core.advice;

import in.das.shared.exception.AccountCreationException;
import in.das.shared.exception.BadRequestException;
import in.das.shared.models.ErrorResponse;
import in.das.shared.utils.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(AccountCreationException.class)
    public ResponseEntity<ErrorResponse> handlerAccountCreationException(AccountCreationException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .uuid(CommonUtils.getRandomUUID())
                .errorCode("ERR_001")
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .uuid(CommonUtils.getRandomUUID())
                .errorCode("ERR_002")
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
