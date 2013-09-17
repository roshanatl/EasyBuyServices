package pl.pjagielski.jersey;

import org.joda.time.DateTime;

public class CurrentDate {

    private DateTime date;

    public CurrentDate() {
    }

    public CurrentDate(DateTime date) {
        this.date = date;
    }

    public DateTime getDate() {
        return date;
    }

}
