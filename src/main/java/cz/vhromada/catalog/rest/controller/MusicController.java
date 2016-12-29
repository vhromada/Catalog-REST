package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;

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
 * A class represents controller for music.
 *
 * @author Vladimir Hromada
 */
@Controller("musicController")
@RequestMapping("/music")
@CrossOrigin
public class MusicController extends AbstractCatalogController {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Creates a new instance of MusicController.
     *
     * @param musicFacade facade for music
     * @throws IllegalArgumentException if facade for music is null
     */
    @Autowired
    public MusicController(final MusicFacade musicFacade) {
        Assert.notNull(musicFacade, "Facade for music mustn't be null.");

        this.musicFacade = musicFacade;
    }

    /**
     * Creates new data.
     *
     * @return response status
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        musicFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of music.
     *
     * @return list of music
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getMusic() {
        return getDataResponseEntity(musicFacade.getMusic());
    }

    /**
     * Returns music with ID or null if there isn't such music.
     *
     * @param id ID
     * @return music with ID or null if there isn't such music
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getMusic(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(musicFacade.getMusic(id));
    }

    /**
     * Adds music. Sets new ID and position.
     *
     * @param music music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about music is null
     *                                  or URL to czech Wikipedia page about music is null
     *                                  or count of media isn't positive number
     *                                  or note is null
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String music) {
        musicFacade.add(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates music.
     *
     * @param music new value of music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about music is null
     *                                  or URL to czech Wikipedia page about music is null
     *                                  or count of media isn't positive number
     *                                  or note is null
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String music) {
        musicFacade.update(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes music.
     *
     * @param music music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String music) {
        musicFacade.remove(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates music.
     *
     * @param music music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String music) {
        musicFacade.duplicate(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves music in list one position up.
     *
     * @param music music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music can't be moved up
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String music) {
        musicFacade.moveUp(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves music in list one position down.
     *
     * @param music music
     * @return response status
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music can't be moved down
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String music) {
        musicFacade.moveDown(deserialize(music, Music.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePositions() {
        musicFacade.updatePositions();

        return getEmptyResponseEntity();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @RequestMapping(value = "/totalMedia", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalMediaCount() {
        return getDataResponseEntity(musicFacade.getTotalMediaCount());
    }

    /**
     * Returns total length of all music.
     *
     * @return total length of all music
     */
    @RequestMapping(value = "/totalLength", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalLength() {
        return getDataResponseEntity(musicFacade.getTotalLength().getLength());
    }

    /**
     * Returns count of songs from all music.
     *
     * @return count of songs from all music
     */
    @RequestMapping(value = "/songsCount", method = RequestMethod.GET)
    public ResponseEntity<String> getSongsCount() {
        return getDataResponseEntity(musicFacade.getSongsCount());
    }

}
