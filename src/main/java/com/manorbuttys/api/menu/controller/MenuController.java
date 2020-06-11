package com.manorbuttys.api.menu.controller;

import com.manorbuttys.api.menu.model.MenuSection;
import com.manorbuttys.api.menu.repository.MenuRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping(value = "/menu", produces = "application/json")
    public Iterable<MenuSection> getMenu() {
        return menuRepository.findAll();
    }
}
