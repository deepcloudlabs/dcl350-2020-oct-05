package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LotteryApiHttpClient {
	private static final String URL = "http://localhost:8300/lottery/api/v1/numbers?n=10";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				                 .uri(URI.create(URL))
				                 .header("Accept", "application/json")
				                 .build();
		var start = System.currentTimeMillis();
		for (var i = 0; i < 10; ++i) {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);
		}
		var stop = System.currentTimeMillis();
		System.err.println("Duration: "+(stop-start)+" ms.");
	}

}
