import java.util.ArrayList;

import csc2a.desc.remote.ComputeCostExecutor;

public class Main {

	public static void main(String[] args) {
		
ArrayList<Integer> SpecificIDs = new ArrayList<>();
		
		SpecificIDs.add(1);
		SpecificIDs.add(10);
		SpecificIDs.add(15);
		SpecificIDs.add(23);
		SpecificIDs.add(25);
		SpecificIDs.add(26);
		SpecificIDs.add(66);
		SpecificIDs.add(78);
		SpecificIDs.add(89);
		SpecificIDs.add(190);
		
		ComputeCostExecutor computerExecutor = new ComputeCostExecutor(SpecificIDs);
		Thread thread=new Thread(computerExecutor);
		
		thread.start();
		
	}

}
