package org.troublert.domain;

public class FullData {
	private NetworkActivity networkActivity;
	private NetworkActivity baseline;

	public FullData(NetworkActivity networkActivity, NetworkActivity baseline) {
		super();
		this.networkActivity = networkActivity;
		this.baseline = baseline;
	}

	public NetworkActivity getNetworkActivity() {
		return networkActivity;
	}

	public void setNetworkActivity(NetworkActivity networkActivity) {
		this.networkActivity = networkActivity;
	}

	public NetworkActivity getBaseline() {
		return baseline;
	}

	public void setBaseline(NetworkActivity baseline) {
		this.baseline = baseline;
	}

	public enum Tables{
		NETWORK_ACTIVITY("network_activity"),
		//TODO actual baseline table name
		BASELINE("baseline");

		private String table;

		private Tables(String table){
			this.table= table;
		}

		public String table(){
			return this.table;
		}
	}
}
