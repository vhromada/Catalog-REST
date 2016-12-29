package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for movies.
 *
 * @author Vladimir Hromada
 */
@Controller("movieController")
@RequestMapping("/movies")
@CrossOrigin
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
     * @return response status
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        movieFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of movies.
     *
     * @return list of movies
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getMovies() {
        return getDataResponseEntity(movieFacade.getMovies());
    }

    /**
     * Returns movie with ID or null if there isn't such movie.
     *
     * @param id ID
     * @return movie with ID or null if there isn't such movie
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getMovie(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(movieFacade.getMovie(id));
    }

    /**
     * Adds movie. Sets new ID and position.
     *
     * @param movie movie
     * @return response status
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String movie) {
        movieFacade.add(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates movie.
     *
     * @param movie new value of movie
     * @return response status
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
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String movie) {
        movieFacade.update(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes movie.
     *
     * @param movie movie
     * @return response status
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String movie) {
        movieFacade.remove(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates movie.
     *
     * @param movie movie
     * @return response status
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String movie) {
        movieFacade.duplicate(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves movie in list one position up.
     *
     * @param movie movie
     * @return response status
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie can't be moved up
     *                                  or movie doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String movie) {
        movieFacade.moveUp(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves movie in list one position down.
     *
     * @param movie movie
     * @return response status
     * @throws IllegalArgumentException if movie is null
     *                                  or ID is null
     *                                  or movie can't be moved down
     *                                  or movie doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String movie) {
        movieFacade.moveDown(deserialize(movie, Movie.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePositions() {
        movieFacade.updatePositions();

        return getEmptyResponseEntity();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @RequestMapping(value = "/totalMedia", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalMediaCount() {
        return getDataResponseEntity(movieFacade.getTotalMediaCount());
    }

    /**
     * Returns total length of all movies.
     *
     * @return total length of all movies
     */
    @RequestMapping(value = "/totalLength", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalLength() {
        return getDataResponseEntity(movieFacade.getTotalLength());
    }

}
