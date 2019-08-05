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
public class ConsoleDaoTest {

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
    public void addGetDeleteConsole() {
        Console console = new Console();
        console.setModel("Xbox One");
        console.setManufacturer("Microsoft");
        console.setProcessor("5.5ghz");
        console.setMemoryAmount("500gb");
        console.setPrice(300.00);
        console.setQuantity(15);

        consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getId());
        assertEquals(console, console1);

        consoleDao.deleteConsole(console.getId());

        console1 = consoleDao.getConsole(console.getId());
        assertNull(console1);
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

        Console console1 = new Console();
        console1.setModel("PS4");
        console1.setManufacturer("Sony");
        console1.setProcessor("5.5ghz");
        console1.setMemoryAmount("500gb");
        console1.setPrice(300.00);
        console1.setQuantity(20);

        consoleDao.addConsole(console1);

        consoles = consoleDao.getAllConsoles();

        assertEquals(2, consoles.size());
    }

    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setProcessor("5.5ghz");
        console.setMemoryAmount("500gb");
        console.setPrice(300.00);
        console.setQuantity(20);

        consoleDao.addConsole(console);

        console.setMemoryAmount("1tb");

        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getId());

        assertEquals(console, console1);
    }

    @Test
    public void getConsolesByManufacturer() {
        Console console = new Console();
        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setProcessor("5.5ghz");
        console.setMemoryAmount("500gb");
        console.setPrice(300.00);
        console.setQuantity(20);

        consoleDao.addConsole(console);

        Console console1 = new Console();
        console1.setModel("Xbox One");
        console1.setManufacturer("Microsoft");
        console1.setProcessor("5.5ghz");
        console1.setMemoryAmount("500gb");
        console1.setPrice(300.00);
        console1.setQuantity(15);

        List<Console> consoleList = consoleDao.getConsolesByManufacturer("Sony");

        assertEquals(1, consoleList.size());


    }
}