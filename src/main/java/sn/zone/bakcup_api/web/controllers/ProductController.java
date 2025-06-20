package sn.zone.bakcup_api.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sn.zone.bakcup_api.web.dto.ProductBasicDTO;
import sn.zone.bakcup_api.web.dto.ProductResponseDTO;

@RequestMapping("api/products")
public interface ProductController {
    @PostMapping
    ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductBasicDTO productBasicDTO);

    @GetMapping
    ResponseEntity<List<ProductResponseDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDTO> getById(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDTO> update(@PathVariable String id,
            @RequestBody @Valid ProductBasicDTO productBasicDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
