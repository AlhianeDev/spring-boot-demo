package com.amigoscode.global.dto;

import com.amigoscode.global.entities.Customer;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class CustomerDto {

    private  Integer id;

    @NotNull(message = "Username shouldn't be null!")
    private String name;

    @Email(message = "Invalid Email!")
    private String email;

    @Min(value = 18, message = "You're younger that 18!")
    @Max(value = 60, message = "You're Older that 60!")
    private Integer age;

    @Pattern(regexp = "^(Female|Male)$", message = "Invalid Gender!")
    private String gender;

    @NotBlank(message = "Nationality shouldn't be Blank!")
    private String nationality;

    @NotNull(message = "MobilE phone Shouldn't be null!")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Mobile Phone Number!")
    private String mobile;

    public static CustomerDto mapToDto(Customer customer) {

        return CustomerDto.builder()

                .id(customer.getId())

                .name(customer.getName())

                .email(customer.getEmail())

                .age(customer.getAge())

                .gender(customer.getGender())

                .nationality(customer.getNationality())

                .mobile(customer.getMobile())

                .build();

    }

}
