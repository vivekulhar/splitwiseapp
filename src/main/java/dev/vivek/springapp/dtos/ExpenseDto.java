package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.ExpenseType;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpenseDto {
    private String name;
    private String description;
    private int amount;
    private Date createdAt;

    private ExpenseType expenseType;


    private UserDto createdBy;


    private GroupDto group;
}
