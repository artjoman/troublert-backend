package org.troublert.domain;

public class Activity {
	private Integer calls;
	private Integer data;

	public Activity(Integer calls, Integer data) {
		super();
		this.calls = calls;
		this.data = data;
	}

	public Integer getCalls() {
		return calls;
	}

	public void setCalls(Integer calls) {
		this.calls = calls;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}
}
