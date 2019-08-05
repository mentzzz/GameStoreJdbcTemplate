package com.company.JordanMentzU1Capstone.model;

import com.company.JordanMentzU1Capstone.viewmodel.ConsoleViewModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class Console {
    private Integer id;
    @NotEmpty
    private String model;
    @NotEmpty
    private String manufacturer;
    @NotEmpty
    private String memoryAmount;
    @NotEmpty
    private String processor;
    @Positive
    private double price;
    @Positive
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        Console console = (Console) o;
        return Double.compare(console.price, price) == 0 &&
                Objects.equals(id, console.id) &&
                model.equals(console.model) &&
                manufacturer.equals(console.manufacturer) &&
                memoryAmount.equals(console.memoryAmount) &&
                processor.equals(console.processor) &&
                quantity.equals(console.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, manufacturer, memoryAmount, processor, price, quantity);
    }
}
