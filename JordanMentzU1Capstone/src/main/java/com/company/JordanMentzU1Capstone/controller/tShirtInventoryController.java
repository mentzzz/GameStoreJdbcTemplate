package com.company.JordanMentzU1Capstone.controller;

import com.company.JordanMentzU1Capstone.exception.NotFoundException;
import com.company.JordanMentzU1Capstone.service.InventoryService;
import com.company.JordanMentzU1Capstone.viewmodel.tShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tshirt")//uri to access the tshirt crud methods
public class tShirtInventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping//HTTP method to create a new t-shirt
    @ResponseStatus(HttpStatus.CREATED)
    public tShirtViewModel createTshirt(@RequestBody @Valid tShirtViewModel tShirt) {
        return inventoryService.saveTshirt(tShirt);
    }

    @GetMapping("/{id}")//HTTOP method to get a tshirt by id
    @ResponseStatus(HttpStatus.OK)
    public tShirtViewModel getTshirt(@PathVariable("id") int tshirtId) {
        tShirtViewModel tShirtViewModel = inventoryService.findTshirt(tshirtId);
        if (tShirtViewModel == null)
            throw new NotFoundException("Tshirt could not be retrieved for id " + tshirtId);
        return tShirtViewModel;
    }

    @GetMapping//HTTOP method to get all tshirts
    @ResponseStatus(HttpStatus.OK)
    public List<tShirtViewModel> getAllTshirts() {
        List<tShirtViewModel> tShirtViewModel = inventoryService.findAllTshirt();
        if (tShirtViewModel == null)
            throw new NotFoundException("Tshirts could not be retrieved");
        return tShirtViewModel;
    }

    @DeleteMapping("/{id}")//http method to delete a tshirt by id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id) {
        inventoryService.removeTshirt(id);
    }

    @PutMapping("/{id}")//http method to update a tshirt with id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@PathVariable("id") int tshirtId, @RequestBody @Valid tShirtViewModel tShirtViewModel) {
        if (tShirtViewModel.getId() == 0)
            tShirtViewModel.setId(tshirtId);
        if (tshirtId != tShirtViewModel.getId()) {
            throw new IllegalArgumentException("Tshirt ID on path must match the ID in the Tshirt object");
        }
        inventoryService.updateTshirt(tShirtViewModel);
    }

    @GetMapping("/color/{color}")//http method to get tshirt by color
    @ResponseStatus(HttpStatus.OK)
    public List<tShirtViewModel> getTshirtsByColor(@PathVariable("color") String color) {
        List<tShirtViewModel> tShirts = inventoryService.findTshirtByColor(color);
        if (tShirts != null && tShirts.size() == 0)
            throw new NotFoundException("Tshirts could not be retrieved for color " + color);
        return tShirts;
    }

    @GetMapping("/size/{size}")//http method to get tshirt by size
    @ResponseStatus(HttpStatus.OK)
    public List<tShirtViewModel> getTshirtsBySize(@PathVariable("size") String size) {
        List<tShirtViewModel> tShirts = inventoryService.findTshirtBySize(size);
        if (tShirts != null && tShirts.size() == 0)
            throw new NotFoundException("Tshirts could not be retrieved for size " + size);
        return tShirts;
    }


}
