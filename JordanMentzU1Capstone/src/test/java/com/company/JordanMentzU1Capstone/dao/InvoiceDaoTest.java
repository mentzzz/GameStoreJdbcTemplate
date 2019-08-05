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
public class InvoiceDaoTest {

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
    public void addGetDeleteInvoice() {
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
        invoice = invoiceDao.saveInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getId());
        assertEquals(invoice, invoice1);

        invoiceDao.deleteInvoice(invoice.getId());

        invoice1 = invoiceDao.getInvoice(invoice.getId());
        assertNull(invoice1);


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

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(2, invoiceList.size());
    }

    @Test
    public void updateInvoice() {
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
        invoice.setSubtotal(600);
        invoice.setItemId(32);
        invoice.setTax(.04);
        invoice.setUnitPrice(300.00);
        invoice = invoiceDao.saveInvoice(invoice);

        invoice.setQuantity(6);

        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getId());
        assertEquals(invoice, invoice1);
    }
}