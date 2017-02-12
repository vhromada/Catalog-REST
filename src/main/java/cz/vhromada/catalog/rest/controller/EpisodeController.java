package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.EpisodeFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for episodes.
 *
 * @author Vladimir Hromada
 */
@RestController("episodeController")
@RequestMapping("/shows/{showId}/seasons/{seasonId}/episodes")
@CrossOrigin
public class EpisodeController {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of EpisodeController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public EpisodeController(final EpisodeFacade episodeFacade) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.episodeFacade = episodeFacade;
    }

    /**
     * Returns episode with ID or null if there isn't such episode.
     *
     * @param showId    show ID
     * @param seasonId  season ID
     * @param episodeId episode ID
     * @return episode with ID or null if there isn't such episode
     * @throws IllegalArgumentException if episode ID is null
     */
    @GetMapping("/{episodeId}")
    public Episode getEpisode(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @PathVariable("episodeId") final Integer episodeId) {
        return episodeFacade.getEpisode(episodeId);
    }

    /**
     * Adds episode. Sets new ID and position.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException if episode is null
     *                                  or season ID is null
     *                                  or episode ID isn't null
     *                                  or number of episode isn't positive number
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of episode is negative value
     *                                  or note is null
     *                                  or season doesn't exist in data storage
     */
    @PutMapping("/add")
    public void add(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        final Season season = new Season();
        season.setId(seasonId);

        episodeFacade.add(season, episode);
    }

    /**
     * Updates episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  new value of episode
     * @throws IllegalArgumentException if episode is null
     *                                  or episode ID is null
     *                                  or number of episode isn't positive number
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of episode is negative value
     *                                  or note is null
     *                                  or episode doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        episodeFacade.update(episode);
    }

    /**
     * Removes episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException if episode is null
     *                                  or ID is null
     *                                  or episode doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        episodeFacade.remove(episode);
    }

    /**
     * Duplicates episode.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException if episode is null
     *                                  or ID is null
     *                                  or episode doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        episodeFacade.duplicate(episode);
    }

    /**
     * Moves episode in list one position up.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException if episode is null
     *                                  or ID is null
     *                                  or episode can't be moved up
     *                                  or episode doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        episodeFacade.moveUp(episode);
    }

    /**
     * Moves episode in list one position down.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @throws IllegalArgumentException if episode is null
     *                                  or ID is null
     *                                  or episode can't be moved down
     *                                  or episode doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        episodeFacade.moveDown(episode);
    }

    /**
     * Returns list of episodes for specified season.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return list of episodes for specified season
     * @throws IllegalArgumentException if season is null
     *                                  or season ID is null
     *                                  or season doesn't exist in data storage
     */
    @GetMapping({ "", "/", "/list" })
    public List<Episode> findEpisodesBySeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
            @PathVariable("seasonId") final Integer seasonId) {
        final Season season = new Season();
        season.setId(seasonId);

        return episodeFacade.findEpisodesBySeason(season);
    }

}
