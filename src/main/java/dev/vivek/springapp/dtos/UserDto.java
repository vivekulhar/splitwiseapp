package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.UserStatus;
import dev.vivek.springapp.models.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String phoneNumber;
    private String uname;
    private UserStatus userStatus;
    private UserType userType;
    private List<GroupDto> groups;
}
