package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepTrackerApp {

    private static final List<SleepAnalyzer> ANALYZERS =
            List.of(
                    new CountSleepSessionsAnalyzer(),
                    new MinSleepDurationAnalysis(),
                    new MaxSleepDurationAnalysis(),
                    new AvgSleepDurationAnalysis(),
                    new BadQualitySleepAnalyzer(),
                    new SleeplessNightsAnalyzer(),
                    new ChronotypeAnalyzer()
            );

    public static void main(String[] args)
            throws IOException {

        List<SleepingSession> sessions =
                readSessions(args[0]);

        ANALYZERS.stream()
                .map(analyzer ->
                        analyzer.apply(sessions))
                .forEach(result ->
                        System.out.println(
                                result.getDescription()
                                        + ": "
                                        + result.getValue()
                        ));
    }

    private static List<SleepingSession>
    readSessions(String fileName)
            throws IOException {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd.MM.yy HH:mm"
                );

        return Files.lines(Path.of(fileName))
                .map(line -> line.split(";"))
                .map(parts ->
                        new SleepingSession(
                                LocalDateTime.parse(
                                        parts[0],
                                        formatter
                                ),
                                LocalDateTime.parse(
                                        parts[1],
                                        formatter
                                ),
                                SleepQuality.valueOf(
                                        parts[2]
                                )
                        ))
                .toList();
    }
}