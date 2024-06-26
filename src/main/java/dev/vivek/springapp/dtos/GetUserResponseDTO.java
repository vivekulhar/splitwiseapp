package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserResponseDTO {
    private String phoneNumber;
    private String uname;

    private List<GroupDto> groups;
}
