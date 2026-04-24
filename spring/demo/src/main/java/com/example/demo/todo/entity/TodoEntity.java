package com.example.demo.todo.entity;
import org.hibernate.annotations.DialectOverride.Version;

import jakarta.persistence.*;

//DBのテーブルになる
@Entity
public class TodoEntity {
    //IDを主キーとして自動生成する設定
    @Id
    //ID生成の戦略をIDENTITYに設定することで、データベースが自動的にIDを生成するようになる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean done;

    @Version
    private Integer version; //楽観的ロックのためのバージョンフィールド

    public TodoEntity(){}
    
    public TodoEntity(String title, boolean done){
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
    public boolean isDone(){
        return done;
    }
    public void setDone(boolean done){
        this.done = done;
    }
}
