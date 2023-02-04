package com.justinmtech.healthyhabits.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class VisualizerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VisualizerController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}