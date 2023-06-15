package http_server;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private Socket client;
  private SocketIO socketIO;
  private Router router;

  public ClientHandler(Socket client, SocketIO socketIO, Router router) {
    this.client = client;
    this.socketIO = socketIO;
    this.router = router;
  }

  @Override
  public void run() {
    try {
      RequestReader httpRequest = new RequestReader(socketIO);
      httpRequest.readRequest();
      String responseBody = router.routeRequest(httpRequest);

      String response = "HTTP/1.1 200 OK\r\n\r\n" + responseBody;
      socketIO.sendMessage(response);

      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
