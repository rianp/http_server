package http_server;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private final Socket client;
  private final SocketIO socketIO;
  private final ResponseBuilder responseBuilder;

  public ClientHandler(Socket client, SocketIO socketIO, ResponseBuilder responseBuilder) {
    this.client = client;
    this.socketIO = socketIO;
    this.responseBuilder = responseBuilder;
  }

  @Override
  public void run() {
    try {
      RequestReader httpRequest = new RequestReader(socketIO);
      httpRequest.readRequest();
      Response response = responseBuilder.buildResponse(httpRequest);
      ResponseFormatter responseFormatter = new ResponseFormatter();
      socketIO.sendMessage(responseFormatter.format(response));

      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
