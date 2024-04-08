package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserForGroupDto {
    private String uname;
    private String phone;
    private String password;
    private UserStatus userStatus;
}
