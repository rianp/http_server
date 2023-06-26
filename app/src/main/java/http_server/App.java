package http_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(final String[] args) throws IOException {
        Console console = new Console();

        ResponseHandler requestHandler = new ResponseHandler();
        ResponseBuilder responseBuilder = new ResponseBuilder(requestHandler);
      
        int port = 8080;

        ServerSocket server = new ServerSocket(port);
        console.print("Listening on port " + port);
        Socket client;

        while ((client = server.accept()) != null) {
            SocketIO socketIO = new SocketIO(client);
            console.print("Received connection from " + client.getRemoteSocketAddress().toString());

            ClientHandler handler = new ClientHandler(client, socketIO, responseBuilder);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }
}
