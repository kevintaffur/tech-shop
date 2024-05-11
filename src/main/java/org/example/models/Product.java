package org.example.models;

public class Product {
    private int id;
    private String name;
    private String type;
    private Shelving shelving;
    private int launchYear;

    public enum Shelving {
        RED("red"),
        GREEN("green"),
        BLUE("blue"),
        YELLOW("yellow"),
        WHITE("white"),
        BLACK("black");

        private String value;

        Shelving(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public Product(int id, String name, String type, Shelving shelving, int launchYear) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.shelving = shelving;
        this.launchYear = launchYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shelving getShelving() {
        return shelving;
    }

    public void setShelving(Shelving shelving) {
        this.shelving = shelving;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(int launchYear) {
        this.launchYear = launchYear;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", shelving='" + shelving + '\'' +
                ", launchYear=" + launchYear +
                '}';
    }
}
