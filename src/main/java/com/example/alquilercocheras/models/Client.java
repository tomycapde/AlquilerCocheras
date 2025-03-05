package com.example.alquilercocheras.models;

public class Client {

    private int idClient;
    private String name;
    private String lastName;
    private String dni;
    private String phone;

    public Client() {
    }

    public Client(String name, String lastName, String dni, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
