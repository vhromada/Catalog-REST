package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.result.Result;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class represents controller for time.
 *
 * @author Vladimir Hromada
 */
@RestController("timeController")
@RequestMapping("/catalog/time")
@CrossOrigin
public class TimeController {

    /**
     * Returns time.
     * <br>
     * Validation errors:
     * <ul>
     * <li>Time is null</li>
     * <li>Time is negative number</li>
     * </ul>
     *
     * @param time time
     * @return result with time or validation errors
     */
    @GetMapping("/{time}")
    public Result<String> getTime(@PathVariable final Integer time) {
        if (time == null) {
            return Result.error("TIME_NULL", "Time mustn't be null.");
        }
        if (time < 0) {
            return Result.error("TIME_NEGATIVE", "Time mustn't be negative number.");
        }

        return Result.of(new Time(time).toString());
    }

}
