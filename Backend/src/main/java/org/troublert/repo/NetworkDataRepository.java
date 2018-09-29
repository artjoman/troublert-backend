package org.troublert.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.troublert.models.database.NetworkData;

public interface NetworkDataRepository extends CrudRepository<NetworkData, String> {
	
	@Query("SELECT c FROM NetworkData c WHERE c.index=:index")
	List<NetworkData> findByIndex(@Param("index") String index);
	
	@Query("SELECT c FROM NetworkData c WHERE c.datetime between ?1 and ?2")
	List<NetworkData> findBetweenDates(@Param("from") String from, @Param("to") String to);
	//SELECT * FROM schemaName.TableName WHERE datetime > '2017-02-09 00:00:00' AND datetime < '2017-06-09 00:00:00';
	//where x.startDate between ?1 and ?2

}
