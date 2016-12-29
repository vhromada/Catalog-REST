package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
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

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Creates a new instance of GenreController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public GenreController(final GenreFacade genreFacade) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.genreFacade = genreFacade;
    }

    /**
     * Creates new data.
     *
     * @return response status
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
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String genre) {
        genreFacade.add(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates genre.
     *
     * @param genre new value of genre
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String genre) {
        genreFacade.update(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes genre.
     *
     * @param genre genre
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String genre) {
        genreFacade.remove(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates genre.
     *
     * @param genre genre
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String genre) {
        genreFacade.duplicate(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves genre in list one position up.
     *
     * @param genre genre
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre can't be moved up
     *                                  or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String genre) {
        genreFacade.moveUp(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves genre in list one position down.
     *
     * @param genre genre
     * @return response status
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre can't be moved down
     *                                  or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String genre) {
        genreFacade.moveDown(deserialize(genre, Genre.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePositions() {
        genreFacade.updatePositions();

        return getEmptyResponseEntity();
    }

}
