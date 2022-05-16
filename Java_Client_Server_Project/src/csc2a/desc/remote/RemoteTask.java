package csc2a.desc.remote;

import java.text.DecimalFormat;

public class RemoteTask implements Runnable{
	private int rDisaster_ID=0; 
	
	/*
	 * contructor
	 */
	public RemoteTask(int rDisaster_ID) {
		this.rDisaster_ID = rDisaster_ID;
	}
	@Override
	public void run() {
		
		//creating a decimal convertor
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		System.out.println(rDisaster_ID+":"+"D$"+df.format(RemoteReportProcessor.processReportCost(rDisaster_ID))+"M");
		
	}

}
