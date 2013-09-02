package com.city81.hateoas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.BetType;

@Component
public class MapBasedBetRepository implements BetRepository{
 
	private final AtomicLong idGenerator = new AtomicLong(); 
	private final Map<Long, Bet> betMap = new HashMap<Long, Bet>();
	
	public Bet create(Long marketId, Long selectionId, Double price, 
			Double stake, BetType betType) {
		Bet bet = null;
		synchronized (this) {
			Long id = this.idGenerator.getAndIncrement();
			bet =  new Bet(id, marketId, selectionId, price, stake, betType);
			betMap.put(id,  bet);
		}
		return bet;
	}

	public Bet retrieve(Long id) {
        synchronized (this) {
            if (this.betMap.containsKey(id)) {
                return this.betMap.get(id);
            }
        }
        return null;
	}

	public void remove(Long id) {
        synchronized (this) {
            if (this.betMap.containsKey(id)) {
               betMap.remove(id);
            }
        }		
	}

	public List<Bet> findAll() {
		return new ArrayList<Bet>(betMap.values());
	}
	
	public List<Long> findAllIds() {
		return new ArrayList<Long>(betMap.keySet());
	}

	public void save(Bet bet) {
		Assert.notNull(bet.getId());
		betMap.put(bet.getId(), bet);
		
	}

}
