package org.troublert.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.troublert.domain.Activity;
import org.troublert.domain.FullData;
import org.troublert.domain.GeoData;
import org.troublert.domain.NetworkActivity;
import org.troublert.domain.ResultData;
import org.troublert.models.CsvLine;

public class DataAccessUtil {
	private static final Logger log = LogManager.getLogManager().getLogger(DataAccessUtil.class.getName());
	private final static String INSERT_STMNT_NETWORK_ACTIVITY = "INSERT INTO network_data (datetime, calls, `data`, `index`) VALUES (?, ?, ?, ?);";
	private final static String SELECT_STMNT_LONGITUDE_LATTITUDE = "SELECT `long`, lang FROM geo_data g WHERE g.`index` = ?;";
	private final static String SELECT_STMNT_NETWORK_ACTIVITY_OR_BASELINE = "SELECT datetime, calls, `data`, `index` FROM %s n WHERE n.datetime = ? AND n.`index` = ?;";
	private final static String SELECT_STMNT_UNIQUE_INDEXES = "SELECT  DISTINCT `index` FROM geo_data;";


	public static NetworkActivity processCsvLine(CsvLine csvLine, Connection dbConnection){
		//TODO null checks
		//TODO if date is not parsed it's probably bad
		Date datetime = DateUtil.parseDateFromCsv(csvLine.getDatetime());
		String index = csvLine.getPostOffice();
		Integer networkActivity = Integer.valueOf(csvLine.getNetworkActivity());
		Integer callActivity = Integer.valueOf(csvLine.getCallActivity());
		GeoData geoData = null;
		PreparedStatement stmtInsert = null;
		try{
			stmtInsert = dbConnection.prepareStatement(INSERT_STMNT_NETWORK_ACTIVITY);
			stmtInsert.setDate(1, datetime);
			stmtInsert.setInt(2, callActivity);
			stmtInsert.setInt(3, networkActivity);
			//TODO what if index isn't a valid index from geo_data
			stmtInsert.setString(4, index);
			int countInserted = stmtInsert.executeUpdate();
			log.info(String.format("Statement %s executed, datetime=%s, callActivity=%s, dataActivity=%s, index=%s, countInserted=%s", INSERT_STMNT_NETWORK_ACTIVITY, 
					datetime, callActivity, networkActivity, index, countInserted));

			geoData = queryGeoData(index, dbConnection);

		} catch (SQLException e){
			log.warning(e.getMessage());
		}finally {

			if (stmtInsert != null) {
				try {
					stmtInsert.close();
				} catch (SQLException e) {
					log.warning(e.getMessage());
				}
			}

		}
		return new NetworkActivity(datetime, new Activity(networkActivity, callActivity), geoData);
	}

	//TODO is this really needed public?
	public static NetworkActivity queryNetworkActivityOrBaseline(Date datetime, String index, String tableName, GeoData geoData, Connection dbConnection){
		//TODO null checks
		PreparedStatement stmtSelect = null;
		Integer networkActivity = null;
		Integer callActivity = null;
		try{
			stmtSelect = dbConnection.prepareStatement(String.format(SELECT_STMNT_NETWORK_ACTIVITY_OR_BASELINE, tableName));
			stmtSelect.setDate(1, datetime);
			stmtSelect.setString(2, index);
			ResultSet rset = stmtSelect.executeQuery();
			log.info(String.format("Statement %s executed, datetime=%s, index=%s, result set has %s rows", String.format(SELECT_STMNT_NETWORK_ACTIVITY_OR_BASELINE, tableName), datetime, 
					index, getRowCount(rset)));
			//TODO what if there are more than one row or what if there aren't any
			while(rset.next()){
				//TODO is it `data` or data
				networkActivity = Integer.valueOf(rset.getInt(NetworkActivity.Attrs.NETWORK_ACTIVITY.attribute()));
				callActivity = Integer.valueOf(rset.getInt(NetworkActivity.Attrs.CALL_ACTIVITY.attribute()));
				break;
			}
			if (geoData == null){
				geoData = queryGeoData(index, dbConnection);
			}

		} catch (SQLException e){
			log.warning(e.getMessage());
		}finally {

			if (stmtSelect != null) {
				try {
					stmtSelect.close();
				} catch (SQLException e) {
					log.warning(e.getMessage());
				}
			}

		}
		return new NetworkActivity(datetime, new Activity(networkActivity, callActivity), geoData);
	}

	private static GeoData queryGeoData(String index, Connection dbConnection){
		PreparedStatement stmtSelect = null;
		String longitude = null;
		String lattitude = null;
		String radius = null;
		try{
			stmtSelect = dbConnection.prepareStatement(SELECT_STMNT_LONGITUDE_LATTITUDE);
			stmtSelect.setString(1, index);
			ResultSet rset = stmtSelect.executeQuery();
			log.info(String.format("Statement %s executed, index=%s, result set has %s rows", SELECT_STMNT_LONGITUDE_LATTITUDE, index, 
					getRowCount(rset)));
			//TODO what if there are more than one row or what if there aren't any
			while(rset.next()){
				//TODO is it `long` or long?!
				longitude = rset.getString(GeoData.Attrs.LONGITUDE.attribute());
				lattitude = rset.getString(GeoData.Attrs.LATTITUDE.attribute());
				radius = rset.getString(GeoData.Attrs.RADIUS.attribute());
				break;
			}

		} catch (SQLException e){
			log.warning(e.getMessage());
		}finally {

			if (stmtSelect != null) {
				try {
					stmtSelect.close();
				} catch (SQLException e) {
					log.warning(e.getMessage());
				}
			}

		}
		return new GeoData(index, longitude, lattitude, radius);
	}


	private static List<String> queryPostalIndexes(Connection dbConnection){
		List<String> result = new LinkedList<String>();
		PreparedStatement stmtSelect = null;
		try{
			stmtSelect = dbConnection.prepareStatement(SELECT_STMNT_UNIQUE_INDEXES);
			ResultSet rset = stmtSelect.executeQuery();
			log.info(String.format("Statement %s executed, result set has %s rows", SELECT_STMNT_UNIQUE_INDEXES, 
					getRowCount(rset)));
			while(rset.next()){
				//TODO is it `index` or index?!
				result.add(rset.getString(GeoData.Attrs.INDEX.attribute()));
			}

		} catch (SQLException e){
			log.warning(e.getMessage());
		}finally {

			if (stmtSelect != null) {
				try {
					stmtSelect.close();
				} catch (SQLException e) {
					log.warning(e.getMessage());
				}
			}

		}
		return result;
	}

	//TODO is this really needed public?
	public static List<FullData> queryData(Date datetime, Connection dbConnection){
		List<FullData> result = new LinkedList<FullData>();
		List<String> indexes = queryPostalIndexes(dbConnection);
		for (String index:indexes){
			result.add(queryFullData(datetime, index, dbConnection));
		}
		return result;
	}
	
	private static FullData queryFullData(Date datetime, String index, Connection dbConnection){
		NetworkActivity networkActivity = queryNetworkActivityOrBaseline(datetime, index, FullData.Tables.NETWORK_ACTIVITY.table(), null, dbConnection);
		NetworkActivity baseline = queryNetworkActivityOrBaseline(datetime, index, FullData.Tables.BASELINE.table(), networkActivity.getGeoData(), dbConnection);
		return new FullData(networkActivity, baseline);
	}
	
	public static List<ResultData> queryAndCalculateResult(Date datetime, Integer treshold, Connection dbConnection){
		List<ResultData> result = new LinkedList<ResultData>();
		List<FullData> fullData = queryData(datetime, dbConnection);
		for (FullData data:fullData){
			result.add(new ResultData(data, treshold));
		}
		return result;
	}


	private static int getRowCount(ResultSet resultSet) throws SQLException{
		resultSet.last();
		int size = resultSet.getRow();
		resultSet.beforeFirst();
		return size;
	}

}
