package cz.vhromada.catalog.rest.controller;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.result.Result;

import org.springframework.http.ResponseEntity;
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
public class TimeController extends AbstractCatalogController {

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
    public ResponseEntity<Result<String>> getTime(@PathVariable final Integer time) {
        if (time == null) {
            return processErrorResult(Result.error("TIME_NULL", "Time mustn't be null."));
        }
        if (time < 0) {
            return processErrorResult(Result.error("TIME_NEGATIVE", "Time mustn't be negative number."));
        }

        return processResult(Result.of(new Time(time).toString()));
    }

}
