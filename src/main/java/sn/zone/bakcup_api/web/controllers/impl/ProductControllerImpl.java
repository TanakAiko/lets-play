package sn.zone.bakcup_api.web.controllers.impl;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import sn.zone.bakcup_api.services.ProductService;
import sn.zone.bakcup_api.web.controllers.ProductController;
import sn.zone.bakcup_api.web.dto.ProductBasicDTO;
import sn.zone.bakcup_api.web.dto.ProductResponseDTO;

@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final String maxAge = "300";

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> create(ProductBasicDTO productBasicDTO) {
        System.out.println("CREATE product : " + productBasicDTO);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        System.out.println("userId(from createProduct): " + userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=" + maxAge)
                .body(new ProductResponseDTO(productService.create(productBasicDTO.toProduct(userId))));
    }

    @Override
    public ResponseEntity<ProductResponseDTO> getById(String id) {
        System.out.println("GET(getById) product : " + id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=" + maxAge)
                .body(new ProductResponseDTO(productService.getById(id)));
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        System.out.println("GET(getAll) products");
        List<ProductResponseDTO> allDtos = productService.getAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=" + maxAge)
                .body(allDtos);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> update(String id, ProductBasicDTO productBasicDTO) {
        System.out.println("UPDATE product : " + productBasicDTO);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        System.out.println("userId(from updateProduct): " + userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=" + maxAge)
                .body(new ProductResponseDTO(
                        productService.update(
                                id, productBasicDTO.toProduct(userId))));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        System.out.println("DELETE product : " + id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
