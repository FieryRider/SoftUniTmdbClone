package com.fieryrider.tmdbclone.global;

import com.fieryrider.tmdbclone.models.dtos.exceptions.InputErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public @ResponseBody Map<String, List<InputErrorDto>> handleInvalidInput(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        Map<String, List<InputErrorDto>> errors = new HashMap<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage =  error.getDefaultMessage();
            //TODO Find how to get failed constraint type
            errors.putIfAbsent(fieldName, new ArrayList<>());
            errors.get(fieldName).add(new InputErrorDto(errorMessage,null));
        }
        return errors;
    }
}
