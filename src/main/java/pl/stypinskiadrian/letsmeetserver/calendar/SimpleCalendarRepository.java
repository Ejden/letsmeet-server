package pl.stypinskiadrian.letsmeetserver.calendar;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class SimpleCalendarRepository {

    private final List<SimpleCalendar> calendars;
    private static Long nextIdValue = 0L;

    public SimpleCalendarRepository() {
        this.calendars = new ArrayList<>();
    }

    public List<SimpleCalendar> findAll() {
        return Collections.unmodifiableList(calendars);
    }

    public Optional<SimpleCalendar> getById(Long id) {
        return calendars.stream()
                        .filter(calendar -> calendar.getId().equals(id))
                        .findFirst();
    }

    public SimpleCalendar save(SimpleCalendar calendar) {
        calendar.setId(generateId());
        calendars.add(calendar);
        return calendar;
    }

    public void removeById(Long id) {
        this.calendars.removeIf(calendar -> calendar.getId().equals(id));
    }

    private static Long generateId() {
        return nextIdValue++;
    }

}
