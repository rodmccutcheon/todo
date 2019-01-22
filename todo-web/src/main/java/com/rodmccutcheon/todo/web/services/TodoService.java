package com.rodmccutcheon.todo.web.services;

import com.rodmccutcheon.todo.web.entities.Todo;
import com.rodmccutcheon.todo.web.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    public static final String CACHE_KEY = "todo";

    @Autowired
    private TodoRepository todoRepository;

    @Cacheable(value = CACHE_KEY, key = "#id")
    public Todo findOne(Long id) {
        return todoRepository.findOne(id);
    }

}
