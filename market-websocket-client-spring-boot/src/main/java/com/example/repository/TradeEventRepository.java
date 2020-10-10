package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.event.TradeEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface TradeEventRepository extends MongoRepository<TradeEvent, String> {

}
