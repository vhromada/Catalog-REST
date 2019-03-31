package cz.vhromada.catalog.rest.controller;

import cz.vhromada.validation.result.Result;
import cz.vhromada.validation.result.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * An abstract class represents catalog for controller.
 *
 * @author Vladimir Hromada
 */
public abstract class AbstractCatalogController {

    /**
     * Process result.
     *
     * @param result result
     * @param <T>    type of data
     * @return response with result
     */
    protected static <T> ResponseEntity<Result<T>> processResult(final Result<T> result) {
        return processResult(result, HttpStatus.OK);
    }

    /**
     * Process result.
     *
     * @param result result
     * @param status HTTP status
     * @param <T>    type of data
     * @return response with result
     */
    protected static <T> ResponseEntity<Result<T>> processResult(final Result<T> result, final HttpStatus status) {
        Assert.notNull(result, "Result mustn't be null.");

        if (Status.ERROR == result.getStatus()) {
            return processErrorResult(result);
        }

        return new ResponseEntity<>(result, status);
    }

    /**
     * Process error result.
     *
     * @param result result
     * @param <T>    type of data
     * @return response with result
     */
    protected static <T> ResponseEntity<Result<T>> processErrorResult(final Result<T> result) {
        if (result.getEvents().stream().anyMatch(event -> event.getKey().contains("NOT_EXIST"))) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
