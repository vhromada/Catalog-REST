package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;

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
 * A class represents controller for music.
 *
 * @author Vladimir Hromada
 */
@Controller("musicController")
@RequestMapping("/music")
@CrossOrigin
public class MusicController {

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
     */
    @PostMapping("/new")
    public void newData() {
        musicFacade.newData();
    }

    /**
     * Returns list of music.
     *
     * @return list of music
     */
    @GetMapping({ "", "/", "list" })
    public List<Music> getMusic() {
        return musicFacade.getMusic();
    }

    /**
     * Returns music with ID or null if there isn't such music.
     *
     * @param id ID
     * @return music with ID or null if there isn't such music
     * @throws IllegalArgumentException if ID is null
     */
    @GetMapping("/{id}")
    public Music getMusic(@PathVariable("id") final Integer id) {
        return musicFacade.getMusic(id);
    }

    /**
     * Adds music. Sets new ID and position.
     *
     * @param music music
     * @throws IllegalArgumentException if music is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about music is null
     *                                  or URL to czech Wikipedia page about music is null
     *                                  or count of media isn't positive number
     *                                  or note is null
     */
    @PutMapping
    public void add(@RequestBody final Music music) {
        musicFacade.add(music);
    }

    /**
     * Updates music.
     *
     * @param music new value of music
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
    @PostMapping("/update")
    public void update(@RequestBody final Music music) {
        musicFacade.update(music);
    }

    /**
     * Removes music.
     *
     * @param music music
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Music music) {
        musicFacade.remove(music);
    }

    /**
     * Duplicates music.
     *
     * @param music music
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Music music) {
        musicFacade.duplicate(music);
    }

    /**
     * Moves music in list one position up.
     *
     * @param music music
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music can't be moved up
     *                                  or music doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Music music) {
        musicFacade.moveUp(music);
    }

    /**
     * Moves music in list one position down.
     *
     * @param music music
     * @throws IllegalArgumentException if music is null
     *                                  or ID is null
     *                                  or music can't be moved down
     *                                  or music doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Music music) {
        musicFacade.moveDown(music);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        musicFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @GetMapping("/totalMedia")
    public Integer getTotalMediaCount() {
        return musicFacade.getTotalMediaCount();
    }

    /**
     * Returns total length of all music.
     *
     * @return total length of all music
     */
    @GetMapping("/totalLength")
    public Integer getTotalLength() {
        return musicFacade.getTotalLength().getLength();
    }

    /**
     * Returns count of songs from all music.
     *
     * @return count of songs from all music
     */
    @GetMapping("/songsCount")
    public Integer getSongsCount() {
        return musicFacade.getSongsCount();
    }

}
