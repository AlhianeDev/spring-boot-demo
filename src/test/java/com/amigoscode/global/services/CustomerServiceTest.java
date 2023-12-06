package com.amigoscode.global.services;

import com.amigoscode.global.Repositories.CustomerRepo;

import com.amigoscode.global.dto.CustomerDto;

import com.amigoscode.global.entities.Customer;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.BeanUtils;

import java.util.List;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;

import static org.mockito.BDDMockito.willDoNothing;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    // private AutoCloseable autoCloseable;

    @InjectMocks
    private CustomerService underTest;

    private Customer customer;

    @BeforeEach
    void setUp() {

        // Given : Setup object or precondition

        customer = Customer.builder()

            .id(1)

            .name("Alhiane")

            .email("alhiane@gmail.com")

            .age(25)

            .gender("Male")

            .nationality("MA")

            .mobile("0616962457")

            .build();

    }

    /*

        @AfterEach
        void tearDown() throws Exception {

            // autoCloseable.close();

            // customerRepo.deleteAll();

        }

    */

    // JUnit test for createCustomer method

    @Test
    @DisplayName("JUnit test for createCustomer method")
    void givenCustomerObject_whenSaveCustomer_thenReturnCustomerObject() throws Exception {

        // Given : Setup object or precondition:

        Customer inCustomer = Customer.builder()

            .id(1)

            .name("Lahcen Alhiane")

            .email("alhiane@gmail.com")

            .age(25)

            .gender("Male")

            .nationality("MA")

            .mobile("0616962457")

        .build();

        when(customerRepo.findByEmail(inCustomer.getEmail()))

            .thenReturn(Optional.empty());

        given(customerRepo.save(inCustomer)).willReturn(inCustomer);

        // when -  action or the behaviour that we are going test

        CustomerDto customerDto = CustomerDto.mapToDto(inCustomer);

        Customer savedCustomer = underTest.createCustomer(customerDto);

        // then - verify the output

        assertThat(savedCustomer).isNotNull();

    }

    // JUnit test for createCustomer method which throws exception:

    @Test
    @DisplayName("JUnit test for createCustomer method which throws exception")
    void givenExistingEmail_whenSaveCustomer_thenThrowsException() throws Exception {

        // Given : Setup object or precondition:

        Customer inCustomer = new Customer();

        CustomerDto customerDto = CustomerDto.builder()

            .id(1)

            .name("Aicha Alhiane")

            .email("aicha@gmail.com")

            .age(23)

            .gender("Female")

            .nationality("MA")

            .mobile("0616962457")

            .build();

        BeanUtils.copyProperties(customerDto, inCustomer);

        when(customerRepo.save(any(Customer.class))).thenReturn(inCustomer);

        // when -  action or the behaviour that we are going test

        underTest.createCustomer(customerDto);

        when(customerRepo.findByEmail(inCustomer.getEmail()))

                .thenReturn(Optional.of(inCustomer));

        // then - verify the output

        assertThrows(Exception.class, () -> { underTest.createCustomer(customerDto); });

    }

    // JUnit test for getAllCustomers method:

    @Test
    @DisplayName("JUnit test for getAllCustomers method")
    void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {

        // Given : Setup object or precondition:

        Customer customer1 = new Customer();

        Customer customer2 = new Customer();

        CustomerDto customerDto1 = CustomerDto.builder()

            .id(1)

            .name("Aicha Alhiane")

            .email("aicha@gmail.com")

            .age(23)

            .gender("Female")

            .nationality("MA")

            .mobile("0616962457")

        .build();

        CustomerDto customerDto2 = CustomerDto.builder()

            .id(2)

            .name("Khadija Chafi")

            .email("khadija@gmail.com")

            .age(58)

            .gender("Female")

            .nationality("MA")

            .mobile("0616962457")

        .build();

        BeanUtils.copyProperties(customerDto1, customer1);

        BeanUtils.copyProperties(customerDto2, customer2);

        // given(customerRepo.save(customer1)).willReturn(customer1);

        // given(customerRepo.save(customer2)).willReturn(customer2);

        when(customerRepo.save(customer1)).thenReturn(customer1);

        when(customerRepo.save(customer2)).thenReturn(customer2);

        underTest.createCustomer(customerDto1);

        underTest.createCustomer(customerDto2);

        when(customerRepo.findAll()).thenReturn(List.of(customer1, customer2));

        // when -  action or the behaviour that we are going test

        List<Customer> customers = underTest.findAllCustomers();

        // then - verify the output

        assertThat(customers).isNotEmpty();

        assertThat(customers.size()).isEqualTo(2);

    }

    // JUnit test for getCustomerById method:

    @Test
    @DisplayName("JUnit test for getCustomerById method")
    void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {

        // Given : Setup object or precondition:

        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));

        // when -  action or the behaviour that we are going test:

        Customer savedCustomer = underTest.findCustomerById(customer.getId());

        // then - verify the output

        assertThat(savedCustomer).isNotNull();

    }

    // JUnit test for updateCustomer method

    @Test
    @DisplayName("JUnit test for updateCustomer method")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {

        // Given : Setup object or precondition:

        when(customerRepo.findById(1)).thenReturn(Optional.ofNullable(customer));

        Customer gotCustomer = underTest.findCustomerById(1);

        gotCustomer.setEmail("email@gmail.com");

        when(customerRepo.save(gotCustomer)).thenReturn(gotCustomer);

        // when -  action or the behaviour that we are going test:

        CustomerDto customerDto = CustomerDto.mapToDto(gotCustomer);

        Customer updatedCustomer = underTest.updateCustomerById(customerDto);

        // then - verify the output

        assertThat(updatedCustomer).isNotNull();

        assertThat(updatedCustomer.getEmail()).isEqualTo("email@gmail.com");

    }

    // JUnit test for deleteCustomer method:

    @Test
    @DisplayName("JUnit test for deleteCustomer method")
    void givenCustomerId_whenDeleteCustomer_thenNothing() throws Exception {

        // Given : Setup object or precondition:

        // when(customerRepo.findById(1)).thenReturn(Optional.ofNullable(customer));

        // doNothing().when(customerRepo).deleteById(1);

        willDoNothing().given(customerRepo).deleteById(1);

        // when -  action or the behaviour that we are going test:

        underTest.deleteCustomerById(1);

        // then - verify the output

        // assertAll(() -> underTest.deleteCustomerById(1));

        verify(customerRepo, times(1)).deleteById(1);

    }

}