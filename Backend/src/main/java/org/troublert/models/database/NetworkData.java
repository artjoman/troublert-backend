package org.troublert.models.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="network_data")
public class NetworkData {

	public NetworkData() {
	}

	//
	@Column(name="datetime")
	private String datetime;
	@Column(name="calls")
	private int calls;
	@Column(name="data")
	private int data;
	
	@Id
	@Column(name="index")
	private String index;
	
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getCalls() {
		return calls;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public NetworkData(String datetime, int calls, int data, String index) {
		super();
		this.datetime = datetime;
		this.calls = calls;
		this.data = data;
		this.index = index;
	}

	@Override
	public String toString() {
		return "NetworkData [datetime=" + datetime + ", calls=" + calls + ", data=" + data + ", index=" + index + "]";
	}
	
}
