package org.example.services;

import org.example.dbc.ProductDBC;
import org.example.models.Product;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> products = new ArrayList<>();
    private ProductDBC productDBC = new ProductDBC();

    public void addProduct(Product product) {
        productDBC.save(product);
    }

    public List<Product> getAllProducts() {
        return productDBC.getAll();
    }

    public Product getById(int id) {
        return productDBC.getProductById(id);
    }

    public List<Product> getAllByContent(String text) {
        return productDBC.getProductsByContent(text);
    }

    public List<Product> getByShelving(Product.Shelving shelving) {
        return productDBC.getProductsByShelving(shelving);
    }

    // 'Old product' is a 10 years old from launch year
    public List<Product> getOldProducts() {
        return productDBC.getOldProducts();
    }
}
