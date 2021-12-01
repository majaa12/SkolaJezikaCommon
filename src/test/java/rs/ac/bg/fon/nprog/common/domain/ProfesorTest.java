package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class ProfesorTest extends GenericEntityTest{

	private Profesor profesor;
	private Jezik jezik;
	
	@BeforeEach
	void setUp() throws Exception {
		jezik = new Jezik(1l, "Francuski");
		genericEntity = new Profesor(3l, "Maja", "Colovic", "0612365456", "maja@gmail.com", jezik);
		profesor = new Profesor(5l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		profesor = null;
	}

	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(3L).doReturn(1l).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("0612365456")
				.doReturn("maja@gmail.com")				
				.doReturn("Francuski")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Profesor expectedProfesor = new Profesor(3l, "Maja", "Colovic", "0612365456", "maja@gmail.com", jezik);
		assertEquals(expectedProfesor, genericEntity);
	}

	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(3L).doReturn(1l).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("0612365456")
				.doReturn("maja@gmail.com")
				.doReturn("Francuski")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Profesor expectedProfesor = new Profesor(3l, "Maja", "Colovic", "0612365456", "maja@gmail.com", jezik);
		
		assertEquals(1, list.size());
		assertEquals(expectedProfesor, list.get(0));
	}
	
	@Test
	public void getTableName_successful() {
		assertEquals("profesor", profesor.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Ime, Prezime, Telefon, Email, IDJezika", profesor.getColumnNamesForInsert());
	}
	
	@Test
	public void getInsertValues_successful() {
		assertEquals("'Ivana', 'Ilic', '0612365456', 'ivana@gmail.com', 1",
				profesor.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		assertEquals(5l, profesor.getIDProfesora());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("p.IDJezika = 1", profesor.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Ime = 'Ivana', Prezime = 'Ilic', Telefon = '0612365456', Email = 'ivana@gmail.com', IDJezika = 1",
				profesor.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals(" p join jezik j on p.IDJezika = j.IDJezika", profesor.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals(" where p.IDJezika = 1 and p.Ime like 'Ivana%' and p.Prezime like 'Ilic%'", profesor.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by IDProfesora", profesor.getSort());
	}
	
	@Test
	public void setJezik_whenJezikNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> profesor.setJezik(null),
				"Jezik profesora ne sme biti null vrednost!");
	}
	
	@Test
	public void setJezik_successful() {
		Jezik j = new Jezik(2l, "Nemacki");
		profesor.setJezik(j);
		assertEquals(j, profesor.getJezik());
	}
	
	@Test
	public void setIDProfesora_successful() {
		profesor.setIDProfesora(2l);

		assertEquals(2l, profesor.getIDProfesora());
	}
	
	@Test
	public void setIme_whenImeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> profesor.setIme(null),
				"Ime profesora ne sme biti null vrednost!");
	}

	@Test
	public void setIme_whenImeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> profesor.setIme(""), "Ime profesora ne sme biti prazan string!");
	}

	@Test
	public void setIme_successful() {
		profesor.setIme("Jovan");

		assertEquals("Jovan", profesor.getIme());
	}
	
	@Test
	public void setPrezime_whenPrezimeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> profesor.setPrezime(null),
				"Prezime profesora ne sme biti null vrednost!");
	}

	@Test
	public void setPrezime_whenPrezimeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> profesor.setPrezime(""),
				"Prezime profesora ne sme biti prazan string!");
	}

	@Test
	public void setPrezime_successful() {
		profesor.setPrezime("Colovic");

		assertEquals("Colovic", profesor.getPrezime());
	}
	
	@Test
	public void setTelefon_whenTelefonNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> profesor.setTelefon(null),
				"Telefon profesora ne sme biti null vrednost!");
	}

	@Test
	public void setTelefon_whenTelefonEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> profesor.setTelefon(""),
				"Telefon profesora ne sme biti prazan string!");
	}

	@Test
	public void setTelefon_successful() {
		profesor.setTelefon("0621234695");

		assertEquals("0621234695", profesor.getTelefon());
	}
	
	@Test
	public void setEmail_whenEmailNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> profesor.setEmail(null),
				"Email profesora ne sme biti null vrednost!");
	}

	@Test
	public void setEmail_whenEmailEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> profesor.setEmail(""),
				"Email profesora ne sme biti prazan string!");
	}

	@Test
	public void setEmail_whenEmailDoesNotHaveAT_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> profesor.setEmail("majagmail.com"),
				"Email mora sadrzati @");
	}
	
	@Test
	public void setEmail_successful() {
		profesor.setEmail("maja@gmail.com");

		assertEquals("maja@gmail.com", profesor.getEmail());
	}
	
	@Test
	public void testToString() {
		String s = profesor.toString();
		
		assertTrue(s.contains("Ivana"));
		assertTrue(s.contains("Ilic"));
	}
	
	@Test
	public void testEqualsObject() {
		Profesor p = new Profesor(5l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
		assertEquals(false, profesor.equals(genericEntity));
		assertEquals(true, profesor.equals(p));
	}
}
