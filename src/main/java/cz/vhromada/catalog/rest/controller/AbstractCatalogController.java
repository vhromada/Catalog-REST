package cz.vhromada.catalog.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * An abstract class represents controller for catalog.
 *
 * @author Vladimir Hromada
 */
public abstract class AbstractCatalogController extends JsonController {

    /**
     * Returns response with data as JSON and status.
     *
     * @param data data
     * @param <T>  type of data
     * @return response with data as JSON and status
     */
    protected <T> ResponseEntity<String> getDataResponseEntity(final T data) {
        final String json = serialize(data);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    /**
     * Returns response status.
     *
     * @return response status
     */
    protected static ResponseEntity<Void> getEmptyResponseEntity() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
