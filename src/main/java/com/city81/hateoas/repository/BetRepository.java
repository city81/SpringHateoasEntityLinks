package com.city81.hateoas.repository;

import java.util.List;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.BetType;

public interface BetRepository {
	
    Bet create(Long marketId, Long selectionId, Double price, 
			Double stake, BetType betType);

    Bet retrieve(Long id);
    
    void save(Bet bet);

    void remove(Long id);
    
    List<Bet> findAll();
    
    List<Long> findAllIds();
    
}
