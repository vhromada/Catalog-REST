package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;

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
 * A class represents controller for seasons.
 *
 * @author Vladimir Hromada
 */
@Controller("seasonController")
@RequestMapping("/shows/{showId}/seasons")
@CrossOrigin
public class SeasonController extends AbstractCatalogController {

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
    @RequestMapping(value = "/{seasonId}", method = RequestMethod.GET)
    public ResponseEntity<String> getSeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") final Integer seasonId) {
        return getDataResponseEntity(seasonFacade.getSeason(seasonId));
    }

    /**
     * Adds season. Sets new ID and position.
     *
     * @param showId show ID
     * @param season season
     * @return response status
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@PathVariable("showId") final Integer showId, @RequestBody final String season) {
        final Show show = new Show();
        show.setId(showId);

        seasonFacade.add(show, deserialize(season, Season.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates season.
     *
     * @param showId show ID
     * @param season new value of season
     * @return response status
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
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final String season) {
        seasonFacade.update(deserialize(season, Season.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes season.
     *
     * @param showId show ID
     * @param season season
     * @return response status
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final String season) {
        seasonFacade.remove(deserialize(season, Season.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates season.
     *
     * @param showId show ID
     * @param season season
     * @return response status
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final String season) {
        seasonFacade.duplicate(deserialize(season, Season.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves season in list one position up.
     *
     * @param showId show ID
     * @param season season
     * @return response status
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season can't be moved up
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final String season) {
        seasonFacade.moveUp(deserialize(season, Season.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves season in list one position down.
     *
     * @param showId show ID
     * @param season season
     * @return response status
     * @throws IllegalArgumentException if season is null
     *                                  or ID is null
     *                                  or season can't be moved down
     *                                  or season doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final String season) {
        seasonFacade.moveDown(deserialize(season, Season.class));

        return getEmptyResponseEntity();
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
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> findSeasonsByShow(@PathVariable("showId") final Integer showId) {
        final Show show = new Show();
        show.setId(showId);

        return getDataResponseEntity(seasonFacade.findSeasonsByShow(show));
    }

}
