package http_server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteValidator {

  private final Map<String, List<String>> routes;

  public RouteValidator() {
    routes = new HashMap<>();

    routes.put("/simple_get_with_body", List.of("GET", "HEAD", "OPTIONS"));
    routes.put("/simple_get", List.of("GET", "HEAD", "OPTIONS"));
    routes.put("/echo_body", List.of("POST"));
    routes.put("/head_request", List.of("HEAD", "GET"));
    routes.put("/method_options", List.of("OPTIONS"));
    routes.put("/method_options2", List.of("OPTIONS"));
    routes.put("/redirect", List.of("GET"));
  }

  public boolean hasMethod(String key, String value) {
    if (routes.containsKey(key)) {
      List<String> values = routes.get(key);
      return values.contains(value);
    }
    return false;
  }

  public boolean hasPath(String key) {
    return routes.containsKey(key);
  }
}
