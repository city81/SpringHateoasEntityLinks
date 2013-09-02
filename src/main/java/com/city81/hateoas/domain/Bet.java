package com.city81.hateoas.domain;


public class Bet {

	private Long id;
	private Long marketId;
	private Long selectionId;
	private Double price;
	private Double stake;
	private BetType type;
	private BetStatus status;
	
	public Bet() {
	}

	public Bet(Long id, Long marketId, Long selectionId, Double price, 
			Double stake, BetType type) {
		this.id = id;
		this.marketId = marketId;
		this.selectionId = selectionId;
		this.price = price;
		this.stake = stake;
		this.type = type;
		this.status = BetStatus.UNMATCHED;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public Long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStake() {
		return stake;
	}

	public void setStake(Double stake) {
		this.stake = stake;
	}

	public BetType getType() {
		return type;
	}

	public void setType(BetType type) {
		this.type = type;
	}

	public BetStatus getStatus() {
		return status;
	}

	public void setStatus(BetStatus status) {
		this.status = status;
	}

}
