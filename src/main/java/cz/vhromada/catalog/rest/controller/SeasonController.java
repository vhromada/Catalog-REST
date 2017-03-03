package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.result.Result;

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
@RequestMapping("/catalog/shows/{showId}/seasons")
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
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return result with season or validation errors
     */
    @GetMapping("/{seasonId}")
    public Result<Season> getSeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") final Integer seasonId) {
        return seasonFacade.get(seasonId);
    }

    /**
     * Adds season. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>Show ID is null</li>
     * <li>Show doesn't exist in season storage</li>
     * <li>Season is null</li>
     * <li>Season ID isn't null</li>
     * <li>Number of season isn't positive number</li>
     * <li>Starting year isn't between 1940 and current year</li>
     * <li>Ending year isn't between 1940 and current year</li>
     * <li>Starting year is greater than ending year</li>
     * <li>Language is null</li>
     * <li>Subtitles are null</li>
     * <li>Subtitles contain null value</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param showId show ID
     * @param season season
     * @return result with validation errors
     */
    @PutMapping("/add")
    public Result<Void> add(@PathVariable("showId") final Integer showId, @RequestBody final Season season) {
        final Show show = new Show();
        show.setId(showId);

        return seasonFacade.add(show, season);
    }

    /**
     * Updates season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Number of season isn't positive number</li>
     * <li>Starting year isn't between 1940 and current year</li>
     * <li>Ending year isn't between 1940 and current year</li>
     * <li>Starting year is greater than ending year</li>
     * <li>Language is null</li>
     * <li>Subtitles are null</li>
     * <li>Subtitles contain null value</li>
     * <li>Note is null</li>
     * <li>Season doesn't exist in data storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param season new value of season
     * @return result with validation errors
     * @throws IllegalArgumentException if season is null
     */
    @PostMapping("/update")
    public Result<Void> update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return seasonFacade.update(season);
    }

    /**
     * Removes season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Season doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param season season
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return seasonFacade.remove(season);
    }

    /**
     * Duplicates season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Season doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param season season
     * @return result with validation errors
     */
    @RequestMapping("/duplicate")
    public Result<Void> duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return seasonFacade.duplicate(season);
    }

    /**
     * Moves season in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Season can't be moved up</li>
     * <li>Season doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param season season
     * @return result with validation errors
     */
    @RequestMapping("/moveUp")
    public Result<Void> moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return seasonFacade.moveUp(season);
    }

    /**
     * Moves season in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Season can't be moved down</li>
     * <li>Season doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param season season
     * @return result with validation errors
     */
    @RequestMapping("/moveDown")
    public Result<Void> moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return seasonFacade.moveDown(season);
    }

    /**
     * Returns list of seasons for specified show.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Show is null</li>
     * <li>ID is null</li>
     * <li>Show doesn't exist in data storage</li>
     * </ul>
     *
     * @param showId show ID
     * @return result with list of seasons or validation error
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Season>> findSeasonsByShow(@PathVariable("showId") final Integer showId) {
        final Show show = new Show();
        show.setId(showId);

        return seasonFacade.find(show);
    }

}
