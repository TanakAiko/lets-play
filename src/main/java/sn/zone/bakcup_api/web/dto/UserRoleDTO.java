package sn.zone.bakcup_api.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zone.bakcup_api.data.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {
    @NotNull
    private Role role;
}
