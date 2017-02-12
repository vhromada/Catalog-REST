package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.ShowFacade;

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
 * A class represents controller for shows.
 *
 * @author Vladimir Hromada
 */
@Controller("showController")
@RequestMapping("/shows")
@CrossOrigin
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
     */
    @PostMapping("/new")
    public void newData() {
        showFacade.newData();
    }

    /**
     * Returns list of shows.
     *
     * @return list of shows
     */
    @GetMapping({ "", "/", "list" })
    public List<Show> getShows() {
        return showFacade.getShows();
    }

    /**
     * Returns show with ID or null if there isn't such show.
     *
     * @param id ID
     * @return show with ID or null if there isn't such show
     * @throws IllegalArgumentException if ID is null
     */
    @PostMapping("/{id}")
    public Show getShow(@PathVariable("id") final Integer id) {
        return showFacade.getShow(id);
    }

    /**
     * Adds show. Sets new ID and position.
     *
     * @param show show
     * @throws IllegalArgumentException if show is null
     *                                  or ID isn't null
     *                                  or czech name is null
     *                                  or czech name is empty string
     *                                  or original name is null
     *                                  or original name is empty string
     *                                  or URL to ČSFD page about show is null
     *                                  or IMDB code isn't -1 or between 1 and 9999999
     *                                  or URL to english Wikipedia page about show is null
     *                                  or URL to czech Wikipedia page about show is null
     *                                  or path to file with show picture is null
     *                                  or count of seasons is negative number
     *                                  or count of episodes is negative number
     *                                  or total length of seasons is null
     *                                  or total length of seasons is negative number
     *                                  or note is null
     *                                  or genres are null
     *                                  or genres contain null value
     *                                  or genre ID is null
     *                                  or genre name is null
     *                                  or genre name is empty string
     *                                  or genre doesn't exist in data storage
     */
    @PutMapping("/add")
    public void add(@RequestBody final Show show) {
        showFacade.add(show);
    }

    /**
     * Updates show.
     *
     * @param show new value of show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or czech name is null
     *                                  or czech name is empty string
     *                                  or original name is null
     *                                  or original name is empty string
     *                                  or URL to ČSFD page about show is null
     *                                  or IMDB code isn't -1 or between 1 and 9999999
     *                                  or URL to english Wikipedia page about show is null
     *                                  or URL to czech Wikipedia page about show is null
     *                                  or path to file with show picture is null
     *                                  or count of seasons is negative number
     *                                  or count of episodes is negative number
     *                                  or total length of seasons is null
     *                                  or total length of seasons is negative number
     *                                  or note is null
     *                                  or genres are null
     *                                  or genres contain null value
     *                                  or genre ID is null
     *                                  or genre name is null
     *                                  or genre name is empty string
     *                                  or genre name is empty string
     *                                  or show doesn't exist in data storage
     *                                  or genre doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@RequestBody final Show show) {
        showFacade.update(show);
    }

    /**
     * Removes show.
     *
     * @param show show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or show doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Show show) {
        showFacade.remove(show);
    }

    /**
     * Duplicates show.
     *
     * @param show show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or show doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Show show) {
        showFacade.duplicate(show);
    }

    /**
     * Moves show in list one position up.
     *
     * @param show show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or show can't be moved up
     *                                  or show doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Show show) {
        showFacade.moveUp(show);
    }

    /**
     * Moves show in list one position down.
     *
     * @param show show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or show can't be moved down
     *                                  or show doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Show show) {
        showFacade.moveDown(show);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        showFacade.updatePositions();
    }

    /**
     * Returns total length of all shows.
     *
     * @return total length of all shows
     */
    @GetMapping("/totalLength")
    public Integer getTotalLength() {
        return showFacade.getTotalLength().getLength();
    }

    /**
     * Returns count of seasons from all shows.
     *
     * @return count of seasons from all shows
     */
    @GetMapping("/seasonsCount")
    public Integer getSeasonsCount() {
        return showFacade.getSeasonsCount();
    }

    /**
     * Returns count of episodes from all shows.
     *
     * @return count of episodes from all shows
     */
    @GetMapping("/episodesCount")
    public Integer getEpisodesCount() {
        return showFacade.getEpisodesCount();
    }

}
