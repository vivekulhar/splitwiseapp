package dev.vivek.springapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    private String uname;
    private String phone;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "GROUP_USERS_MAPPING",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
    private List<Group> groups;

    private UserType userType;

//    @Override
//    public String toString() {
//        return "User{" +
//                "uname='" + uname + '\'' +
//                ", phone='" + phone + '\'' +
//                ", password='" + password + '\'' +
//                ", userStatus=" + userStatus +
//                ", groups=" + groups +
//                ", userType=" + userType +
//                '}';
//    }
}
