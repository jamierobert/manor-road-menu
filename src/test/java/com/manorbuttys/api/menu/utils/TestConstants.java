package com.manorbuttys.api.menu.utils;

import com.manorbuttys.api.menu.model.Item;
import com.manorbuttys.api.menu.model.MenuSection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestConstants {

    public static List<Item> buildItems() {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder()
                .name("Devious Monkey")
                .count(1)
                .price(BigDecimal.valueOf(2.5))
                .build());
        return items;
    }

    public static MenuSection buildMenuSection() {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder()
                .name("Devious Monkey")
                .count(1)
                .price(BigDecimal.valueOf(2.5))
                .build());

        return MenuSection.builder()
                .name("Dinner Menu")
                .items(items)
                .build();
    }
}
