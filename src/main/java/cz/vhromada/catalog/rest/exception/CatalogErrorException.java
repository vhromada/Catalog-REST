package cz.vhromada.catalog.rest.exception;

import cz.vhromada.result.Result;

import org.springframework.http.HttpStatus;

/**
 * A class represents exception for catalog error.
 *
 * @author Vladimir Hromada
 */
public class CatalogErrorException extends RuntimeException {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * HTTP status
     */
    private final HttpStatus status;

    /**
     * Event
     */
    private final Result<?> result;

    /**
     * Creates a new instance of CatalogErrorException.
     *
     * @param status HTTP status
     * @param result result
     */

    public CatalogErrorException(final HttpStatus status, final Result<?> result) {
        this.status = status;
        this.result = result;
    }

    /**
     * Returns HTTP status.
     *
     * @return HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Result
     */
    public Result<?> getResult() {
        return result;
    }

    @Override
    public String getMessage() {
        return String.format("status=%s, result=%s", status, result);
    }

    @Override
    public String toString() {
        return String.format("CatalogErrorException [status=%s, result=%s]", status, result);
    }

}
