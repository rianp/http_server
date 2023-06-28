package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RouterTest {

  @Test
  @DisplayName("should return true when a method exists for a given path")
  void should_ReturnTrue_When_AMethodExistsForAGivenPath() {
    Router router = new Router();
    String key = "/simple_get_with_body";
    String value = "GET";

    boolean result = router.isValuePresent(key, value);

    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("should return false when a method doesnt exist for a given path")
  void should_ReturnFalse_When_AMethodDoesntExistForAGivenPath() {
    Router router = new Router();
    String key = "/simple_get_with_body";
    String value = "POST";

    boolean result = router.isValuePresent(key, value);

    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("should return false when a given path doesn't exist")
  void should_ReturnFalse_WhenAGivenPathDoesNotExist() {
    Router router = new Router();
    String key = "/non_existing_key";
    String value = "GET";

    boolean result = router.isValuePresent(key, value);

    assertThat(result).isFalse();
  }
}

