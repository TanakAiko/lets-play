package sn.zone.bakcup_api.services;

import java.util.List;

import sn.zone.bakcup_api.data.entities.User;
import sn.zone.bakcup_api.data.enums.Role;

public interface UserService {
    User create(User user);
    User getById(String id);
    User getByEmail(String name);
    List<User> getAll();
    User update(String id, User user);
    User updateRole(String id, Role rol);
    boolean delete(String id);
}
