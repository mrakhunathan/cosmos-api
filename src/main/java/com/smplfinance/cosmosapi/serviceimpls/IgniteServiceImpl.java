package com.smplfinance.cosmosapi.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.smplfinance.cosmosapi.models.Balance;
import com.smplfinance.cosmosapi.models.BalanceResponseModel;
import com.smplfinance.cosmosapi.services.IgniteService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class IgniteServiceImpl implements IgniteService {

	@Autowired
	Environment environment;

	@Override
	public ResponseEntity<List<Balance>> getBalance(String account) {
		try {
			HttpResponse<String> response = Unirest
					.get(new StringBuilder().append(environment.getProperty("ignite.baseurl"))
							.append("/cosmos/bank/v1beta1/balances/").append(account).toString())
					.header("accept", "application/json").asString();
			BalanceResponseModel balanceResponseModel = new ObjectMapper()
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(response.getBody(), BalanceResponseModel.class);
			return new ResponseEntity<List<Balance>>(balanceResponseModel.getBalances(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public ResponseEntity<String> transfer(String address, int numberOfTokens) {
		try {
			HttpResponse<String> response = Unirest.post(environment.getProperty("ignite.fauceturl"))
					.header("accept", "application/json").header("Content-Type", "application/json")
					.body(new StringBuilder().append("{  \"address\": \"").append(address)
							.append("\",  \"coins\": [    \"").append(numberOfTokens).append("token\"  ]}").toString())
					.asString();
			if (response.getStatus() == 200) {
				return new ResponseEntity<String>("Succesfully Updated", HttpStatus.OK);
			}
			System.out.println(response.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new ResponseEntity<String>("Failed to Update", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
