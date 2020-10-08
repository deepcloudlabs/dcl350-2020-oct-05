package com.example.lottery.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.Ticker;

@Service
public class LotteryServiceClient {
	private static final String URL_LOTTERY = "http://localhost:8300/lottery/api/v1/numbers?n=10";
	private static final String URL_BINANCE = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	@Scheduled(fixedRate = 3_000)
	public void callLotteryRestApi() {
		var rt = new RestTemplate();
		var numbers = rt.getForEntity(URL_LOTTERY, String.class).getBody();
		System.out.println(numbers);
	}
	
	@Scheduled(fixedRate = 5_000)
	public void callBinanceRestApi() {
		var rt = new RestTemplate();
		var ticker = rt.getForEntity(URL_BINANCE, Ticker.class).getBody();
		System.out.println(ticker);
	}
	
	@Scheduled(fixedRate = 500)
	public void callAsyncBinanceRestApi() {
		var rt = new AsyncRestTemplate();
		rt.getForEntity(URL_BINANCE, Ticker.class)
		    .addCallback(new ListenableFutureCallback<ResponseEntity<Ticker>>() {

				@Override
				public void onSuccess(ResponseEntity<Ticker> result) {
					System.err.println(result.getBody());
					
				}

				@Override
				public void onFailure(Throwable ex) {
					System.err.println(ex.getMessage());
	
				}
		    	
		    });
		
	}
	
	
}
