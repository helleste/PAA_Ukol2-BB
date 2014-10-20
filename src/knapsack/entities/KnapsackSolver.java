package knapsack.entities;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import filehandle.FileLoader;
import timemeasure.CPUTiming;

class RatioComparator implements Comparator<Item> {
	
	@Override
	public int compare(Item i1, Item i2) {
		if(((float) i1.getPrice()/(float) i1.getWeight()) > ((float) i2.getPrice()/(float)i2.getWeight()))
			return -1;
		else if (((float) i1.getPrice()/(float) i1.getWeight()) < ((float) i2.getPrice()/(float)i2.getWeight()))
			return 1;
		return 0;
	}
	
}

class UpperBoundComparator implements Comparator<Node> {
	
	@Override
	public int compare(Node n1, Node n2) {
		if (n1.getUpperBound() < n2.getUpperBound())
			return 1;
		else if (n1.getUpperBound() < n2.getUpperBound())
			return -1;
		
		return 0;
	}
}


public class KnapsackSolver {

	public static void main(String[] args) {
		Node current, left, right;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new UpperBoundComparator());
		
		current = new Node(0,0,0,0,4);
		queue.add(current);
		ItemPool itemPool = new ItemPool(4);
		itemPool.addToPool(new Item(0, 3, 45), 0);
		itemPool.addToPool(new Item(1, 5, 30), 1);
		itemPool.addToPool(new Item(2, 9, 45), 2);
		itemPool.addToPool(new Item(3, 5, 10), 3);
		sortItemPool(itemPool);
		System.out.println(itemPool.toString());
		
		while(!queue.isEmpty()) {
			left = new Node(current.getCumPrice(), current.getCumWeight(), 
					current.getUpperBound(), current.getLevel() + 1, current.getItemVector());
			left.setBitInVector(current.getLevel() + 1);
			
		}
		
		/*FileLoader loader = new FileLoader();
		List<Instance> instList = loader.loadFile();
		float totalRelativeError = 0, maxRelativeError = 0, relativeError;
		int optPrice, appPrice;*/

		//long startTime = CPUTiming.getCpuTime();
		//for (Instance instance : instList) {
			
			/*bruteForceSolver(instance);
			optPrice = instance.getKnapsack().getPrice();
			ratioSolver(instance);
			appPrice = instance.getKnapsack().getPrice();
			relativeError = relativeError(optPrice, appPrice);
			totalRelativeError += relativeError;
			
			// Is current relative error greater than current max relative error?
			if (relativeError > maxRelativeError) {
				maxRelativeError = relativeError;
			}*/
			
		//}
		//long cpuTime = CPUTiming.getCpuTime() - startTime;
		//System.out.println((float)cpuTime/(float) 50);
		//System.out.println("total :"  + totalRelativeError/50);
		//System.out.println("max: " + maxRelativeError);
	}
	
	private static int getUpperBound(int pWeight, int pPrice, int levelToStart, int maxWeight, int maxLevel, ItemPool itemPool) {
		int curWeight = pWeight;
		int upperBound = pPrice;
		
		while ((curWeight != maxWeight) && (levelToStart <= maxLevel)) {
			System.out.println("cw: " + curWeight);
			System.out.println("ub: " + upperBound);
			if(curWeight + itemPool.getItems()[levelToStart].getWeight() <= maxWeight) {
				upperBound += itemPool.getItems()[levelToStart].getPrice();
				curWeight += itemPool.getItems()[levelToStart].getWeight();
				levelToStart++;
			}
			else {
				if (levelToStart < maxLevel) {
					System.out.println("tu!");
					upperBound += (maxWeight - curWeight) * 
					(itemPool.getItems()[levelToStart].getPrice()
							/itemPool.getItems()[levelToStart].getWeight());
				}
				break;
			}
		}
		
		return upperBound;
	}
	
	// Sorts the ItemPool using price/weight ratio
	private static void sortItemPool(ItemPool itemPool) {
		Arrays.sort(itemPool.getItems(), new RatioComparator());
	}
	
	// If we add nWeight to current knapsack weight it will still be under limit?
	public static boolean isUnderLimit(Instance instance, int nWeight) {
		if ((instance.getKnapsack().getWeight() + 
				nWeight) <= instance.getKnapsack().getLimit())
			return true;
		
		return false;
	}
	
	// Compute the relative error
	private static float relativeError(int optPrice, int appPrice) {
		return Math.abs(((float)optPrice - (float)appPrice))/Math.abs((float)optPrice);
	}
}
