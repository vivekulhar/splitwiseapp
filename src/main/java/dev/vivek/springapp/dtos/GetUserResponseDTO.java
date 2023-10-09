package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDTO {
    private String name;
    private String uname;

    private ResponseStatus responseStatus;
    private String message;
}
