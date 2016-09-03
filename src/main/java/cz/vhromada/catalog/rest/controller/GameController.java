package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.facade.to.GameTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for games.
 *
 * @author Vladimir Hromada
 */
@Controller("gameController")
@RequestMapping("/games")
@CrossOrigin
public class GameController extends AbstractCatalogController {

    @Autowired
    @Qualifier("gameFacade")
    private GameFacade gameFacade;

    /**
     * Creates new data.
     *
     * @return response status
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        gameFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of games.
     *
     * @return list of games
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getGames() {
        return getDataResponseEntity(gameFacade.getGames());
    }

    /**
     * Returns game with ID or null if there isn't such game.
     *
     * @param id ID
     * @return game with ID or null if there isn't such game
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getGame(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(gameFacade.getGame(id));
    }

    /**
     * Adds game. Sets new ID and position.
     *
     * @param game game
     * @return response status
     * @throws IllegalArgumentException                              if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException if ID isn't null
     *                                                               or name is null
     *                                                               or name is empty string
     *                                                               or URL to english Wikipedia page about game is null
     *                                                               or URL to czech Wikipedia page about game is null
     *                                                               or count of media isn't positive number
     *                                                               or other data is null
     *                                                               or note is null
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String game) {
        gameFacade.add(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates game.
     *
     * @param game new value of game
     * @return response status
     * @throws IllegalArgumentException                                  if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or name is null
     *                                                                   or name is empty string
     *                                                                   or URL to english Wikipedia page about game is null
     *                                                                   or URL to czech Wikipedia page about game is null
     *                                                                   or count of media isn't positive number
     *                                                                   or other data is null
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if game doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String game) {
        gameFacade.update(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes game.
     *
     * @param game game
     * @return response status
     * @throws IllegalArgumentException                                  if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if game doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String game) {
        gameFacade.remove(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates game.
     *
     * @param game game
     * @return response status
     * @throws IllegalArgumentException                                  if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if game doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String game) {
        gameFacade.duplicate(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves game in list one position up.
     *
     * @param game game
     * @return response status
     * @throws IllegalArgumentException                                  if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or game can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if game doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String game) {
        gameFacade.moveUp(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves game in list one position down.
     *
     * @param game game
     * @return response status
     * @throws IllegalArgumentException                                  if game is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or game can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if game doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String game) {
        gameFacade.moveDown(deserialize(game, GameTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.GET)
    public ResponseEntity<Void> updatePositions() {
        gameFacade.updatePositions();

        return getEmptyResponseEntity();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @RequestMapping(value = "/totalMedia", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalMediaCount() {
        return getDataResponseEntity(gameFacade.getTotalMediaCount());
    }

}
