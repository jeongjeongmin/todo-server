package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    //Jpa레포지토리를 상속하는 인터페이스, <데이터베이스 엔티티, ID(키)형식>

}
