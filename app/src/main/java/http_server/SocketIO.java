package http_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketIO {

  public String readRequest(Socket serverConnection) {
    String message = "";
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
      message = in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return message;
  }

  public void sendMessage(Socket socket, String message) throws IOException {
    socket.getOutputStream().write(message.getBytes());
  }
}
