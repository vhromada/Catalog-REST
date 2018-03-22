package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * A class represents controller for episodes.
 *
 * @author Vladimir Hromada
 */
@RestController("episodeController")
@RequestMapping("/catalog/shows/{showId}/seasons/{seasonId}/episodes")
public class EpisodeController extends AbstractCatalogController {

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
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param showId    show ID
     * @param seasonId  season ID
     * @param episodeId episode ID
     * @return result with episode or validation errors
     */
    @GetMapping("/{episodeId}")
    public ResponseEntity<Result<Episode>> getEpisode(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @PathVariable("episodeId") final Integer episodeId) {
        return processResult(episodeFacade.get(episodeId));
    }

    /**
     * Adds episode. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>Season ID is null</li>
     * <li>Season doesn't exist in data storage</li>
     * <li>Episode is null</li>
     * <li>Episode ID isn't null</li>
     * <li>Number of episode isn't positive number</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>Length of episode is negative value</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @return result with validation errors
     */
    @PutMapping("/add")
    public ResponseEntity<Result<Void>> add(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        final Season season = new Season();
        season.setId(seasonId);

        return processResult(episodeFacade.add(season, episode), HttpStatus.CREATED);
    }

    /**
     * Updates episode.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Episode is null</li>
     * <li>ID is null</li>
     * <li>Number of episode isn't positive number</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>Length of episode is negative value</li>
     * <li>Note is null</li>
     * <li>Season doesn't exist in data storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  new value of episode
     * @return result with validation errors
     */
    @PostMapping("/update")
    public ResponseEntity<Result<Void>> update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        return processResult(episodeFacade.update(episode));
    }

    /**
     * Removes episode.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Episode is null</li>
     * <li>ID is null</li>
     * <li>Episode doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public ResponseEntity<Result<Void>> remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        return processResult(episodeFacade.remove(episode));
    }

    /**
     * Duplicates episode.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Episode is null</li>
     * <li>ID is null</li>
     * <li>Episode doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public ResponseEntity<Result<Void>> duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        return processResult(episodeFacade.duplicate(episode), HttpStatus.CREATED);
    }

    /**
     * Moves episode in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Episode is null</li>
     * <li>ID is null</li>
     * <li>Season can't be moved up</li>
     * <li>Episode doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public ResponseEntity<Result<Void>> moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        return processResult(episodeFacade.moveUp(episode));
    }

    /**
     * Moves episode in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Episode is null</li>
     * <li>ID is null</li>
     * <li>Season can't be moved down</li>
     * <li>Episode doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @param episode  episode
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public ResponseEntity<Result<Void>> moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") @SuppressWarnings("unused") final Integer seasonId, @RequestBody final Episode episode) {
        return processResult(episodeFacade.moveDown(episode));
    }

    /**
     * Returns list of episodes for specified season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Season doesn't exist in data storage</li>
     * </ul>
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return result with list of episodes or validation error
     */
    @GetMapping({ "", "/", "/list" })
    public ResponseEntity<Result<List<Episode>>> findEpisodesBySeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") final Integer seasonId) {
        final Season season = new Season();
        season.setId(seasonId);

        return processResult(episodeFacade.find(season));
    }

}
