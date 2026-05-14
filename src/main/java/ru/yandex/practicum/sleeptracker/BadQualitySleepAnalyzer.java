package ru.yandex.practicum.sleeptracker;

import java.util.List;

class BadQualitySleepAnalyzer
        implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult apply(
            List<SleepingSession> sessions) {

        long count = sessions.stream()
                .filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult(
                "Количество плохих сессий сна",
                count
        );
    }
}
