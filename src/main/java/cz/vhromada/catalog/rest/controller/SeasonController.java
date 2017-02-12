package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;

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
 * A class represents controller for seasons.
 *
 * @author Vladimir Hromada
 */
@Controller("seasonController")
@RequestMapping("/shows/{showId}/seasons")
@CrossOrigin
public class SeasonController {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Creates a new instance of SeasonController.
     *
     * @param seasonFacade facade for seasons
     * @throws IllegalArgumentException if facade for seasons is null
     */
    @Autowired
    public SeasonController(final SeasonFacade seasonFacade) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");

        this.seasonFacade = seasonFacade;
    }

    /**
     * Returns season with ID or null if there isn't such season.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return season with ID or null if there isn't such season
     * @throws IllegalArgumentException if ID is null
     */
    @GetMapping("/{seasonId}")
    public Season getSeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @PathVariable("seasonId") final Integer seasonId) {
        return seasonFacade.getSeason(seasonId);
    }

    /**
     * Adds season. Sets new ID and position.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException if season is null
     *                                  or show ID is null
     *                                  or season ID isn't null
     *                                  or number of season isn't positive number
     *                                  or starting year isn't between 1940 and current year
     *                                  or ending year isn't between 1940 and current year
     *                                  or starting year is greater than ending year
     *                                  or language is null
     *                                  or subtitles are null
     *                                  or subtitles contain null value
     *                                  or note is null
     *                                  or show doesn't exist in data storage
     */
    @PutMapping("/add")
    public void add(@PathVariable("showId") final Integer showId, @RequestBody final Season season) {
        final Show show = new Show();
        show.setId(showId);

        seasonFacade.add(show, season);
    }

    /**
     * Updates season.
     *
     * @param showId show ID
     * @param season new value of season
     * @throws IllegalArgumentException if season is null
     *                                  or season ID is null
     *                                  or number of season isn't positive number
     *                                  or starting year isn't between 1940 and current year
     *                                  or ending year isn't between 1940 and current year
     *                                  or starting year is greater than ending year
     *                                  or language is null
     *                                  or subtitles are null
     *                                  or subtitles contain null value
     *                                  or note is null
     *                                  or season doesn't exist in data storage
     *                                  or show doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        seasonFacade.update(season);
    }

    /**
     * Removes season.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        seasonFacade.remove(season);
    }

    /**
     * Duplicates season.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping("/duplicate")
    public void duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        seasonFacade.duplicate(season);
    }

    /**
     * Moves season in list one position up.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season can't be moved up
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping("/moveUp")
    public void moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        seasonFacade.moveUp(season);
    }

    /**
     * Moves season in list one position down.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season can't be moved down
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping("/moveDown")
    public void moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        seasonFacade.moveDown(season);
    }

    /**
     * Returns list of seasons for specified show.
     *
     * @param showId show ID
     * @return list of seasons for specified show
     * @throws IllegalArgumentException if show is null
     *                                  or ID is null
     *                                  or show doesn't exist in data storage
     */
    @GetMapping({ "", "/", "/list" })
    public List<Season> findSeasonsByShow(@PathVariable("showId") final Integer showId) {
        final Show show = new Show();
        show.setId(showId);

        return seasonFacade.findSeasonsByShow(show);
    }

}
