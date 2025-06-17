package sn.zone.bakcup_api.services;

import java.util.List;

import sn.zone.bakcup_api.data.entities.Product;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getByUserId(String userId);
    List<Product> getAll();
    Product update(String id, Product product);
    boolean delete(String id);
}
