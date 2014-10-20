package knapsack.entities;
import java.util.BitSet;

// Represent a knapsack
public class Knapsack {
	
	private int limit;
	private int weight;
	private int price;
	private BitSet itemsInBag = new BitSet();
	
	public BitSet getItemsInBag() {
		return itemsInBag;
	}

	public void setItemsInBag(BitSet itemsInBag) {
		this.itemsInBag = itemsInBag;
	}

	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	// TODO toString()
}
