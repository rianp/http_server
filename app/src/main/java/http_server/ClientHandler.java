package http_server;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private final Socket client;
  private final SocketIO socketIO;
  private final ResponseBuilder router;

  public ClientHandler(Socket client, SocketIO socketIO, ResponseBuilder router) {
    this.client = client;
    this.socketIO = socketIO;
    this.router = router;
  }

  @Override
  public void run() {
    try {
      RequestReader httpRequest = new RequestReader(socketIO);
      httpRequest.readRequest();
      Response response = router.routeRequest(httpRequest);
      ResponseFormatter formattedResponse = new ResponseFormatter();

      socketIO.sendMessage(formattedResponse.formatResponse(response));

      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
