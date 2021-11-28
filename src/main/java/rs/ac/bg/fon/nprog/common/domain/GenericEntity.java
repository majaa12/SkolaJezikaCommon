package rs.ac.bg.fon.nprog.common.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface GenericEntity extends Serializable {

	String getTableName();

	String getColumnNamesForInsert();

	String getInsertValues();

	void setId(Long id);

	String getConditionForOne();

	String setUpdateValues();

	GenericEntity fillFromRS(ResultSet rs) throws Exception;

	List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception;

	String getJoinCondition();

	String getConditionForMore();

	String getSort();
}
