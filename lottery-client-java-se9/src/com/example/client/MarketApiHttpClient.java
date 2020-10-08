package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

public class MarketApiHttpClient {
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				                 .uri(URI.create(URL))
				                 .header("Accept", "application/json")
				                 .build();
		while (true) {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);
			TimeUnit.SECONDS.sleep(1);
		}

	}

}
