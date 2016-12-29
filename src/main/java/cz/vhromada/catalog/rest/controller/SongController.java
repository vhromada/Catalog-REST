package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;

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
 * A class represents controller for songs.
 *
 * @author Vladimir Hromada
 */
@Controller("songController")
@RequestMapping("/music/{musicId}/songs")
@CrossOrigin
public class SongController extends AbstractCatalogController {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Creates a new instance of SongController.
     *
     * @param songFacade facade for songs
     * @throws IllegalArgumentException if facade for songs is null
     */
    @Autowired
    public SongController(final SongFacade songFacade) {
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");

        this.songFacade = songFacade;
    }

    /**
     * Returns song with ID or null if there isn't such song.
     *
     * @param musicId music ID
     * @param songId  song ID
     * @return song with ID or null if there isn't such song
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{songId}", method = RequestMethod.GET)
    public ResponseEntity<String> getSong(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId,
            @PathVariable("songId") final Integer songId) {
        return getDataResponseEntity(songFacade.getSong(songId));
    }

    /**
     * Adds song. Sets new ID and position.
     *
     * @param musicId music ID
     * @param song    song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or music ID is null
     *                                  or song ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of song is negative value
     *                                  or note is null
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@PathVariable("musicId") final Integer musicId, @RequestBody final String song) {
        final Music music = new Music();
        music.setId(musicId);

        songFacade.add(music, deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates song.
     *
     * @param musicId music ID
     * @param song    new value of song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or song ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of song is negative value
     *                                  or note is null
     *                                  or song doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final String song) {
        songFacade.update(deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes song.
     *
     * @param musicId music ID
     * @param song    song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final String song) {
        songFacade.remove(deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates song.
     *
     * @param musicId music ID
     * @param song    song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final String song) {
        songFacade.duplicate(deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves song in list one position up.
     *
     * @param musicId music ID
     * @param song    song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song can't be moved up
     *                                  or song doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final String song) {
        songFacade.moveUp(deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves song in list one position down.
     *
     * @param musicId music ID
     * @param song    song
     * @return response status
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song can't be moved down
     *                                  or song doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final String song) {
        songFacade.moveDown(deserialize(song, Song.class));

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of songs for specified music.
     *
     * @param musicId music ID
     * @return list of songs for specified music
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music doesn't exist in data storage
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> findSongsByMusic(@PathVariable("musicId") final Integer musicId) {
        final Music music = new Music();
        music.setId(musicId);

        return getDataResponseEntity(songFacade.findSongsByMusic(music));
    }

}
