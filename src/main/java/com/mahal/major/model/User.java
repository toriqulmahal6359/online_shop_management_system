package com.mahal.major.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "{errors.invalid_email}")
    @NotEmpty
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> roles;

    //Constructors
    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public User() { }
}
