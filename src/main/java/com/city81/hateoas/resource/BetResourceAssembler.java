package com.city81.hateoas.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.city81.hateoas.controller.BetController;
import com.city81.hateoas.domain.Bet;

@Component
public class BetResourceAssembler extends ResourceAssemblerSupport<Bet, BetResource> {

	public BetResourceAssembler() {
		super(BetController.class, BetResource.class);
	}

	public BetResource toResource(Bet bet) {
		BetResource resource = instantiateResource(bet);
		resource.bet = bet;
        resource.add(linkTo(BetController.class).slash(bet.getId()).withSelfRel());
		return resource;
	}

}
