package org.troublert.domain;

public class GeoData {
	private String index;
	private String longittude;
	private String latitude;
	private String radius;

	public GeoData(String index, String longittude, String lattitude, String radius) {
		super();
		this.index = index;
		this.longittude = longittude;
		this.latitude = lattitude;
		this.radius = radius;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getLongittude() {
		return longittude;
	}

	public void setLongittude(String longittude) {
		this.longittude = longittude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String lattitude) {
		this.latitude = lattitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public enum Attrs{
		INDEX("index"),
		LONGITUDE("long"),
		LATTITUDE("lang"),
		RADIUS("radious");

		private String attribute;

		private Attrs(String attribute){
			this.attribute= attribute;
		}

		public String attribute(){
			return this.attribute;
		}
	}
}
