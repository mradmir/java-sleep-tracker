package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult apply(
            List<SleepingSession> sessions) {

        Map<Chronotype, Long> stats =
                sessions.stream()
                        .filter(SleepingSession::isNightSession)
                        .collect(Collectors.groupingBy(
                                this::defineType,
                                Collectors.counting()
                        ));

        long owl =
                stats.getOrDefault(
                        Chronotype.OWL,
                        0L
                );

        long lark =
                stats.getOrDefault(
                        Chronotype.LARK,
                        0L
                );

        long pigeon =
                stats.getOrDefault(
                        Chronotype.PIGEON,
                        0L
                );

        Chronotype result;

        if (owl > lark && owl > pigeon) {
            result = Chronotype.OWL;
        } else if (lark > owl && lark > pigeon) {
            result = Chronotype.LARK;
        } else {
            result = Chronotype.PIGEON;
        }

        return new SleepAnalysisResult(
                "Хронотип пользователя",
                result
        );
    }

    private Chronotype defineType(
            SleepingSession session) {

        LocalTime start =
                session.getStart().toLocalTime();

        LocalTime end =
                session.getEnd().toLocalTime();

        boolean owl =
                start.isAfter(LocalTime.of(23, 0))
                        && end.isAfter(LocalTime.of(9, 0));

        boolean lark =
                start.isBefore(LocalTime.of(22, 0))
                        && end.isBefore(LocalTime.of(7, 0));

        if (owl) {
            return Chronotype.OWL;
        }

        if (lark) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }
}
