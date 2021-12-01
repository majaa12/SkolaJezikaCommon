package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JezikTest extends GenericEntityTest{

	private Jezik jezik;
	
	@BeforeEach
	void setUp() throws Exception {
		genericEntity = new Jezik(1l, "Italijanski");
		jezik = new Jezik(2l, "Ruski");
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		jezik = null;
	}
	
	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(3L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Nemacki")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Jezik expectedJezik = new Jezik(3l, "Nemacki");
		assertEquals(expectedJezik, genericEntity);
	}
	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(3L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Nemacki")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Jezik expectedJezik = new Jezik(3l, "Nemacki");
		
		assertEquals(1, list.size());
		assertEquals(expectedJezik, list.get(0));
	}

	@Test
	public void getTableName_successful() {
		assertEquals("jezik", jezik.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Naziv", jezik.getColumnNamesForInsert());
	}

	@Test
	public void getInsertValues_successful() {
		assertEquals("'Ruski'",
				jezik.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		assertEquals(2l, jezik.getIDJezika());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("IDJezika = 2", jezik.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Naziv = 'Ruski'",
				jezik.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals("", jezik.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals("", jezik.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by IDJezika", jezik.getSort());
	}
	
	@Test
	public void setNaziv_whenNazivNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> jezik.setNaziv(null),
				"Naziv jezika ne sme biti null vrednost!");
	}

	@Test
	public void setNaziv_whenNazivEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> jezik.setNaziv(""), "Naziv jezika ne sme biti prazan string!");
	}

	@Test
	public void setNaziv_successful() {
		jezik.setNaziv("Nemacki");

		assertEquals("Nemacki", jezik.getNaziv());
	}
	
	@Test
	public void setIDJezika_successful() {
		jezik.setIDJezika(7l);

		assertEquals(7l, jezik.getIDJezika());
	}
	
	@Test
	public void testToString() {
		String s = jezik.toString();
		
		assertTrue(s.contains("Ruski"));
	}
	
	@Test
	public void testEqualsObject() {
		Jezik j = new Jezik(2l, "Ruski");
		Jezik j2 = new Jezik(5l, "Ruski");
		Jezik j3 = new Jezik(2l, "Nemacki");
		assertEquals(false, jezik.equals(genericEntity));
		assertEquals(true, jezik.equals(j));
		assertEquals(false, jezik.equals(j2));
		assertEquals(false, jezik.equals(j3));
	}
}
