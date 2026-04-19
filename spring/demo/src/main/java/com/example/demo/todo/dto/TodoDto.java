package com.example.demo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TodoDto{

    // id に@NotNull は付けない→
    // POSTでエラーになるため @NotNull(message = "id must not be null")
    // idはControllerのパスパラメータで受け取る
    private Long id;

    @NotBlank(message = "title must not be blank")
    @Size(max = 255, message = "title must be 255 characters or less")
    private String title;

    // Boolean (ラッパークラス)
    // nullを取れる→@NotNullをつけられる。JSONからの入力でnullが来た場合に検知可能。
    //REST APIの入力はJSONのため、クライアントがdoneを送ってこない→null になるケースが存在する。
    @NotNull(message = "done must not be null")
    private Boolean done;

    public TodoDto(){

    }
    public TodoDto(Long id , String title, Boolean done){
        this.id = id;
        this.title = title;
        this.done = done;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public Boolean isDone(){
        return done;
    }
    public void setDone(Boolean done){
        this.done = done;
    }
}

/*
@NotBlank
文字列がnullでなく、空でなく、空白のみでないことを検証するアノテーション
文字列の入力が必須である場合に使用される。
@Size(max = 255)
文字列の長さが指定された最大値以下であることを検証するアノテーション
入力の長さ制限を設ける場合に使用される。
@NotNull
値がnullでないことを検証するアノテーション
 */