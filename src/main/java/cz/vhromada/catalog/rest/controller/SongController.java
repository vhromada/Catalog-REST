package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A class represents controller for songs.
 *
 * @author Vladimir Hromada
 */
@Controller("songController")
@RequestMapping("/music/{musicId}/songs")
@CrossOrigin
public class SongController {

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
    @GetMapping("/{songId}")
    public Song getSong(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId,
            @PathVariable("songId") final Integer songId) {
        return songFacade.getSong(songId);
    }

    /**
     * Adds song. Sets new ID and position.
     *
     * @param musicId music ID
     * @param song    song
     * @throws IllegalArgumentException if song is null
     *                                  or music ID is null
     *                                  or song ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of song is negative value
     *                                  or note is null
     *                                  or music doesn't exist in data storage
     */
    @PutMapping("/add")
    public void add(@PathVariable("musicId") final Integer musicId, @RequestBody final Song song) {
        final Music music = new Music();
        music.setId(musicId);

        songFacade.add(music, song);
    }

    /**
     * Updates song.
     *
     * @param musicId music ID
     * @param song    new value of song
     * @throws IllegalArgumentException if song is null
     *                                  or song ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or length of song is negative value
     *                                  or note is null
     *                                  or song doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        songFacade.update(song);
    }

    /**
     * Removes song.
     *
     * @param musicId music ID
     * @param song    song
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        songFacade.remove(song);
    }

    /**
     * Duplicates song.
     *
     * @param musicId music ID
     * @param song    song
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        songFacade.duplicate(song);
    }

    /**
     * Moves song in list one position up.
     *
     * @param musicId music ID
     * @param song    song
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song can't be moved up
     *                                  or song doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        songFacade.moveUp(song);
    }

    /**
     * Moves song in list one position down.
     *
     * @param musicId music ID
     * @param song    song
     * @throws IllegalArgumentException if song is null
     *                                  or ID is null
     *                                  or song can't be moved down
     *                                  or song doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        songFacade.moveDown(song);
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
    @GetMapping({ "", "/", "/list" })
    public List<Song> findSongsByMusic(@PathVariable("musicId") final Integer musicId) {
        final Music music = new Music();
        music.setId(musicId);

        return songFacade.findSongsByMusic(music);
    }

}
