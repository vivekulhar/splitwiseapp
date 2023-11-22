package dev.vivek.springapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String uname;
    private String phone;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "members")
    private List<Group> groups;

}
