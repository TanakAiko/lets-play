package sn.zone.bakcup_api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sn.zone.bakcup_api.web.dto.ProductResponseDTO;
import sn.zone.bakcup_api.web.dto.UserBasicDTO;
import sn.zone.bakcup_api.web.dto.UserResponseDTO;
import sn.zone.bakcup_api.web.dto.UserRoleDTO;

@RequestMapping("api/users")
public interface UserController {
    @GetMapping
    ResponseEntity<List<UserResponseDTO>> getAll();

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserBasicDTO userBasicDTO);

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO> getById(@PathVariable String id);

    @GetMapping("/{id}/products")
    ResponseEntity<List<ProductResponseDTO>> getProducts(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<UserResponseDTO> update(@PathVariable String id, @RequestBody @Valid UserBasicDTO userBasicDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    ResponseEntity<UserResponseDTO> updateRole(@PathVariable String id, @RequestBody @Valid UserRoleDTO userRoleDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
