package http_server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

  private final Map<String, List<String>> routes;

  public Router() {
    routes = new HashMap<>();

    routes.put("/simple_get_with_body", List.of("GET", "HEAD", "OPTIONS"));
    routes.put("/simple_get", List.of("GET", "HEAD", "OPTIONS"));
    routes.put("/echo_post", List.of("POST"));
    routes.put("/head_request", List.of("HEAD", "GET"));
    routes.put("/method_options", List.of("OPTIONS"));
    routes.put("/method_options2", List.of("OPTIONS"));
  }

  public boolean isValuePresent(String key, String value) {
    if (routes.containsKey(key)) {
      List<String> values = routes.get(key);
      return values.contains(value);
    }
    return false;
  }
}



// router will refer to routes
// routes will create route objects
// that have method, path, and controller instance variables
// these routes will be stored in a hashmap in router
// these route objects will be grabbed in ResponseBuilder
