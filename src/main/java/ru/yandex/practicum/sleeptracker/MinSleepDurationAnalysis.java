package ru.yandex.practicum.sleeptracker;

import java.util.List;

class MinSleepDurationAnalysis
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult apply(
            List<SleepingSession> sessions) {

        long min = sessions.stream()
                .mapToLong(SleepingSession::getDurationInMinutes)
                .min()
                .orElse(0);

        return new SleepAnalysisResult(
                "Минимальная продолжительность сна",
                min
        );
    }
}
