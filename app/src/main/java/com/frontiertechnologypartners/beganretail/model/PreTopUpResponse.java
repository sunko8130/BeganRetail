package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

public class PreTopUpResponse {

	@SerializedName("amount")
	private double amount;

	@SerializedName("status")
	private Status status;

	public double getAmount() {
		return amount;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "PreTopUpResponse{" +
				"data=" + amount +
				", status=" + status +
				'}';
	}
}