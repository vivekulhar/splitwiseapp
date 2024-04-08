package dev.vivek.springapp.exception;

import dev.vivek.springapp.dtos.GetUserResponseDTO;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userDoesNotExists) {
    }
}
