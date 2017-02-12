package cz.vhromada.catalog.rest.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A class represents result
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class Result<T> {

    /**
     * Status
     */
    private Status status;

    /**
     * Data
     */
    private T data;

    /**
     * Messages
     */
    private List<Message> messages;

    /**
     * Creates a new instance of Result.
     */
    public Result() {
        this.status = Status.OK;
        this.messages = new ArrayList<>();
    }

    /**
     * Returns status.
     *
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns data.
     *
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * Returns messages.
     *
     * @return messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Adds error message.
     *
     * @param key     error key
     * @param message error message
     * @throws IllegalArgumentException if key is null
     *                                  or value is null
     */
    public void addErrorMessage(final String key, final String message) {
        this.status = Status.ERROR;
        this.messages.add(new Message(key, message));
    }

    /**
     * Returns result with specified data.
     *
     * @param data data
     * @param <T>  type of data
     * @return result with specified data
     */
    public static <T> Result<T> of(final T data) {
        final Result<T> result = new Result<>();
        result.data = data;

        return result;
    }

    /**
     * Returns result with specified error message.
     *
     * @param key     error key
     * @param message error message
     * @param <T>     type of data
     * @return result with specified error message
     * @throws IllegalArgumentException if key is null
     *                                  or value is null
     */
    public static <T> Result<T> of(final String key, final String message) {
        final Result<T> result = new Result<>();
        result.addErrorMessage(key, message);

        return result;
    }

}
