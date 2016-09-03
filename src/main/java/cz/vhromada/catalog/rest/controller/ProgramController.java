package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.facade.to.ProgramTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @Autowired
    @Qualifier("programFacade")
    private ProgramFacade programFacade;

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
     * @throws IllegalArgumentException                              if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException if ID isn't null
     *                                                               or name is null
     *                                                               or name is empty string
     *                                                               or URL to english Wikipedia page about program is null
     *                                                               or URL to czech Wikipedia page about program is null
     *                                                               or count of media isn't positive number
     *                                                               or other data is null
     *                                                               or note is null
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody final String program) {
        programFacade.add(deserialize(program, ProgramTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Updates program.
     *
     * @param program new value of program
     * @return response status
     * @throws IllegalArgumentException                                  if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or name is null
     *                                                                   or name is empty string
     *                                                                   or URL to english Wikipedia page about program is null
     *                                                                   or URL to czech Wikipedia page about program is null
     *                                                                   or count of media isn't positive number
     *                                                                   or other data is null
     *                                                                   or note is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if program doesn't exist in data storage
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody final String program) {
        programFacade.update(deserialize(program, ProgramTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Removes program.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException                                  if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if program doesn't exist in data storage
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Void> remove(@RequestBody final String program) {
        programFacade.remove(deserialize(program, ProgramTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Duplicates program.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException                                  if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if program doesn't exist in data storage
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity<Void> duplicate(@RequestBody final String program) {
        programFacade.duplicate(deserialize(program, ProgramTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves program in list one position up.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException                                  if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or program can't be moved up
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if program doesn't exist in data storage
     */
    @RequestMapping(value = "/moveUp", method = RequestMethod.POST)
    public ResponseEntity<Void> moveUp(@RequestBody final String program) {
        programFacade.moveUp(deserialize(program, ProgramTO.class));

        return getEmptyResponseEntity();
    }

    /**
     * Moves program in list one position down.
     *
     * @param program program
     * @return response status
     * @throws IllegalArgumentException                                  if program is null
     * @throws cz.vhromada.validators.exceptions.ValidationException     if ID is null
     *                                                                   or program can't be moved down
     * @throws cz.vhromada.validators.exceptions.RecordNotFoundException if program doesn't exist in data storage
     */
    @RequestMapping(value = "/moveDown", method = RequestMethod.POST)
    public ResponseEntity<Void> moveDown(@RequestBody final String program) {
        programFacade.moveDown(deserialize(program, ProgramTO.class));

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
