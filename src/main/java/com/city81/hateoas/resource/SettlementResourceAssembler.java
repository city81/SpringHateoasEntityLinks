package com.city81.hateoas.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.city81.hateoas.controller.SettlementController;
import com.city81.hateoas.domain.Settlement;

@Component
public class SettlementResourceAssembler extends ResourceAssemblerSupport<Settlement, SettlementResource> {

	public SettlementResourceAssembler() {
		super(SettlementController.class, SettlementResource.class);
	}

	public SettlementResource toResource(Settlement settlement) {
		SettlementResource resource = instantiateResource(settlement);
		resource.settlement = settlement;
        resource.add(linkTo(SettlementController.class).slash(settlement.getId()).withSelfRel());
		return resource;
	}

}
