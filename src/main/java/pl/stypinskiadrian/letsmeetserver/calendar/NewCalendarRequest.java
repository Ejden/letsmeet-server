package pl.stypinskiadrian.letsmeetserver.calendar;

import java.util.List;

public class NewCalendarRequest {
    private final WorkingHours workingHours;
    private final List<Meeting> meetings;

    public NewCalendarRequest(WorkingHours workingHours, List<Meeting> meetings) {
        this.workingHours = workingHours;
        this.meetings = meetings;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public static class WorkingHours {
        private final String start;
        private final String end;

        public WorkingHours(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }
    }

    public static class Meeting {
        private final String start;
        private final String end;

        public Meeting(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }
    }
}
