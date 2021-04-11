package pl.stypinskiadrian.letsmeetserver.calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.stypinskiadrian.letsmeet.FreeTime;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/calendars")
public class SimpleCalendarController {
    private final SimpleCalendarService calendarService;

    public SimpleCalendarController(SimpleCalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping()
    public List<SimpleCalendar> getAllCalendars() {
        return calendarService.findAll();
    }

    @PostMapping("/common")
    public List<FreeTime> findCommonFreeTime(@RequestBody CommonTimeRequest request) {
        return calendarService.findCommonFreeTime(request.getCalendarIds(), request.getMeetingTime());
    }

    @GetMapping("{id}")
    public Optional<SimpleCalendar> getCalendarById(@PathVariable Long id) {
        return calendarService.getById(id);
    }

    @PostMapping
    public ResponseEntity<SimpleCalendar> addCalendar(@RequestBody NewCalendarRequest request) {
        SimpleCalendar newCalendar = calendarService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCalendar.getId())
                .toUri();

        return ResponseEntity.created(location).body(newCalendar);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<?> deleteCalendarById(@PathVariable Long id) {
        calendarService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
