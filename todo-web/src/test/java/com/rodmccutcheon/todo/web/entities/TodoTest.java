package com.rodmccutcheon.todo.web.entities;

import com.rodmccutcheon.test.groups.Unit;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.DateTimeException;

@Category(Unit.class)
public class TodoTest {

    @Test(expected = DateTimeException.class)
    public void testCantHaveInvalidZone() {
        new Todo("foo").setTimeZone("Foobar");
    }

}