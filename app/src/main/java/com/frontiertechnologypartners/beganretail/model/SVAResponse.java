package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

public class SVAResponse {

	@SerializedName("sva")
	private double sva;

	@SerializedName("status")
	private Status status;

	public double getSva() {
		return sva;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "SVAResponse{" +
				"sva=" + sva +
				", status=" + status +
				'}';
	}
}