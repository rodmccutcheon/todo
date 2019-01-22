package com.rodmccutcheon.todo.web.data;

import com.rodmccutcheon.todo.web.entities.Todo;
import com.rodmccutcheon.todo.web.repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@Profile("sample-data")
public class SampleData {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleData.class);

    @Autowired
    private TodoRepository todoRepository;

    @PostConstruct
    @Transactional
    public void seedDatabase() {
        LOGGER.info("Seeding database with test data...");

        todoRepository.save(new Todo("Vacuum"));
        todoRepository.save(new Todo("Wash dishes"));
        todoRepository.save(new Todo("Wash laundry"));

        LOGGER.info("Seeding database complete.");
    }
}
