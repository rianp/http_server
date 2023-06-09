package http_server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConsoleTest {
  Console console = new Console();
  private PrintStream originalSystemOut;
  private InputStream originalSystemIn;

  @BeforeEach
  public void setUp() {
    originalSystemOut = System.out;
    originalSystemIn = System.in;
  }

  @AfterEach
  public void tearDown() {
    System.setOut(originalSystemOut);
    System.setIn(originalSystemIn);
  }

  @Test
  @DisplayName("should output a string that matches the provided input")
  public void should_PrintCorrectOutput_When_PrintingString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    console.print("Print");

    String output = outputStream.toString().trim();
    assertThat(output, equalTo("Print"));
  }


  @Test
  @DisplayName("should return a string that matches the string entered by the user")
  public void should_ReturnInputString_When_ProvidingInput() throws IOException {
    String expectedInput = "test input";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
    System.setIn(inputStream);

    String input = console.inputString("Enter input: ");

    assertThat(input, equalTo(expectedInput));
  }
}
