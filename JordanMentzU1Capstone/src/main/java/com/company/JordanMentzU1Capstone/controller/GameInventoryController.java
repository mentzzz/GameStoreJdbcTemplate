package com.company.JordanMentzU1Capstone.controller;

import com.company.JordanMentzU1Capstone.exception.NotFoundException;
import com.company.JordanMentzU1Capstone.service.InventoryService;
import com.company.JordanMentzU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameInventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping//Http method to create a new game
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game) {
        return inventoryService.saveGame(game);
    }

    @GetMapping("/{id}")//Http method to get all games
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int gameId) {
        GameViewModel gameViewModel = inventoryService.findGame(gameId);
        if (gameViewModel == null)
            throw new NotFoundException("Game could not be retrieved for id " + gameId);
        return gameViewModel;
    }

    @GetMapping//HTTOP method to get all games
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        List<GameViewModel> gameViewModels = inventoryService.findAllGames();
        if (gameViewModels == null)
            throw new NotFoundException("Games could not be retrieved");
        return gameViewModels;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        inventoryService.removeGame(id);
    }

    @PutMapping("/{id}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") int gameId, @RequestBody @Valid GameViewModel gameViewModel) {
        if (gameViewModel.getId() == 0)
            gameViewModel.setId(gameId);
        if (gameId != gameViewModel.getId()) {
            throw new IllegalArgumentException("Game ID on path must match the ID in the Game object");
        }
        inventoryService.updateGame(gameViewModel);
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {
        List<GameViewModel> games = inventoryService.findGameByStudio(studio);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Games could not be retrieved for manufacturer " + studio);
        return games;
    }

    @GetMapping("/esrbrating/{esrbrating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable("esrbrating") String esrbRating) {
        List<GameViewModel> games = inventoryService.findGamesByEsrbRating(esrbRating);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Games could not be retrieved for EsrbRating " + esrbRating);
        return games;
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title) {
        List<GameViewModel> games = inventoryService.findGamesByTitle(title);
        if (games != null && games.size() == 0)
            throw new NotFoundException("Games could not be retrieved for title " + title);
        return games;
    }


}
