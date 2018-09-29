package org.troublert.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.troublert.models.database.GeoData;

public interface GeoRepository extends CrudRepository<GeoData, String> {
	
//	@Query("SELECT c FROM GeoData c WHERE c.index_1=:index")
//	List<GeoData> findByIndex(@Param("index") String index);
	
	//@Query("SELECT top 1 c FROM NetworkData")
	@Query(value="SELECT indexaa, max(langaa) as mlan, max(longaa) as mlon, radious FROM geo_data group by indexaa", nativeQuery = true)
	List<GeoData> getAllData();

}
