package dev.vivek.springapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String name;
    private String description;
    private int amount;
    private Date createdAt;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Group group;


}
