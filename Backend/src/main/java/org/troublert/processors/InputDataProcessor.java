package org.troublert.processors;


import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.troublert.models.CsvLine;
import org.troublert.models.database.NetworkData;
import org.troublert.repo.NetworkDataRepository;

@Component
public class InputDataProcessor implements Processor{
	
	private static final Logger log = LogManager.getLogger();
	@Autowired
	private NetworkDataRepository repo;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		//lets read data object
		CsvLine line = exchange.getIn().getBody(CsvLine.class);
		
		//log.info(line.toString());
		
	//	List<NetworkData> = new ArrayList<>;
		try{
			List<NetworkData> data = repo.findByIndex("LV-1001");
			log.info(data.toString());
		}catch (Exception e) {
			log.error("DB failed",e);
		}
		
		
		//returns data to routing
		exchange.getIn().setBody("Whatever kind of object");
	}

}
