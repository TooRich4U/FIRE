package com.pixelnos.fire.backend.manager;

public class Asset {

	public String name;
	public double yearReturn;
	public double initialValue;

	public Asset(String newAssetName, double newYearReturn, double newAssetCurrentValue) {
		name = newAssetName;
		yearReturn = newYearReturn;
		initialValue = newAssetCurrentValue;
	}

}
