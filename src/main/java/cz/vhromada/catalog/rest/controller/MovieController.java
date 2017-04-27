package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

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
 * A class represents controller for movies.
 *
 * @author Vladimir Hromada
 */
@RestController("movieController")
@RequestMapping("/catalog/movies")
@CrossOrigin
public class MovieController {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MovieController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MovieController(final MovieFacade movieFacade) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");

        this.movieFacade = movieFacade;
    }

    /**
     * Creates new data.
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        return movieFacade.newData();
    }

    /**
     * Returns list of movies.
     *
     * @return result with list of movies
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Movie>> getMovies() {
        return movieFacade.getAll();
    }

    /**
     * Returns movie with ID or null if there isn't such movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with movie or validation errors
     */
    @GetMapping("/{id}")
    public Result<Movie> getMovie(@PathVariable("id") final Integer id) {
        return movieFacade.get(id);
    }

    /**
     * Adds movie. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID isn't null</li>
     * <li>Czech name is null</li>
     * <li>Czech name is empty string</li>
     * <li>Original name is null</li>
     * <li>Original name is empty string</li>
     * <li>Year isn't between 1940 and current year</li>
     * <li>Language is null</li>
     * <li>Subtitles are null</li>
     * <li>Subtitles contain null value</li>
     * <li>Media are null</li>
     * <li>Media contain null value</li>
     * <li>Length of medium is negative value</li>
     * <li>URL to ČSFD page about movie is null</li>
     * <li>IMDB code isn't -1 or between 1 and 9999999</li>
     * <li>URL to english Wikipedia page about movie is null</li>
     * <li>URL to czech Wikipedia page about movie is null</li>
     * <li>Path to file with movie's picture is null</li>
     * <li>Note is null</li>
     * <li>Genres are null</li>
     * <li>Genres contain null value</li>
     * <li>Genre ID is null</li>
     * <li>Genre name is null</li>
     * <li>Genre name is empty string</li>
     * <li>Genre doesn't exist</li>
     * </ul>
     *
     * @param movie movie
     * @return result with validation errors
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestBody final Movie movie) {
        return movieFacade.add(movie);
    }

    /**
     * Updates movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Czech name is null</li>
     * <li>Czech name is empty string</li>
     * <li>Original name is null</li>
     * <li>Original name is empty string</li>
     * <li>Year isn't between 1940 and current year</li>
     * <li>Language is null</li>
     * <li>Subtitles are null</li>
     * <li>Subtitles contain null value</li>
     * <li>Media are null</li>
     * <li>Media contain null value</li>
     * <li>Length of medium is negative value</li>
     * <li>URL to ČSFD page about movie is null</li>
     * <li>IMDB code isn't -1 or between 1 and 9999999</li>
     * <li>URL to english Wikipedia page about movie is null</li>
     * <li>URL to czech Wikipedia page about movie is null</li>
     * <li>Path to file with movie's picture is null</li>
     * <li>Note is null</li>
     * <li>Genres are null</li>
     * <li>Genres contain null value</li>
     * <li>Genre ID is null</li>
     * <li>Genre name is null</li>
     * <li>Genre name is empty string</li>
     * <li>Genre doesn't exist</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param movie new value of movie
     * @return result with validation errors
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody final Movie movie) {
        return movieFacade.update(movie);
    }

    /**
     * Removes movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param movie movie
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Movie movie) {
        return movieFacade.remove(movie);
    }

    /**
     * Duplicates movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param movie movie
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public Result<Void> duplicate(@RequestBody final Movie movie) {
        return movieFacade.duplicate(movie);
    }

    /**
     * Moves movie in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Movie can't be moved up</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param movie movie
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public Result<Void> moveUp(@RequestBody final Movie movie) {
        return movieFacade.moveUp(movie);
    }

    /**
     * Moves movie in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Movie can't be moved down</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param movie movie
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public Result<Void> moveDown(@RequestBody final Movie movie) {
        return movieFacade.moveDown(movie);
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return movieFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public Result<Integer> getTotalMediaCount() {
        return movieFacade.getTotalMediaCount();
    }

    /**
     * Returns total length of all movies.
     *
     * @return result with total length of all movies
     */
    @GetMapping("/totalLength")
    @SuppressWarnings("Duplicates")
    public Result<Integer> getTotalLength() {
        final Result<Time> lengthResult = movieFacade.getTotalLength();

        if (Status.OK == lengthResult.getStatus()) {
            return Result.of(lengthResult.getData().getLength());
        }

        final Result<Integer> result = new Result<>();
        result.addEvents(lengthResult.getEvents());

        return result;
    }

}
