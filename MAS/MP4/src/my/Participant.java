package my;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {
    private String name;
    private String email;
    private List<Event> events = new ArrayList<>();
    private List<UnavailabilityPeriod> unavailabilityPeriods = new ArrayList<>();

    public Participant(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Participant name cannot be empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Participant email cannot be empty");
        }
        this.email = email;
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        // Custom business constraint: participant must be available for event dates
        if (!isAvailableFor(event)) {
            throw new IllegalArgumentException(
                    "Participant is not available for event " + event.getName() + " date range");
        }

        if (!events.contains(event)) {
            events.add(event);
            event.addParticipant(this);
        }
    }

    public void removeEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        if (events.remove(event)) {
            event.removeParticipant(this);
        }
    }

    public List<UnavailabilityPeriod> getUnavailabilityPeriods() {
        return Collections.unmodifiableList(unavailabilityPeriods);
    }

    public void addUnavailabilityPeriod(LocalDate fromDate, LocalDate toDate) {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        // Validate dates (custom constraint)
        if (toDate.isBefore(fromDate)) {
            throw new IllegalArgumentException("End date must be after or equal to start date");
        }

        UnavailabilityPeriod period = new UnavailabilityPeriod(fromDate, toDate);
        unavailabilityPeriods.add(period);
    }

    // Custom business logic to check availability
    public boolean isAvailableFor(Event event) {
        if (event == null || event.getStartDate() == null || event.getEndDate() == null) {
            return false;
        }

        // Check if event overlaps with any unavailability period
        for (UnavailabilityPeriod period : unavailabilityPeriods) {
            // Check for overlap
            if (!(event.getEndDate().isBefore(period.getFromDate()) ||
                    event.getStartDate().isAfter(period.getToDate()))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
