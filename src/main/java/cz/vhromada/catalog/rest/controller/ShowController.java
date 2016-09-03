package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.facade.to.ShowTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for shows.
 *
 * @author Vladimir Hromada
 */
@Controller("showController")
@RequestMapping("/shows")
@CrossOrigin
public class ShowController extends AbstractCatalogController {

    @Autowired
    @Qualifier("showFacade")
    private ShowFacade showFacade;

    /**
     * Creates new data.
     *
     * @return response status
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        showFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of shows.
     *
     * @return list of shows
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getShows() {
        return getDataResponseEntity(showFacade.getShows());
    }

    /**
     * Returns show with ID or null if there isn't such show.
     *
     * @param id ID
     * @return show with ID or null if there isn't such show
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getShow(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(showFacade.getShow(id));
    }

    /**
     * Adds show. Sets new ID and position.
     *
     * @param show show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID isn't null
     *                                                                   or czech name is null
     *                                                                   or czech name is empty string
     *                                                                   or original name is null
     *                                                                   or original name is empty string
     *                                                                   or URL to ČSFD page about show is null
     *                                                                   or IMDB code isn't -1 or between 1 and 9999999
     *                                                                   or URL to english Wikipedia page about show is null
     *                                                                   or URL to czech Wikipedia page about show is null
     *                                                                   or path to file with show picture is null
     *                                                                   or count of seasons is negative number
     *                                                                   or count of episodes is negative number
     *                                                                   or total length of seasons is null
     *                                                                   or total length of seasons is negative number
     *                                                                   or note is null
     *                                                                   or genres are null
     *                                                                   or genres contain null value
     *                                                                   or genre ID is null
     *                                                                   or genre name is null
     *                                                                   or genre name is empty string
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if genre doesn't exist in data storage
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String show) {
        showFacade.add(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates show.
     *
     * @param show new value of show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or czech name is null
     *                                                                   or czech name is empty string
     *                                                                   or original name is null
     *                                                                   or original name is empty string
     *                                                                   or URL to ČSFD page about show is null
     *                                                                   or IMDB code isn't -1 or between 1 and 9999999
     *                                                                   or URL to english Wikipedia page about show is null
     *                                                                   or URL to czech Wikipedia page about show is null
     *                                                                   or path to file with show picture is null
     *                                                                   or count of seasons is negative number
     *                                                                   or count of episodes is negative number
     *                                                                   or total length of seasons is null
     *                                                                   or total length of seasons is negative number
     *                                                                   or note is null
     *                                                                   or genres are null
     *                                                                   or genres contain null value
     *                                                                   or genre ID is null
     *                                                                   or genre name is null
     *                                                                   or genre name is empty string
     *                                                                   or genre name is empty string
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     *                                                                   or genre doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String show) {
        showFacade.update(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes show.
     *
     * @param show show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String show) {
        showFacade.remove(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates show.
     *
     * @param show show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String show) {
        showFacade.duplicate(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves show in list one position up.
     *
     * @param show show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or show can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String show) {
        showFacade.moveUp(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves show in list one position down.
     *
     * @param show show
     * @return response status
     * @throws IllegalArgumentException                                  if show is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or show can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if show doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String show) {
        showFacade.moveDown(deserialize(show, ShowTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePositions() {
        showFacade.updatePositions();

        return getEmptyResponseEntity();
    }

    /**
     * Returns total length of all shows.
     *
     * @return total length of all shows
     */
    @RequestMapping(value = "/totalLength", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalLength() {
        return getDataResponseEntity(showFacade.getTotalLength());
    }

    /**
     * Returns count of seasons from all shows.
     *
     * @return count of seasons from all shows
     */
    @RequestMapping(value = "/seasonsCount", method = RequestMethod.GET)
    public ResponseEntity<String> getSeasonsCount() {
        return getDataResponseEntity(showFacade.getSeasonsCount());
    }

    /**
     * Returns count of episodes from all shows.
     *
     * @return count of episodes from all shows
     */
    @RequestMapping(value = "/episodesCount", method = RequestMethod.GET)
    public ResponseEntity<String> getEpisodesCount() {
        return getDataResponseEntity(showFacade.getEpisodesCount());
    }

}
