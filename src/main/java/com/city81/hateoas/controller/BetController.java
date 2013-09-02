package com.city81.hateoas.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.BetStatus;
import com.city81.hateoas.exception.BetNotFoundException;
import com.city81.hateoas.exception.BetNotUnmatchedException;
import com.city81.hateoas.resource.BetResource;
import com.city81.hateoas.resource.BetResourceAssembler;
import com.city81.hateoas.service.BetService;

//@EnableEntityLinks
@Controller
//@ExposesResourceFor(Bet.class)
@RequestMapping("/bets")
public class BetController {

	private BetService betService;
	private BetResourceAssembler betResourceAssembler;

	@Autowired
	public BetController(BetService betService,
			BetResourceAssembler betResourceAssembler) {
		this.betService = betService;
		this.betResourceAssembler = betResourceAssembler;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<BetResource> createBet(@RequestBody Bet body) {
		Bet bet = betService.createBet(body.getMarketId(),
				body.getSelectionId(), body.getPrice(), body.getStake(),
				body.getType());
		BetResource resource = betResourceAssembler.toResource(bet);
		return new ResponseEntity<BetResource>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{betId}")
	ResponseEntity<BetResource> updateBet(@PathVariable Long betId,
			@RequestBody Bet body) throws BetNotFoundException,	BetNotUnmatchedException {
		Bet bet = betService.updateBet(betId, body);
		BetResource resource = betResourceAssembler.toResource(bet);
		return new ResponseEntity<BetResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{betId}")
	ResponseEntity<BetResource> getBet(@PathVariable Long betId) throws BetNotFoundException {
		Bet bet = betService.getBet(betId);
		BetResource resource = betResourceAssembler.toResource(bet);
		if (bet.getStatus() == BetStatus.UNMATCHED) {
			resource.add(linkTo(BetController.class).slash(bet.getId()).withRel("cancel"));
		}
		return new ResponseEntity<BetResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<BetResource>> getBets() {
		ResponseEntity getBets() {
//		List<Bet> betList = betService.getAllBets();
//		List<BetResource> resourceList = betResourceAssembler.toResources(betList);
//		return new ResponseEntity<List<BetResource>>(resourceList, HttpStatus.OK);
		ResourceSupport resourceSupport = new ResourceSupport();
		List<Long> betIdList = betService.getAllBetIds();
		for (Long id : betIdList) {
			resourceSupport.add(linkTo(BetController.class).slash(id).withRel(id.toString()));
		}
		return new ResponseEntity(resourceSupport, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{betId}")
	ResponseEntity<BetResource> cancelBet(@PathVariable Long betId) {
		Bet bet = betService.cancelBet(betId);
		BetResource resource = betResourceAssembler.toResource(bet);
		return new ResponseEntity<BetResource>(resource, HttpStatus.OK);
	}

	@ExceptionHandler
	ResponseEntity handleExceptions(Exception ex) {
		ResponseEntity responseEntity = null;
		if (ex instanceof BetNotFoundException) {
			responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
		} else if (ex instanceof BetNotUnmatchedException) {
			responseEntity = new ResponseEntity(HttpStatus.CONFLICT);
		} else {
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
}
