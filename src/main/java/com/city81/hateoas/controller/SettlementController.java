package com.city81.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.city81.hateoas.domain.Bet;
import com.city81.hateoas.domain.Settlement;
import com.city81.hateoas.resource.SettlementResource;
import com.city81.hateoas.resource.SettlementResourceAssembler;

@Controller
@RequestMapping("/settlements")
public class SettlementController {

	private SettlementResourceAssembler settlementResourceAssembler;
//	private EntityLinks entityLinks;

	@Autowired
	public SettlementController(SettlementResourceAssembler settlementResourceAssembler
//			, EntityLinks entityLinks
			) {
		this.settlementResourceAssembler = settlementResourceAssembler;
//		this.entityLinks = entityLinks;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{betId}")
	ResponseEntity<SettlementResource> settleBet(@PathVariable Long betId) {
		Settlement settlement = new Settlement();
		settlement.setId(1L);
		SettlementResource resource = settlementResourceAssembler.toResource(settlement);
//		Link link = entityLinks.linkToSingleResource(Bet.class, betId);		
//		resource.add(link);
		return new ResponseEntity<SettlementResource>(resource, HttpStatus.OK);
	}
	
}
