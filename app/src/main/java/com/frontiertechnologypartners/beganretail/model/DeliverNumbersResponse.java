package com.frontiertechnologypartners.beganretail.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeliverNumbersResponse{

	@SerializedName("data")
	private List<String> data;

	@SerializedName("status")
	private Status status;

	public List<String> getData(){
		return data;
	}

	public Status getStatus(){
		return status;
	}
}