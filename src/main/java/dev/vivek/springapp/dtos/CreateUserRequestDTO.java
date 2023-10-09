package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequestDTO {
    private String name;
    private String uname;
    private String pwd;
}
