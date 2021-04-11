package pl.stypinskiadrian.letsmeetserver.calendar;

import org.jetbrains.annotations.NotNull;
import pl.stypinskiadrian.letsmeet.Calendar;
import pl.stypinskiadrian.letsmeet.WorkingHours;

public class SimpleCalendar extends Calendar {

    private Long id;

    public SimpleCalendar(@NotNull WorkingHours workingHours) {
        super(workingHours);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
