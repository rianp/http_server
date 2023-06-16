package http_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIO {
  private final BufferedReader in;
  private final OutputStream out;
  private final Socket clientSocket;

  public SocketIO(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    in = createSocketInput();
    out = createSocketOutput();
  }

  public String readLine() throws IOException {
    return in.readLine();
  }

  public String readBytes(int length) throws IOException {
    char[] container = new char[length];
    in.read(container, 0, length);
    return new String(container, 0, length);
  }

  public void sendMessage(String message) throws IOException {
    out.write(message.getBytes());
  }

  private BufferedReader createSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  private OutputStream createSocketOutput() throws IOException {
    return clientSocket.getOutputStream();
  }
}
