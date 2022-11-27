package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor

public class TodoService {
    private final TodoRepository todoRepository;
    // 서비스는 우리가 구현해야 하는 구체적인 기능들을 정의해야 함. 기능명세서를 참고 (아이템 추가/조회, 목록 조회, 아이템 수정/삭제, 전체목록 삭제)

    //1 todo 리스트 목록에 아이템을 추가
    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        // 레포지토리로 데이터베이스 값이 입력됨
        return this.todoRepository.save(todoEntity);
    }

    //2 todo 리스트 목록 중 특정 아이템을 조회
    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //입력값이 없으면 404 에러코드를 반환
    }

    //3. todo 리스트 전체 목록을 조회
    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    //4. todo 리스트 목록 중 특정 아이템을 수정
    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if(request.getTitle() != null ){
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder()!=null) {
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted()!=null){
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }

    // 5. todo 리스트 목록 중 특정 아이템을 삭제
    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    //6. todo 리스트 전체 목록을 삭제
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

}
