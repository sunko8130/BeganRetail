package com.frontiertechnologypartners.beganretail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProvidersResponse {

	@SerializedName("data")
	private List<Operators> data;

	@SerializedName("status")
	private Status status;

	public void setData(List<Operators> data){
		this.data = data;
	}

	public List<Operators> getData(){
		return data;
	}

	public Status getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ProvidersResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' +
			"}";
		}
}