package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for programs.
 *
 * @author Vladimir Hromada
 */
@Controller("programController")
@RequestMapping("/programs")
@CrossOrigin
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
     * @return response status
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> newData() {
        programFacade.newData();

        return getEmptyResponseEntity();
    }

    /**
     * Returns list of programs.
     *
     * @return list of programs
     */
    @RequestMapping(value = { "", "/", "list" }, method = RequestMethod.GET)
    public ResponseEntity<String> getPrograms() {
        return getDataResponseEntity(programFacade.getPrograms());
    }

    /**
     * Returns program with ID or null if there isn't such program.
     *
     * @param id ID
     * @return program with ID or null if there isn't such program
     * @throws IllegalArgumentException if ID is null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getProgram(@PathVariable("id") final Integer id) {
        return getDataResponseEntity(programFacade.getProgram(id));
    }

    /**
     * Adds program. Sets new ID and position.
     *
     * @param program program
     * @return response status
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String program) {
        programFacade.add(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates program.
     *
     * @param program new value of program
     * @return response status
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
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String program) {
        programFacade.update(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes program.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String program) {
        programFacade.remove(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates program.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String program) {
        programFacade.duplicate(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves program in list one position up.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program can't be moved up
     *                                  or program doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String program) {
        programFacade.moveUp(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves program in list one position down.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException if program is null
     *                                  or ID is null
     *                                  or program can't be moved down
     *                                  or program doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String program) {
        programFacade.moveDown(deserialize(program, Program.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates positions.
     *
     * @return response status
     */
    @RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePositions() {
        programFacade.updatePositions();

        return getEmptyResponseEntity();
    }

    /**
     * Returns total count of media.
     *
     * @return total count of media
     */
    @RequestMapping(value = "/totalMedia", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalMediaCount() {
        return getDataResponseEntity(programFacade.getTotalMediaCount());
    }

}
