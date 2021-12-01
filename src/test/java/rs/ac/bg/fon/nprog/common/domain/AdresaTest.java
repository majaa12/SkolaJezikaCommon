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

public class AdresaTest extends GenericEntityTest{

	private Adresa adresa;
	private Grad grad;
	
	@BeforeEach
	void setUp() throws Exception {
		grad = new Grad(3l, "Beograd", null);
		genericEntity = new Adresa(1l, "Makedonska", 2, grad);
		adresa = new Adresa(2l, "Glavna", 3, grad);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		adresa = null;
		grad = null;
	}

	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(1L).doReturn(3l).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn(2).when(rs).getInt(Mockito.anyString());
		Mockito.doReturn("Makedonska")
				.doReturn("Beograd")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Adresa expectedAdresa = new Adresa(1l, "Makedonska", 2, grad);
		assertEquals(expectedAdresa, genericEntity);
	}
	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(1L).doReturn(3l).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn(2).when(rs).getInt(Mockito.anyString());
		Mockito.doReturn("Makedonska")
				.doReturn("Beograd")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Adresa expectedAdresa = new Adresa(1l, "Makedonska", 2, grad);
		
		assertEquals(1, list.size());
		assertEquals(expectedAdresa, list.get(0));
	}

	@Test
	public void getTableName_successful() {
		assertEquals("adresa", adresa.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Ulica, Broj, IDGrada", adresa.getColumnNamesForInsert());
	}
	
	@Test
	public void getInsertValues_successful() {
		assertEquals("'Glavna', 3, 3",
				adresa.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		adresa.setId(3l);
		assertEquals(3l, adresa.getIDAdrese());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("a.IDAdrese = 2", adresa.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Ulica = 'Glavna', Broj = 3, IDGrada = 3",
				adresa.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals(" a JOIN grad g ON a.IDGrada = g.IDGrada", adresa.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals(" where a.IDGrada = 3", adresa.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by a.IDAdrese", adresa.getSort());
	}
	
	@Test
	public void setGrad_whenGradNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> adresa.setGrad(null),
				"Grad ne sme biti null vrednost!");
	}
	
	@Test
	public void setGrad_successful() {
		Grad g = new Grad(1l, "Novi Sad", null);
		adresa.setGrad(g);
		assertEquals(g, adresa.getGrad());
	}
	
	@Test
	public void setIDAdrese_successful() {
		adresa.setIDAdrese(2l);

		assertEquals(2l, adresa.getIDAdrese());
	}
	
	@Test
	public void setUlica_whenUlicaNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> adresa.setUlica(null),
				"Ulica ne sme biti null vrednost!");
	}

	@Test
	public void setUlica_whenUlicaEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> adresa.setUlica(""), "Ulica ne sme biti prazan string!");
	}

	@Test
	public void setUlica_successful() {
		adresa.setUlica("Makedonska");

		assertEquals("Makedonska", adresa.getUlica());
	}
	
	@Test
	public void setBroj_whenBrojLessThenZero_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> adresa.setBroj(-5),
				"Broj ne sme biti negativan ili 0!");
	}

	@Test
	public void setBroj_successful() {
		adresa.setBroj(5);

		assertEquals(5, adresa.getBroj());
	}
	
	@Test
	public void testToString() {
		String s = adresa.toString();
		
		assertTrue(s.contains("Glavna"));
		assertTrue(s.contains("Beograd"));
		assertTrue(s.contains(3+""));
	}
	
	@Test
	public void testEqualsObject() {
		Adresa a = new Adresa(2l, "Glavna", 3, grad);
		assertEquals(false, adresa.equals(genericEntity));
		assertEquals(true, adresa.equals(a));
	}
}

