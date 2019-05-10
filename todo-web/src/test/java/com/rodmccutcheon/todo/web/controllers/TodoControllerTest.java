package com.rodmccutcheon.todo.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodmccutcheon.todo.web.TodoApplication;
import com.rodmccutcheon.todo.web.entities.SavedEntity;
import com.rodmccutcheon.todo.web.entities.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebIntegrationTest
public class TodoControllerTest {

    public static final String TODO_URL = "/api/v1/todo";

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected MockMvc mockMvc;

    @Autowired
    protected CacheManager cacheManager;

    @Autowired
    protected WebApplicationContext wac;

    @PostConstruct
    public void intializeControllerTest() {
        DefaultMockMvcBuilder builder = webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Before
    public void setup() {
        clearCaches();
    }

    private void clearCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Test
    public void testCreateGet() throws Exception {
        MvcResult result = mockMvc.perform(post(TODO_URL)
                .content(OBJECT_MAPPER.writeValueAsString(new Todo("foobar")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String url = OBJECT_MAPPER.readValue(result.getResponse().getContentAsByteArray(), SavedEntity.class).getUrl();
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testFailure() {
        //fail();
    }

}
