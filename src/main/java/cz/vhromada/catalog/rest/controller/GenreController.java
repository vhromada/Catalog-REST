package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.rest.entity.Result;
import cz.vhromada.catalog.rest.entity.Status;
import cz.vhromada.catalog.rest.validator.GenreValidator;

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
     * Key for not existing genre
     */
    private static final String NOT_EXISTING_GENRE_KEY = "GENRE_NOT_EXISTS";

    /**
     * Message for not existing genre
     */
    private static final String NOT_EXISTING_GENRE_MESSAGE = "Genre doesn't exist.";

    /**
     * Key for not movable genre
     */
    private static final String NOT_MOVABLE_GENRE_KEY = "GENRE_NOT_MOVABLE";

    /**
     * Message for not movable genre
     */
    private static final String NOT_MOVABLE_GENRE_MESSAGE = "ID isn't valid - genre can't be moved.";

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Validator for genre
     */
    private GenreValidator genreValidator;

    /**
     * Creates a new instance of GenreController.
     *
     * @param genreFacade    facade for genres
     * @param genreValidator validator for genre
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or validator for genre is null
     */
    @Autowired
    public GenreController(final GenreFacade genreFacade,
            final GenreValidator genreValidator) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(genreValidator, "Validator for genre mustn't be null.");

        this.genreFacade = genreFacade;
        this.genreValidator = genreValidator;
    }

    /**
     * Creates new data.
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        genreFacade.newData();

        return new Result<>();
    }

    /**
     * Returns list of genres.
     *
     * @return result with list of genres
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Genre>> getGenres() {
        return Result.of(genreFacade.getGenres());
    }

    /**
     * Returns genre with ID or null if there isn't such genre.
     *
     * @param id ID
     * @return result with genre with ID or null if there isn't such genre
     */
    @GetMapping("/{id}")
    public Result<Genre> getGenre(@PathVariable("id") final Integer id) {
        if (id == null) {
            return Result.of("ID_NULL", "ID mustn't be null");
        }

        return Result.of(genreFacade.getGenre(id));
    }

    /**
     * Adds genre. Sets new ID and position.
     *
     * @param genre genre
     * @return result
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateNewGenre(genre);

        if (Status.OK == result.getStatus()) {
            genreFacade.add(genre);
        }

        return result;
    }

    /**
     * Updates genre.
     *
     * @param genre new value of genre
     * @return result
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateExistingGenre(genre);

        if (Status.OK == result.getStatus()) {
            if (existsGenres(genre)) {
                genreFacade.update(genre);
            } else {
                result.addErrorMessage(NOT_EXISTING_GENRE_KEY, NOT_EXISTING_GENRE_MESSAGE);
            }
        }

        return result;
    }

    /**
     * Removes genre.
     *
     * @param genre genre
     * @return result
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateGenreWithId(genre);

        if (Status.OK == result.getStatus()) {
            if (existsGenres(genre)) {
                genreFacade.remove(genre);
            } else {
                result.addErrorMessage(NOT_EXISTING_GENRE_KEY, NOT_EXISTING_GENRE_MESSAGE);
            }
        }

        return result;
    }

    /**
     * Duplicates genre.
     *
     * @param genre genre
     * @return result
     */
    @PostMapping("/duplicate")
    public Result<Void> duplicate(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateGenreWithId(genre);

        if (Status.OK == result.getStatus()) {
            if (existsGenres(genre)) {
                genreFacade.duplicate(genre);
            } else {
                result.addErrorMessage(NOT_EXISTING_GENRE_KEY, NOT_EXISTING_GENRE_MESSAGE);
            }
        }

        return result;
    }

    /**
     * Moves genre in list one position up.
     *
     * @param genre genre
     * @return result
     */
    @PostMapping("/moveUp")
    public Result<Void> moveUp(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateGenreWithId(genre);

        if (Status.OK == result.getStatus()) {
            if (existsGenres(genre)) {
                final List<Genre> genres = genreFacade.getGenres();
                if (genres.indexOf(genre) <= 0) {
                    result.addErrorMessage(NOT_MOVABLE_GENRE_KEY, NOT_MOVABLE_GENRE_MESSAGE);
                } else {
                    genreFacade.moveUp(genre);
                }
            } else {
                result.addErrorMessage(NOT_EXISTING_GENRE_KEY, NOT_EXISTING_GENRE_MESSAGE);
            }
        }

        return result;
    }

    /**
     * Moves genre in list one position down.
     *
     * @param genre genre
     * @return result
     */
    @PostMapping("/moveDown")
    public Result<Void> moveDown(@RequestBody final Genre genre) {
        final Result<Void> result = genreValidator.validateGenreWithId(genre);

        if (Status.OK == result.getStatus()) {
            if (existsGenres(genre)) {
                final List<Genre> genres = genreFacade.getGenres();
                if (genres.indexOf(genre) >= genres.size() - 1) {
                    result.addErrorMessage(NOT_MOVABLE_GENRE_KEY, NOT_MOVABLE_GENRE_MESSAGE);
                } else {
                    genreFacade.moveDown(genre);
                }
            } else {
                result.addErrorMessage(NOT_EXISTING_GENRE_KEY, NOT_EXISTING_GENRE_MESSAGE);
            }
        }

        return result;
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        genreFacade.updatePositions();

        return new Result<>();
    }

    /**
     * Returns true if genre exists.
     *
     * @param genre genre
     * @return true if genre exists
     */
    private boolean existsGenres(final Genre genre) {
        return genreFacade.getGenre(genre.getId()) != null;
    }

}
