package org.troublert.domain;

import java.sql.Date;

public class ResultData {
	private Date datetime;
	private GeoData geoData;
	private Activity actual;
	private Activity baseline;
	private Activity deviationPercent;
	private Activity overTresholdDeviation;

	public ResultData(FullData fullData, Integer treshold){
      this.deviationPercent = calculateDeviationPercent(fullData);
      this.overTresholdDeviation = calculateOverTresholdDeviation(this.deviationPercent, treshold);
      this.actual = fullData.getNetworkActivity().getActivity();
      this.baseline = fullData.getBaseline().getActivity();
      this.datetime = fullData.getNetworkActivity().getDatetime();
      this.geoData = fullData.getNetworkActivity().getGeoData();
	}
	
	private Activity calculateDeviationPercent(FullData fullData){
		int calls = fullData.getNetworkActivity().getActivity().getCalls()/fullData.getBaseline().getActivity().getCalls();
		int data = fullData.getNetworkActivity().getActivity().getData()/fullData.getBaseline().getActivity().getData();
		return new Activity(Integer.valueOf(calls), Integer.valueOf(data));
	}
	
	private Activity calculateOverTresholdDeviation(Activity deviationPercent, Integer treshold){
		int calls = deviationPercent.getCalls() > 100 ? deviationPercent.getCalls() - 100 : 100 - deviationPercent.getCalls();
		int callsDeviation = calls > treshold ? calls - treshold : 0;
		int data = deviationPercent.getCalls() > 100 ? deviationPercent.getCalls() - 100 : 100 - deviationPercent.getCalls();
		int dataDeviation = data > treshold ? data - treshold : 0;
		return new Activity(Integer.valueOf(callsDeviation), Integer.valueOf(dataDeviation));
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Activity getActual() {
		return actual;
	}

	public void setActual(Activity actual) {
		this.actual = actual;
	}

	public Activity getBaseline() {
		return baseline;
	}

	public void setBaseline(Activity baseline) {
		this.baseline = baseline;
	}

	public Activity getDeviationPercent() {
		return deviationPercent;
	}

	public void setDeviationPercent(Activity deviationPercent) {
		this.deviationPercent = deviationPercent;
	}

	public GeoData getGeoData() {
		return geoData;
	}

	public void setGeoData(GeoData geoData) {
		this.geoData = geoData;
	}

	public Activity getOverTresholdDeviation() {
		return overTresholdDeviation;
	}

	public void setOverTresholdDeviation(Activity overTresholdDeviation) {
		this.overTresholdDeviation = overTresholdDeviation;
	}
}
