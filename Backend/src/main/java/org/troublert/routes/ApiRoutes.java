package org.troublert.routes;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.troublert.models.dtr.Data;
import org.troublert.models.dtr.Output;
import org.troublert.processors.GetDataSetsProcessor;
import org.troublert.processors.InputDataProcessor;

@Component
public class ApiRoutes extends RouteBuilder {
	
	@Autowired
	InputDataProcessor inputDataProcessor;
	
	@Autowired
	GetDataSetsProcessor getDataSetsProcessor;
	
	@Override
	public void configure() throws Exception {
		
		from("cxfrs:bean:rsServer")
		.log("Processing CXF route....http method: ${header.CamelHttpMethod}")
        .log("Processing CXF route....http path: ${header.CamelHttpPath}")
        .log("Processing CXF route....operation name: ${header.operationName}")
        .choice()
        .when(header("operationName").isEqualTo("getData"))
		.process(x->{					
			String params[] = ((String) x.getIn().getHeader("CamelHttpPath")).substring(9).split("/");
			
			x.getIn().setHeader("TIME_FROM", params[0]);
			x.getIn().setHeader("TIME_TO", params[1]);
			
			//dummy data for now	
//			Output out = new Output();
//			Data data = new Data();
//			data.setDif("10");
//			
//			List<Data> dataList = new ArrayList<>();
//			dataList.add(data);
//			out.setData(dataList );
//			x.getIn().setBody(out);
		}
			)	
		.process(getDataSetsProcessor);
		
		//.log("${body}");
		
	
		
		
	}

}
