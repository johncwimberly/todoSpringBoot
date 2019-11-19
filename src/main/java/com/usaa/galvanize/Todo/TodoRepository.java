package com.usaa.galvanize.Todo;

import com.usaa.galvanize.Todo.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, Integer> {

    TodoEntity findById(int id);
}
