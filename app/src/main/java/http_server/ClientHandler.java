package http_server;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private final Socket client;
  private final SocketIO socketIO;
  private final Router router;
  private final ResponseHandler responseHandler;

  public ClientHandler(Socket client, SocketIO socketIO, Router router, ResponseHandler responseHandler) {
    this.responseHandler = responseHandler;
    this.client = client;
    this.socketIO = socketIO;
    this.router = router;
  }

  @Override
  public void run() {
    try {
      RequestReader httpRequest = new RequestReader(socketIO);
      httpRequest.readRequest();
      String response = responseHandler.buildResponse(httpRequest, router);
      socketIO.sendMessage(response);

      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
