package com.bean;

public class StockBean {

	private String shareName;
	private int noOfShares;
	private double sharePrice;
	private double stockValue;
	
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	public int getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	
	public double getStockValue() {
		return stockValue;
	}
	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	public String getShareName() {
		return shareName;
	}
	public static double CalucateStockValue(StockBean stb)
	{
		return (stb.noOfShares*stb.sharePrice);
	}
	
}
