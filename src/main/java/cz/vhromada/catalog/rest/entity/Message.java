package cz.vhromada.catalog.rest.entity;

import org.springframework.util.Assert;

/**
 * A class represents message.
 *
 * @author Vladimir Hromada
 */
public class Message {

    /**
     * Key
     */
    private String key;

    /**
     * Value
     */
    private String value;

    /**
     * Creates a new instance of Message.
     *
     * @param key   key
     * @param value value
     * @throws IllegalArgumentException if key is null
     *                                  or value is null
     */
    public Message(final String key, final String value) {
        Assert.notNull(key, "Key mustn't be null.");
        Assert.notNull(value, "Value mustn't be null.");

        this.key = key;
        this.value = value;
    }

    /**
     * Returns key.
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns value.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

}
