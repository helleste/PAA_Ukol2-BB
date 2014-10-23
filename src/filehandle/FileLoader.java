package filehandle;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import knapsack.entities.Instance;
import knapsack.entities.Item;
import knapsack.entities.ItemPool;
import knapsack.entities.Knapsack;


public class FileLoader {

	private String filename = "src/Files/inst/knap_30.inst.dat";
	
	// Parse a line of the given file and create Instance from the data
	private Instance parseLine(String strLine) {
		
		String[] lineData = strLine.split(" ");
		int instId = Integer.parseInt(lineData[0]);
		int instSize = Integer.parseInt(lineData[1]);
		Knapsack instKnapsack = new Knapsack();
		instKnapsack.setLimit(Integer.parseInt(lineData[2]));
		ItemPool instItemPool = new ItemPool(instSize);
		int itemId = 0;
		
		for (int i = 3; i < lineData.length; i+=2) {
			Item instItem = new Item(itemId, 
					Integer.parseInt(lineData[i]), 
					Integer.parseInt(lineData[i+1]));
			instItemPool.addToPool(instItem, itemId);
			itemId++;
		}
		
		Instance instance = new Instance(instId, instSize, instItemPool, instKnapsack);
		return instance;
	}
	
	// Parse the file and return array of instances
	public List<Instance> loadFile() {
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			List<Instance> instList = new ArrayList<Instance>();
			
			// Read till you reach EOF
			while ((strLine = br.readLine()) != null) {
				instList.add(parseLine(strLine));
			}
			
			// Close the input stream
			in.close();
			
			// Return list of instances
			return instList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
