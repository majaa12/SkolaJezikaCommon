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


public class AdministratorTest extends GenericEntityTest {

	private Administrator admin;
	
	@BeforeEach
	void setUp() throws Exception {
		 genericEntity = new Administrator(5l, "Maja", "Colovic", "admin", "admin");
		 admin = new Administrator(2l, "Jovan", "Maric", "jovan", "jovan");
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		admin = null;
	}

	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(5L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("admin")
				.doReturn("admin")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Administrator expectedAdmin = new Administrator(5l, "Maja", "Colovic", "admin", "admin");
		assertEquals(expectedAdmin, genericEntity);
	}
	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(5L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("admin")
				.doReturn("admin")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Administrator expectedAdmin = new Administrator(5l, "Maja", "Colovic", "admin", "admin");
		
		assertEquals(1, list.size());
		assertEquals(expectedAdmin, list.get(0));
	}
	
	@Test
	public void getTableName_successful() {
		assertEquals("administrator", admin.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Ime, Prezime, KorisnickoIme, Lozinka", admin.getColumnNamesForInsert());
	}
	
	@Test
	public void getInsertValues_successful() {
		assertEquals("'Jovan', 'Maric', 'jovan', 'jovan'",
				admin.getInsertValues());
	}

	@Test
	public void setId_successful() {
		admin.setId(5l);
		assertEquals(5l, admin.getIDAdministratora());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("KorisnickoIme = 'jovan' AND Lozinka = 'jovan'", admin.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Ime = 'Jovan', Prezime = 'Maric', KorisnickoIme = 'jovan', Lozinka = 'jovan'",
				admin.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals("", admin.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals("", admin.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by IDAdministratora", admin.getSort());
	}
	
	@Test
	public void setLozinka_whenLozinkaNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> admin.setLozinka(null),
				"Lozinka ne sme biti null vrednost!");
	}

	@Test
	public void setLozinka_whenLozinkaEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> admin.setLozinka(""),
				"Lozinka ne sme biti prazan string!");
	}

	@Test
	public void setLozinka_successful() {
		admin.setLozinka("j123");

		assertEquals("j123", admin.getLozinka());
	}
	
	@Test
	public void setIDAdministratora_successful() {
		admin.setIDAdministratora(3l);

		assertEquals(3l, admin.getIDAdministratora());
	}
	
	@Test
	public void setIme_whenImeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> admin.setIme(null),
				"Ime ne sme biti null vrednost!");
	}

	@Test
	public void setIme_whenImeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> admin.setIme(""),
				"Ime ne sme biti prazan string!");
	}

	@Test
	public void setIme_successful() {
		admin.setIme("Maja");

		assertEquals("Maja", admin.getIme());
	}
	
	@Test
	public void setPrezime_whenPrezimeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> admin.setPrezime(null),
				"Prezime ne sme biti null vrednost!");
	}

	@Test
	public void setPrezime_whenPrezimeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> admin.setPrezime(""),
				"Prezime ne sme biti prazan string!");
	}

	@Test
	public void setPrezime_successful() {
		admin.setPrezime("Colovic");

		assertEquals("Colovic", admin.getPrezime());
	}
	
	@Test
	public void setKorIme_whenKorImeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> admin.setKorisnickoIme(null),
				"Korisnicko ime ne sme biti null vrednost!");
	}

	@Test
	public void setKorIme_whenKorImeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> admin.setKorisnickoIme(""),
				"Korisnicko ime ne sme biti prazan string!");
	}

	@Test
	public void setKorIme_successful() {
		admin.setKorisnickoIme("Maja");

		assertEquals("Maja", admin.getKorisnickoIme());
	}
	
	@Test
	public void testToString() {
		String s = admin.toString();
		
		assertTrue(s.contains("Jovan"));
		assertTrue(s.contains("Maric"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"Maja, 123, Maja, 123, true",
		"Maja, 123, Maja, 456, false",
		"Maja, 123, Jovan, 123, false",
		"Maja, 123, Jovan, 456, false"
	})
	void testEqualsObject(String Korime1, String lozinka1, String Korime2, String lozinka2, boolean eq) {
		admin.setKorisnickoIme(Korime1);
		admin.setLozinka(lozinka1);
		
		Administrator b = new Administrator();
		b.setKorisnickoIme(Korime2);
		b.setLozinka(lozinka2);
		
		assertEquals(eq, admin.equals(b));
	}
}
