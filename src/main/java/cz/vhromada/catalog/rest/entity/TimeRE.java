package cz.vhromada.catalog.rest.entity;

/**
 * A class represents controller REST entity for time.
 *
 * @author Vladimir Hromada
 */
public class TimeRE {

    private int hours;
    private int minutes;
    private int seconds;

    public TimeRE() {
    }

    public TimeRE(final int hours, final int minutes, final int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(final int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(final int seconds) {
        this.seconds = seconds;
    }

}
