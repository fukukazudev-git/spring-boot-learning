package com.example.demo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// POST用 
public class CreateTodoRequest{
    @NotBlank
    private String title;

    //POST 時は done を送らせない設計も多い
    //必要なら @NotNullを付けてもよい
    @NotNull
    private Boolean done;

    public String getTitle(){
        return title;
    }
    
    public Boolean getDone(){
        return done;
    }
}