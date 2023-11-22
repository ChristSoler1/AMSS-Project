package com.cb.kanbanbackend.exception;

// GlobalException
/*

This code is essential for handling exceptions in a Spring Boot application.
It utilizes @ControllerAdvice to provide a centralized location for managing exceptions across multiple controllers.
The custom exception handlers (@ExceptionHandler methods) address specific exceptions, such as NoDataFoundException
and CommonException, enabling tailored responses for different scenarios. Additionally,
the global exception handler ensures that any unhandled exceptions result in a consistent
error response with a 500 Internal Server Error status. The inclusion of handleMethodArgumentNotValid
method allows for meaningful handling of validation errors in request bodies, maintaining
standardized response format throughout the application.
 */

import com.cb.kanbanbackend.dto.res.ResponseDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Iterator;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception handler for custom exception NoDataFoundException
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ResponseDto> customHandleNoDataFoundException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("501");
        errorResponse.setResponseMsg("NO_DATA_FOUND");
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    // Exception handler for custom exception CommonException
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseDto> customHandleCommonException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("500");
        errorResponse.setResponseMsg(ex.getMessage());
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    // Exception handler for custom exception CommonException
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> customHandleException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("500");
        errorResponse.setResponseMsg(ex.getMessage());
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for MethodArgumentNotValidException for validation json request body
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Order(1)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("400");


        StringBuilder sb = new StringBuilder();
        Iterator var2 = ex.getBindingResult().getAllErrors().iterator();

        while(var2.hasNext()) {
            ObjectError error = (ObjectError)var2.next();
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }

        errorResponse.setResponseMsg("INVALID_REQUEST");
        errorResponse.setContent(sb.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
