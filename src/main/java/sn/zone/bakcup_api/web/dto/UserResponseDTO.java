package sn.zone.bakcup_api.web.dto;

import lombok.Data;
import sn.zone.bakcup_api.data.entities.User;

@Data
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;

    public UserResponseDTO (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}