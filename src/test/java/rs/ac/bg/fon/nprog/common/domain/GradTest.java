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

public class GradTest extends GenericEntityTest{

	private Grad grad;
	private ArrayList<Adresa> adrese;
	
	@BeforeEach
	void setUp() throws Exception {
		adrese = new ArrayList<>();
		Adresa a = new Adresa(1l, "Makedonska", 2, grad);
		adrese.add(a);
		genericEntity = new Grad();
		grad = new Grad(2l, "Novi Sad", adrese);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		grad = null;
		adrese = null;
	}

	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(3L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Beograd")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Grad expectedGrad = new Grad(3l, "Beograd", new ArrayList<Adresa>());
		assertEquals(expectedGrad, genericEntity);
	}
	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(3L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Beograd")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Grad expectedGrad = new Grad(3l, "Beograd", null);
		
		assertEquals(1, list.size());
		assertEquals(expectedGrad, list.get(0));
	}
	
	@Test
	public void getTableName_successful() {
		assertEquals("grad", grad.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Naziv", grad.getColumnNamesForInsert());
	}

	@Test
	public void getInsertValues_successful() {
		assertEquals("'Novi Sad'",
				grad.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		grad.setId(3l);
		assertEquals(3l, grad.getIDGrada());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("IDGrada = 2", grad.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Naziv = 'Novi Sad'",
				grad.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals("", grad.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals("", grad.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by IDGrada", grad.getSort());
	}
	
	@Test
	public void setNaziv_whenNazivNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> grad.setNaziv(null),
				"Naziv grada ne sme biti null vrednost!");
	}

	@Test
	public void setNaziv_whenNazivEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> grad.setNaziv(""), "Naziv grada ne sme biti prazan string!");
	}

	@Test
	public void setNaziv_successful() {
		grad.setNaziv("Kragujevac");

		assertEquals("Kragujevac", grad.getNaziv());
	}
	
	@Test
	public void setIDGrada_successful() {
		grad.setIDGrada(7l);

		assertEquals(7l, grad.getIDGrada());
	}
	
	@Test
	public void setAdrese_whenAdreseNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> grad.setAdrese(null),
				"Lista adresa grada ne sme biti null vrednost!");
	}
	
	@Test
	public void setAdrese_whenAdreseEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> grad.setAdrese(new ArrayList<Adresa>()), "Grad mora imati makar jednu adresu!");
	}
	
	@Test
	public void setAdrese_successful() {
		grad.setAdrese(adrese);
		
		assertEquals(adrese, grad.getAdrese());
	}
	
	@Test
	public void testToString() {
		String s = grad.toString();
		
		assertTrue(s.contains("Novi Sad"));
	}
	
	@Test
	public void testEqualsObject() {
		Grad g = new Grad(2l, "Novi Sad", adrese);		
		assertEquals(false, grad.equals(genericEntity));
		assertEquals(true, grad.equals(g));
	}
}
