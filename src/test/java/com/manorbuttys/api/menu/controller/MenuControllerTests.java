package com.manorbuttys.api.menu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manorbuttys.api.menu.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuRepository menuRepository;


    @Test
    public void testMenuEndpoint() throws Exception {
        mockMvc.perform(get("/menu"))
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name")
                        .value("Lunch Items"))
                .andExpect(jsonPath("$[0].items[0].name")
                        .value("Roast Beef and Onion Baguette"));
    }
}
