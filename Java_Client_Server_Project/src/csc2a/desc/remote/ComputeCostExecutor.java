package csc2a.desc.remote;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputeCostExecutor implements Runnable{
		ArrayList<Integer> SpecificIDs = new ArrayList<>();
		
		
	public ComputeCostExecutor(ArrayList<Integer> SpecificIDs) {
			
			this.SpecificIDs = SpecificIDs;
		}


	@Override
	public void run() {
		 ExecutorService ThreadPool = Executors.newSingleThreadExecutor();
		 for(int i=0;i<SpecificIDs.size();i++)
		 {
			 Runnable runTask =new RemoteTask(SpecificIDs.get(i));
			 ThreadPool.execute(runTask);
		 }
		 ThreadPool.shutdown();
	}

}
