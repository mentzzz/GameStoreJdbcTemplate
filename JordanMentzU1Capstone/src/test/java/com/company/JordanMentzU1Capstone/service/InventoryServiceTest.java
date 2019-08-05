package com.company.JordanMentzU1Capstone.service;

import com.company.JordanMentzU1Capstone.dao.*;
import com.company.JordanMentzU1Capstone.model.*;
import com.company.JordanMentzU1Capstone.viewmodel.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InventoryServiceTest {

    ConsoleDao consoleDao;
    InvoiceDao invoiceDao;
    GameDao gameDao;
    tShirtDao tShirtDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;
    InventoryService inventoryService;


    @Before
    public void setUp() throws Exception {
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpInvoiceDaoMock();
        setUpTshirtDaoMock();
        setUpPurchaseDaoMock();

        inventoryService = new InventoryService(consoleDao, invoiceDao, gameDao, tShirtDao, salesTaxRateDao, processingFeeDao);
    }


    private void setUpPurchaseDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setId(2);
        invoice.setName("Jordan Mentz");
        invoice.setStreet("Shandon");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipCode("28262");
        invoice.setItemType("Consoles");
        invoice.setItemId(3);
        //invoice.setUnitPrice(200.00);
        invoice.setQuantity(2);
//        invoice.setSubtotal(400.00);
//        invoice.setTax(.05);
//        invoice.setProcessingFee(14.99);
//        invoice.setTotal(432.85);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Jordan Mentz");
        invoice1.setStreet("Shandon");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipCode("28262");
        invoice1.setItemType("Consoles");
        invoice1.setItemId(3);
        //invoice1.setUnitPrice(200.00);
        invoice1.setQuantity(2);
//        invoice1.setSubtotal(400.00);
//        invoice1.setTax(.05);
//        invoice1.setProcessingFee(14.99);
//        invoice1.setTotal(432.85);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).saveInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(2);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setId(2);
        invoice.setName("Jordan Mentz");
        invoice.setStreet("Shandon");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipCode("28262");
        invoice.setItemType("Consoles");
        invoice.setItemId(3);
        invoice.setUnitPrice(200.00);
        invoice.setQuantity(2);
        invoice.setSubtotal(400.00);
        invoice.setTax(.05);
        invoice.setProcessingFee(14.99);
        invoice.setTotal(432.85);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Jordan Mentz");
        invoice1.setStreet("Shandon");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipCode("28262");
        invoice1.setItemType("Consoles");
        invoice1.setItemId(3);
        invoice1.setUnitPrice(200.00);
        invoice1.setQuantity(2);
        invoice1.setSubtotal(400.00);
        invoice1.setTax(.05);
        invoice1.setProcessingFee(14.99);
        invoice1.setTotal(432.85);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).saveInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(2);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }


    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setId(42);
        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500gb");
        console.setProcessor("35ghz");
        console.setPrice(200.00);
        console.setQuantity(1);

        Console console1 = new Console();
        console1.setModel("PS4");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("500gb");
        console1.setProcessor("35ghz");
        console1.setPrice(200.00);
        console1.setQuantity(1);

        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsole(42);
        doReturn(consoles).when(consoleDao).getAllConsoles();
        doReturn(consoles).when(consoleDao).getConsolesByManufacturer("Sony");

    }

    private void setUpGameDaoMock() {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setId(42);
        game.setTitle("GTA5");
        game.setEsrbRating("M");
        game.setDescription("Shooting game");
        game.setPrice(25.00);
        game.setStudio("Rockstar");
        game.setQuantity(4);

        Game game1 = new Game();
        game1.setTitle("GTA5");
        game1.setEsrbRating("M");
        game1.setDescription("Shooting game");
        game1.setPrice(25.00);
        game1.setStudio("Rockstar");
        game1.setQuantity(4);

        List<Game> games = new ArrayList<>();
        games.add(game);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGame(42);
        doReturn(games).when(gameDao).getAllGames();
        doReturn(games).when(gameDao).getGamesByStudio("Rockstar");
        doReturn(games).when(gameDao).getGamesByEsrbRating("M");
        doReturn(games).when(gameDao).getGamesByTitle("GTA5");


    }

    private void setUpTshirtDaoMock() {
        tShirtDao = mock(tShirtDaoJdbcTemplateImpl.class);

        tShirt tShirt = new tShirt();
        tShirt.setId(42);
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fit");
        tShirt.setPrice(15.00);
        tShirt.setQuantity(5);


        tShirt tShirt1 = new tShirt();
        tShirt1.setSize("M");
        tShirt1.setColor("Blue");
        tShirt1.setDescription("Slim fit");
        tShirt1.setPrice(15.00);
        tShirt1.setQuantity(5);

        List<tShirt> tShirts = new ArrayList<>();
        tShirts.add(tShirt);

        doReturn(tShirt).when(tShirtDao).addTshirt(tShirt1);
        doReturn(tShirt).when(tShirtDao).getTshirt(42);
        doReturn(tShirts).when(tShirtDao).getAllTshirts();
        doReturn(tShirts).when(tShirtDao).getTshirtByColor("Blue");
        doReturn(tShirts).when(tShirtDao).getTshirtBySize("M");
    }


    private void setUpSalesTaxMock() {
        salesTaxRateDao = mock(SalexTaxDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setRate(.05);
        salesTaxRate.setState("NC");

        SalesTaxRate salesTaxRate1 = new SalesTaxRate();
        salesTaxRate1.setRate(.05);
        salesTaxRate1.setState("AL");

        doReturn(salesTaxRate).when(salesTaxRateDao).getTaxByState("NC");
        doReturn(salesTaxRate).when(salesTaxRateDao).getTaxByState("AL");

    }

    private void setUpProcessingFeeMock() {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Consoles");
        processingFee.setFee(14.95);

        ProcessingFee processingFee1 = new ProcessingFee();
        processingFee1.setProductType("Games");
        processingFee1.setFee(1.49);

        doReturn(processingFee).when(processingFeeDao).getFeeByItemType("Consoles");
        doReturn(processingFee).when(processingFeeDao).getFeeByItemType("Games");
    }

    @Test
    public void saveFindTshirt() {
        tShirtViewModel tShirt = new tShirtViewModel();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Slim fit");
        tShirt.setPrice(15.00);
        tShirt.setQuantity(5);

        tShirt = inventoryService.saveTshirt(tShirt);
        tShirtViewModel fromService = inventoryService.findTshirt(tShirt.getId());
        assertEquals(tShirt, fromService);
    }

    @Test
    public void getAllTshirts() {
        tShirt tShirt = new tShirt();
        tShirt.setColor("Blue");
        tShirt.setSize("M");
        tShirt.setDescription("Slim fit");
        tShirt.setQuantity(5);
        tShirt.setPrice(10.00);

        tShirtDao.addTshirt(tShirt);

        List<tShirt> tList = tShirtDao.getAllTshirts();
        assertEquals(1, tList.size());

    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("M");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        List<Game> gameList = gameDao.getAllGames();
        assertEquals(1, gameList.size());
    }

    @Test
    public void getAllConsoles() {
        Console console = new Console();
        console.setModel("Xbox One");
        console.setManufacturer("Microsoft");
        console.setProcessor("5.5ghz");
        console.setMemoryAmount("500gb");
        console.setPrice(300.00);
        console.setQuantity(15);

        consoleDao.addConsole(console);

        List<Console> consoles = consoleDao.getAllConsoles();
        assertEquals(1, consoles.size());
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setStreet("Couzas Ln");
        invoice.setZipCode("27654");
        invoice.setState("IL");
        invoice.setQuantity(2);
        invoice.setName("Jerry Little");
        invoice.setCity("Chicago");
        invoice.setItemType("Console");
        invoice.setTotal(656.76);
        invoice.setProcessingFee(14.99);
        invoice.setSubtotal(600.00);
        invoice.setItemId(32);
        invoice.setTax(.04);
        invoice.setUnitPrice(300.00);
        invoiceDao.saveInvoice(invoice);

        Invoice invoice1 = new Invoice();
        invoice1.setStreet("Couzas Ln");
        invoice1.setZipCode("27654");
        invoice1.setState("IL");
        invoice1.setQuantity(5);
        invoice1.setName("Jerry Little");
        invoice1.setCity("Chicago");
        invoice1.setItemType("Console");
        invoice1.setTotal(900.00);
        invoice1.setProcessingFee(14.99);
        invoice1.setSubtotal(600);
        invoice1.setItemId(32);
        invoice1.setTax(.04);
        invoice1.setUnitPrice(300.00);
        invoiceDao.saveInvoice(invoice1);

        //getting all invoices and putting them in an array list
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        //check this again
        assertEquals(1, invoiceList.size());
    }

    @Test
    public void saveFindConsole() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500gb");
        console.setProcessor("35ghz");
        console.setPrice(200.00);
        console.setQuantity(1);

        console = inventoryService.saveConsole(console);
        ConsoleViewModel fromService = inventoryService.findConsole(console.getId());
        assertEquals(console, fromService);
    }

    @Test
    public void saveFindGame() {
        GameViewModel game = new GameViewModel();
        game.setTitle("GTA5");
        game.setEsrbRating("M");
        game.setDescription("Shooting game");
        game.setPrice(25.00);
        game.setStudio("Rockstar");
        game.setQuantity(4);

        game = inventoryService.saveGame(game);
        GameViewModel fromService = inventoryService.findGame(game.getId());
        assertEquals(game, fromService);
    }

    @Test
    public void saveFindPurchase() {
        PurchaseViewModel invoice = new PurchaseViewModel();

        InvoiceViewModel invoice1 = new InvoiceViewModel();

        invoice.setName("Jordan Mentz");
        invoice.setStreet("Shandon");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipCode("28262");
        invoice.setItemType("Consoles");
        invoice.setItemId(3);
        invoice.setQuantity(2);

        invoice1.setName("Jordan Mentz");
        invoice1.setStreet("Shandon");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipCode("28262");
        invoice1.setItemType("Consoles");
        invoice1.setItemId(3);
        invoice1.setQuantity(2);
        invoice1.setId(2);

        invoice = inventoryService.saveInvoicePurchase(invoice);

        invoice1 = inventoryService.saveInvoice(invoice1);

        InvoiceViewModel fromService = inventoryService.findInvoiceById(invoice.getId());

        assertEquals(fromService, invoice1);
    }

    @Test
    public void saveFindInvoice() {
        InvoiceViewModel invoice = new InvoiceViewModel();

        InvoiceViewModel invoice1 = new InvoiceViewModel();


        invoice.setName("Jordan Mentz");
        invoice.setStreet("Shandon");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipCode("28262");
        invoice.setItemType("Consoles");
        invoice.setItemId(3);
        invoice.setUnitPrice(200.00);
        invoice.setQuantity(2);
        invoice.setSubtotal(400.00);
        invoice.setTax(.05);
        invoice.setProcessingFee(14.99);
        invoice.setTotal(432.85);

        invoice1.setName("Jordan Mentz");
        invoice1.setStreet("Shandon");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipCode("28262");
        invoice1.setItemType("Consoles");
        invoice1.setItemId(3);
        invoice1.setUnitPrice(200.00);
        invoice1.setQuantity(2);
        invoice1.setSubtotal(400.00);
        invoice1.setTax(.05);
        invoice1.setProcessingFee(14.99);
        invoice1.setTotal(432.85);
        invoice1.setId(0);

        invoice = inventoryService.saveInvoice(invoice);

        invoice1 = inventoryService.saveInvoice(invoice1);

        InvoiceViewModel fromService = inventoryService.findInvoiceById(invoice.getId());
        assertEquals(invoice1, invoice);
    }

    @Test
    public void findGameByStudio() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("GTA5");
        gameViewModel.setEsrbRating("M");
        gameViewModel.setDescription("Shooting game");
        gameViewModel.setPrice(25.00);
        gameViewModel.setStudio("Rockstar");
        gameViewModel.setQuantity(4);

        gameViewModel = inventoryService.saveGame(gameViewModel);
        List<GameViewModel> games = inventoryService.findGameByStudio("Rockstar");

        assertEquals(1, games.size());
        assertEquals(gameViewModel, games.get(0));

    }

    @Test
    public void findGamesByEsrbRating() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("GTA5");
        gameViewModel.setEsrbRating("M");
        gameViewModel.setDescription("Shooting game");
        gameViewModel.setPrice(25.00);
        gameViewModel.setStudio("Rockstar");
        gameViewModel.setQuantity(4);

        gameViewModel = inventoryService.saveGame(gameViewModel);
        List<GameViewModel> games = inventoryService.findGamesByEsrbRating("M");

        assertEquals(1, games.size());
        assertEquals(gameViewModel, games.get(0));
    }

    @Test
    public void findGamesByTitle() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("GTA5");
        gameViewModel.setEsrbRating("M");
        gameViewModel.setDescription("Shooting game");
        gameViewModel.setPrice(25.00);
        gameViewModel.setStudio("Rockstar");
        gameViewModel.setQuantity(4);

        gameViewModel = inventoryService.saveGame(gameViewModel);
        List<GameViewModel> games = inventoryService.findGamesByTitle("GTA5");

        assertEquals(1, games.size());
        assertEquals(gameViewModel, games.get(0));
    }

    @Test
    public void findConsoleByManufacturer() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("GTA5");
        gameViewModel.setEsrbRating("M");
        gameViewModel.setDescription("Shooting game");
        gameViewModel.setPrice(25.00);
        gameViewModel.setStudio("Rockstar");
        gameViewModel.setQuantity(4);

        gameViewModel = inventoryService.saveGame(gameViewModel);
        List<GameViewModel> games = inventoryService.findGamesByTitle("GTA5");

        assertEquals(1, games.size());
        assertEquals(gameViewModel, games.get(0));
    }

    @Test
    public void findTshirtByColor() {
        tShirtViewModel tShirtViewModel = new tShirtViewModel();
        tShirtViewModel.setSize("M");
        tShirtViewModel.setColor("Blue");
        tShirtViewModel.setDescription("Slim fit");
        tShirtViewModel.setPrice(15.00);
        tShirtViewModel.setQuantity(5);


        tShirtViewModel = inventoryService.saveTshirt(tShirtViewModel);
        List<tShirtViewModel> tShirts = inventoryService.findTshirtByColor("Blue");

        assertEquals(1, tShirts.size());
        assertEquals(tShirtViewModel, tShirts.get(0));
    }

    @Test
    public void findTshirtBySize() {
        tShirtViewModel tShirtViewModel = new tShirtViewModel();
        tShirtViewModel.setSize("M");
        tShirtViewModel.setColor("Blue");
        tShirtViewModel.setDescription("Slim fit");
        tShirtViewModel.setPrice(15.00);
        tShirtViewModel.setQuantity(5);


        tShirtViewModel = inventoryService.saveTshirt(tShirtViewModel);
        List<tShirtViewModel> tShirts = inventoryService.findTshirtBySize("M");

        assertEquals(1, tShirts.size());
        assertEquals(tShirtViewModel, tShirts.get(0));
    }

    @Test
    public void createConsolePurchase() {

    }

    @Test
    public void createGamePurchase() {
    }

    @Test
    public void createTshirtPurchase() {
    }
}