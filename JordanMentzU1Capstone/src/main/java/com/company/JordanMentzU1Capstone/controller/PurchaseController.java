package com.company.JordanMentzU1Capstone.controller;

import com.company.JordanMentzU1Capstone.model.Invoice;
import com.company.JordanMentzU1Capstone.service.InventoryService;
import com.company.JordanMentzU1Capstone.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    //injecting inventory service layer
    @Autowired
    InventoryService inventoryService;

    //HTTP request to create a new purchase order and return a full invoice
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createPurchase(@RequestBody @Valid PurchaseViewModel purchaseViewModel) {

        //if itemtype equals console than create a console purchase order with the purchase view model
        if (purchaseViewModel.getItemType().equals("consoles")) {
//            ConsoleViewModel console = inventoryService.findConsole(purchaseViewModel.getItemId());
            //make service call to create invoice with console
//            inventoryService.saveConsole(console);
            return inventoryService.createConsolePurchase(purchaseViewModel);
        }
        if (purchaseViewModel.getItemType().equals("games")) {
//            GameViewModel game = inventoryService.findGame(purchaseViewModel.getItemId());
//            inventoryService.saveGame(game);
            return inventoryService.createGamePurchase(purchaseViewModel);
        }
        if (purchaseViewModel.getItemType().equals("t-shirts")) {
//            tShirtViewModel tShirt = inventoryService.findTshirt(purchaseViewModel.getItemId());
//            inventoryService.saveTshirt(tShirt);
            return inventoryService.createTshirtPurchase(purchaseViewModel);

        }
        return null;


    }
//
//    @PostMapping("/console")
//    @ResponseStatus(HttpStatus.CREATED)
//    public InvoiceViewModel createConsolePurchase(@RequestBody @Valid PurchaseViewModel purchaseViewModel) {
//        ConsoleViewModel console = inventoryService.findConsole(purchaseViewModel.getItemId());
//        inventoryService.saveConsole(console);
//        return inventoryService.saveInvoice(inventoryService.createConsolePurchase(purchaseViewModel));
//    }

}
