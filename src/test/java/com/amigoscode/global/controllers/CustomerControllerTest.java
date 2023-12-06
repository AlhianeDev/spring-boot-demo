package com.amigoscode.global.controllers;

import com.amigoscode.global.Repositories.CustomerRepo;

import com.amigoscode.global.dto.CustomerDto;

import com.amigoscode.global.entities.Customer;

import com.amigoscode.global.services.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import org.junit.runner.RunWith;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Collections;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    @MockBean
    private CustomerRepo customerRepo;

    @Autowired
    @MockBean
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {

        customer = Customer.builder()

            .id(1)

            .name("Lahcen Alhiane")

            .email("alhiane@gmail.com")

            .age(25)

            .gender("Male")

            .nationality("MA")

            .mobile("0616962457")

        .build();

    }

    @Test
    void canFindAllCustomers() throws Exception {

        List<Customer> customers = Collections.singletonList(customer);

        when(customerService.findAllCustomers()).thenReturn(customers);

        mvc.perform(get("/api/v1/customers")

            .contentType(MediaType.APPLICATION_JSON))

            .andExpect(status().isOk())

            .andExpect(jsonPath("$", hasSize(1)))

            .andExpect(jsonPath("$[0].name", is(customer.getName())));

    }

    @Test
    void canFindCustomerById() throws Exception {

        when(customerService.findCustomerById(1)).thenReturn(customer);

        mvc.perform(get("/api/v1/customers/1")

            .contentType(MediaType.APPLICATION_JSON))

            .andExpect(status().isOk())

            .andExpect(jsonPath("$.name", is(customer.getName())));

    }

    @Test
    void canCreateCustomer() throws Exception {

        CustomerDto customerDto = CustomerDto.mapToDto(customer);

        when(customerService.createCustomer(customerDto)).thenReturn(customer);

        mvc.perform(post("/api/v1/customers")

            .content(asJsonString(customer))

            .contentType(MediaType.APPLICATION_JSON))

            .andExpect(status().isCreated())

            .andExpect(MockMvcResultMatchers.jsonPath("$.id")

                .exists());

    }

    public static String asJsonString(final Object obj) {

        try {

            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    @Test
    void canUpdateCustomerById() throws Exception {

        customer.setEmail("lalala@gmail.com");

        CustomerDto customerDto = CustomerDto.mapToDto(customer);

        when(customerService.updateCustomerById(customerDto)).thenReturn(customer);

        mvc.perform(put("/api/v1/customers")

            .content(asJsonString(customer))

            .contentType(MediaType.APPLICATION_JSON))

            .andExpect(status().isAccepted())

            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())

            .andExpect(MockMvcResultMatchers.jsonPath("$.email")

                .value("lalala@gmail.com"));

    }

    @Test
    void canDeleteCustomerById() throws Exception {

        mvc.perform(delete("/api/v1/customers/1"))

            .andExpect(status().isNoContent());

    }

}