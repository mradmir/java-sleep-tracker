package ru.yandex.practicum.sleeptracker;

import java.util.List;

class AvgSleepDurationAnalysis
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult apply(
            List<SleepingSession> sessions) {

        double avg = sessions.stream()
                .mapToLong(SleepingSession::getDurationInMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult(
                "Средняя продолжительность сна",
                avg
        );
    }
}
