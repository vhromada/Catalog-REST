package cz.vhromada.catalog.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * A class represents controller for pictures.
 *
 * @author Vladimir Hromada
 */
@RestController("pictureController")
@RequestMapping("/catalog/pictures")
@CrossOrigin
public class PictureController {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Creates a new instance of PictureController.
     *
     * @param pictureFacade facade for pictures
     * @throws IllegalArgumentException if facade for pictures is null
     */
    @Autowired
    public PictureController(final PictureFacade pictureFacade) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");

        this.pictureFacade = pictureFacade;
    }

    /**
     * Creates new data.
     *
     * @return result
     */
    @PostMapping("/new")
    public Result<Void> newData() {
        return pictureFacade.newData();
    }

    /**
     * Returns list of pictures.
     *
     * @return result with list of pictures
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Integer>> getPictures() {
        final Result<List<Picture>> pictures = pictureFacade.getAll();

        final Result<List<Integer>> result = new Result<>();
        result.addEvents(result.getEvents());
        if (pictures.getData() != null) {
            result.setData(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
        }

        return result;
    }

    /**
     * Returns picture with ID.
     * <br>
     * Returned HTTP status:
     * <ul>
     * <li>200 - found</li>
     * <li>404 - not found</li>
     * <li>422 - ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return picture
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPicture(@PathVariable("id") final Integer id) {
        final Result<Picture> picture = pictureFacade.get(id);

        if (Status.OK == picture.getStatus()) {
            if (picture.getData() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"picture.jpg\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
                .body(new ByteArrayResource(picture.getData().getContent()));
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    /**
     * Adds picture.
     * <br>
     * Validation errors:
     * <ul>
     * <li>File is null</li>
     * </ul>
     *
     * @param file picture
     * @return result with validation errors
     * @throws IOException if getting picture content fails
     */
    @PutMapping("/add")
    public Result<Void> add(@RequestParam("file") final MultipartFile file) throws IOException {
        final Result<Void> result = new Result<>();
        if (file == null) {
            return Result.error("FILE_NULL", "File mustn't be null.");
        }
        if (file.isEmpty()) {
            return Result.error("FILE_EMPTY", "File mustn't be empty.");
        }

        final Picture picture = new Picture();
        picture.setContent(file.getBytes());
        result.addEvents(pictureFacade.add(picture).getEvents());

        return result;
    }

    /**
     * Removes picture.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Picture doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public Result<Void> remove(@PathVariable("id") final Integer id) {
        return pictureFacade.remove(newPicture(id));
    }

    /**
     * Duplicates picture.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Picture doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @PostMapping("/duplicate/{id}")
    public Result<Void> duplicate(@PathVariable("id") final Integer id) {
        return pictureFacade.duplicate(newPicture(id));
    }

    /**
     * Moves picture in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Picture can't be moved up</li>
     * <li>Picture doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @PostMapping("/moveUp/{id}")
    public Result<Void> moveUp(@PathVariable("id") final Integer id) {
        return pictureFacade.moveUp(newPicture(id));
    }

    /**
     * Moves picture in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Picture can't be moved down</li>
     * <li>Picture doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @PostMapping("/moveDown/{id}")
    public Result<Void> moveDown(@PathVariable("id") final Integer id) {
        return pictureFacade.moveDown(newPicture(id));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return pictureFacade.updatePositions();
    }

    /**
     * Creates a new picture.
     *
     * @param id ID
     * @return new picture
     */
    private static Picture newPicture(final Integer id) {
        final Picture picture = new Picture();
        picture.setId(id);

        return picture;
    }

}
