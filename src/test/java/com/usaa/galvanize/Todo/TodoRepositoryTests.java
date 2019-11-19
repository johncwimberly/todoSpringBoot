package com.usaa.galvanize.Todo;

import com.usaa.galvanize.Todo.entities.TodoEntity;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepo;

    @After
    public void tearDown() throws Exception {
        System.out.println("We are running this how often?");
        todoRepo.deleteAll();
    }

    @Test
    public void testFetchData(){
        TodoEntity todo = new TodoEntity("buy dog");
        TodoEntity addedEntity = this.todoRepo.save(todo);
        /*Test data retrieval*/
        TodoEntity retrievedEntity = todoRepo.findById(addedEntity.getId());

        assertNotNull(todo);
        assertEquals(addedEntity.getId(), retrievedEntity.getId());
        assertEquals("buy dog", retrievedEntity.getName());
    }

    @Test
    public void testFetchNonExistent() {
        TodoEntity todo = todoRepo.findById(53);
        assertNull(todo);
    }

    @Test
    public void testFetchAll() {
        TodoEntity todo = new TodoEntity("buy dog");
        this.todoRepo.save(todo);
        TodoEntity newTodo = new TodoEntity("sell dog");
        this.todoRepo.save(newTodo);

        Iterable<TodoEntity> todos = todoRepo.findAll();
        List<TodoEntity> entityList = new ArrayList<TodoEntity>();

        Iterator<TodoEntity> todoIterable = todos.iterator();
        while(todoIterable.hasNext()){
            entityList.add(todoIterable.next());
        }

        assertEquals(2, entityList.size());

    }

    @Test
    public void testUpdateShouldAppropriatelyModifyEntity(){
        TodoEntity todo = new TodoEntity("buy dog");
        TodoEntity addedEntity = this.todoRepo.save(todo);
        /*Test data retrieval*/

        TodoEntity updateEntity = new TodoEntity("buy dog");
        updateEntity.setId(addedEntity.getId());
        updateEntity.setCompleted(true);

        todoRepo.save(updateEntity);

        TodoEntity actual = todoRepo.findById(addedEntity.getId());
        assertNotNull(actual);
        assertEquals(true, actual.isCompleted());
    }

    @Test
    public void testDeleteShouldRemoveEntityFromTable() {
        TodoEntity todo = new TodoEntity("buy dog");
        todo.setCompleted(true);

        TodoEntity addedEntity = this.todoRepo.save(todo);

        this.todoRepo.delete(addedEntity);

        assertNull(todoRepo.findById(addedEntity.getId()));
    }
}
