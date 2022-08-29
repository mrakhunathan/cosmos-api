package com.smplfinance.cosmosapi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smplfinance.cosmosapi.models.Balance;

public interface IgniteService {

	ResponseEntity<List<Balance>> getBalance(String account);

	ResponseEntity<String> transfer(String address, int numberOfTokens);

}
