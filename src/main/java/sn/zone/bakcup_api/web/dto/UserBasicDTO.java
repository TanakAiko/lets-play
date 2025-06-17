package sn.zone.bakcup_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zone.bakcup_api.data.entities.User;
import sn.zone.bakcup_api.data.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDTO {
    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    public User toUser() {
        return new User(name, email, password, Role.USER);
    }
}
