package com.smplfinance.cosmosapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountItem {

	@JsonProperty("@type")
	String type;

	@JsonProperty("address")
	String address;

//	@JsonProperty("base_account")
//	BaseAccount baseAccount;
}
