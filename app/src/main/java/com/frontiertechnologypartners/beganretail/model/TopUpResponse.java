package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

public class TopUpResponse {

	@SerializedName("status")
	private Status status;

	public Status getStatus() {
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TopUpResponse{" + 
			"status = '" + status + '\'' +
			"}";
		}
}