package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateGroupResponseDTO {
    private Long groupId;
    private ResponseStatus responseStatus;
    private String message;
}
