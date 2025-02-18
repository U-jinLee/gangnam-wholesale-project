package com.gangnam.wholesale.application.customer;

public record CustomerRequestDto(String email, String password, String registrationNumber) {
}