package utils;

import java.util.Map;

/**
 * Class
 *
 * @author Scoowy
 * @version 2021.11.30.1807
 */
public class DbConfig {
    private static final ProcessBuilder processBuilder = new ProcessBuilder();
    private static final Map<String, String> env = processBuilder.environment();

    public static final String DB_URL = env.get("DB_URL") != null ? env.get("DB_URL") : "N/A";
    public static final String DB_USER = env.get("DB_USER") != null ? env.get("DB_USER") : "N/A";
    public static final String DB_PASSWORD = env.get("DB_PASSWORD") != null ? env.get("DB_PASSWORD") : "N/A";
}
