package com.example.demo.todo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.todo.dto.CreateTodoRequest;
import com.example.demo.todo.dto.UpdateTodoRequest;
import com.example.demo.todo.dto.TodoResponse;
import com.example.demo.todo.exception.TodoNotFoundException;
import com.example.demo.todo.dto.TodoDto;
import com.example.demo.todo.entity.TodoEntity;
import com.example.demo.todo.repository.TodoRepository;

@Service
@Transactional  //更新処理が途中で失敗した場合でもロールバック
public class TodoService {
    
    private final TodoRepository repository;

    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    //登録処理
    public TodoResponse create(CreateTodoRequest request) {
    TodoEntity entity = new TodoEntity(request.getTitle(), request.getDone());
    TodoEntity saved = repository.save(entity);
    return new TodoResponse(saved.getId(), saved.getTitle(), saved.isDone());
    }



    //全件取得
    public List<TodoDto> getAll(){
        //repository.findAll()はList<TodoEntity>を返す。これをList<TodoDto>に変換するためにストリームAPIを使用している
        return repository.findAll().stream() // findAllで全件取得、stream()で1件ずつ処理するモードへ
                .map(this::toDto)   //map()でEntity→DTOに変換する処理を適用
                .collect(Collectors.toList());  //ToList()でListに戻す
    }

    //更新処理
    public TodoResponse update(Long id, UpdateTodoRequest request){

        //orElseThrow()は値が無い場合に例外を投げる
        TodoEntity entity = repository.findById(id)
               .orElseThrow(() -> new TodoNotFoundException(id));

        entity.setTitle(request.getTitle());
        entity.setDone(request.isDone());

        TodoEntity updated = repository.save(entity);
        return new TodoResponse(updated.getId(), updated.getTitle(), updated.isDone());
    }

    //削除処理
    public void delete(Long id){
        TodoEntity entity = repository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));
        repository.delete(entity);
    }
    
    //DTO変換をメソッド化、コードの重複を減らし、保守性も上げる
    private TodoDto toDto(TodoEntity e ){
        return new TodoDto(e.getId(), e.getTitle(), e.isDone());
    }
}
