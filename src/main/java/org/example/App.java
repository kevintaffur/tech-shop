package org.example;

import org.example.models.Product;
import org.example.services.ProductService;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        ProductService productService = new ProductService();

//        productService.addProduct(new Product(1, "iPhone 15", "Cellphone", Product.Shelving.YELLOW, 2023));
//        productService.addProduct(new Product(2, "iPhone 14", "Cellphone", Product.Shelving.BLUE, 2023));
//        productService.addProduct(new Product(3, "iPhone 13", "Cellphone", Product.Shelving.BLACK, 2022));
//        productService.addProduct(new Product(4, "iPhone 12", "Cellphone", Product.Shelving.BLACK, 2020));
//        productService.addProduct(new Product(5, "iPhone 11", "Cellphone", Product.Shelving.RED, 2019));
//        productService.addProduct(new Product(6, "iPhone X", "Cellphone", Product.Shelving.YELLOW, 2017));
//        productService.addProduct(new Product(7, "iPhone XR", "Cellphone", Product.Shelving.GREEN, 2015));
//        productService.addProduct(new Product(8, "iPhone 8", "Cellphone", Product.Shelving.WHITE, 2013));
//        productService.addProduct(new Product(9, "iPhone 7", "Cellphone", Product.Shelving.YELLOW, 2010));
//        productService.addProduct(new Product(10, "NVIDIA GTX 3060", "Graphics card", Product.Shelving.BLUE, 2023));
//        productService.addProduct(new Product(11, "MacBook Air", "Laptop", Product.Shelving.BLACK, 2005));
//        productService.addProduct(new Product(12, "MacBook Pro", "Laptop", Product.Shelving.BLUE, 2009));
//        productService.addProduct(new Product(13, "HP Desktop PC", "PC", Product.Shelving.GREEN, 2023));
//        productService.addProduct(new Product(14, "Samsung Galaxy S24", "Cellphone", Product.Shelving.BLUE, 2023));
//        productService.addProduct(new Product(15, "Pixel 8", "Cellphone", Product.Shelving.RED, 2024));

        List<Product> products = productService.getAllProducts();

        System.out.println("ALL PRODUCTS");
        for (Product p : products) {
            System.out.println(p);
        }

        System.out.println("OLD PRODUCTS");
        List<Product> oldProducts = productService.getOldProducts();
        for (Product p : oldProducts) {
            System.out.println(p);
        }

        System.out.println("PRODUCT BY ID");
        Product byId = productService.getById(4);
        System.out.println(byId);

        System.out.println("PRODUCTS BY SHELVING");
        List<Product> byShelving = productService.getByShelving(Product.Shelving.BLUE);
        for (Product p : byShelving) {
            System.out.println(p);
        }

        System.out.println("PRODUCTS BY TEXT CONTENT");
        List<Product> byContent = productService.getAllByContent("e");
        for (Product p : byContent) {
            System.out.println(p);
        }
    }
}
