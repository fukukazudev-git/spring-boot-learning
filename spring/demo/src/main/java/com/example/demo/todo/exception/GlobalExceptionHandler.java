package com.example.demo.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//アプリ全体の例外を扱うことを宣言
@ControllerAdvice
public class GlobalExceptionHandler {

    //Service層でTodoNotFoundExceptionが発生した場合の処理を定義
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException ex){

        ErrorResponse body = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
        
        // 最初のエラーメッセージを取り出す
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> err.getField() + " " + err.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");
        
        ErrorResponse body = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
