package org.example.model;

public class Developer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int squadId;

    public Developer() {}

    public Developer(int id, String name, String email, String phone, int squadId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.squadId = squadId;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getSquadId() {
        return squadId;
    }
    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}
