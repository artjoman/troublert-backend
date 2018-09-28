package org.troublert.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.troublert.processors.InputDataProcessor;

@Component
public class ProcessFileRoute extends RouteBuilder {
	
	@Autowired
	InputDataProcessor inputDataProcessor;
	
	@Override
	public void configure() throws Exception {
		
		from("file://data")
		.streamCaching()
		.convertBodyTo(String.class)
		.split(body().tokenize("\r\n|\n"))
			.log("-- ${body}")
			.unmarshal()
			.bindy(BindyType.Csv, org.troublert.models.CsvLine.class)
		    .process(inputDataProcessor);
		
	
		
		
	}

}
