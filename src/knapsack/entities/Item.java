package knapsack.entities;

// Represents a single item
public class Item {
	
	private int id;
	private int weight;
	private int price;
	
	public Item(int id, int weight, int price) {
		this.id = id;
		this.weight = weight;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		
		return "Item #" + this.id + "\n--Weight: " + this.weight + "\n--Price: " + this.price;
	}
}
