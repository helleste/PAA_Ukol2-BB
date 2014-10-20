package knapsack.entities;

// Represents a pool of items to be evaluated
public class ItemPool {
	
	private Item[] items;
	
	public ItemPool(int size) {
		this.items = new Item[size];
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
	
	public void addToPool (Item item, int index) {
		this.items[index] = item;
	}
	
	@Override
	public String toString() {
		String itemsData = "";
		
		for (Item item : items) {
			itemsData += item.toString();
			itemsData += "\n";
		}
		
		return itemsData;
	}

}
