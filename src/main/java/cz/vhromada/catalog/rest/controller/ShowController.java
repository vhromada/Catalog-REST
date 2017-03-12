package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

import org.springframework.beans.factory.annotation.Autowired;
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
 * A class represents controller for shows.
 *
 * @author Vladimir Hromada
 */
@RestController("showController")
@RequestMapping("/catalog/shows")
public class ShowController {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Creates a new instance of ShowController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public ShowController(final ShowFacade showFacade) {
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");

        this.showFacade = showFacade;
    }

    /**
     * Creates new data.
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        return showFacade.newData();
    }

    /**
     * Returns list of shows.
     *
     * @return result with list of shows
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Show>> getShows() {
        return showFacade.getAll();
    }

    /**
     * Returns show with ID or null if there isn't such show.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with show or validation errors
     */
    @GetMapping("/{id}")
    public Result<Show> getShow(@PathVariable("id") final Integer id) {
        return showFacade.get(id);
    }

    /**
     * Adds show. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID isn't null</li>
     * <li>Czech name is null</li>
     * <li>Czech name is empty string</li>
     * <li>Original name is null</li>
     * <li>Original name is empty string</li>
     * <li>URL to ČSFD page about show is null</li>
     * <li>IMDB code isn't -1 or between 1 and 9999999</li>
     * <li>URL to english Wikipedia page about show is null</li>
     * <li>URL to czech Wikipedia page about show is null</li>
     * <li>Path to file with show's picture is null</li>
     * <li>Note is null</li>
     * <li>Genres are null</li>
     * <li>Genres contain null value</li>
     * <li>Genre ID is null</li>
     * <li>Genre name is null</li>
     * <li>Genre name is empty string</li>
     * <li>Genre doesn't exist</li>
     * </ul>
     *
     * @param show show
     * @return result with validation errors
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestBody final Show show) {
        return showFacade.add(show);
    }

    /**
     * Updates show.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Czech name is null</li>
     * <li>Czech name is empty string</li>
     * <li>Original name is null</li>
     * <li>Original name is empty string</li>
     * <li>URL to ČSFD page about show is null</li>
     * <li>IMDB code isn't -1 or between 1 and 9999999</li>
     * <li>URL to english Wikipedia page about show is null</li>
     * <li>URL to czech Wikipedia page about show is null</li>
     * <li>Path to file with show's picture is null</li>
     * <li>Note is null</li>
     * <li>Genres are null</li>
     * <li>Genres contain null value</li>
     * <li>Genre ID is null</li>
     * <li>Genre name is null</li>
     * <li>Genre name is empty string</li>
     * <li>Genre doesn't exist</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param show new value of show
     * @return result with validation errors
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody final Show show) {
        return showFacade.update(show);
    }

    /**
     * Removes show.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param show show
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Show show) {
        return showFacade.remove(show);
    }

    /**
     * Duplicates show.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param show show
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public Result<Void> duplicate(@RequestBody final Show show) {
        return showFacade.duplicate(show);
    }

    /**
     * Moves show in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Show can't be moved up</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param show show
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public Result<Void> moveUp(@RequestBody final Show show) {
        return showFacade.moveUp(show);
    }

    /**
     * Moves show in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Show can't be moved down</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param show show
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public Result<Void> moveDown(@RequestBody final Show show) {
        return showFacade.moveDown(show);
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return showFacade.updatePositions();
    }

    /**
     * Returns total length of all shows.
     *
     * @return result with total length of all shows
     */
    @GetMapping("/totalLength")
    public Result<Integer> getTotalLength() {
        final Result<Time> lengthResult = showFacade.getTotalLength();

        if (Status.OK == lengthResult.getStatus()) {
            return Result.of(lengthResult.getData().getLength());
        }

        final Result<Integer> result = new Result<>();
        result.addEvents(lengthResult.getEvents());

        return result;
    }

    /**
     * Returns count of seasons from all shows.
     *
     * @return result with count of seasons from all shows
     */
    @GetMapping("/seasonsCount")
    public Result<Integer> getSeasonsCount() {
        return showFacade.getSeasonsCount();
    }

    /**
     * Returns count of episodes from all shows.
     *
     * @return result with count of episodes from all shows
     */
    @GetMapping("/episodesCount")
    public Result<Integer> getEpisodesCount() {
        return showFacade.getEpisodesCount();
    }

}
