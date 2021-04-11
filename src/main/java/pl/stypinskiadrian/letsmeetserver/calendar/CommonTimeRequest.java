package pl.stypinskiadrian.letsmeetserver.calendar;

import java.util.List;

public class CommonTimeRequest {
    private final List<Long> calendarIds;
    private final int meetingTime;

    public CommonTimeRequest(List<Long> calendarIds, int meetingTime) {
        this.calendarIds = calendarIds;
        this.meetingTime = meetingTime;
    }

    public List<Long> getCalendarIds() {
        return calendarIds;
    }

    public int getMeetingTime() {
        return meetingTime;
    }
}
