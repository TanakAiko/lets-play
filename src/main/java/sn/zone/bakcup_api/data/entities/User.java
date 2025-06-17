package sn.zone.bakcup_api.data.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sn.zone.bakcup_api.data.enums.Role;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "user")
public class User extends AbstractType {
    @Indexed(unique = true)
    private String email;
    private String password;
    private Role role;

    public User(String name, String email, String password, Role role) {
        super(name);
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
