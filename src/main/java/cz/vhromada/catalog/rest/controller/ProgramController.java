package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.validation.result.Result;

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
 * A class represents controller for programs.
 *
 * @author Vladimir Hromada
 */
@RestController("programController")
@RequestMapping("/catalog/programs")
public class ProgramController extends AbstractCatalogController {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramController(final ProgramFacade programFacade) {
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");

        this.programFacade = programFacade;
    }

    /**
     * Creates new data.
     *
     * @return result
     */
    @PostMapping("/new")
    public ResponseEntity<Result<Void>> newData() {
        return processResult(programFacade.newData());
    }

    /**
     * Returns list of programs.
     *
     * @return result with list of programs
     */
    @GetMapping
    public ResponseEntity<Result<List<Program>>> getPrograms() {
        return processResult(programFacade.getAll());
    }

    /**
     * Returns program with ID or null if there isn't such program.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * </ul>
     *
     * @param id ID
     * @return result with program or validation errors
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<Program>> getProgram(@PathVariable("id") final Integer id) {
        return processResult(programFacade.get(id));
    }

    /**
     * Adds program. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID isn't null</li>
     * <li>Position isn't null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about program is null</li>
     * <li>URL to czech Wikipedia page about program is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * </ul>
     *
     * @param program program
     * @return result with validation errors
     */
    @PutMapping("/add")
    public ResponseEntity<Result<Void>> add(@RequestBody final Program program) {
        return processResult(programFacade.add(program), HttpStatus.CREATED);
    }

    /**
     * Updates program.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID is null</li>
     * <li>Position is null</li>
     * <li>Name is null</li>
     * <li>Name is empty string</li>
     * <li>URL to english Wikipedia page about program is null</li>
     * <li>URL to czech Wikipedia page about program is null</li>
     * <li>Count of media isn't positive number</li>
     * <li>Other data is null</li>
     * <li>Note is null</li>
     * <li>Program doesn't exist in data storage</li>
     * </ul>
     *
     * @param program new value of program
     * @return result with validation errors
     */
    @PostMapping("/update")
    public ResponseEntity<Result<Void>> update(@RequestBody final Program program) {
        return processResult(programFacade.update(program));
    }

    /**
     * Removes program.
     * <br>
     * Validation errors:
     * <ul>
     * <li>ID is null</li>
     * <li>Program doesn't exist in data storage</li>
     * </ul>
     *
     * @param id ID
     * @return result with validation errors
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Result<Void>> remove(@PathVariable("id") final Integer id) {
        final Program program = new Program();
        program.setId(id);
        return processResult(programFacade.remove(program));
    }

    /**
     * Duplicates program.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID is null</li>
     * <li>Program doesn't exist in data storage</li>
     * </ul>
     *
     * @param program program
     * @return result with validation errors
     */
    @PostMapping("/duplicate")
    public ResponseEntity<Result<Void>> duplicate(@RequestBody final Program program) {
        return processResult(programFacade.duplicate(program), HttpStatus.CREATED);
    }

    /**
     * Moves program in list one position up.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID is null</li>
     * <li>Program can't be moved up</li>
     * <li>Program doesn't exist in data storage</li>
     * </ul>
     *
     * @param program program
     * @return result with validation errors
     */
    @PostMapping("/moveUp")
    public ResponseEntity<Result<Void>> moveUp(@RequestBody final Program program) {
        return processResult(programFacade.moveUp(program));
    }

    /**
     * Moves program in list one position down.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID is null</li>
     * <li>Program can't be moved down</li>
     * <li>Program doesn't exist in data storage</li>
     * </ul>
     *
     * @param program program
     * @return result with validation errors
     */
    @PostMapping("/moveDown")
    public ResponseEntity<Result<Void>> moveDown(@RequestBody final Program program) {
        return processResult(programFacade.moveDown(program));
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public ResponseEntity<Result<Void>> updatePositions() {
        return processResult(programFacade.updatePositions());
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public ResponseEntity<Result<Integer>> getTotalMediaCount() {
        return processResult(programFacade.getTotalMediaCount());
    }

}
