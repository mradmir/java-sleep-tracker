package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {
    @Test
    void shouldCountSessionsCorrectly() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(8),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(7),
                        SleepQuality.BAD
                )
        );

        CountSleepSessionsAnalyze analyzer =
                new CountSleepSessionsAnalyze();

        SleepAnalysisResult result =
                analyzer.apply(sessions);

        assertEquals(2, result.getValue());
    }
}