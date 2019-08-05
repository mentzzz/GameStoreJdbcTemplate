package com.company.JordanMentzU1Capstone.service;

import com.company.JordanMentzU1Capstone.dao.*;
import com.company.JordanMentzU1Capstone.model.*;
import com.company.JordanMentzU1Capstone.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryService {
    ConsoleDao consoleDao;
    InvoiceDao invoiceDao;
    GameDao gameDao;
    tShirtDao tShirtDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;

    @Autowired
    public InventoryService(ConsoleDao consoleDao, InvoiceDao invoiceDao, GameDao gameDao, tShirtDao tShirtDao, SalesTaxRateDao salesTaxRateDao, ProcessingFeeDao processingFeeDao) {
        this.consoleDao = consoleDao;
        this.invoiceDao = invoiceDao;
        this.gameDao = gameDao;
        this.tShirtDao = tShirtDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
    }

    @Transactional
    public InvoiceViewModel createConsolePurchase(PurchaseViewModel purchaseViewModel) {
        Console console = consoleDao.getConsole(purchaseViewModel.getItemId());
        //if console quantity is greater than purchase quantity, go ahead with the order
        if (console.getQuantity() >= purchaseViewModel.getQuantity()) {
            Invoice invoice = new Invoice();
            invoice.setName(purchaseViewModel.getName());
            invoice.setStreet(purchaseViewModel.getStreet());
            invoice.setCity(purchaseViewModel.getCity());
            invoice.setState(purchaseViewModel.getState());
            invoice.setZipCode(purchaseViewModel.getZipCode());
            invoice.setItemType(purchaseViewModel.getItemType());
            invoice.setItemId(purchaseViewModel.getItemId());
            invoice.setQuantity(purchaseViewModel.getQuantity());
            invoice.setUnitPrice(console.getPrice());

            //setting subtotal, price * quantity
            invoice.setSubtotal(console.getPrice() * purchaseViewModel.getQuantity());

            //setting new sales tax and getting sales tax from invoice
            SalesTaxRate salesTaxRate = new SalesTaxRate();
            salesTaxRate = salesTaxRateDao.getTaxByState(invoice.getState());

            //getting sales tax claculations for items
            invoice.setTax(salesTaxRate.getRate() * invoice.getSubtotal());

            //new processing fee
            ProcessingFee processingFee = new ProcessingFee();
            processingFee = processingFeeDao.getFeeByItemType(invoice.getItemType());
            //if quantity of invoice order is less than 10, get the processing fee by item type
            if (invoice.getQuantity() < 10) {
                invoice.setProcessingFee(processingFee.getFee());
            } else {
                //if quantity of invoice order is more than 10 add additional processing fee
                invoice.setProcessingFee(processingFee.getFee() + 15.49);
            }
            invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());

            invoice = invoiceDao.saveInvoice(invoice);

            //create invoice view from invoice
            InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
            invoiceViewModel1.setId(invoice.getId());
            invoiceViewModel1.setName(invoice.getName());
            invoiceViewModel1.setStreet(invoice.getStreet());
            invoiceViewModel1.setCity(invoice.getCity());
            invoiceViewModel1.setState(invoice.getState());
            invoiceViewModel1.setZipCode(invoice.getZipCode());
            invoiceViewModel1.setItemType(invoice.getItemType());
            invoiceViewModel1.setItemId(invoice.getItemId());
            invoiceViewModel1.setUnitPrice(invoice.getUnitPrice());
            invoiceViewModel1.setQuantity(invoice.getQuantity());
            invoiceViewModel1.setSubtotal(invoice.getSubtotal());
            invoiceViewModel1.setTax(invoice.getTax());
            invoiceViewModel1.setProcessingFee(invoice.getProcessingFee());
            invoiceViewModel1.setTotal(invoice.getTotal());


            //updating original quantity to new quanitty
            console.setQuantity(console.getQuantity() - purchaseViewModel.getQuantity());

            consoleDao.updateConsole(console);

            return invoiceViewModel1;
        } if (console.getQuantity() <= purchaseViewModel.getQuantity()) {
            throw new IllegalArgumentException("There is not enough in stock. Please try again later.");
        } else {
            return null;
        }

    }

    //method that creates a purchase order of a game(with taxes and fees) and returns the full invoice
    @Transactional
    public InvoiceViewModel createGamePurchase(PurchaseViewModel purchaseViewModel) {
        Game game = gameDao.getGame(purchaseViewModel.getItemId());
        //if game quantity is greater than purchase quantity, go ahead with the order
        if (game.getQuantity() >= purchaseViewModel.getQuantity()) {
            Invoice invoice = new Invoice();
            invoice.setName(purchaseViewModel.getName());
            invoice.setStreet(purchaseViewModel.getStreet());
            invoice.setCity(purchaseViewModel.getCity());
            invoice.setState(purchaseViewModel.getState());
            invoice.setZipCode(purchaseViewModel.getZipCode());
            invoice.setItemType(purchaseViewModel.getItemType());
            invoice.setItemId(purchaseViewModel.getItemId());
            invoice.setQuantity(purchaseViewModel.getQuantity());
            invoice.setUnitPrice(game.getPrice());

            //setting subtotal, price * quantity
            invoice.setSubtotal(game.getPrice() * purchaseViewModel.getQuantity());

            SalesTaxRate salesTaxRate = new SalesTaxRate();
            salesTaxRate = salesTaxRateDao.getTaxByState(invoice.getState());

            invoice.setTax(salesTaxRate.getRate() * invoice.getSubtotal());

            //new processing fee
            ProcessingFee processingFee = new ProcessingFee();
            processingFee = processingFeeDao.getFeeByItemType(invoice.getItemType());
            if (invoice.getQuantity() < 10) {
                invoice.setProcessingFee(processingFee.getFee());
                //if quantity of invoice order is more than 10 add additional processing fee
            } else {
                invoice.setProcessingFee(processingFee.getFee() + 15.49);
            }
            invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());

            invoice = invoiceDao.saveInvoice(invoice);

            //create invoice view from invoice
            InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
            invoiceViewModel1.setId(invoice.getId());
            invoiceViewModel1.setName(invoice.getName());
            invoiceViewModel1.setStreet(invoice.getStreet());
            invoiceViewModel1.setCity(invoice.getCity());
            invoiceViewModel1.setState(invoice.getState());
            invoiceViewModel1.setZipCode(invoice.getZipCode());
            invoiceViewModel1.setItemType(invoice.getItemType());
            invoiceViewModel1.setItemId(invoice.getItemId());
            invoiceViewModel1.setUnitPrice(invoice.getUnitPrice());
            invoiceViewModel1.setQuantity(invoice.getQuantity());
            invoiceViewModel1.setSubtotal(invoice.getSubtotal());
            invoiceViewModel1.setTax(invoice.getTax());
            invoiceViewModel1.setProcessingFee(invoice.getProcessingFee());
            invoiceViewModel1.setTotal(invoice.getTotal());
            //updating original quantity to new quanitty
            game.setQuantity(game.getQuantity() - purchaseViewModel.getQuantity());

            gameDao.updateGame(game);

            return invoiceViewModel1;
        } if (game.getQuantity() <= purchaseViewModel.getQuantity()) {
            throw new IllegalArgumentException("There is not enough in stock. Please try again later.");
        }
        else {
            return null;
        }

    }

    //creating a new tshirt purchase order
    @Transactional
    public InvoiceViewModel createTshirtPurchase(PurchaseViewModel purchaseViewModel) {
        tShirt tShirt = tShirtDao.getTshirt(purchaseViewModel.getItemId());
        //if tshirt quantity is greater than purchase quantity, go ahead with the order
        if (tShirt.getQuantity() >= purchaseViewModel.getQuantity()) {
            Invoice invoice = new Invoice();
            invoice.setName(purchaseViewModel.getName());
            invoice.setStreet(purchaseViewModel.getStreet());
            invoice.setCity(purchaseViewModel.getCity());
            invoice.setState(purchaseViewModel.getState());
            invoice.setZipCode(purchaseViewModel.getZipCode());
            invoice.setItemType(purchaseViewModel.getItemType());
            invoice.setItemId(purchaseViewModel.getItemId());
            invoice.setQuantity(purchaseViewModel.getQuantity());
            invoice.setUnitPrice(tShirt.getPrice());

            //setting subtotal, price * quantity
            invoice.setSubtotal(tShirt.getPrice() * purchaseViewModel.getQuantity());

//            System.out.println("This is jordan" + invoiceViewModel.getState());
            //getting sales tax rate by state;
            SalesTaxRate salesTaxRate = new SalesTaxRate();
            salesTaxRate = salesTaxRateDao.getTaxByState(invoice.getState());

            invoice.setTax(salesTaxRate.getRate() * invoice.getSubtotal());

            //new processing fee
            ProcessingFee processingFee = new ProcessingFee();
            //getting fee by item type
            processingFee = processingFeeDao.getFeeByItemType(invoice.getItemType());
            if (invoice.getQuantity() < 10) {
                invoice.setProcessingFee(processingFee.getFee());
                //if quantity of invoice order is more than 10 add additional processing fee
            } else {
                invoice.setProcessingFee(processingFee.getFee() + 15.49);
            }
            invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());

            invoice = invoiceDao.saveInvoice(invoice);


            //create invoice view from invoice
            InvoiceViewModel invoiceViewModel1 = new InvoiceViewModel();
            invoiceViewModel1.setId(invoice.getId());
            invoiceViewModel1.setName(invoice.getName());
            invoiceViewModel1.setStreet(invoice.getStreet());
            invoiceViewModel1.setCity(invoice.getCity());
            invoiceViewModel1.setState(invoice.getState());
            invoiceViewModel1.setZipCode(invoice.getZipCode());
            invoiceViewModel1.setItemType(invoice.getItemType());
            invoiceViewModel1.setItemId(invoice.getItemId());
            invoiceViewModel1.setUnitPrice(invoice.getUnitPrice());
            invoiceViewModel1.setQuantity(invoice.getQuantity());
            invoiceViewModel1.setSubtotal(invoice.getSubtotal());
            invoiceViewModel1.setTax(invoice.getTax());
            invoiceViewModel1.setProcessingFee(invoice.getProcessingFee());
            invoiceViewModel1.setTotal(invoice.getTotal());
            //updating original quantity to new quanitty
            tShirt.setQuantity(tShirt.getQuantity() - purchaseViewModel.getQuantity());

            tShirtDao.updateTshirt(tShirt);

            return invoiceViewModel1;

        } if (tShirt.getQuantity() <= purchaseViewModel.getQuantity()) {
            throw new IllegalArgumentException("There is not enough in stock. Please try again later.");
        }
        else {
            return null;
        }

    }

    //method to save a game
    public GameViewModel saveGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());
        game = gameDao.addGame(game);
        gameViewModel.setId(game.getId());

        return gameViewModel;
    }

    //method to updiate a game
    public void updateGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setId(gameViewModel.getId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        gameDao.updateGame(game);
    }

    //method to find a game
    public GameViewModel findGame(int id) {
        Game game = gameDao.getGame(id);

        if (game == null)
            return null;
        else
            return buildGameViewModel(game);
    }

    //method to remove a game
    public void removeGame(int id) {
        gameDao.deleteGame(id);
    }

    public List<GameViewModel> findGameByStudio(String studio) {
        List<Game> games = gameDao.getGamesByStudio(studio);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game : games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    //method to find a game by esrb rating
    public List<GameViewModel> findGamesByEsrbRating(String esrbRating) {
        List<Game> games = gameDao.getGamesByEsrbRating(esrbRating);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game : games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    //method to find a game by title
    public List<GameViewModel> findGamesByTitle(String title) {
        List<Game> games = gameDao.getGamesByTitle(title);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game : games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    //method to find all games
    public List<GameViewModel> findAllGames() {
        List<Game> games = gameDao.getAllGames();
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game game : games) {
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    //ethod to find all consoles
    public List<ConsoleViewModel> findAllConsoles() {
        List<Console> consoles = consoleDao.getAllConsoles();
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for (Console console : consoles) {
            consoleViewModels.add(buildConsoleViewModel(console));
        }
        return consoleViewModels;
    }

    //method to save a console
    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        console = consoleDao.addConsole(console);

        consoleViewModel.setId(console.getId());
        return consoleViewModel;
    }

    //method to update a console
    public void updateConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setId(consoleViewModel.getId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleDao.updateConsole(console);
    }

    //method to find a console
    public ConsoleViewModel findConsole(int id) {
        Console console = consoleDao.getConsole(id);

        if (console == null)
            return null;
        else
            return buildConsoleViewModel(console);

    }

    //method to remove a console
    public void removeConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    //method to find console by manufacturer
    public List<ConsoleViewModel> findConsoleByManufacturer(String manufacturer) {
        List<Console> consoles = consoleDao.getConsolesByManufacturer(manufacturer);
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for (Console console : consoles) {
            consoleViewModels.add(buildConsoleViewModel(console));
        }
        return consoleViewModels;
    }

    //method to save a tshirt
    public tShirtViewModel saveTshirt(tShirtViewModel tShirtViewModel) {
        tShirt tShirt = new tShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt.setPrice(tShirtViewModel.getPrice());

        tShirt = tShirtDao.addTshirt(tShirt);
        tShirtViewModel.setId(tShirt.getId());
        return tShirtViewModel;
    }

    //method to find a t shirt
    public tShirtViewModel findTshirt(int id) {
        tShirt tShirt = tShirtDao.getTshirt(id);
        if (tShirt != null) {
            tShirtViewModel tShirtViewModel = new tShirtViewModel();
            tShirtViewModel.setId(tShirt.getId());
            tShirtViewModel.setSize(tShirt.getSize());
            tShirtViewModel.setColor(tShirt.getColor());
            tShirtViewModel.setDescription(tShirt.getDescription());
            tShirtViewModel.setQuantity(tShirt.getQuantity());
            tShirtViewModel.setPrice(tShirt.getPrice());

            return tShirtViewModel;
        } else return null;
    }

    //method to delete a tshirt
    public void removeTshirt(int id) {
        tShirtDao.deleteTshirt(id);
    }

    //method to update a tshirt
    public void updateTshirt(tShirtViewModel tShirtViewModel) {
        tShirt tShirt = new tShirt();
        tShirt.setId(tShirtViewModel.getId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirtDao.updateTshirt(tShirt);
    }

    //method to find all tshirts
    public List<tShirtViewModel> findAllTshirt() {
        List<tShirt> tShirts = tShirtDao.getAllTshirts();
        List<tShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (tShirt tShirt : tShirts) {
            tShirtViewModels.add(buildTshirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    //method to find t shirt  by color
    public List<tShirtViewModel> findTshirtByColor(String color) {
        List<tShirt> tShirts = tShirtDao.getTshirtByColor(color);
        List<tShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (tShirt tShirt : tShirts) {
            tShirtViewModels.add(buildTshirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    //method to find t shirt by size
    public List<tShirtViewModel> findTshirtBySize(String size) {
        List<tShirt> tShirts = tShirtDao.getTshirtBySize(size);
        List<tShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (tShirt tShirt : tShirts) {
            tShirtViewModels.add(buildTshirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    //method to save an invoice
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceViewModel.getId());
        invoice.setName(invoice.getName());
        invoice.setStreet(invoice.getStreet());
        invoice.setCity(invoice.getCity());
        invoice.setState(invoice.getState());
        invoice.setZipCode(invoice.getZipCode());
        invoice.setItemType(invoice.getItemType());
        invoice.setItemId(invoice.getItemId());
        invoice.setUnitPrice(invoice.getUnitPrice());
        invoice.setQuantity(invoice.getQuantity());
        invoice.setSubtotal(invoice.getSubtotal());
        invoice.setTax(invoice.getTax());
        invoice.setProcessingFee(invoice.getProcessingFee());
        invoice.setTotal(invoice.getTotal());

        invoiceViewModel.setId(invoice.getId());

//        List<Console> consoles = invoiceViewModel.getConsoles();
//        consoles.stream().forEach(console -> {
//            console.setId(invoiceViewModel.getId());
//            consoleDao.addConsole(console);
//        });
//        List<Game> games = invoiceViewModel.getGames();
//        games.stream().forEach(game -> {
//            game.setId(invoiceViewModel.getId());
//            gameDao.addGame(game);
//        });
//        List<tShirt> tShirts = invoiceViewModel.gettShirts();
//        tShirts.stream().forEach(tShirt -> {
//            tShirt.setId(invoiceViewModel.getId());
//            tShirtDao.addTshirt(tShirt);
//        });

        return invoiceViewModel;
    }

    //method to save a purchase
    @Transactional
    public PurchaseViewModel saveInvoicePurchase(PurchaseViewModel purchaseViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(purchaseViewModel.getName());
        invoice.setStreet(purchaseViewModel.getStreet());
        invoice.setCity(purchaseViewModel.getCity());
        invoice.setState(purchaseViewModel.getState());
        invoice.setZipCode(purchaseViewModel.getZipCode());
        invoice.setItemType(purchaseViewModel.getItemType());
        invoice.setItemId(purchaseViewModel.getItemId());
        invoice.setQuantity(purchaseViewModel.getQuantity());
        invoice = invoiceDao.saveInvoice(invoice);

        purchaseViewModel.setId(invoice.getId());

//        List<Console> consoles = purchaseViewModel.getConsoles();
//        consoles.stream().forEach(console -> {
//            console.setId(purchaseViewModel.getId());
//            consoleDao.addConsole(console);
//        });
//        List<Game> games = purchaseViewModel.getGames();
//        games.stream().forEach(game -> {
//            game.setId(purchaseViewModel.getId());
//            gameDao.addGame(game);
//        });
//        List<tShirt> tShirts = purchaseViewModel.gettShirts();
//        tShirts.stream().forEach(tShirt -> {
//            tShirt.setId(purchaseViewModel.getId());
//            tShirtDao.addTshirt(tShirt);
//        });

        return purchaseViewModel;
    }

    //method to update an invoice
    public void updateInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceViewModel.getId());
        invoice.setName(invoice.getName());
        invoice.setStreet(invoice.getStreet());
        invoice.setCity(invoice.getCity());
        invoice.setState(invoice.getState());
        invoice.setZipCode(invoice.getZipCode());
        invoice.setItemType(invoice.getItemType());
        invoice.setItemId(invoice.getItemId());
        invoice.setUnitPrice(invoice.getUnitPrice());
        invoice.setQuantity(invoice.getQuantity());
        invoice.setSubtotal(invoice.getSubtotal());
        invoice.setTax(invoice.getTax());
        invoice.setProcessingFee(invoice.getProcessingFee());
        invoice.setTotal(invoice.getTotal());

        invoiceDao.updateInvoice(invoice);
    }

    //method to delete an invoice
    public void removeInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }


    public InvoiceViewModel findInvoiceById(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if (invoice == null)
            return null;
        else
            return buildInvoiceViewModel(invoice);
    }


    //build view model method
    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setId(console.getId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }

    //build view model method
    private GameViewModel buildGameViewModel(Game game) {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setId(game.getId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    //build view model method
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipCode(invoice.getZipCode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }

    //build view model method
    private tShirtViewModel buildTshirtViewModel(tShirt tShirt) {
        tShirtViewModel tShirtViewModel = new tShirtViewModel();
        tShirtViewModel.setId(tShirt.getId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }
}


