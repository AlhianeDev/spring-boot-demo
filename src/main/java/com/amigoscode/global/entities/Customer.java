package com.amigoscode.global.entities;

import com.amigoscode.global.dto.CustomerDto;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private String gender;

    private String nationality;

    private String mobile;

    public static Customer mapToCustomer(CustomerDto customerDto) {

        return Customer.builder()

            .id(customerDto.getId())

            .name(customerDto.getName())

            .email(customerDto.getEmail())

            .age(customerDto.getAge())

            .gender(customerDto.getGender())

            .nationality(customerDto.getNationality())

            .mobile(customerDto.getMobile())

        .build();

    }

}
