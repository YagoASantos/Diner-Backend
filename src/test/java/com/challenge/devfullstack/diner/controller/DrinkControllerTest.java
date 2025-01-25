package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.Drink;
import com.challenge.devfullstack.diner.service.DrinkService;
import com.challenge.devfullstack.diner.util.dto.DrinkDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class DrinkControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrinkService service;

    private static Drink mockedDrink;

    private static ObjectMapper mapper;

    private static String DEFAULT_URL_TEMPLATE = "/api/drinks";

    @BeforeAll
    static void setUp() {
        mockedDrink = new Drink(1L, "Sprite 2L", new BigDecimal("12.00"), false, null);
        mapper = new ObjectMapper();
    }

    @Test
    void shouldReturnStatus201WhenDrinkIsCreated() throws Exception {
        given(service.create(any())).willReturn(mockedDrink);

        String json = mapper.writeValueAsString(new DrinkDto(mockedDrink));
        var response = mvc.perform(
                post(DEFAULT_URL_TEMPLATE)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void shouldReturnStatus200AndCorrectlyBodyWhenDrinkIsFoundedById() throws Exception {
        given(service.findById(1L)).willReturn(mockedDrink);
        var response = mvc.perform(
                get(DEFAULT_URL_TEMPLATE + "/1")
        ).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(new DrinkDto(mockedDrink), mapper.readValue(response.getContentAsString(), DrinkDto.class));
    }

    @Test
    void shouldReturnStatus404WhenDrinkIsNotFounded() throws Exception {
        given(service.findById(75L)).willThrow(EntityNotFoundException.class);
        var response = mvc.perform(
                get(DEFAULT_URL_TEMPLATE + "/75")
        ).andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void shouldReturnStatus400WhenPostDtoIsBlank() throws Exception {
        var response = mvc.perform(
                post(DEFAULT_URL_TEMPLATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new DrinkDto(null, null, null)))
        ).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        System.out.println(response.getContentAsString());
    }

}