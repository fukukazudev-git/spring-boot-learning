package com.example.demo.todo.dto;

public class TodoResponse {
    
    private Long id;
    private String title;
    private Boolean done;
    
    // レスポンス用DTO
    public TodoResponse(Long id , String title, Boolean done){
        this.id = id;
        this.title = title;
        this.done = done;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public Boolean getDone(){
        return done;
    }
}
