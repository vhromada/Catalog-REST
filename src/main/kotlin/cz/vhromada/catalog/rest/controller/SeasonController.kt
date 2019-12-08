package cz.vhromada.catalog.rest.controller

import cz.vhromada.catalog.entity.Season
import cz.vhromada.catalog.entity.Show
import cz.vhromada.catalog.facade.SeasonFacade
import cz.vhromada.common.web.controller.AbstractController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * A class represents controller for seasons.
 *
 * @author Vladimir Hromada
 */
@RestController("seasonController")
@RequestMapping("/catalog/shows/{showId}/seasons")
class SeasonController(private val seasonFacade: SeasonFacade) : AbstractController() {

    /**
     * Returns season with ID or null if there isn't such season.
     *
     * @param showId   show ID
     * @param seasonId season ID
     * @return season with ID or null if there isn't such season
     */
    @GetMapping("/{seasonId}")
    fun getSeason(@PathVariable("showId") showId: Int,
                  @PathVariable("seasonId") seasonId: Int): Season? {
        return processResult(seasonFacade.get(seasonId))
    }

    /**
     * Adds season. Sets new ID and position.
     * <br></br>
     * Validation errors:
     *
     *  * Show ID is null
     *  * Show doesn't exist in season storage
     *  * Season ID isn't null
     *  * Season position isn't null
     *  * Number of season isn't positive number
     *  * Starting year isn't between 1940 and current year
     *  * Ending year isn't between 1940 and current year
     *  * Starting year is greater than ending year
     *  * Language is null
     *  * Subtitles are null
     *  * Subtitles contain null value
     *  * Note is null
     *
     * @param showId show ID
     * @param season season
     */
    @PutMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@PathVariable("showId") showId: Int,
            @RequestBody season: Season) {
        val show = Show(id = showId, czechName = null, originalName = null, csfd = null, imdbCode = null, wikiEn = null, wikiCz = null, picture = null, note = null, position = null, genres = null)
        processResult(seasonFacade.add(show, season))
    }

    /**
     * Updates season.
     * <br></br>
     * Validation errors:
     *
     *  * ID is null
     *  * Position is null
     *  * Number of season isn't positive number
     *  * Starting year isn't between 1940 and current year
     *  * Ending year isn't between 1940 and current year
     *  * Starting year is greater than ending year
     *  * Language is null
     *  * Subtitles are null
     *  * Subtitles contain null value
     *  * Note is null
     *  * Season doesn't exist in data storage
     *
     * @param showId show ID
     * @param season new value of season
     */
    @PostMapping("/update")
    fun update(@PathVariable("showId") showId: Int,
               @RequestBody season: Season) {
        processResult(seasonFacade.update(season))
    }

    /**
     * Removes season.
     * <br></br>
     * Validation errors:
     *
     *  * Season doesn't exist in season storage
     *
     * @param showId show ID
     * @param id     ID
     */
    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable("showId") showId: Int,
               @PathVariable("id") id: Int) {
        val season = Season(id = id, number = null, startYear = null, endYear = null, language = null, subtitles = null, note = null, position = null)
        processResult(seasonFacade.remove(season))
    }

    /**
     * Duplicates season.
     * <br></br>
     * Validation errors:
     *
     *  * ID is null
     *  * Season doesn't exist in season storage
     *
     * @param showId show ID
     * @param season season
     */
    @PostMapping("/duplicate")
    @ResponseStatus(HttpStatus.CREATED)
    fun duplicate(@PathVariable("showId") showId: Int,
                  @RequestBody season: Season) {
        processResult(seasonFacade.duplicate(season))
    }

    /**
     * Moves season in list one position up.
     * <br></br>
     * Validation errors:
     *
     *  * ID is null
     *  * Season can't be moved up
     *  * Season doesn't exist in season storage
     *
     * @param showId show ID
     * @param season season
     */
    @PostMapping("/moveUp")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun moveUp(@PathVariable("showId") showId: Int,
               @RequestBody season: Season) {
        processResult(seasonFacade.moveUp(season))
    }

    /**
     * Moves season in list one position down.
     * <br></br>
     * Validation errors:
     *
     *  * ID is null
     *  * Season can't be moved down
     *  * Season doesn't exist in season storage
     *
     * @param showId show ID
     * @param season season
     */
    @PostMapping("/moveDown")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun moveDown(@PathVariable("showId") showId: Int,
                 @RequestBody season: Season) {
        processResult(seasonFacade.moveDown(season))
    }

    /**
     * Returns list of seasons for specified show.
     * <br></br>
     * Validation errors:
     *
     *  * ID is null
     *  * Show doesn't exist in data storage
     *
     * @param showId show ID
     * @return list of seasons for specified show
     */
    @GetMapping
    fun findSeasonsByShow(@PathVariable("showId") showId: Int): List<Season> {
        val show = Show(id = showId, czechName = null, originalName = null, csfd = null, imdbCode = null, wikiEn = null, wikiCz = null, picture = null, note = null, position = null, genres = null)
        return processResult(seasonFacade.find(show))!!
    }

}
