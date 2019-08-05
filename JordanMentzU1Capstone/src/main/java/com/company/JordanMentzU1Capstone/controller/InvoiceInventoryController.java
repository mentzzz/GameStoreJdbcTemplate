package com.company.JordanMentzU1Capstone.controller;

import com.company.JordanMentzU1Capstone.exception.NotFoundException;
import com.company.JordanMentzU1Capstone.service.InventoryService;
import com.company.JordanMentzU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
//URI in case the client wants to retrieve a full invoice by ID
public class InvoiceInventoryController {
    @Autowired
    InventoryService inventoryService;

//    @PostMapping//Another way to set the Rest API Post mapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PurchaseViewModel createInvoice(@RequestBody @Valid PurchaseViewModel invoice) {
//        return inventoryService.saveInvoicePurchase(invoice);
//    }

    //URI to get the full invoice if the client wanted to see everything that is listed in the full invoice
    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable("id") int invoiceId) {
        InvoiceViewModel invoiceViewModel = inventoryService.findInvoiceById(invoiceId);
        if (invoiceViewModel == null)
            throw new NotFoundException("Invoice could not be retrieved for id " + invoiceId);
        return invoiceViewModel;
    }

    //no delete or update because you don't want the client being able to delete or update an invoice


}
