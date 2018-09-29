package org.troublert.processors;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.troublert.models.CsvLine;
import org.troublert.models.database.GeoData;
import org.troublert.models.database.NetworkData;
import org.troublert.models.dtr.Data;
import org.troublert.models.dtr.Output;
import org.troublert.repo.GeoRepository;
import org.troublert.repo.NetworkDataRepository;

@Component
public class GetDataSetsProcessor implements Processor{
	
	private static final Logger log = LogManager.getLogger();
	@Autowired
	private NetworkDataRepository repo;
	@Autowired
	GeoRepository geoRepository;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String from = (String) exchange.getIn().getHeader("TIME_FROM");
		String to = (String) exchange.getIn().getHeader("TIME_TO");		
		
		Output output = new Output();
		List<Data> outputDataset = new ArrayList<>();
		
		List<GeoData> geoData = new ArrayList<>();
		try{
			geoData = geoRepository.getAllData();
		}catch (Exception e) {
			log.error("DB fench for network failed",e);
		}
		
		HashMap<String, Map<String,String>> coordinates = new HashMap<>();
		
		for (GeoData geo: geoData){
			Map<String,String> z = new HashMap<>();
			z.put(geo.getLang(), geo.getLongitude());
			
			coordinates.put(geo.getIndex(), z);
		}
		
		
		
		List<NetworkData> networkData = new ArrayList<>();
		try{
			networkData = repo.findBetweenDates(from, to);
		}catch (Exception e) {
			log.error("DB fench for network failed",e);
		}
		
		for (NetworkData elemet: networkData){
			
			if(coordinates.get(elemet.getIndex())!=null){
				
				Data out = new Data();
				out.setIndex(elemet.getIndex());
				
				Map<String, String> c = coordinates.get(elemet.getIndex());		
				
				c.forEach((k,v)->{
					out.setLat(k);
					out.setLng(v);
				});
				
				Random rand = new Random();
				int  n = rand.nextInt(10) + 1;
				
				out.setDif(String.valueOf(n));
				out.setRadius("5000");
				
				outputDataset.add(out);
				
			}
						
		}
		
		output.setData(outputDataset);
		//returns data to routing
		exchange.getIn().setBody(output);
	}

}
