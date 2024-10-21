package org.example.model;

public class Papel {
    private int id;       // ID of the Papel
    private String name;  // Name of the Papel (e.g., Admin, Desenvolvedor, etc.)

    public Papel() {}

    public Papel(int id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public String toString() {
        return "Papel{id=" + id + ", name='" + name + '\'' + '}';
    }
}
