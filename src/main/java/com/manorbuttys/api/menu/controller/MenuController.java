package com.manorbuttys.api.menu.controller;

import com.manorbuttys.api.menu.model.MenuSection;
import com.manorbuttys.api.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping(value = "/menu", produces = "application/json")
    public Iterable<MenuSection> getMenu() {
        return menuRepository.findAll();
    }
}
