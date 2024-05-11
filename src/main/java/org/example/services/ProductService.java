package org.example.services;

import org.example.models.Product;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Product> getAllByContent(String text) {
        String txt = text.toLowerCase();

        return products.stream().filter(product -> {
            String name = product.getName().toLowerCase();
            String type = product.getName().toLowerCase();
            String shelving = product.getShelving().toString();
            return name.contains(txt) || type.contains(txt) || shelving.contains(txt);
        }).collect(Collectors.toList());
    }

    public List<Product> getByShelving(Product.Shelving shelving) {
        return products.stream().filter(product -> {
            return product.getShelving().equals(shelving);
        }).collect(Collectors.toList());
    }

    // 'Old product' is a 10 years old from launch year
    public List<Product> getOldProducts() {
        int currentYear = Year.now().getValue();
        int past = currentYear - 10;

        // Using stream api
        return products.stream().filter(product -> {
            return product.getLaunchYear() < past;
        }).collect(Collectors.toList());
    }
}
