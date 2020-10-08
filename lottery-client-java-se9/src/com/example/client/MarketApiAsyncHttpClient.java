package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MarketApiAsyncHttpClient {
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var counter = new AtomicInteger(0);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder().uri(URI.create(URL)).header("Accept", "application/json").build();
		var start = System.currentTimeMillis();
		for (var i = 0; i < 10; ++i) {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				  .thenAccept( ticker -> {
					  System.out.println(Thread.currentThread().getName()+": "+ticker.body());
					  var value = counter.incrementAndGet();
					  if (value==10) {
						  var stop = System.currentTimeMillis();
						   System.err.println("Duration: "+(stop-start)+" ms.");
					  }
				  });
		}
		System.err.println("All requests are sent.");
		TimeUnit.SECONDS.sleep(5);
	}

}
