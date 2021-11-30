package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class GenericEntityTest {
	
	protected GenericEntity genericEntity;
	
	@Test
	protected void fillFromRS_whenDoesNotExistInDb_thenException() throws SQLException {
		ResultSet resultSet = mock(ResultSet.class);
		Mockito.doReturn(false).when(resultSet).next();
		//Mockito.when(resultSet.next()).thenReturn(false);
		
		assertThrows(Exception.class, () -> genericEntity.fillFromRS(resultSet));
	}
	
	@Test
	protected void fillFromRS_whenRSIsNull_thenException() {		
		assertThrows(Exception.class, () -> genericEntity.fillFromRS(null), "rs ne moze biti null!");
	}
	
	@Test
	protected void fillListFromRS_whenRSIsNull_thenException() {		
		assertThrows(Exception.class, () -> genericEntity.fillListFromRS(null), "rs ne moze biti null!");
	}
	
	@Test
	protected void fillListFromRS_whenDoesNotExistInDb_thenReturnEmptyList() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		Mockito.doReturn(false).when(resultSet).next();
		
		List<GenericEntity> result = genericEntity.fillListFromRS(resultSet);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	protected void getTableName_notNull() {
		assertNotNull(genericEntity.getTableName());
	}
	
	@Test
	protected void getColumnNamesForInsert_notNull() {
		assertNotNull(genericEntity.getColumnNamesForInsert());
	}
	
	@Test
	protected void getInsertValues_notNull() {
		assertNotNull(genericEntity.getInsertValues());
	}
	
	@Test
	protected void getConditionForOne_notNull() {
		assertNotNull(genericEntity.getConditionForOne());
	}
	
	@Test
	protected void setUpdateValues_notNull() {
		assertNotNull(genericEntity.setUpdateValues());
	}
	
	@Test
	protected void getJoinCondition_notNull() {
		assertNotNull(genericEntity.getJoinCondition());
	}
	
	@Test
	protected void getConditionForMore_notNull() {
		assertNotNull(genericEntity.getConditionForMore());
	}
	
	@Test
	protected void getSort_notNull() {
		assertNotNull(genericEntity.getSort());
	}

}
