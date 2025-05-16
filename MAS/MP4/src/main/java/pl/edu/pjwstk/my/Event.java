package pl.edu.pjwstk.my;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Participant> participants = new ArrayList<>();

    public Event(String name, String description, LocalDate startDate, LocalDate endDate) {
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Event name cannot be empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (this.endDate != null && startDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException(
                    "Start date (" + startDate + ") must be before or equal to end date (" + this.endDate + ")");
        }

        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }

        if (this.startDate != null && endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException(
                    "End date (" + endDate + ") must be after or equal to start date (" + this.startDate + ")");
        }

        this.endDate = endDate;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public void addParticipant(Participant participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Participant cannot be null");
        }

        if (!participant.isAvailableFor(this)) {
            throw new IllegalArgumentException(
                    "Participant " + participant.getName() + " is not available for this event date range");
        }

        if (!participants.contains(participant)) {
            participants.add(participant);
            participant.addEvent(this);
        }
    }

    public void removeParticipant(Participant participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Participant cannot be null");
        }

        if (participants.remove(participant)) {
            participant.removeEvent(this);
        }
    }

    @Override
    public String toString() {
        return name + " (" + startDate + " to " + endDate + ")";
    }
}
