package com.webservice.restapiwebservice.version;

public class Name {
    private String firtsName;
    private String lastName;

    public Name(String firtsName, String lastName) {
        this.firtsName = firtsName;
        this.lastName = lastName;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firtsName='" + firtsName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
