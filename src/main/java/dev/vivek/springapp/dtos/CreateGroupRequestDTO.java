package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequestDTO {
    private String groupName;
    private String groupDescription;
    private String[] userIds;
}
