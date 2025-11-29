package com.jpmc.midascore;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileLoader {

    public String[] loadStrings(String resourcePath) {
        // TaskTwoTests passes "/test_data/poiuytrewq.uiop"
        String normalized = resourcePath.startsWith("/")
                ? resourcePath.substring(1)
                : resourcePath;

        // 1) Try to load from classpath
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(normalized);

        if (is != null) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {

                List<String> lines = reader.lines().collect(Collectors.toList());
                return lines.toArray(new String[0]);
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to read classpath resource " + resourcePath, e);
            }
        }

        // 2) Fallback: load directly from src/test/resources on disk
        try {
            Path path = Path.of("src", "test", "resources", normalized);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read file from src/test/resources: " + normalized, e);
        }
    }
}
