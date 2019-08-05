package com.company.JordanMentzU1Capstone.dao;

import com.company.JordanMentzU1Capstone.model.Console;
import com.company.JordanMentzU1Capstone.model.Game;
import com.company.JordanMentzU1Capstone.model.Invoice;
import com.company.JordanMentzU1Capstone.model.tShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class tShirtDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    GameDao gameDao;

    @Autowired
    tShirtDao tShirtDao;

    @Autowired
    ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice invoice : invoices) {
            invoiceDao.deleteInvoice(invoice.getId());
        }

        List<Console> consoles = consoleDao.getAllConsoles();
        for (Console console : consoles) {
            consoleDao.deleteConsole(console.getId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getId());
        }

        List<tShirt> tShirts = tShirtDao.getAllTshirts();
        for (tShirt tShirt : tShirts) {
            tShirtDao.deleteTshirt(tShirt.getId());
        }
    }

    @Test
    public void addGetDeleteTshirt() {
        tShirt tShirt = new tShirt();
        tShirt.setColor("Blue");
        tShirt.setSize("M");
        tShirt.setDescription("Slim fit");
        tShirt.setQuantity(5);
        tShirt.setPrice(10.00);

        tShirt = tShirtDao.addTshirt(tShirt);

        tShirt tShirt1 = tShirtDao.getTshirt(tShirt.getId());
        assertEquals(tShirt1, tShirt);

        tShirtDao.deleteTshirt(tShirt.getId());
        tShirt1 = tShirtDao.getTshirt(tShirt.getId());
        assertNull(tShirt1);
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

        tShirt tShirt1 = new tShirt();
        tShirt1.setColor("Blue");
        tShirt1.setSize("M");
        tShirt1.setDescription("Slim fit");
        tShirt1.setQuantity(5);
        tShirt1.setPrice(10.00);

        tShirtDao.addTshirt(tShirt1);

        tList = tShirtDao.getAllTshirts();
        assertEquals(2, tList.size());
    }

    @Test
    public void updateTshirt() {
        tShirt tShirt = new tShirt();
        tShirt.setColor("Blue");
        tShirt.setSize("M");
        tShirt.setDescription("Slim fit");
        tShirt.setQuantity(5);
        tShirt.setPrice(10.00);

        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("L");

        tShirtDao.updateTshirt(tShirt);

        tShirt tShirt1 = tShirtDao.getTshirt(tShirt.getId());

        assertEquals(tShirt, tShirt1);
    }

    @Test
    public void getTshirtByColor() {
        tShirt tShirt = new tShirt();
        tShirt.setColor("Blue");
        tShirt.setSize("M");
        tShirt.setDescription("Slim fit");
        tShirt.setQuantity(5);
        tShirt.setPrice(10.00);

        tShirtDao.addTshirt(tShirt);

        tShirt tShirt1 = new tShirt();
        tShirt1.setColor("Red");
        tShirt1.setSize("M");
        tShirt1.setDescription("Slim fit");
        tShirt1.setQuantity(10);
        tShirt1.setPrice(10.00);

        tShirtDao.addTshirt(tShirt1);

       List<tShirt> tList = tShirtDao.getTshirtByColor("Red");

       assertEquals(1, tList.size());
    }

    @Test
    public void getTshirtBySize() {
        tShirt tShirt = new tShirt();
        tShirt.setColor("Blue");
        tShirt.setSize("L");
        tShirt.setDescription("Slim fit");
        tShirt.setQuantity(5);
        tShirt.setPrice(10.00);

        tShirtDao.addTshirt(tShirt);

        tShirt tShirt1 = new tShirt();
        tShirt1.setColor("Red");
        tShirt1.setSize("M");
        tShirt1.setDescription("Slim fit");
        tShirt1.setQuantity(10);
        tShirt1.setPrice(10.00);

        tShirtDao.addTshirt(tShirt1);

        List<tShirt> tList = tShirtDao.getTshirtBySize("M");

        assertEquals(1, tList.size());
    }
}