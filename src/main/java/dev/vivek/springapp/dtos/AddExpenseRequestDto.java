package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.ExpenseType;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.SplitType;
import dev.vivek.springapp.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AddExpenseRequestDto {
    private String name;
    private String description;
    private int amount;

    private ExpenseType expenseType;
    private String createdBy;
    private String groupId;
    private List<String> paidBy;
    private SplitType splitType;
    private String splitDetails;
}
