package knapsack.entities;

// Represent an instance of the problem
public class Instance {

	private int id;
	private int nSize;
	private ItemPool itemPool;
	private Knapsack knapsack;

	public Instance(int id, int nSize, ItemPool itemPool, Knapsack knapsack) {
		
		this.id = id;
		this.nSize = nSize;
		this.itemPool = itemPool;
		this.knapsack = knapsack;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getnSize() {
		return nSize;
	}
	
	public void setnSize(int nSize) {
		this.nSize = nSize;
	}
	
	public ItemPool getItemPool() {
		return itemPool;
	}
	
	public void setItemPool(ItemPool itemPool) {
		this.itemPool = itemPool;
	}
	
	public Knapsack getKnapsack() {
		return knapsack;
	}

	public void setKnapsack(Knapsack knapsack) {
		this.knapsack = knapsack;
	}
	
	@Override
	public String toString() {
		return "Instance #" + this.id + "\n--------------\n" + this.itemPool.toString() + "\n";
	}

}
