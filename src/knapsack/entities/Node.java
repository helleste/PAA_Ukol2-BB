package knapsack.entities;

import java.util.Arrays;

public class Node {
	
	private int cumPrice;
	private int cumWeight;
	private double upperBound;
	private int level;
	private boolean[] itemVector;

	public Node(int cumPrice, int cumWeight, double upperBound, int level, int vectorSize) {
		this.cumPrice = cumPrice;
		this.cumWeight = cumWeight;
		this.upperBound = upperBound;
		this.level = level;
		this.itemVector = new boolean[vectorSize];
	}
	
	public Node(int cumPrice, int cumWeight, double upperBound, int level, boolean[] itemVector) {
		this.cumPrice = cumPrice;
		this.cumWeight = cumWeight;
		this.upperBound = upperBound;
		this.level = level;
		this.itemVector = itemVector.clone();
	}

	public int getCumPrice() {
		return cumPrice;
	}

	public void setCumPrice(int cumPrice) {
		this.cumPrice = cumPrice;
	}

	public int getCumWeight() {
		return cumWeight;
	}

	public void setCumWeight(int cumWeight) {
		this.cumWeight = cumWeight;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public boolean[] getItemVector() {
		return itemVector;
	}

	public void setItemVector(boolean[] itemVector) {
		this.itemVector = itemVector;
	}
	
	public void setBitInVector(int index) {
		this.itemVector[index] = true;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "Node\n"
				+ "--Level: " + level + "\n"
				+ "--CumPrice: " + cumPrice + "\n"
				+ "--CumWeight: " + cumWeight + "\n"
				+ "--UpperBound: " + upperBound + "\n"
				+ "--Vector: " + Arrays.toString(itemVector);
	}

}
