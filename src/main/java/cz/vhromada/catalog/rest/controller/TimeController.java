package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.common.Time;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class represents controller for time.
 *
 * @author Vladimir Hromada
 */
@Controller("timeController")
@RequestMapping("/time")
@CrossOrigin
public class TimeController extends AbstractCatalogController {

    /**
     * Returns time.
     *
     * @param time time
     * @return time
     * @throws IllegalArgumentException if time in seconds is negative number
     */
    @RequestMapping(value = "/{time}", method = RequestMethod.GET)
    public ResponseEntity<String> getTime(@PathVariable final String time) {
        final Time value = new Time(deserialize(time, Integer.class));

        return getDataResponseEntity(value.toString());
    }

}
