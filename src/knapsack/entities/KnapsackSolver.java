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
		else if (n1.getUpperBound() > n2.getUpperBound())
			return -1;
		
		return 0;
	}
}


public class KnapsackSolver {

	public static void main(String[] args) {
		FileLoader loader = new FileLoader();
		List<Instance> instList = loader.loadFile();
		
		long startTime = CPUTiming.getCpuTime();
		for (Instance instance : instList) {
			sortItemPool(instance.getItemPool());
			solveBnB(instance);
		}
		long cpuTime = CPUTiming.getCpuTime() - startTime;
		System.out.println((float)cpuTime/(float) 50);
		
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
	
	private static void solveBnB(Instance instance) {
		Node current, left, right;
		int newPrice, newWeight, nextWeight, bestPrice = -1;
		double newUpperBound;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new UpperBoundComparator());
		
		current = new Node(0,0,0,0,instance.getnSize()); // TODO upravit na automatiku
		queue.add(current);
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			nextWeight = current.getCumWeight() + instance.getItemPool().getItems()[current.getLevel()].getWeight();
			//System.out.println("Current " + current.toString());
			
			if(current.getUpperBound() > bestPrice) {
				if(nextWeight <= instance.getKnapsack().getLimit() ) {
					newPrice = current.getCumPrice() + instance.getItemPool().getItems()[current.getLevel()].getPrice();
					newWeight = current.getCumWeight() + instance.getItemPool().getItems()[current.getLevel()].getWeight();
					newUpperBound = getUpperBound(current.getCumWeight(), current.getCumPrice(), 
							current.getLevel(), instance.getKnapsack().getLimit(), 
							instance.getnSize(), instance.getItemPool());
					
					left = new Node(newPrice, newWeight, newUpperBound, current.getLevel() + 1, current.getItemVector());
					left.setBitInVector(current.getLevel());
					//System.out.println("Left " + left.toString());
					
					if (left.getCumPrice() > bestPrice && left.getLevel() <= (instance.getnSize()))
						bestPrice = left.getCumPrice();
					
					if (left.getUpperBound() > bestPrice && left.getLevel() <= (instance.getnSize()))
						queue.add(left);
				}
				
				newPrice = current.getCumPrice();
				newWeight = current.getCumWeight();
				newUpperBound = getUpperBound(current.getCumWeight(), current.getCumPrice(), 
						current.getLevel() + 1, instance.getKnapsack().getLimit(), 
						instance.getnSize(), instance.getItemPool());
				
				right = new Node(newPrice, newWeight, newUpperBound, current.getLevel() + 1, current.getItemVector());
				//System.out.println("Right " + right.toString());
				
				if (right.getCumPrice() > bestPrice && right.getLevel() <= (instance.getnSize()))
					bestPrice = right.getCumPrice();
				
				if (right.getUpperBound() > bestPrice && right.getLevel() <= (instance.getnSize()))
					queue.add(right);
			}
			
		}
		
		//System.out.println("BEST PRICE: " + bestPrice);
		
	}
	
	private static double getUpperBound(int pWeight, int pPrice, int levelToStart, int maxWeight, int maxLevel, ItemPool itemPool) {
		int curWeight = pWeight;
		double upperBound = pPrice;
		
		while ((curWeight != maxWeight) && (levelToStart < maxLevel)) {
			//System.out.println("cw: " + curWeight);
			//System.out.println("ub: " + upperBound);
			if(curWeight + itemPool.getItems()[levelToStart].getWeight() <= maxWeight) {
				upperBound += (double) itemPool.getItems()[levelToStart].getPrice();
				curWeight += itemPool.getItems()[levelToStart].getWeight();
				levelToStart++;
			}
			else {
				if (levelToStart < maxLevel) {
					//System.out.println("tu!");
					upperBound += ((double)maxWeight - (double)curWeight) * 
					((double)itemPool.getItems()[levelToStart].getPrice()
							/(double)itemPool.getItems()[levelToStart].getWeight());
					//System.out.println("ub: " + upperBound);
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
