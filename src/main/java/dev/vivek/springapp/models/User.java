package dev.vivek.springapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @ManyToMany
    private List<Group> groups;

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                ", groups=" + groups +
                '}';
    }
}
