package com.city81.hateoas.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.BetStatus;
import com.city81.hateoas.domain.BetType;
import com.city81.hateoas.exception.BetNotFoundException;
import com.city81.hateoas.exception.BetNotUnmatchedException;
import com.city81.hateoas.repository.BetRepository;

@Service
public class BetServiceImpl implements BetService { 
		 
	private BetRepository betRepository;
	
	public BetServiceImpl() {
	}
	
	@Inject
	public BetServiceImpl(BetRepository betRepository) {
		this.betRepository = betRepository;
	}
	
	public Bet createBet(Long marketId, Long selectionId, Double price, 
			Double stake, BetType type) {
		return betRepository.create(marketId, selectionId, price, stake, type);
	}	
	
	public Bet updateBet(Long id, Bet bet) throws BetNotFoundException, BetNotUnmatchedException {
		// get bet to ensure it exists and is in unmatched
		Bet retrievedBet = getBet(id);
		if (retrievedBet.getStatus() == BetStatus.UNMATCHED) {
			bet.setId(id);
			betRepository.save(bet);
		} else {
			throw new BetNotUnmatchedException(id);
		}
		return bet;
	}	
	
	public Bet getBet(Long id) throws BetNotFoundException {   
		Bet bet =  betRepository.retrieve(id);
		if (bet == null){
			throw new BetNotFoundException(id);
		}
		return bet;
	}
	
	public Bet cancelBet(Long id) {
		Bet bet = betRepository.retrieve(id);
		bet.setStatus(BetStatus.CANCELLED);
		betRepository.save(bet);
		return bet;
	}
	
	public List<Bet> getAllBets() {
		return betRepository.findAll();
	}
	
	public List<Long> getAllBetIds() {
		return betRepository.findAllIds();
	}
	
}
