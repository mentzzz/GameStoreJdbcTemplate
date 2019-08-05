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
public class GameDaoTest {

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
    public void addGetDeleteGame() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("Mature");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        Game game1 = gameDao.getGame(game.getId());
        assertEquals(game, game1);

        gameDao.deleteGame(game.getId());
        game1 = gameDao.getGame(game.getId());
        assertNull(game1);
    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("Mature");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        List<Game> gameList = gameDao.getAllGames();
        assertEquals(1, gameList.size());

        Game game1 = new Game();
        game1.setTitle("GTA2");
        game1.setDescription("Shooting Game");
        game1.setEsrbRating("Mature");
        game1.setPrice(10.00);
        game1.setQuantity(2);
        game1.setStudio("Rockstar");
        gameDao.addGame(game1);

        gameList = gameDao.getAllGames();
        assertEquals(2, gameList.size());
    }

    @Test
    public void updateGame() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("Mature");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        game.setQuantity(25);

        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getId());

        assertEquals(game, game1);
    }

    @Test
    public void getGamesByStudio() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("Mature");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        Game game1 = new Game();
        game1.setTitle("Forza");
        game1.setDescription("Racing Game");
        game1.setEsrbRating("E");
        game1.setPrice(10.00);
        game1.setQuantity(5);
        game1.setStudio("Forzas");
        gameDao.addGame(game1);

        List<Game> gameList = gameDao.getGamesByStudio("Rockstar");
        assertEquals(1, gameList.size());

    }

    @Test
    public void getGamesByEsrbRating() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("M");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        Game game1 = new Game();
        game1.setTitle("Forza");
        game1.setDescription("Racing Game");
        game1.setEsrbRating("E");
        game1.setPrice(10.00);
        game1.setQuantity(5);
        game1.setStudio("Forzas");
        gameDao.addGame(game1);

        List<Game> gameList = gameDao.getGamesByEsrbRating("E");
        assertEquals(1, gameList.size());
    }

    @Test
    public void getGamesByTitle() {
        Game game = new Game();
        game.setTitle("GTA5");
        game.setDescription("Shooting Game");
        game.setEsrbRating("Mature");
        game.setPrice(40.00);
        game.setQuantity(5);
        game.setStudio("Rockstar");
        gameDao.addGame(game);

        Game game1 = new Game();
        game1.setTitle("Forza");
        game1.setDescription("Racing Game");
        game1.setEsrbRating("E");
        game1.setPrice(10.00);
        game1.setQuantity(5);
        game1.setStudio("Forzas");
        gameDao.addGame(game1);

        List<Game> gameList = gameDao.getGamesByTitle("Forza");
        assertEquals(1, gameList.size());
    }
}