package cz.vhromada.catalog.rest.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;

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
@RequestMapping("/programs")
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
     */
    @PostMapping("/new")
    public void newData() {
        programFacade.newData();
    }

    /**
     * Returns list of programs.
     *
     * @return list of programs
     */
    @GetMapping({ "", "/", "/list" })
    public List<Program> getPrograms() {
        return programFacade.getPrograms();
    }

    /**
     * Returns program with ID or null if there isn't such program.
     *
     * @param id ID
     * @return program with ID or null if there isn't such program
     * @throws IllegalArgumentException if ID is null
     */
    @GetMapping("/{id}")
    public Program getProgram(@PathVariable("id") final Integer id) {
        return programFacade.getProgram(id);
    }

    /**
     * Adds program. Sets new ID and position.
     *
     * @param program program
     * @throws IllegalArgumentException if program is null
     *                                  or ID isn't null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about program is null
     *                                  or URL to czech Wikipedia page about program is null
     *                                  or count of media isn't positive number
     *                                  or other data is null
     *                                  or note is null
     */
    @PutMapping("/add")
    public void add(@RequestBody final Program program) {
        programFacade.add(program);
    }

    /**
     * Updates program.
     *
     * @param program new value of program
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or name is null
     *                                  or name is empty string
     *                                  or URL to english Wikipedia page about program is null
     *                                  or URL to czech Wikipedia page about program is null
     *                                  or count of media isn't positive number
     *                                  or other data is null
     *                                  or note is null
     *                                  or program doesn't exist in data storage
     */
    @PostMapping("/update")
    public void update(@RequestBody final Program program) {
        programFacade.update(program);
    }

    /**
     * Removes program.
     *
     * @param program program
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program doesn't exist in data storage
     */
    @DeleteMapping("/remove")
    public void remove(@RequestBody final Program program) {
        programFacade.remove(program);
    }

    /**
     * Duplicates program.
     *
     * @param program program
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program doesn't exist in data storage
     */
    @PostMapping("/duplicate")
    public void duplicate(@RequestBody final Program program) {
        programFacade.duplicate(program);
    }

    /**
     * Moves program in list one position up.
     *
     * @param program program
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program can't be moved up
     *                                  or program doesn't exist in data storage
     */
    @PostMapping("/moveUp")
    public void moveUp(@RequestBody final Program program) {
        programFacade.moveUp(program);
    }

    /**
     * Moves program in list one position down.
     *
     * @param program program
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program can't be moved down
     *                                  or program doesn't exist in data storage
     */
    @PostMapping("/moveDown")
    public void moveDown(@RequestBody final Program program) {
        programFacade.moveDown(program);
    }

    /**
     * Updates positions.
     */
    @PostMapping("/updatePositions")
    public void updatePositions() {
        programFacade.updatePositions();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @GetMapping("/totalMedia")
    public Integer getTotalMediaCount() {
        return programFacade.getTotalMediaCount();
    }

}
