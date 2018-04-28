package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;
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
 * A class represents controller for songs.
 *
 * @author Vladimir Hromada
 */
@RestController("songController")
@RequestMapping("/catalog/music/{musicId}/songs")
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
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param musicId music ID
     * @param songId  song ID
     * @return result with song or validation errors
     */
    @GetMapping("/{songId}")
    public ResponseEntity<Result<Song>> getSong(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId,
        @PathVariable("songId") final Integer songId) {
        return processResult(songFacade.get(songId));
    }

    /**
     * Adds song. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>Music ID is null</li>
     * <li>Music doesn't exist in data storage</li>
     * <li>Song is null</li>
     * <li>Song ID isn't null</li>
     * <li>Song position isn't null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>Length of song is negative value</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    song
     * @return result with validation errors
     */
    @PutMapping("/add")
    public ResponseEntity<Result<Void>> add(@PathVariable("musicId") final Integer musicId, @RequestBody final Song song) {
        final Music music = new Music();
        music.setId(musicId);

        return processResult(songFacade.add(music, song), HttpStatus.CREATED);
    }

    /**
     * Updates song.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Song is null</li>
     * <li>ID isn't null</li>
     * <li>Position is null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>Length of song is negative value</li>
     * <li>Note is null</li>
     * <li>Song doesn't exist in data storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    new value of song
     * @return result with validation errors
     */
    @PostMapping("/update")
    public ResponseEntity<Result<Void>> update(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        return processResult(songFacade.update(song));
    }

    /**
     * Removes song.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Song is null</li>
     * <li>ID is null</li>
     * <li>Song doesn't exist in song storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    song
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public ResponseEntity<Result<Void>> remove(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        return processResult(songFacade.remove(song));
    }

    /**
     * Duplicates song.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Song is null</li>
     * <li>ID is null</li>
     * <li>Song doesn't exist in song storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    song
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public ResponseEntity<Result<Void>> duplicate(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        return processResult(songFacade.duplicate(song), HttpStatus.CREATED);
    }

    /**
     * Moves song in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Song is null</li>
     * <li>ID is null</li>
     * <li>Song can't be moved up</li>
     * <li>Song doesn't exist in song storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    song
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public ResponseEntity<Result<Void>> moveUp(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        return processResult(songFacade.moveUp(song));
    }

    /**
     * Moves song in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Song is null</li>
     * <li>ID is null</li>
     * <li>Song can't be moved down</li>
     * <li>Song doesn't exist in song storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @param song    song
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public ResponseEntity<Result<Void>> moveDown(@PathVariable("musicId") @SuppressWarnings("unused") final Integer musicId, @RequestBody final Song song) {
        return processResult(songFacade.moveDown(song));
    }

    /**
     * Returns list of songs for specified music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param musicId music ID
     * @return result with list of songs or validation error
     */
    @GetMapping({ "", "/list" })
    public ResponseEntity<Result<List<Song>>> findSongsByMusic(@PathVariable("musicId") final Integer musicId) {
        final Music music = new Music();
        music.setId(musicId);

        return processResult(songFacade.find(music));
    }

}
