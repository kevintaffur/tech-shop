package org.example.dbc;

import org.example.models.Product;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ProductDBC {
    private Connection conn;

    public ProductDBC() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techshop", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Product product) {
        String query = "insert into products(name, type, shelving, launch_year) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setString(3, product.getShelving().toString());
            preparedStatement.setInt(4, product.getLaunchYear());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "select name,type,shelving,launch_year from products";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            // executeQuery() returns a ResultSet, execute() don't
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                Product.Shelving shelving = Product.Shelving.valueOf(rs.getString("shelving").toUpperCase());
                int launchYear = rs.getInt("launch_year");
                Product product = new Product(name, type, shelving, launchYear);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductById(int id) {
        String query = "select name,type,shelving,launch_year from products where product_id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String type = rs.getString("type");
            Product.Shelving shelving = Product.Shelving.valueOf(rs.getString("shelving").toUpperCase());
            int launchYear = rs.getInt("launch_year");
            return new Product(name, type, shelving, launchYear);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getOldProducts() {
        List<Product> products = new ArrayList<>();
        String query = "select * from products where launch_year<?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, Year.now().getValue() - 10);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                Product.Shelving shelving = Product.Shelving.valueOf(rs.getString("shelving").toUpperCase());
                int launchYear = rs.getInt("launch_year");
                products.add(new Product(id, name, type, shelving, launchYear));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByShelving(Product.Shelving shelving) {
        List<Product> products = new ArrayList<>();
        String query = "select * from products where shelving=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, shelving.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                Product.Shelving sh = Product.Shelving.valueOf(rs.getString("shelving").toUpperCase());
                int launchYear = rs.getInt("launch_year");
                products.add(new Product(id, name, type, sh, launchYear));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByContent(String txt) {
        List<Product> products = new ArrayList<>();
        String query = "select * from products where\n" +
                "name like concat('%',?,'%')or name like concat('%',?) or name like concat(?,'%') or\n" +
                "type like concat('%',?,'%')or type like concat('%',?) or type like concat(?,'%') or\n" +
                "shelving like concat('%',?,'%') or shelving like concat('%',?) or shelving like concat(?,'%')";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            for (int i = 1; i <= 9; i++) {
                preparedStatement.setString(i, txt);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                Product.Shelving sh = Product.Shelving.valueOf(rs.getString("shelving").toUpperCase());
                int launchYear = rs.getInt("launch_year");
                products.add(new Product(id, name, type, sh, launchYear));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
