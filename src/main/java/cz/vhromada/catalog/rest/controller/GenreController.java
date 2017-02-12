package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for genres.
 *
 * @author Vladimir Hromada
 */
@RestController("genreController")
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

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
     */
    @PostMapping("/new")
    public void newData() {
        genreFacade.newData();
    }

    /**
     * Returns list of genres.
     *
     * @return list of genres
     */
    @GetMapping({ "", "/", "/list" })
    public List<Genre> getGenres() {
        return genreFacade.getGenres();
    }

    /**
     * Returns genre with ID or null if there isn't such genre.
     *
     * @param id ID
     * @return genre with ID or null if there isn't such genre
     * @throws IllegalArgumentException if ID is null
     */
    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") final Integer id) {
        return genreFacade.getGenre(id);
    }

    /**
     * Adds genre. Sets new ID and position.
     *
     * @param genre genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     */
    @PutMapping("/add")
    public void add(@RequestBody final Genre genre) {
        genreFacade.add(genre);
    }

    /**
     * Updates genre.
     *
     * @param genre new value of genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@RequestBody final Genre genre) {
        genreFacade.update(genre);
    }

    /**
     * Removes genre.
     *
     * @param genre genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Genre genre) {
        genreFacade.remove(genre);
    }

    /**
     * Duplicates genre.
     *
     * @param genre genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Genre genre) {
        genreFacade.duplicate(genre);
    }

    /**
     * Moves genre in list one position up.
     *
     * @param genre genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre can't be moved up
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Genre genre) {
        genreFacade.moveUp(genre);
    }

    /**
     * Moves genre in list one position down.
     *
     * @param genre genre
     * @throws IllegalArgumentException if genre is null
     *                                  or ID is null
     *                                  or genre can't be moved down
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Genre genre) {
        genreFacade.moveDown(genre);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        genreFacade.updatePositions();
    }

}
