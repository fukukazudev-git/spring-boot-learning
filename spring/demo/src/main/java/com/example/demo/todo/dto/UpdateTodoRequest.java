package com.example.demo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// PUT用 全項目必須パターン
public class UpdateTodoRequest {

    @NotBlank
    private String title;

    @NotNull
    private boolean done;

    public String getTitle(){
        return title;
    }

    public boolean isDone(){
        return done;
    }
    
}
