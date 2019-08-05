package com.company.JordanMentzU1Capstone.controller;

import com.company.JordanMentzU1Capstone.exception.NotFoundException;
import com.company.JordanMentzU1Capstone.service.InventoryService;
import com.company.JordanMentzU1Capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleInventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping//HTTP method to create a console
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console) {
        return inventoryService.saveConsole(console);
    }

    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int consoleId) {
        ConsoleViewModel consoleViewModel = inventoryService.findConsole(consoleId);
        if (consoleViewModel == null)
            throw new NotFoundException("Console could not be retrieved for id " + consoleId);
        return consoleViewModel;
    }

    @GetMapping//HTTP method to get all consoles
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {
        List<ConsoleViewModel> consoleViewModels = inventoryService.findAllConsoles();
        if (consoleViewModels == null)
            throw new NotFoundException("Consoles could not be retrieved");
        return consoleViewModels;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        inventoryService.removeConsole(id);
    }

    @PutMapping("/{id}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int consoleId, @RequestBody @Valid ConsoleViewModel consoleViewModel) {
        if (consoleViewModel.getId() == 0)
            consoleViewModel.setId(consoleId);
        if (consoleId != consoleViewModel.getId()) {
            throw new IllegalArgumentException("Console ID on path must match the ID in the Console object");
        }
        inventoryService.updateConsole(consoleViewModel);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        List<ConsoleViewModel> consoles = inventoryService.findConsoleByManufacturer(manufacturer);
        if (consoles != null && consoles.size() == 0)
            throw new NotFoundException("Console could not be retrieved for manufacturer " + manufacturer);
        return consoles;
    }

}
