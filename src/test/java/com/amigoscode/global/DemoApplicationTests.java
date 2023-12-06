package com.amigoscode.global;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class DemoApplicationTests {

    Calculator calculator = new Calculator();

    @Test
    public void itShouldAddTwoNumbers() {

        // Giver = Inputs:

        int number1 = 10,

            number2 = 20;

        // When:

        int result = calculator.sum(number1, number2);

        // Then

        assertThat(result).isEqualTo(30);

    }

    static class Calculator {

        public Integer sum(int num1, int num2) {

            return num1 + num2;

        }

    }

}
