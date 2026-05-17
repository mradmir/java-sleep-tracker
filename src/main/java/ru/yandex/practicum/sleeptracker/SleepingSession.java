package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepingSession {
    private LocalDateTime start;
    private LocalDateTime end;
    private SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationInMinutes() {

        if (start == null || end == null) {
            return 0;
        }

        return Duration.between(start, end).toMinutes();
    }

    public boolean isNightSession() {

        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        return start.toLocalDate().isBefore(end.toLocalDate())
                || startTime.isBefore(LocalTime.of(6, 0))
                || endTime.isBefore(LocalTime.of(6, 0));
    }

    public LocalDate getNightDate() {

        if (start.toLocalTime().isBefore(LocalTime.NOON)) {
            return start.toLocalDate().minusDays(1);
        }

        return start.toLocalDate();
    }
}
