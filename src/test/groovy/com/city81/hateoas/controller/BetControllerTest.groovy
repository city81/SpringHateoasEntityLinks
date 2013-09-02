package com.city81.hateoas.controller;

import org.springframework.http.HttpStatus;

import com.city81.hateoas.domain.Bet
import com.city81.hateoas.domain.BetType
import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON

class BetControllerTest extends GroovyTestCase {

	def restClient = new RESTClient( 'http://localhost:8080/hateoas-entityLinks-1-SNAPSHOT/' )

	void testTest() {
		assert true == true
	}

	void testCreateBet() {
		
		def bet = new Bet()
		bet.setMarketId(1)
		bet.setSelectionId(22)
		bet.setPrice(4.0)
		bet.setStake(2.00)
		bet.setType(BetType.BACK)
		
		def builder = new JsonBuilder(bet)
		
		def resp = restClient.post(
				path : 'bets/',
				requestContentType: JSON,
				body : builder.toString() )
		
		assert resp.status == 201
		assert resp.contentType == JSON.toString()
		
		bet = new Bet()
		bet.setMarketId(2)
		bet.setSelectionId(44)
		bet.setPrice(2.0)
		bet.setStake(5.00)
		bet.setType(BetType.LAY)
		
		builder = new JsonBuilder(bet)
		
		resp = restClient.post(
				path : 'bets/',
				requestContentType: JSON,
				body : builder.toString() )
		
		assert resp.status == 201
		assert resp.contentType == JSON.toString()
		
	}

	void testGetBet() {

		def resp = restClient.get( path : 'bets/0' )

		assert resp.status == 200
		assert resp.contentType == JSON.toString()
		
		
		resp = restClient.get( path : 'settlements/0' )
		
		println resp
		
		
		try {
			resp = restClient.get( path : 'bets/99' )
		} catch ( HttpResponseException ex ) {		
			assert ex.message == 'Not Found'
		}
		
	}

	void testGetAllBets() {
		
		def resp = restClient.get( path : 'bets' )

		assert resp.status == 200
		assert resp.contentType == JSON.toString()
		
	}
	
	void testCancelBet() {
		
		def resp = restClient.delete( path : 'bets/0' )

		assert resp.status == 200
		assert resp.contentType == JSON.toString()
	}
	
	
//	void testUpdateBet() {
//
//		def bet = new Bet()
//		bet.setMarketId(2)
//		bet.setSelectionId(44)
//		bet.setPrice(2.0)
//		bet.setStake(15.00)
//		bet.setType(BetType.LAY)
//		
//		def builder = new JsonBuilder(bet)
//		
//		def resp = restClient.put(
//				path : 'bets/1',
//				requestContentType: JSON,
//				body : builder.toString() )
//
//		assert resp.status == 200
//		assert resp.contentType == JSON.toString()
//
//	}

	
}

