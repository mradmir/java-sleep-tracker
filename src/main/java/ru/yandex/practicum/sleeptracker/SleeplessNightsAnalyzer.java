package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNightsAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult apply(
            List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(
                    "Количество бессонных ночей",
                    0
            );
        }

        SleepingSession first = sessions.get(0);
        SleepingSession last =
                sessions.get(sessions.size() - 1);

        LocalDate startDate =
                getStartNightDate(first);

        LocalDate endDate =
                last.getEnd().toLocalDate();

        long totalNights =
                ChronoUnit.DAYS.between(
                        startDate,
                        endDate
                );

        Set<LocalDate> sleepNights =
                sessions.stream()
                        .filter(this::isNightSleep)
                        .map(SleepingSession::getNightDate)
                        .collect(Collectors.toSet());

        long sleepless =
                totalNights - sleepNights.size();

        return new SleepAnalysisResult(
                "Количество бессонных ночей",
                sleepless
        );
    }

    private boolean isNightSleep(
            SleepingSession session) {

        return session.isNightSession();
    }

    private LocalDate getStartNightDate(
            SleepingSession firstSession) {

        if (firstSession.getStart()
                .toLocalTime()
                .isBefore(LocalTime.NOON)) {

            return firstSession.getStart()
                    .toLocalDate()
                    .minusDays(1);
        }

        return firstSession.getStart()
                .toLocalDate();
    }
}   
