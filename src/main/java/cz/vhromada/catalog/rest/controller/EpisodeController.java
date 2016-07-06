package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.facade.to.SeasonTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for episodes.
 *
 * @author Vladimir Hromada
 */
@Controller("episodeController")
@RequestMapping("/shows/{showId}/seasons/{seasonId}/episodes")
public class EpisodeController extends JsonController {

    @Autowired
    @Qualifier("episodeFacade")
    private EpisodeFacade episodeFacade;

    /**
     * Returns episode with ID or null if there isn't such episode.
     *
     * @param showId    show ID
     * @param seasonId  season ID
     * @param episodeId episode ID
     * @return episode with ID or null if there isn't such episode
     * @throws IllegalArgumentException if episode ID is null
     */
    @RequestMapping(value = "/{episodeId}", method = RequestMethod.GET)
    public String getEpisode(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @PathVariable("episodeId") final Integer episodeId) {
        return serialize(episodeFacade.getEpisode(episodeId));
    }

    /**
     * Adds episode. Sets new ID and position.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if season ID is null
     *                                                                   or episode ID isn't null
     *                                                                   or number of episode isn't positive number
     *                                                                   or name is null
     *                                                                   or name is empty string
     *                                                                   or length of episode is negative value
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = "/addg", method = RequestMethod.POST)
    public void add(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        final SeasonTO season = new SeasonTO();
        season.setId(seasonId);

        episodeFacade.add(season, deserialize(episode, EpisodeTO.class));
    }

    /**
     * Updates episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  new value of episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if episode ID is null
     *                                                                   or number of episode isn't positive number
     *                                                                   or name is null
     *                                                                   or name is empty string
     *                                                                   or length of episode is negative value
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if episode doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        episodeFacade.update(deserialize(episode, EpisodeTO.class));
    }

    /**
     * Removes episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if episode doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        episodeFacade.remove(deserialize(episode, EpisodeTO.class));
    }

    /**
     * Duplicates episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if episode doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public void duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        episodeFacade.duplicate(deserialize(episode, EpisodeTO.class));
    }

    /**
     * Moves episode in list one position up.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or episode can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if episode doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public void moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        episodeFacade.moveUp(deserialize(episode, EpisodeTO.class));
    }

    /**
     * Moves episode in list one position down.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException                                  if episode is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or episode can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if episode doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public void moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, final String episode) {
        episodeFacade.moveDown(deserialize(episode, EpisodeTO.class));
    }

    /**
     * Returns list of episodes for specified season.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return list of episodes for specified season
     * @throws IllegalArgumentException                                  if season is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if season ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if season doesn't exist in data storage
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public String findEpisodesBySeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") final Integer seasonId) {
        final SeasonTO season = new SeasonTO();
        season.setId(seasonId);

        return serialize(episodeFacade.findEpisodesBySeason(season));
    }

}
