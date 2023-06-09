package http_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class SocketIOTest {
  SocketIO socketIO = new SocketIO();
  Socket mockSocket = Mockito.mock(Socket.class);
  InputStream mockInputStream = mock(InputStream.class);

  @Test
  @DisplayName("should read a message when a message is sent")
  public void should_ReadRequest_When_MessageIsSent() throws IOException {
    String input = "Hello\n";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

    when(mockSocket.getInputStream()).thenReturn(mockInputStream);
    when(mockInputStream.read(any(byte[].class), anyInt(), anyInt()))
        .thenAnswer(invocation -> byteArrayInputStream.read(
            (byte[]) invocation.getArgument(0),
            invocation.getArgument(1),
            invocation.getArgument(2)));

    String result = socketIO.readRequest(mockSocket);

    assertThat(result, equalTo("Hello"));
  }

  @Test
  @DisplayName("should send a message when a user inputs a message")
  void should_SendMessage_When_MessageIsGiven() throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    when(mockSocket.getOutputStream()).thenReturn(outputStream);
    String message = "Test message";

    socketIO.sendMessage(mockSocket, message);

    String actualOutput = outputStream.toString();
    assertThat(actualOutput, equalTo("Test message"));
  }
}
