package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A class represents controller for games.
 *
 * @author Vladimir Hromada
 */
@Controller("gameController")
@RequestMapping("/games")
@CrossOrigin
public class GameController {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GameController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GameController(final GameFacade gameFacade) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");

        this.gameFacade = gameFacade;
    }

    /**
     * Creates new data.
     */
    @PostMapping("/new")
    public void newData() {
        gameFacade.newData();
    }

    /**
     * Returns list of games.
     *
     * @return list of games
     */
    @GetMapping({ "", "/", "/list" })
    public List<Game> getGames() {
        return gameFacade.getGames();
    }

    /**
     * Returns game with ID or null if there isn't such game.
     *
     * @param id ID
     * @return game with ID or null if there isn't such game
     * @throws IllegalArgumentException if ID is null
     */
    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id") final Integer id) {
        return gameFacade.getGame(id);
    }

    /**
     * Adds game. Sets new ID and position.
     *
     * @param game game
     * @throws IllegalArgumentException if game is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about game is null
     *                                  or URL to czech Wikipedia page about game is null
     *                                  or count of media isn't positive number
     *                                  or other data is null
     *                                  or note is null
     */
    @PutMapping("/add")
    public void add(@RequestBody final Game game) {
        gameFacade.add(game);
    }

    /**
     * Updates game.
     *
     * @param game new value of game
     * @throws IllegalArgumentException if game is null
     *                                  or ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about game is null
     *                                  or URL to czech Wikipedia page about game is null
     *                                  or count of media isn't positive number
     *                                  or other data is null
     *                                  or note is null
     *                                  or game doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@RequestBody final Game game) {
        gameFacade.update(game);
    }

    /**
     * Removes game.
     *
     * @param game game
     * @throws IllegalArgumentException if game is null
     *                                  or ID is null
     *                                  or game doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Game game) {
        gameFacade.remove(game);
    }

    /**
     * Duplicates game.
     *
     * @param game game
     * @throws IllegalArgumentException if game is null
     *                                  or ID is null
     *                                  or game doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Game game) {
        gameFacade.duplicate(game);
    }

    /**
     * Moves game in list one position up.
     *
     * @param game game
     * @throws IllegalArgumentException if game is null
     *                                  or ID is null
     *                                  or game can't be moved up
     *                                  or game doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Game game) {
        gameFacade.moveUp(game);
    }

    /**
     * Moves game in list one position down.
     *
     * @param game game
     * @throws IllegalArgumentException if game is null
     *                                  or ID is null
     *                                  or game can't be moved down
     *                                  or game doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Game game) {
        gameFacade.moveDown(game);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        gameFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @GetMapping("/totalMedia")
    public Integer getTotalMediaCount() {
        return gameFacade.getTotalMediaCount();
    }

}
