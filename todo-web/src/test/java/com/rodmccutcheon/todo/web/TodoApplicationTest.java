package com.rodmccutcheon.todo.web;

import com.rodmccutcheon.test.groups.Smoke;
import com.rodmccutcheon.test.groups.WebIntegration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@Category({ WebIntegration.class, Smoke.class })
public class TodoApplicationTest {

    @Test
    public void contextLoads() {
    }

}