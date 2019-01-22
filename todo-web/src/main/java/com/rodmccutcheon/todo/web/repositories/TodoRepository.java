package com.rodmccutcheon.todo.web.repositories;

import com.rodmccutcheon.todo.web.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> { }
