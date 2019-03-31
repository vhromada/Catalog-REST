package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.validation.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * A class represents controller for genres.
 *
 * @author Vladimir Hromada
 */
@RestController("genreController")
@RequestMapping("/catalog/genres")
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
     * @return result
     */
    @PostMapping("/new")
    public ResponseEntity<Result<Void>> newData() {
        return processResult(genreFacade.newData());
    }

    /**
     * Returns list of genres.
     *
     * @return result with list of genres
     */
    @GetMapping
    public ResponseEntity<Result<List<Genre>>> getGenres() {
        return processResult(genreFacade.getAll());
    }

    /**
     * Returns genre with ID or null if there isn't such genre.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with genre or validation errors
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<Genre>> getGenre(@PathVariable("id") final Integer id) {
        return processResult(genreFacade.get(id));
    }

    /**
     * Adds genre. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Genre is null</li>
     * <li>ID isn't null</li>
     * <li>Position isn't null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * </ul>
     *
     * @param genre genre
     * @return result with validation errors
     */
    @PutMapping("/add")
    public ResponseEntity<Result<Void>> add(@RequestBody final Genre genre) {
        return processResult(genreFacade.add(genre), HttpStatus.CREATED);
    }

    /**
     * Updates genre.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Genre is null</li>
     * <li>ID is null</li>
     * <li>Position is null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>Genre doesn't exist in data storage</li>
     * </ul>
     *
     * @param genre new value of genre
     * @return result with validation errors
     */
    @PostMapping("/update")
    public ResponseEntity<Result<Void>> update(@RequestBody final Genre genre) {
        return processResult(genreFacade.update(genre));
    }

    /**
     * Removes genre.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Genre doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Result<Void>> remove(@PathVariable("id") final Integer id) {
        final Genre genre = new Genre();
        genre.setId(id);
        return processResult(genreFacade.remove(genre));
    }

    /**
     * Duplicates genre.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Genre is null</li>
     * <li>ID is null</li>
     * <li>Genre doesn't exist in data storage</li>
     * </ul>
     *
     * @param genre genre
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public ResponseEntity<Result<Void>> duplicate(@RequestBody final Genre genre) {
        return processResult(genreFacade.duplicate(genre), HttpStatus.CREATED);
    }

    /**
     * Moves genre in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Genre is null</li>
     * <li>ID is null</li>
     * <li>Genre can't be moved up</li>
     * <li>Genre doesn't exist in data storage</li>
     * </ul>
     *
     * @param genre genre
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public ResponseEntity<Result<Void>> moveUp(@RequestBody final Genre genre) {
        return processResult(genreFacade.moveUp(genre));
    }

    /**
     * Moves genre in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Genre is null</li>
     * <li>ID is null</li>
     * <li>Genre can't be moved down</li>
     * <li>Genre doesn't exist in data storage</li>
     * </ul>
     *
     * @param genre genre
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public ResponseEntity<Result<Void>> moveDown(@RequestBody final Genre genre) {
        return processResult(genreFacade.moveDown(genre));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public ResponseEntity<Result<Void>> updatePositions() {
        return processResult(genreFacade.updatePositions());
    }

}
