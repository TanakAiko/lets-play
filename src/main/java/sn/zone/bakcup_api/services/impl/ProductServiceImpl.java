package sn.zone.bakcup_api.services.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sn.zone.bakcup_api.data.entities.Product;
import sn.zone.bakcup_api.data.entities.User;
import sn.zone.bakcup_api.data.enums.Role;
import sn.zone.bakcup_api.data.repositories.ProductRepository;
import sn.zone.bakcup_api.data.repositories.UserRepository;
import sn.zone.bakcup_api.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public List<Product> getByUserId(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return productRepository.findByUserId(userId);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(String id, Product product) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        User user = userRepository.findById(updateProduct.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        if (!updateProduct.getUserId().equals(userId) && user.getRole() != Role.ADMIN) {
            System.out.println("403-Forbidden (from updateProduct)");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Can't update other's product");
        }

        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        productRepository.save(updateProduct);
        return updateProduct;
    }

    @Override
    public boolean delete(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        User user = userRepository.findById(product.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        if (!product.getUserId().equals(userId) && user.getRole() != Role.ADMIN) {
            System.out.println("403-Forbidden (from deleteProduct)");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Can't delete other's product");
        }

        productRepository.delete(product);
        return true;
    }
}
