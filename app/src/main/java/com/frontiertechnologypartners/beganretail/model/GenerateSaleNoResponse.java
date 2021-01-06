package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

public class GenerateSaleNoResponse{

	@SerializedName("data")
	private String data;

	@SerializedName("status")
	private Status status;

	public String getData(){
		return data;
	}

	public Status getStatus(){
		return status;
	}
}