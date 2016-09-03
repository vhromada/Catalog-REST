package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.GenreTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for genres.
 *
 * @author Vladimir Hromada
 */
@RestController("genreController")
@RequestMapping("/genres")
@CrossOrigin
public class GenreController extends AbstractCatalogController {

    @Autowired
    @Qualifier("genreFacade")
    private GenreFacade genreFacade;

    /**
     * Creates new data.
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        genreFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of genres.
     *
     * @return list of genres
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getGenres() {
        return getDataResponseEntity(genreFacade.getGenres());
    }

    /**
     * Returns genre with ID or null if there isn't such genre.
     *
     * @param id ID
     * @return genre with ID or null if there isn't such genre
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getGenre(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(genreFacade.getGenre(id));
    }

    /**
     * Adds genre. Sets new ID and position.
     *
     * @param genre genre
     * @throws IllegalArgumentException                              if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException if ID isn't null
     *                                                               or name is null
     *                                                               or name is empty string
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String genre) {
        genreFacade.add(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Adds list of genre names.
     *
     * @param genre genre
     * @throws IllegalArgumentException                              if list of genre names is null
     * @throws cz.vhromada.validators.exceptions.ValidationException if list of genre names contains null value
     */
    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    public ResponseEntity<Void> addList(@RequestBody final String genre) {
        genreFacade.add(this.deserializeList(genre));

        return getEmptyResponseEntity();
    }

    /**
     * Updates genre.
     *
     * @param genre new value of genre
     * @throws IllegalArgumentException                                  if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or name is null
     *                                                                   or name is empty string
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String genre) {
        genreFacade.update(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes genre.
     *
     * @param genre genre
     * @throws IllegalArgumentException                                  if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String genre) {
        genreFacade.remove(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates genre.
     *
     * @param genre genre
     * @throws IllegalArgumentException                                  if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String genre) {
        genreFacade.duplicate(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves genre in list one position up.
     *
     * @param genre genre
     * @throws IllegalArgumentException                                  if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or genre can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String genre) {
        genreFacade.moveUp(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves genre in list one position down.
     *
     * @param genre genre
     * @throws IllegalArgumentException                                  if genre is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or genre can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String genre) {
        genreFacade.moveDown(deserialize(genre, GenreTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.GET)
    public ResponseEntity<Void> updatePositions() {
        genreFacade.updatePositions();

        return getEmptyResponseEntity();
    }

}
