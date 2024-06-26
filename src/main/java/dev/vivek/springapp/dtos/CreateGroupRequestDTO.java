package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateGroupRequestDTO {
    private String name;
    private String description;
}
