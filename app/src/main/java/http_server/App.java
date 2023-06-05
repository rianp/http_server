package http_server;

import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(final String[] args) throws IOException {
        Console console = new Console();
        SocketIO socketIO = new SocketIO();

        HttpServer server = new HttpServer(console, socketIO,8080);
        server.start();
    }
}
