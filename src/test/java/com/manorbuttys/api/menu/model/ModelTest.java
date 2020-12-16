package com.manorbuttys.api.menu.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModelTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testDeserialization() throws Exception {
        objectMapper = new ObjectMapper();
        List<MenuSection> menuSections = objectMapper.readValue(new File("src/test/resources/menu.json"), new TypeReference<List<MenuSection>>() {
        });
        MenuSection menuSection = objectMapper.readValue(new File("src/test/resources/menu-section.json"), MenuSection.class);
        assertThat(menuSections.size(), equalTo(3));
        assertThat(menuSections.get(0).getName(), equalTo(menuSection.getName()));
    }
}
