package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for games.
 *
 * @author Vladimir Hromada
 */
@RestController("gameController")
@RequestMapping("/catalog/games")
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
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        return gameFacade.newData();
    }

    /**
     * Returns list of games.
     *
     * @return result with list of games
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Game>> getGames() {
        return gameFacade.getAll();
    }

    /**
     * Returns game with ID or null if there isn't such game.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with game or validation errors
     */
    @GetMapping("/{id}")
    public Result<Game> getGame(@PathVariable("id") final Integer id) {
        return gameFacade.get(id);
    }

    /**
     * Adds game. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID isn't null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about game is null</li>
     * <li>URL to czech Wikipedia page about game is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param game game
     * @return result with validation errors
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestBody final Game game) {
        return gameFacade.add(game);
    }

    /**
     * Updates game.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID is null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about game is null</li>
     * <li>URL to czech Wikipedia page about game is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * <li>Game doesn't exist in data storage</li>
     * </ul>
     *
     * @param game new value of game
     * @return result with validation errors
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody final Game game) {
        return gameFacade.update(game);
    }

    /**
     * Removes game.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID is null</li>
     * <li>Game doesn't exist in data storage</li>
     * </ul>
     *
     * @param game game
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Game game) {
        return gameFacade.remove(game);
    }

    /**
     * Duplicates game.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID is null</li>
     * <li>Game doesn't exist in data storage</li>
     * </ul>
     *
     * @param game game
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public Result<Void> duplicate(@RequestBody final Game game) {
        return gameFacade.duplicate(game);
    }

    /**
     * Moves game in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID is null</li>
     * <li>Game can't be moved up</li>
     * <li>Game doesn't exist in data storage</li>
     * </ul>
     *
     * @param game game
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public Result<Void> moveUp(@RequestBody final Game game) {
        return gameFacade.moveUp(game);
    }

    /**
     * Moves game in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Game is null</li>
     * <li>ID is null</li>
     * <li>Game can't be moved down</li>
     * <li>Game doesn't exist in data storage</li>
     * </ul>
     *
     * @param game game
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public Result<Void> moveDown(@RequestBody final Game game) {
        return gameFacade.moveDown(game);
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return gameFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public Result<Integer> getTotalMediaCount() {
        return gameFacade.getTotalMediaCount();
    }

}
