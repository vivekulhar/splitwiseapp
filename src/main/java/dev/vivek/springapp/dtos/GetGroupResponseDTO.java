package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGroupResponseDTO {
    private Group group;
    private ResponseStatus responseStatus;
    private String message;
}
