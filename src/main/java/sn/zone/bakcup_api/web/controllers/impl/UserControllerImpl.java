package sn.zone.bakcup_api.web.controllers.impl;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import sn.zone.bakcup_api.services.ProductService;
import sn.zone.bakcup_api.services.UserService;
import sn.zone.bakcup_api.web.controllers.UserController;
import sn.zone.bakcup_api.web.dto.ProductResponseDTO;
import sn.zone.bakcup_api.web.dto.UserBasicDTO;
import sn.zone.bakcup_api.web.dto.UserResponseDTO;
import sn.zone.bakcup_api.web.dto.UserRoleDTO;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final ProductService productService;

    public UserControllerImpl(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<UserResponseDTO> create(UserBasicDTO userBasicDTO) {
        UserResponseDTO response = new UserResponseDTO(userService.create(userBasicDTO.toUser()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(response);
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> allDTOs = userService.getAll().stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(allDTOs);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getById(String id) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(new UserResponseDTO(userService.getById(id)));
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getProducts(String id) {
        List<ProductResponseDTO> allDTOs = productService.getByUserId(id).stream()
                .map(ProductResponseDTO::new)
                .toList();
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(allDTOs);
    }

    @Override
    public ResponseEntity<UserResponseDTO> update(String id, UserBasicDTO userBasicDTO) {
        UserResponseDTO response = new UserResponseDTO(userService.update(id, userBasicDTO.toUser()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(response);
    }

    @Override
    public ResponseEntity<UserResponseDTO> updateRole(String id, UserRoleDTO userRoleDTO) {
        UserResponseDTO response = new UserResponseDTO(userService.updateRole(id, userRoleDTO.getRole()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(response);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
