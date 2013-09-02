package com.city81.hateoas.exception;

public class BetNotUnmatchedException extends Exception {

	public BetNotUnmatchedException(Long betId) {
        super(String.format("Bet %s is not unmatched", betId));
    }
}
