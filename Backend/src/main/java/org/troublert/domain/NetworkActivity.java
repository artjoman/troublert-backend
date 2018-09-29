package org.troublert.domain;

import java.sql.Date;

public class NetworkActivity {
	private Date datetime;
	private Activity activity;
	private GeoData geoData;

	public NetworkActivity(Date datetime, Activity activity, GeoData geoData) {
		super();
		this.datetime = datetime;
		this.activity = activity;
		this.geoData = geoData;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public GeoData getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoData geoData) {
		this.geoData = geoData;
	}

	public enum Attrs{
		DATETIME("datetime"),
		NETWORK_ACTIVITY("data"),
		CALL_ACTIVITY("calls"),
		INDEX("index");

		private String attribute;

		private Attrs(String attribute){
			this.attribute= attribute;
		}

		public String attribute(){
			return this.attribute;
		}
	}
}
