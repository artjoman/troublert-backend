package org.troublert.models.dtr;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"data"
})
public class Output {

@JsonProperty("data")
private List<Data> data = null;

@JsonProperty("data")
public List<Data> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Data> data) {
this.data = data;
}

}