package cz.vhromada.catalog.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.rest.exception.CatalogErrorException;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
public class PictureController extends AbstractCatalogController {

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
    public ResponseEntity<Result<Void>> newData() {
        return processResult(pictureFacade.newData());
    }

    /**
     * Returns list of pictures.
     *
     * @return result with list of pictures
     */
    @GetMapping({ "", "/", "/list" })
    public ResponseEntity<Result<List<Integer>>> getPictures() {
        final Result<List<Picture>> pictures = pictureFacade.getAll();

        final Result<List<Integer>> result;
        if (pictures.getData() != null) {
            result = Result.of(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
        } else {
            result = new Result<>();
        }
        result.addEvents(result.getEvents());

        return processResult(result);
    }

    /**
     * Returns picture with ID.
     *
     * @param id ID
     * @return picture
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPicture(@PathVariable("id") final Integer id) {
        final Result<Picture> result = pictureFacade.get(id);

        if (Status.OK == result.getStatus()) {
            if (result.getData() == null) {
                throw new CatalogErrorException(HttpStatus.NOT_FOUND, Result.error("PICTURE_NOT_EXIST", "Picture doesn't exist."));
            }

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"picture.jpg\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
                .body(new ByteArrayResource(result.getData().getContent()));
        }

        throw new CatalogErrorException(HttpStatus.UNPROCESSABLE_ENTITY, result);
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
    public ResponseEntity<Result<Void>> add(@RequestParam("file") final MultipartFile file) throws IOException {
        final Result<Void> result = new Result<>();
        if (file == null) {
            return processErrorResult(Result.error("FILE_NULL", "File mustn't be null."));
        }
        if (file.isEmpty()) {
            return processErrorResult(Result.error("FILE_EMPTY", "File mustn't be empty."));
        }

        final Picture picture = new Picture();
        picture.setContent(file.getBytes());
        result.addEvents(pictureFacade.add(picture).getEvents());

        return processResult(result, HttpStatus.CREATED);
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
    public ResponseEntity<Result<Void>> remove(@PathVariable("id") final Integer id) {
        return processResult(pictureFacade.remove(newPicture(id)));
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
    public ResponseEntity<Result<Void>> moveUp(@PathVariable("id") final Integer id) {
        return processResult(pictureFacade.moveUp(newPicture(id)));
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
    public ResponseEntity<Result<Void>> moveDown(@PathVariable("id") final Integer id) {
        return processResult(pictureFacade.moveDown(newPicture(id)));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public ResponseEntity<Result<Void>> updatePositions() {
        return processResult(pictureFacade.updatePositions());
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
