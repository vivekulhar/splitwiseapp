package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddUsersToGroupDto {
    private List<Long> userId;
}
