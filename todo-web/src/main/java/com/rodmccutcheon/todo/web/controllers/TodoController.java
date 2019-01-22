package com.rodmccutcheon.todo.web.controllers;

import com.rodmccutcheon.todo.web.entities.SavedEntity;
import com.rodmccutcheon.todo.web.entities.Todo;
import com.rodmccutcheon.todo.web.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private static final String BASE_URL = "/api/v1/todo";

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(value = BASE_URL, method = RequestMethod.GET)
    public ResponseEntity<Iterable<Todo>> getTodos() {
        Iterable<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodo(@PathVariable long id) {
        Todo todo = todoRepository.findOne(id);
        return ResponseEntity.ok(todo);
    }

    @RequestMapping(value = BASE_URL, method = RequestMethod.POST)
    public ResponseEntity<SavedEntity> createTodo(@RequestBody String title) {
        Todo savedTodo = todoRepository.save(new Todo(title));
        SavedEntity savedEntity = new SavedEntity(savedTodo.getId(), BASE_URL + "/" + savedTodo.getId());

        return ResponseEntity.ok(savedEntity);
    }

    /*@RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateFloor(@PathVariable long floorId) {
        Floor floorToUpdate = safeFindService.findOrThrow(floorRepository, floorId);
        Floor existingFloor = floorRepository.findOneByBuildingAndFloorNumber(floorToUpdate.getBuilding(), floorOutline.getFloorNumber());

        if (existingFloor != null && existingFloor.getId() != floorId) {
            throw new FloorWithSameBuildingAndNumberExistsException();
        }

        floorToUpdate.setFloorNumber(floorOutline.getFloorNumber());
        floorToUpdate.setName(floorOutline.getName());
        floorToUpdate.setNote(floorOutline.getNote());
        updateFloorplan(floorToUpdate, floorOutline.getFloorplan());

        floorRepository.save(floorToUpdate);
    }*/

    @RequestMapping(value = BASE_URL + "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable long id) {
        Todo todo = todoRepository.findOne(id);
        todoRepository.delete(todo);
    }


}
