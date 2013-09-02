package com.city81.hateoas.service;

import java.util.List;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.BetType;
import com.city81.hateoas.exception.BetNotFoundException;
import com.city81.hateoas.exception.BetNotUnmatchedException;

public interface BetService {
	
	public Bet createBet(Long marketId, Long selectionId, Double price, 
			Double stake, BetType type);
	
	public Bet getBet(Long id) throws BetNotFoundException;	
			
	public Bet updateBet(Long id, Bet bet) throws BetNotFoundException, BetNotUnmatchedException;	
	
	public Bet cancelBet(Long id);
	
	public List<Bet> getAllBets();
	
	public List<Long> getAllBetIds();

}
