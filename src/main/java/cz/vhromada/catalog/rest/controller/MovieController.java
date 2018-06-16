package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.common.Time;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

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
 * A class represents controller for movies.
 *
 * @author Vladimir Hromada
 */
@RestController("movieController")
@RequestMapping("/catalog/movies")
public class MovieController extends AbstractCatalogController {

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
    public ResponseEntity<Result<Void>> newData() {
        return processResult(movieFacade.newData());
    }

    /**
     * Returns list of movies.
     *
     * @return result with list of movies
     */
    @GetMapping({ "", "/list" })
    public ResponseEntity<Result<List<Movie>>> getMovies() {
        return processResult(movieFacade.getAll());
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
    public ResponseEntity<Result<Movie>> getMovie(@PathVariable("id") final Integer id) {
        return processResult(movieFacade.get(id));
    }

    /**
     * Adds movie. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID isn't null</li>
     * <li>Position isn't null</li>
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
    public ResponseEntity<Result<Void>> add(@RequestBody final Movie movie) {
        return processResult(movieFacade.add(movie), HttpStatus.CREATED);
    }

    /**
     * Updates movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Movie is null</li>
     * <li>ID is null</li>
     * <li>Position is null</li>
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
    public ResponseEntity<Result<Void>> update(@RequestBody final Movie movie) {
        return processResult(movieFacade.update(movie));
    }

    /**
     * Removes movie.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Movie doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Result<Void>> remove(@PathVariable("id") final Integer id) {
        final Movie movie = new Movie();
        movie.setId(id);
        return processResult(movieFacade.remove(movie));
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
    public ResponseEntity<Result<Void>> duplicate(@RequestBody final Movie movie) {
        return processResult(movieFacade.duplicate(movie), HttpStatus.CREATED);
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
    public ResponseEntity<Result<Void>> moveUp(@RequestBody final Movie movie) {
        return processResult(movieFacade.moveUp(movie));
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
    public ResponseEntity<Result<Void>> moveDown(@RequestBody final Movie movie) {
        return processResult(movieFacade.moveDown(movie));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public ResponseEntity<Result<Void>> updatePositions() {
        return processResult(movieFacade.updatePositions());
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public ResponseEntity<Result<Integer>> getTotalMediaCount() {
        return processResult(movieFacade.getTotalMediaCount());
    }

    /**
     * Returns total length of all movies.
     *
     * @return result with total length of all movies
     */
    @GetMapping("/totalLength")
    @SuppressWarnings("Duplicates")
    public ResponseEntity<Result<Integer>> getTotalLength() {
        final Result<Time> lengthResult = movieFacade.getTotalLength();

        if (Status.OK == lengthResult.getStatus()) {
            return processResult(Result.of(lengthResult.getData().getLength()));
        }

        final Result<Integer> result = new Result<>();
        result.addEvents(lengthResult.getEvents());

        return processResult(result);
    }

}
