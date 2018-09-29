package org.troublert.models;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

//@CsvRecord( separator = ",", skipFirstLine = true )
@CsvRecord( separator = ",", isOrdered = true)
public class CsvLine {

	//2018-04-09 00:00:00
	 @DataField(pos = 1, position = 1)
	private String datetime;
	
	//"LV-1001"
	 @DataField(pos = 2, position = 2)
	private String postOffice;
	
	//1340
	 @DataField(pos = 3, position = 3)
	private int networkActivity;
	
	//10
	 @DataField(pos = 4, position = 4)
	private int callActivity;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public int getNetworkActivity() {
		return networkActivity;
	}

	public void setNetworkActivity(int networkActivity) {
		this.networkActivity = networkActivity;
	}

	public int getCallActivity() {
		return callActivity;
	}

	public void setCallActivity(int callActivity) {
		this.callActivity = callActivity;
	}

	@Override
	public String toString() {
		return "CsvLine [datetime=" + datetime + ", postOffice=" + postOffice + ", networkActivity=" + networkActivity
				+ ", callActivity=" + callActivity + "]";
	}
	
	
}
