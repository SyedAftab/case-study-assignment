package com.justlife.customerservice.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Aftab
 */
public record CustomerRequest(
        @NotBlank
        String fullName,
        @NotBlank
        @Email
        String email,
        String phone,
        String address) {

}
