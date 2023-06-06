package http_server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class HttpServer {
  private int port;
  private final Console console;
  private final SocketIO socketIO;

  public HttpServer(Console console, SocketIO socketIO, int port) {
    this.port = port;
    this.console = console;
    this.socketIO = socketIO;
  }

  public void start() throws IOException  {
    ServerSocket socket = new ServerSocket(port);
    console.print("Listening on port " + port);
    Socket client;
    while ((client = socket.accept()) != null)  {
      console.print("Received connection from " + client.getRemoteSocketAddress().toString());
//      SocketHandler handler = new SocketHandler(client, handlers);
//      Thread t = new Thread(handler);
//      t.start();
    }
  }
}
