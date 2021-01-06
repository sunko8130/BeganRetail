package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

public class SaleOrdersSaveResponse{

	@SerializedName("status")
	private Status status;

	public Status getStatus(){
		return status;
	}
}