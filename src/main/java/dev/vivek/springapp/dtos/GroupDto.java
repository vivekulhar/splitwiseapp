package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GroupDto {
    private String name;
    private String description;

    private UserDto createdBy;
    private List<UserDto> users;
}
