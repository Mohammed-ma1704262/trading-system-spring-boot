package blankProject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class mainApp {

	public static void main(String[] args) throws IOException {
//		HttpServer.create(new InetSocketAddress(7575), 0);

		var server = HttpServer.create(new InetSocketAddress(7575), 0);

		server.createContext("/", new MyHandler());

		server.start();

	}

}

class MyHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		exchange.sendResponseHeaders(200, 0);

		try (OutputStream os = exchange.getResponseBody()) {
			os.write("http".getBytes());

		}
	}
}