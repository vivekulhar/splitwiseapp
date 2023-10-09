package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponseDTO {
    private Long userId;
    private ResponseStatus responseStatus;
    private String message;
}
