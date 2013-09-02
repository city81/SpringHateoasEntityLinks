package com.city81.hateoas.exception;

public class BetNotFoundException extends Exception {

	public BetNotFoundException(Long betId) {
        super(String.format("Could not find Bet %s", betId));
    }
}
