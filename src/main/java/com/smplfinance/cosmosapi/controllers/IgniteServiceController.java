package com.smplfinance.cosmosapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smplfinance.cosmosapi.models.AccountWrapper;
import com.smplfinance.cosmosapi.models.Balance;
import com.smplfinance.cosmosapi.services.IgniteService;

@RestController
public class IgniteServiceController {
	@Autowired
	IgniteService igniteService;

	@PostMapping(value = "api/v1/balance")
	ResponseEntity<List<Balance>> getBalance(@RequestBody String account) {
		return igniteService.getBalance(account);
	}

	@PostMapping(value = "api/v1/transfer")
	ResponseEntity<String> transfer(@RequestBody Map<String, String> data) {
		return igniteService.transfer(data.get("address"), Integer.parseInt(data.get("tokens")));
	}

	@GetMapping(value = "api/v1/accounts")
	ResponseEntity<AccountWrapper> accounts() {
		return igniteService.listAccounts();
	}
}
