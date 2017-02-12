package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.common.Time;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A class represents controller for time.
 *
 * @author Vladimir Hromada
 */
@Controller("timeController")
@RequestMapping("/time")
@CrossOrigin
public class TimeController {

    /**
     * Returns time.
     *
     * @param time time
     * @return time
     * @throws IllegalArgumentException if time in seconds is negative number
     */
    @GetMapping("/{time}")
    public String getTime(@PathVariable final Integer time) {
        return new Time(time).toString();
    }

}
