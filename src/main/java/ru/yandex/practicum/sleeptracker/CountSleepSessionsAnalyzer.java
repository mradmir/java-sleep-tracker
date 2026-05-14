package ru.yandex.practicum.sleeptracker;

import java.util.List;

class CountSleepSessionsAnalyze implements SleepAnalyzer {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> session) {
        return new SleepAnalysisResult("Количество сессий сна", session.size());
    }
}
