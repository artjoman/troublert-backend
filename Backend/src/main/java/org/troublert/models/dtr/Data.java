package org.troublert.models.dtr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"index",
"lat",
"lng",
"radius",
"dif"
})
public class Data {

@JsonProperty("index")
private String index;
@JsonProperty("lat")
private String lat;
@JsonProperty("lng")
private String lng;
@JsonProperty("radius")
private String radius;
@JsonProperty("dif")
private String dif;

@JsonProperty("index")
public String getIndex() {
return index;
}

@JsonProperty("index")
public void setIndex(String index) {
this.index = index;
}

@JsonProperty("lat")
public String getLat() {
return lat;
}

@JsonProperty("lat")
public void setLat(String lat) {
this.lat = lat;
}

@JsonProperty("lng")
public String getLng() {
return lng;
}

@JsonProperty("lng")
public void setLng(String lng) {
this.lng = lng;
}

@JsonProperty("radius")
public String getRadius() {
return radius;
}

@JsonProperty("radius")
public void setRadius(String radius) {
this.radius = radius;
}

@JsonProperty("dif")
public String getDif() {
return dif;
}

@JsonProperty("dif")
public void setDif(String dif) {
this.dif = dif;
}

}