package com.manorbuttys.api.menu.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class ModelTest {
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDeserialization() throws Exception {
        List<MenuSection> menuSections = getMenu();
        MenuSection menuSection = getMenuSection();
        assertThat(menuSections.size(), equalTo(3));
        assertThat(menuSections.get(0).getName(), equalTo(menuSection.getName()));
    }

    @Test
    public void testMenuSectionOrdering() throws Exception {
        List<MenuSection> menuSections = getMenu();
        MenuSection menuSection = getMenuSection();
        assertThat(menuSections.size(), equalTo(3));
        assertThat(menuSections.get(0).getName(), equalTo(menuSection.getName()));
    }

    @Test
    public void testMenuSectionsCanCompared() throws Exception {

        // Given - Unordered list
        MenuSection firstSection = getMenuSection();
        MenuSection secondSection = getMenuSection();

        firstSection.setId("TEST");
        secondSection.setId("TEST");

        secondSection.setSortOrder(2);

        List<MenuSection> menu = new ArrayList<>();
        menu.add(secondSection);
        menu.add(firstSection);

        assertThat(menu.get(0), equalTo(secondSection));
        assertThat(menu.get(1), equalTo(firstSection));

        // When
        Collections.sort(menu);

        // Then
        assertThat(menu.get(0), equalTo(firstSection));
        assertThat(menu.get(1), equalTo(secondSection));
    }

    public MenuSection getMenuSection() throws Exception {
        return objectMapper.readValue(new File("src/test/resources/menu-section.json"), MenuSection.class);
    }

    public List<MenuSection> getMenu() throws Exception {
        return objectMapper.readValue(new File("src/test/resources/menu.json"),
                new TypeReference<>() {
                });
    }
}
