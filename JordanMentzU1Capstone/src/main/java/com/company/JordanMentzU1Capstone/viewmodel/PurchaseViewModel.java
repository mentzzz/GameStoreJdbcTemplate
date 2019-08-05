package com.company.JordanMentzU1Capstone.viewmodel;

import com.company.JordanMentzU1Capstone.model.Console;
import com.company.JordanMentzU1Capstone.model.Game;
import com.company.JordanMentzU1Capstone.model.tShirt;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurchaseViewModel {
    private Integer id;

    @NotEmpty(message = "Please supply a purchase name")
    private String name;
    @NotEmpty(message = "Please supply a street")
    private String street;
    @NotEmpty(message = "Please supply a city")
    private String city;
    @NotEmpty(message = "Please supply a state")
    private String state;
    @NotEmpty(message = "Please supply a zip code")
    private String zipCode;
    @NotEmpty(message = "Please supply an item type")
    private String itemType;
    @Positive(message = "Please supply an Item ID")
    private Integer itemId;
    @Min(1)
    private Integer quantity;

//    private List<Console> consoles = new ArrayList<>();
//    private List<Game> games = new ArrayList<>();
//    private List<tShirt> tShirts = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseViewModel that = (PurchaseViewModel) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                street.equals(that.street) &&
                city.equals(that.city) &&
                state.equals(that.state) &&
                zipCode.equals(that.zipCode) &&
                itemType.equals(that.itemType) &&
                itemId.equals(that.itemId) &&
                quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipCode, itemType, itemId, quantity);
    }
}


