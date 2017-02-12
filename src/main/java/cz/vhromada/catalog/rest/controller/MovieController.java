package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;

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
 * A class represents controller for movies.
 *
 * @author Vladimir Hromada
 */
@Controller("movieController")
@RequestMapping("/movies")
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
     */
    @PostMapping("/new")
    public void newData() {
        movieFacade.newData();
    }

    /**
     * Returns list of movies.
     *
     * @return list of movies
     */
    @GetMapping({ "", "/", "list" })
    public List<Movie> getMovies() {
        return movieFacade.getMovies();
    }

    /**
     * Returns movie with ID or null if there isn't such movie.
     *
     * @param id ID
     * @return movie with ID or null if there isn't such movie
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping("/{id}")
    public Movie getMovie(@PathVariable("id") final Integer id) {
        return movieFacade.getMovie(id);
    }

    /**
     * Adds movie. Sets new ID and position.
     *
     * @param movie movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID isn't null
     *                                  or czech name is null
     *                                  or czech name is empty string
     *                                  or original name is null
     *                                  or original name is empty string
     *                                  or year isn't between 1940 and current year
     *                                  or language is null
     *                                  or subtitles are null
     *                                  or subtitles contain null value
     *                                  or media are null
     *                                  or media contain null value
     *                                  or media contain negative value
     *                                  or URL to ČSFD page about movie is null
     *                                  or IMDB code isn't -1 or between 1 and 9999999
     *                                  or URL to english Wikipedia page about movie is null
     *                                  or URL to czech Wikipedia page about movie is null
     *                                  or path to file with movie's picture is null
     *                                  or note is null
     *                                  or genres are null
     *                                  or genres contain null value
     *                                  or genre ID is null
     *                                  or genre name is null
     *                                  or genre name is empty string
     *                                  or genre doesn't exist in data storage
     */
    @PutMapping("/add")
    public void add(@RequestBody final Movie movie) {
        movieFacade.add(movie);
    }

    /**
     * Updates movie.
     *
     * @param movie new value of movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or czech name is null
     *                                  or czech name is empty string
     *                                  or original name is null
     *                                  or original name is empty string
     *                                  or year isn't between 1940 and current year
     *                                  or language is null
     *                                  or subtitles are null
     *                                  or subtitles contain null value
     *                                  or media are null
     *                                  or media contain null value
     *                                  or media contain negative value
     *                                  or URL to ČSFD page about movie is null
     *                                  or IMDB code isn't -1 or between 1 and 9999999
     *                                  or URL to english Wikipedia page about movie is null
     *                                  or URL to czech Wikipedia page about movie is null
     *                                  or path to file with movie's picture is null
     *                                  or note is null
     *                                  or genres are value
     *                                  or genres contain null value
     *                                  or genre ID is null
     *                                  or genre name is null
     *                                  or genre name is empty string
     *                                  or movie doesn't exist in data storage
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@RequestBody final Movie movie) {
        movieFacade.update(movie);
    }

    /**
     * Removes movie.
     *
     * @param movie movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Movie movie) {
        movieFacade.remove(movie);
    }

    /**
     * Duplicates movie.
     *
     * @param movie movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Movie movie) {
        movieFacade.duplicate(movie);
    }

    /**
     * Moves movie in list one position up.
     *
     * @param movie movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie can't be moved up
     *                                  or movie doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Movie movie) {
        movieFacade.moveUp(movie);
    }

    /**
     * Moves movie in list one position down.
     *
     * @param movie movie
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie can't be moved down
     *                                  or movie doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Movie movie) {
        movieFacade.moveDown(movie);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        movieFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @GetMapping("/totalMedia")
    public Integer getTotalMediaCount() {
        return movieFacade.getTotalMediaCount();
    }

    /**
     * Returns total length of all movies.
     *
     * @return total length of all movies
     */
    @GetMapping("/totalLength")
    public Integer getTotalLength() {
        return movieFacade.getTotalLength().getLength();
    }

}
