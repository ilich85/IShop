package com.ilich.model;

public class DeliveryAddress {

    private int idAddress;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private int house;
    private int flat;
    private int userId;

    public DeliveryAddress() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        DeliveryAddress that = (DeliveryAddress) object;

        if (idAddress != that.idAddress) return false;
        if (house != that.house) return false;
        if (flat != that.flat) return false;
        if (userId != that.userId) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!country.equals(that.country)) return false;
        if (!city.equals(that.city)) return false;
        return street.equals(that.street);
    }

    @Override
    public int hashCode() {
        int result = idAddress;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + house;
        result = 31 * result + flat;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "idAddress=" + idAddress +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                ", userId=" + userId +
                '}';
    }
}