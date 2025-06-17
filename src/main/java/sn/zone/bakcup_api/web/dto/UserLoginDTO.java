package sn.zone.bakcup_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;
}
