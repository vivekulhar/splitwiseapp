package dev.vivek.springapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseModel{
    private String name;
    private String description;
    private Date createdAt;
    @ManyToOne
    private User createdBy;

    @ManyToMany
    private List<User> group_admins;

    @ManyToMany
    private List<User> group_members;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
