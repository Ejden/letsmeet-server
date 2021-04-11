package pl.stypinskiadrian.letsmeetserver.calendar;

import org.springframework.stereotype.Service;
import pl.stypinskiadrian.letsmeet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleCalendarService {
    private final SimpleCalendarRepository calendarRepository;

    public SimpleCalendarService(SimpleCalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public List<SimpleCalendar> findAll() {
        return calendarRepository.findAll();
    }

    public Optional<SimpleCalendar> getById(Long id) {
        return calendarRepository.getById(id);
    }

    public SimpleCalendar save(NewCalendarRequest request) {
        WorkingHours workingHours = new WorkingHours(new Time(request.getWorkingHours().getStart()), new Time(request.getWorkingHours().getEnd()));
        SimpleCalendar newCalendar = new SimpleCalendar(workingHours);
        request.getMeetings().forEach(meeting -> {
            newCalendar.addMeeting(new Meeting(new Time(meeting.getStart()), new Time(meeting.getEnd())));
        });

        return calendarRepository.save(newCalendar);
    }

    public void removeById(Long id) {
        calendarRepository.removeById(id);
    }

    public List<FreeTime> findCommonFreeTime(List<Long> calendarIds, int meetingTime) {
        List<SimpleCalendar> simpleCalendars = new ArrayList<>();

        calendarIds.forEach(id -> {
            Optional<SimpleCalendar> cal = getById(id);
            cal.ifPresent(simpleCalendars::add);
        });

        SimpleCalendar[] simpleCalendars1 = new SimpleCalendar[simpleCalendars.size()];
        simpleCalendars1 = simpleCalendars.toArray(simpleCalendars1);

        return CalendarUtils.findProposalMeetingTimes(meetingTime, simpleCalendars1);
    }
}
