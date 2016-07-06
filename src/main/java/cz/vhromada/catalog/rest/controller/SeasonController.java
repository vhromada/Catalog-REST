package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.facade.to.ShowTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for seasons.
 *
 * @author Vladimir Hromada
 */
@Controller("seasonController")
@RequestMapping("/shows/{showId}/seasons")
public class SeasonController extends JsonController {

    @Autowired
    @Qualifier("seasonFacade")
    private SeasonFacade seasonFacade;

    /**
     * Returns season with ID or null if there isn't such season.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return season with ID or null if there isn't such season
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{seasonId}", method = RequestMethod.GET)
    public String getSeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @PathVariable("seasonId") final Integer seasonId) {
        return serialize(seasonFacade.getSeason(seasonId));
    }

    /**
     * Adds season. Sets new ID and position.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if show ID is null
     *                                                                   or season ID isn't null
     *                                                                   or number of season isn't positive number
     *                                                                   or starting year isn't between 1940 and current year
     *                                                                   or ending year isn't between 1940 and current year
     *                                                                   or starting year is greater than ending year
     *                                                                   or language is null
     *                                                                   or subtitles are null
     *                                                                   or subtitles contain null value
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@PathVariable("showId") final Integer showId, final String season) {
        final ShowTO show = new ShowTO();
        show.setId(showId);

        seasonFacade.add(show, deserialize(season, SeasonTO.class));
    }

    /**
     * Updates season.
     *
     * @param showId show ID
     * @param season new value of season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if season ID is null
     *                                                                   or number of season isn't positive number
     *                                                                   or starting year isn't between 1940 and current year
     *                                                                   or ending year isn't between 1940 and current year
     *                                                                   or starting year is greater than ending year
     *                                                                   or language is null
     *                                                                   or subtitles are null
     *                                                                   or subtitles contain null value
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     *                                                                   or show doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, final String season) {
        seasonFacade.update(deserialize(season, SeasonTO.class));
    }

    /**
     * Removes season.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, final String season) {
        seasonFacade.remove(deserialize(season, SeasonTO.class));
    }

    /**
     * Duplicates season.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public void duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, final String season) {
        seasonFacade.duplicate(deserialize(season, SeasonTO.class));
    }

    /**
     * Moves season in list one position up.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or season can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public void moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, final String season) {
        seasonFacade.moveUp(deserialize(season, SeasonTO.class));
    }

    /**
     * Moves season in list one position down.
     *
     * @param showId show ID
     * @param season season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or season can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public void moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, final String season) {
        seasonFacade.moveDown(deserialize(season, SeasonTO.class));
    }

    /**
     * Returns list of seasons for specified show.
     *
     * @param showId show ID
     * @return list of seasons for specified show
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public String findSeasonsByShow(@PathVariable("showId") final Integer showId) {
        final ShowTO show = new ShowTO();
        show.setId(showId);

        return serialize(seasonFacade.findSeasonsByShow(show));
    }

}
