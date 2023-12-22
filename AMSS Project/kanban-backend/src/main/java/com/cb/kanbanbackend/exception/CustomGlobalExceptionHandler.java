package com.cb.kanbanbackend.exception;

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

    //HTTP Antworten

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ResponseDto> customHandleNoDataFoundException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("501");
        errorResponse.setResponseMsg("NO_DATA_FOUND");
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseDto> customHandleCommonException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("500");
        errorResponse.setResponseMsg(ex.getMessage());
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> customHandleException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ResponseDto errorResponse = new ResponseDto();
        errorResponse.setResponseCode("500");
        errorResponse.setResponseMsg(ex.getMessage());
        errorResponse.setContent(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
// Diese Methode behandelt Ausnahmen vom Typ `MethodArgumentNotValidException`, die geworfen werden, wenn die Validierung des Anfragekörpers fehlschlägt.
    /**
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
