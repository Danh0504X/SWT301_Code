package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static List<String[]> read(String fileName) {
        List<String[]> rows = new ArrayList<>();

        try (InputStream is = CSVUtils.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                rows.add(line.split(",", -1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot read CSV file: " + fileName, e);
        }

        return rows;
    }
}