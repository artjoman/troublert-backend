package org.troublert.processors;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.troublert.models.CsvLine;

@Component
public class InputDataProcessor implements Processor{
	
	private static final Logger log = LogManager.getLogger();
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		//lets read data object
		CsvLine line = exchange.getIn().getBody(CsvLine.class);
		
		log.info(line.toString());
		
		//returns data to routing
		exchange.getIn().setBody("Whatever kind of object");
	}

}
