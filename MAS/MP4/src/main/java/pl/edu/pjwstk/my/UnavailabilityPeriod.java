package pl.edu.pjwstk.my;

import java.time.LocalDate;

public class UnavailabilityPeriod {
    private LocalDate fromDate;
    private LocalDate toDate;

    public UnavailabilityPeriod(LocalDate fromDate, LocalDate toDate) {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException("End date must be after or equal to start date");
        }

        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return "Unavailable from " + fromDate + " to " + toDate;
    }
}
