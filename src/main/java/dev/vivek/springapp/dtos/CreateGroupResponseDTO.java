package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateGroupResponseDTO {
    private Long groupId;
    private ResponseStatus responseStatus;
    private UserDto createdBy;
    private String message;
    private List<UserDto> users;
    private String groupDescription;
}
