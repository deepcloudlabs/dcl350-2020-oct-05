package com.example.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Document(collection = "trades")
public class TradeEvent {
	@Id
	private String id;
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("T")
	private long timestamp;
	@JsonProperty("t")
	private long eventId;
	@JsonProperty("a")
	private long askId;
	@JsonProperty("b")
	private long bidId;

	public TradeEvent() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getAskId() {
		return askId;
	}

	public void setAskId(long askId) {
		this.askId = askId;
	}

	public long getBidId() {
		return bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	@Override
	public String toString() {
		return "TradeEvent [id=" + id + ", symbol=" + symbol + ", price=" + price + ", quantity=" + quantity
				+ ", timestamp=" + timestamp + ", eventId=" + eventId + ", askId=" + askId + ", bidId=" + bidId + "]";
	}

}
