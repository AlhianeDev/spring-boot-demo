package com.amigoscode.global.Repositories;

import com.amigoscode.global.entities.Customer;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// @DataJpaTest
@SpringBootTest
class CustomerRepoTest {

    @Autowired
    private CustomerRepo underTest;

    Customer customer;

    @BeforeEach
    void setUp() {

        // Given : Setup object or precondition

        customer = Customer.builder()

            .name("Alhiane")

            .email("alhiane@gmail.com")

            .age(25)

            .gender("Male")

            .nationality("MA")

            .mobile("0616962457")

            .build();

    }

    @AfterEach
    public void tearDown() {

        underTest.deleteAll();

    }

    // JUnit Test for save customer operation:

    @Test
    @DisplayName("JUnit Test for save customer operation")
    public void itShouldBeReturnSavedCustomerWhenItSavedOn() {

        // When : Action of behaviours that we are going to test

        Customer savedCustomer = underTest.save(customer);

        // Then : Verify the output

        assertThat(savedCustomer).isNotNull();

        assertThat(savedCustomer.getId()).isGreaterThan(0);

    }

    // JUnit test for get Employee List operation:

    @Test
    @DisplayName("JUnit test for get Employee List operation")
    public void itShouldBeReturnCustomersList() {

        // Given : Setup object or precondition

        Customer customer1 = Customer.builder()

                .name("Alhiane")

                .email("alhiane@gmail.com")

                .age(25)

                .gender("Male")

                .nationality("MA")

                .mobile("0616962457")

                .build();

        Customer customer2 = Customer.builder()

                .name("Aicha")

                .email("aicha@gmail.com")

                .age(23)

                .gender("Female")

                .nationality("MA")

                .mobile("0616962457")

                .build();

        underTest.save(customer1);

        underTest.save(customer2);

        // When : Action of behaviours that we are going to test

        List<Customer> customers = underTest.findAll();

        // Then : Verify the output

        assertThat(customers).isNotEmpty();

        assertThat(customers.size()).isEqualTo(2);

    }

    // JUnit test for get Employee By Id operation:

    @Test
    @DisplayName("JUnit test for get Employee By Id operation")
    public void itShouldBeReturnCustomerById() {

        // Given : Setup object or precondition

        underTest.save(customer);

        // When : Action of behaviours that we are going to test

        Customer gotCustomer = underTest.findById(customer.getId()).get();

        // Then : Verify the output

        assertThat(gotCustomer).isNotNull();

    }

    // JUnit test for get employee update operation:

    @Test
    @DisplayName("JUnit test for get employee update operation")
    public void itShouldBeReturnCustomerObjectWhenItUpdatedOn() {

        // Given : Setup object or precondition

        underTest.save(customer);

        // When : Action of behaviours that we are going to test

        Customer gotCustomer = underTest.findById(customer.getId()).get();

        gotCustomer.setName("Lahcen Alhiane");

        gotCustomer.setEmail("lahcen1234@gmail.com");

        gotCustomer.setNationality("USA");

        gotCustomer.setMobile("0612345678");

        Customer updatedCustomer = underTest.save(gotCustomer);

        // Then : Verify the output

        assertThat(updatedCustomer).isNotNull();

        assertThat(updatedCustomer.getEmail()).isEqualTo("lahcen1234@gmail.com");

    }

    // JUnit test for delete employee operation:

    @Test
    @DisplayName("JUnit test for delete employee operation")
    public void itShouldBeEmptyContentWhenCustomerDIsDeleted() {

        // Given : Setup object or precondition

        underTest.save(customer);

        // When : Action of behaviours that we are going to test

        underTest.deleteById(customer.getId());

        Optional<Customer> deletedCustomer = underTest.findById(customer.getId());

        // Then : Verify the output

        assertThat(deletedCustomer).isEmpty();

    }

    // JUnit test for if employee is existing by email:

    @Test
    @DisplayName("JUnit test for if employee is existing by email")
    public void itShouldCustomerExistsByEmail() {

        // Given:

        Customer customer = new Customer();

        customer.setName("Alhiane");

        customer.setEmail("alhiane@gmail.com");

        customer.setAge(25);

        customer.setGender("Male");

        customer.setNationality("MA");

        customer.setMobile("0616962457");

        underTest.save(customer);

        // When:

        boolean expected = underTest.existsByEmail("alhiane@gmail.com");

        // Then

        assertThat(expected).isTrue();

    }

    // JUnit test for if employee isn't existing by email:

    @Test
    @DisplayName("JUnit test for if employee isn't existing by email")
    public void itShouldCustomerDoesNotExistsByEmail() {

        // Given:

        // When:

        boolean expected = underTest.existsByEmail("alhiane@gmail.com");

        // Then

        assertThat(expected).isFalse();

    }

    // JUnit test for custom query using JPQL with index param

    @Test
    @DisplayName("JUnit test for custom query using JPQL with index param")
    public void itShouldCustomerExistsByName() {

        // Given:

        underTest.save(customer);

        // When:

        Customer gotCustomer = underTest.findByJpql(customer.getName());

        // Then

        assertThat(gotCustomer).isNotNull();

    }

}