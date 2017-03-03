package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.result.Result;

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
 * A class represents controller for programs.
 *
 * @author Vladimir Hromada
 */
@Controller("programController")
@RequestMapping("/catalog/programs")
@CrossOrigin
public class ProgramController {

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
    public Result<Void> newData() {
        return programFacade.newData();
    }

    /**
     * Returns list of programs.
     *
     * @return result with list of programs
     */
    @GetMapping({ "", "/", "/list" })
    public Result<List<Program>> getPrograms() {
        return programFacade.getAll();
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
    public Result<Program> getProgram(@PathVariable("id") final Integer id) {
        return programFacade.get(id);
    }

    /**
     * Adds program. Sets new ID and position.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID isn't null</li>
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
    public Result<Void> add(@RequestBody final Program program) {
        return programFacade.add(program);
    }

    /**
     * Updates program.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Program is null</li>
     * <li>ID is null</li>
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
    public Result<Void> update(@RequestBody final Program program) {
        return programFacade.update(program);
    }

    /**
     * Removes program.
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
    @DeleteMapping("/remove")
    public Result<Void> remove(@RequestBody final Program program) {
        return programFacade.remove(program);
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
    public Result<Void> duplicate(@RequestBody final Program program) {
        return programFacade.duplicate(program);
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
    public Result<Void> moveUp(@RequestBody final Program program) {
        return programFacade.moveUp(program);
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
    public Result<Void> moveDown(@RequestBody final Program program) {
        return programFacade.moveDown(program);
    }

    /**
     * Updates positions.
     *
     * @return result
     */
    @PostMapping("/updatePositions")
    public Result<Void> updatePositions() {
        return programFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return result with total count of media
     */
    @GetMapping("/totalMedia")
    public Result<Integer> getTotalMediaCount() {
        return programFacade.getTotalMediaCount();
    }

}
