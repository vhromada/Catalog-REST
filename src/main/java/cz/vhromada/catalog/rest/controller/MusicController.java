package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.common.Time;
import cz.vhromada.validation.result.Result;
import cz.vhromada.validation.result.Status;

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
 * A class represents controller for music.
 *
 * @author Vladimir Hromada
 */
@RestController("musicController")
@RequestMapping("/catalog/music")
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
     * @return result
     */
    @PostMapping("/new")
    public ResponseEntity<Result<Void>> newData() {
        return processResult(musicFacade.newData());
    }

    /**
     * Returns list of music.
     *
     * @return result with list of music
     */
    @GetMapping
    public ResponseEntity<Result<List<Music>>> getMusic() {
        return processResult(musicFacade.getAll());
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
    public ResponseEntity<Result<Music>> getMusic(@PathVariable("id") final Integer id) {
        return processResult(musicFacade.get(id));
    }

    /**
     * Adds music. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID isn't null</li>
     * <li>Position isn't null</li>
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
    public ResponseEntity<Result<Void>> add(@RequestBody final Music music) {
        return processResult(musicFacade.add(music), HttpStatus.CREATED);
    }

    /**
     * Updates music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Music is null</li>
     * <li>ID is null</li>
     * <li>Position is null</li>
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
    public ResponseEntity<Result<Void>> update(@RequestBody final Music music) {
        return processResult(musicFacade.update(music));
    }

    /**
     * Removes music.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Music doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Result<Void>> remove(@PathVariable("id") final Integer id) {
        final Music music = new Music();
        music.setId(id);
        return processResult(musicFacade.remove(music));
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
    public ResponseEntity<Result<Void>> duplicate(@RequestBody final Music music) {
        return processResult(musicFacade.duplicate(music), HttpStatus.CREATED);
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
    public ResponseEntity<Result<Void>> moveUp(@RequestBody final Music music) {
        return processResult(musicFacade.moveUp(music));
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
    public ResponseEntity<Result<Void>> moveDown(@RequestBody final Music music) {
        return processResult(musicFacade.moveDown(music));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public ResponseEntity<Result<Void>> updatePositions() {
        return processResult(musicFacade.updatePositions());
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public ResponseEntity<Result<Integer>> getTotalMediaCount() {
        return processResult(musicFacade.getTotalMediaCount());
    }

    /**
     * Returns total length of all music.
     *
     * @return result with total length of all music
     */
    @GetMapping("/totalLength")
    @SuppressWarnings("Duplicates")
    public ResponseEntity<Result<Integer>> getTotalLength() {
        final Result<Time> lengthResult = musicFacade.getTotalLength();

        if (Status.OK == lengthResult.getStatus()) {
            return processResult(Result.of(lengthResult.getData().getLength()));
        }

        final Result<Integer> result = new Result<>();
        result.addEvents(lengthResult.getEvents());

        return processResult(result);
    }

    /**
     * Returns count of songs from all music.
     *
     * @return result with count of songs from all music
     */
    @GetMapping("/songsCount")
    public ResponseEntity<Result<Integer>> getSongsCount() {
        return processResult(musicFacade.getSongsCount());
    }

}
