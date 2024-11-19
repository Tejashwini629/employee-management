package com.wecodee.EmployeeManagement.Controller;

import com.wecodee.EmployeeManagement.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> exception = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(res->
                {
                    exception.put(res.getField(), res.getDefaultMessage());
                }
        );
        return exception;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> userNotFoundException(UserNotFoundException userEx){
        Map<String,String> error=new HashMap<>();
        error.put("User Not found with id",userEx.getMessage());
        return error;
    }
}
