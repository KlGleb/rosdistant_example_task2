package at.gleb.javaeepractic.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueryStringParser {
    public static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> parameters = new HashMap<>();

        if (queryString != null && !queryString.isEmpty()) {
            String[] pairs = queryString.split("&");

            for (String pair : pairs) {
                int idx = pair.indexOf("=");

                try {
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);

                    parameters.put(key, value);
                } catch (Exception e) {
                    // Обработка исключения, если декодирование не удалось
                    e.printStackTrace();
                }
            }
        }

        return parameters;
    }
}

