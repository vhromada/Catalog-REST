package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

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
@RequestMapping("/catalog/music")
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
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        return musicFacade.newData();
    }

    /**
     * Returns list of music.
     *
     * @return result with list of music
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Music>> getMusic() {
        return musicFacade.getAll();
    }

    /**
     * Returns music with ID or null if there isn't such music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with music or validation errors
     */
    @GetMapping("/{id}")
    public Result<Music> getMusic(@PathVariable("id") final Integer id) {
        return musicFacade.get(id);
    }

    /**
     * Adds music. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID isn't null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about music is null</li>
     * <li>URL to czech Wikipedia page about music is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param music music
     * @return result with validation errors
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestBody final Music music) {
        return musicFacade.add(music);
    }

    /**
     * Updates music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about music is null</li>
     * <li>URL to czech Wikipedia page about music is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param music new value of music
     * @return result with validation errors
     */
    @PostMapping("/update")
    public Result<Void> update(@RequestBody final Music music) {
        return musicFacade.update(music);
    }

    /**
     * Removes music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param music music
     * @return result with validation errors
     */
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Music music) {
        return musicFacade.remove(music);
    }

    /**
     * Duplicates music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param music music
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public Result<Void> duplicate(@RequestBody final Music music) {
        return musicFacade.duplicate(music);
    }

    /**
     * Moves music in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Music can't be moved up</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param music music
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public Result<Void> moveUp(@RequestBody final Music music) {
        return musicFacade.moveUp(music);
    }

    /**
     * Moves music in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Music can't be moved down</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param music music
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public Result<Void> moveDown(@RequestBody final Music music) {
        return musicFacade.moveDown(music);
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return musicFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public Result<Integer> getTotalMediaCount() {
        return musicFacade.getTotalMediaCount();
    }

    /**
     * Returns total length of all music.
     *
     * @return result with total length of all music
     */
    @GetMapping("/totalLength")
    public Result<Integer> getTotalLength() {
        final Result<Time> lengthResult = musicFacade.getTotalLength();

        if (Status.OK == lengthResult.getStatus()) {
            return Result.of(lengthResult.getData().getLength());
        }

        final Result<Integer> result = new Result<>();
        result.addEvents(lengthResult.getEvents());

        return result;
    }

    /**
     * Returns count of songs from all music.
     *
     * @return result with count of songs from all music
     */
    @GetMapping("/songsCount")
    public Result<Integer> getSongsCount() {
        return musicFacade.getSongsCount();
    }

}
