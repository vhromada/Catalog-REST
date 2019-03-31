package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.validation.result.Result;

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
 * A class represents controller for seasons.
 *
 * @author Vladimir Hromada
 */
@RestController("seasonController")
@RequestMapping("/catalog/shows/{showId}/seasons")
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
    public ResponseEntity<Result<Season>> getSeason(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId,
        @PathVariable("seasonId") final Integer seasonId) {
        return processResult(seasonFacade.get(seasonId));
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
     * <li>Season position isn't null</li>
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
    public ResponseEntity<Result<Void>> add(@PathVariable("showId") final Integer showId, @RequestBody final Season season) {
        final Show show = new Show();
        show.setId(showId);

        return processResult(seasonFacade.add(show, season), HttpStatus.CREATED);
    }

    /**
     * Updates season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Season is null</li>
     * <li>ID is null</li>
     * <li>Position is null</li>
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
    public ResponseEntity<Result<Void>> update(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return processResult(seasonFacade.update(season));
    }

    /**
     * Removes season.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Season doesn't exist in season storage</li>
     * </ul>
     *
     * @param showId show ID
     * @param id     ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Result<Void>> remove(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @PathVariable("id") final Integer id) {
        final Season season = new Season();
        season.setId(id);
        return processResult(seasonFacade.remove(season));
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
    @PostMapping("/duplicate")
    public ResponseEntity<Result<Void>> duplicate(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return processResult(seasonFacade.duplicate(season), HttpStatus.CREATED);
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
    @PostMapping("/moveUp")
    public ResponseEntity<Result<Void>> moveUp(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return processResult(seasonFacade.moveUp(season));
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
    @PostMapping("/moveDown")
    public ResponseEntity<Result<Void>> moveDown(@PathVariable("showId") @SuppressWarnings("unused") final Integer showId, @RequestBody final Season season) {
        return processResult(seasonFacade.moveDown(season));
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
    @GetMapping
    public ResponseEntity<Result<List<Season>>> findSeasonsByShow(@PathVariable("showId") final Integer showId) {
        final Show show = new Show();
        show.setId(showId);

        return processResult(seasonFacade.find(show));
    }

}
