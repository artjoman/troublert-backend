package org.troublert.models.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geo_data")
public class GeoData {
	
	public GeoData(){}
	
	@Id
	@Column(name="indexaa")
	private String index;
	
	@Column(name="mlan")
	private String lang;
	
	@Column(name="mlon")
	private String longitude;
	
	@Column(name="radious")
	private String radious;

	public GeoData(String index, String lang, String longitude, String radious) {
		super();
		this.index = index;
		this.lang = lang;
		this.longitude = longitude;
		this.radious = radious;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRadious() {
		return radious;
	}

	public void setRadious(String radious) {
		this.radious = radious;
	}

	@Override
	public String toString() {
		return "GeoData [index=" + index + ", lang=" + lang + ", longitude=" + longitude + ", radious=" + radious + "]";
	}

}
