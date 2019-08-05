package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.tShirt;

import java.util.List;

public interface tShirtDao {
    tShirt addTshirt(tShirt tShirt);

    tShirt getTshirt(int id);

    List<tShirt> getAllTshirts();

    void updateTshirt(tShirt tShirt);

    void deleteTshirt(int id);

    List<tShirt> getTshirtByColor(String color);

    List<tShirt> getTshirtBySize(String size);
}
