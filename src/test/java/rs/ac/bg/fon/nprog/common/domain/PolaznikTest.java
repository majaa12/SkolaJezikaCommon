package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;


public class PolaznikTest extends GenericEntityTest {
	private Polaznik polaznik;

	@BeforeEach
	public void setUp() throws Exception {
		genericEntity = new Polaznik(5l, "Maja", "Colovic", "1547995485254", "0621234567", "maja@gmail.com", "Makedonska 1");
		polaznik = new Polaznik();
	}

	@AfterEach
	public void tearDown() throws Exception {
		genericEntity = null;
		polaznik = null;
	}

	@Test
	public void fillFromResultSet_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(5L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("12069965654565")
				.doReturn("0699999999")
				.doReturn("maja.col@gmail.com")
				.doReturn("Borska 34b")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Polaznik expectedPolaznik = new Polaznik(5L, "Maja", "Colovic", "12069965654565", "0699999999",
				"maja.col@gmail.com", "Borska 34b");
		assertEquals(expectedPolaznik, genericEntity);
	}

	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(5L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Maja")
				.doReturn("Colovic")
				.doReturn("12069965654565")
				.doReturn("0699999999")
				.doReturn("maja.col@gmail.com")
				.doReturn("Borska 34b")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Polaznik expectedPolaznik = new Polaznik(5L, "Maja", "Colovic", "12069965654565", "0699999999",
				"maja.col@gmail.com", "Borska 34b");
		
		assertEquals(1, list.size());
		assertEquals(expectedPolaznik, list.get(0));
	}
	
	@Test
	public void getTableName_successful() {
		assertEquals("polaznik", polaznik.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Ime, Prezime, Jmbg, Telefon, Email, Adresa", polaznik.getColumnNamesForInsert());
	}
	
	@Test
	public void getInsertValues_successful() {
		polaznik.setIme("Maja");
		polaznik.setPrezime("Colovic");
		polaznik.setJmbg("1234567891012");
		polaznik.setTelefon("0611234567");
		polaznik.setEmail("maja@gmail.com");
		polaznik.setAdresa("Makedonska 8");
		
		assertEquals("'Maja', 'Colovic', '1234567891012', '0611234567', 'maja@gmail.com', 'Makedonska 8'",
				polaznik.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		polaznik.setId(5l);
		assertEquals(5l, polaznik.getIDPolaznika());
	}
	
	@Test
	public void getConditionForOne_successful() {
		polaznik.setIDPolaznika(5l);
		assertEquals("IDPolaznika = 5", polaznik.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		polaznik.setIme("Maja");
		polaznik.setPrezime("Colovic");
		polaznik.setJmbg("1234567891012");
		polaznik.setTelefon("0611234567");
		polaznik.setEmail("maja@gmail.com");
		polaznik.setAdresa("Makedonska 8");
		
		assertEquals("Ime = 'Maja', Prezime = 'Colovic', Jmbg = '1234567891012', Telefon = '0611234567', Email = 'maja@gmail.com', Adresa = 'Makedonska 8'",
				polaznik.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals("", polaznik.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		polaznik.setIme("Maja");
		polaznik.setPrezime("Colovic");
		
		assertEquals(" where Ime like 'Maja%' and Prezime like 'Colovic%'", polaznik.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by IDPolaznika", polaznik.getSort());
	}
	
	@Test
	public void setAdresa_whenAdresaNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setAdresa(null),
				"Adresa polaznika ne sme biti null vrednost!");
	}

	@Test
	public void setAdresa_whenAdresaEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setAdresa(""),
				"Adresa polaznika ne sme biti prazan string!");
	}

	@Test
	public void setAdresa_successful() {
		polaznik.setAdresa("Borska");

		assertEquals("Borska", polaznik.getAdresa());
	}

	@Test
	public void setIDPolaznika_successful() {
		polaznik.setIDPolaznika(5l);

		assertEquals(5l, polaznik.getIDPolaznika());
	}
	
	@Test
	public void setIme_whenImeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setIme(null),
				"Ime polaznika ne sme biti null vrednost!");
	}

	@Test
	public void setIme_whenImeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setIme(""), "Ime polaznika ne sme biti prazan string!");
	}

	@Test
	public void setIme_successful() {
		polaznik.setIme("Maja");

		assertEquals("Maja", polaznik.getIme());
	}

	@Test
	public void setPrezime_whenPrezimeNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setPrezime(null),
				"Prezime polaznika ne sme biti null vrednost!");
	}

	@Test
	public void setPrezime_whenPrezimeEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setPrezime(""),
				"Prezime polaznika ne sme biti prazan string!");
	}

	@Test
	public void setPrezime_successful() {
		polaznik.setPrezime("Colovic");

		assertEquals("Colovic", polaznik.getPrezime());
	}
	
	@Test
	public void setJmbg_whenJmbgNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setJmbg(null),
				"JMBG polaznika ne sme biti null vrednost!");
	}
	
	@Test
	public void setJmbg_whenJmbgNot13_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setJmbg("12069965"),
				"JMBG polaznika mora imati 13 cifara!");
	}

	@Test
	public void setJmbg_successful() {
		polaznik.setJmbg("1548996585369");

		assertEquals("1548996585369", polaznik.getJmbg());
	}
	
	@Test
	public void setTelefon_whenTelefonNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setTelefon(null),
				"Telefon polaznika ne sme biti null vrednost!");
	}

	@Test
	public void setTelefon_whenTelefonEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setTelefon(""),
				"Telefon polaznika ne sme biti prazan string!");
	}

	@Test
	public void setTelefon_successful() {
		polaznik.setTelefon("0621234695");

		assertEquals("0621234695", polaznik.getTelefon());
	}
	
	@Test
	public void setEmail_whenEmailNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> polaznik.setEmail(null),
				"Email polaznika ne sme biti null vrednost!");
	}

	@Test
	public void setEmail_whenEmailEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setEmail(""),
				"Email polaznika ne sme biti prazan string!");
	}

	@Test
	public void setEmail_whenEmailDoesNotHaveAT_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> polaznik.setEmail("majagmail.com"),
				"Email mora sadrzati @");
	}
	
	@Test
	public void setEmail_successful() {
		polaznik.setEmail("maja@gmail.com");

		assertEquals("maja@gmail.com", polaznik.getEmail());
	}
	
	@Test
	public void testToString() {
		polaznik.setIme("Maja");
		polaznik.setPrezime("Colovic");

		String s = polaznik.toString();
		
		assertTrue(s.contains("Maja"));
		assertTrue(s.contains("Colovic"));
	}

	@ParameterizedTest
	@CsvSource({
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, true",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Maja, Colovic, 1548995658458, 0621234567, pera@gmail.com, false",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Maja, Colovic, 1548995658458, 0621234444, pera@gmail.com, false",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Maja, Colovic, 1548995644444, 0621234444, pera@gmail.com, false",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Maja, Peric, 1548995644444, 0621234444, pera@gmail.com, false",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Pera, Peric, 1548998958741, 0631234567, maja@gmail.com, false",
		"Maja, Colovic, 1548995658458, 0621234567, maja@gmail.com, Pera, Peric, 1548998958741, 0631234567, pera@gmail.com, false"
	})
	public void testEqualsObject(String ime1, String prezime1, String jmbg1, String telefon1, String email1, String ime2, String prezime2, String jmbg2, String telefon2, String email2, boolean eq) {
		polaznik.setIme(ime1);
		polaznik.setPrezime(prezime1);
		polaznik.setJmbg(jmbg1);
		polaznik.setTelefon(telefon1);
		polaznik.setEmail(email1);
		
		Polaznik p = new Polaznik();
		p.setIme(ime2);
		p.setPrezime(prezime2);
		p.setJmbg(jmbg2);
		p.setTelefon(telefon2);
		p.setEmail(email2);
				
		assertEquals(eq, polaznik.equals(p));
	}
}
