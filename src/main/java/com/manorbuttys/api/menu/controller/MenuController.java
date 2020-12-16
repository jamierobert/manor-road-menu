package com.manorbuttys.api.menu.controller;

import com.manorbuttys.api.menu.model.MenuSection;
import com.manorbuttys.api.menu.repository.MenuRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping(value = "/menu", produces = "application/json")
    public Iterable<MenuSection> getMenu() {
        List<MenuSection> menu = (List<MenuSection>) menuRepository.findAll();
        Stream<MenuSection> sorted = menu.stream()
                .sorted();

        return sorted.collect(Collectors.toList());
    }
}