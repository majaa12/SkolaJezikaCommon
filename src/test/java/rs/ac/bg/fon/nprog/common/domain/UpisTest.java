package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class UpisTest extends GenericEntityTest {

	private Upis upis;
	private Polaznik polaznik;
	private Administrator admin;
	private TerminKursa tk;
	private SimpleDateFormat sdf;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private Kurs kurs;

	@BeforeEach
	void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dat = "2021-10-10";
		String poc = "2021-10-11";
		String zav = "2021-12-10";

		polaznik = new Polaznik(1l, "Maja", "Colovic", "1547995485254", "0621234567", "maja@gmail.com", "Makedonska 1");
		admin = new Administrator(1l, "Jovan", "Maric", "jovan", "jovan");

		jezik = new Jezik(1l, "Francuski");
		profesor = new Profesor(1l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
		ArrayList<Adresa> adrese = new ArrayList<>();
		Grad grad = new Grad(1l, "Beograd", adrese);
		a = new Adresa(1l, "Makedonska", 2, grad);
		adrese.add(a);

		ArrayList<TerminKursa> termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(1l, "Kurs", Nivo.A1, TipKursa.Opsti, jezik, termini);

		tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs,
				profesor, a);
		termini.add(tk);
		genericEntity = new Upis(sdf.parse(dat), polaznik, admin, tk);
		upis = new Upis(sdf.parse(dat), polaznik, admin, tk);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		upis = null;
		polaznik = null;
		admin = null;
		tk = null;
		jezik = null;
		profesor = null;
		a = null;
		kurs = null;
	}
	
	@Test
	public void fillFromRS_successful() throws Exception {		
		assertThrows(UnsupportedOperationException.class, () -> genericEntity.fillFromRS(null));
	}
	
	/*
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(1L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("sreda")
		.doReturn("Kurs")
		.doReturn("A1")
		.doReturn("Opsti")
		.doReturn("Francuski")
		.doReturn("Ivana")
		.doReturn("Ilic")
		.doReturn("0612365456")
		.doReturn("ivana@gmail.com")
		.doReturn("Makedonska")
		.doReturn("Beograd")
		.doReturn("Jovan")
		.doReturn("Maric")
		.doReturn("jovan")
		.doReturn("jovan")
		.doReturn("Maja")
		.doReturn("Colovic")
		.doReturn("1547995485254")
		.doReturn("0621234567")
		.doReturn("maja@gmail.com")
		.doReturn("Makedonska 1")
		.when(rs).getString(Mockito.anyString());
		

		Mockito.doReturn(8)
				.doReturn(55)
				.doReturn(2)
				.when(rs).getInt(Mockito.anyString());
		
		Mockito.doReturn(new BigDecimal("15000")).when(rs).getBigDecimal(Mockito.anyString());
		
		String dat = "2021-10-10";
		String poc = "2021-10-11";
		String zav = "2021-12-10";
		Date datum = Date.valueOf(dat);
		Date pocetak = Date.valueOf(poc);
		Date zavrsetak = Date.valueOf(zav);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Mockito.doReturn(pocetak)
				.doReturn(zavrsetak)
				.doReturn(datum)
				.when(rs).getDate(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		assertEquals(1, list.size());
		assertEquals(upis, list.get(0));
	}
	*/
	
	@Test
	public void getTableName_successful() {
		assertEquals("upis", upis.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("DatumUpisa, IDAdministratora, IDTermina, IDPolaznika, IDKursa", upis.getColumnNamesForInsert());
	}

	@Test
	public void getInsertValues_successful() {
		assertEquals("'2021-10-10', 1, 1, 1, 1",
				upis.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		assertThrows(UnsupportedOperationException.class, () -> upis.setId(null));
	}

	@Test
	public void getConditionForOne_successful() {
		assertEquals("IDTermina = 1 and IDPolaznika = 1 and IDKursa = 1", upis.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("DatumUpisa = '2021-10-10', IDAdministratora = 1, IDTermina = 1, IDPolaznika = 1, IDKursa = 1",
				upis.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals(" u JOIN administrator a ON u.IDAdministratora = a.IDAdministratora JOIN terminkursa tk ON (u.IDKursa = tk.IDKursa AND u.IDTermina = tk.IDTermina) JOIN polaznik p ON u.IDPolaznika = p.IDPolaznika"
				+ "\nJOIN profesor prof ON tk.IDProfesora = prof.IDProfesora JOIN jezik j ON prof.IDJezika = j.IDJezika JOIN adresa adr ON tk.IDAdrese = adr.IDAdrese JOIN"
				+ "\ngrad g ON adr.IDGrada = g.IDGrada JOIN kurs k ON u.IDKursa = k.IDKursa", upis.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals(" where p.Ime like '%Maja%' and "
				+ "p.Prezime like '%Colovic%' and "
				+ "k.Naziv like '%Kurs%' and k.Nivo = 'A1' and "
				+ "k.TipKursa = 'Opsti' and "
				+ "j.IDJezika = 1 and u.DatumUpisa = '2021-10-10'", upis.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by u.DatumUpisa", upis.getSort());
	}
	
	@Test
	public void setTermin_whenTerminNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> upis.setTerminKursa(null),
				"Termin kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setTermin_successful() throws ParseException {
		String poc = "2021-10-11";
		String zav = "2021-12-10";
		tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "utorak", 8, 60, new BigDecimal("15000"), kurs,
				profesor, a);
		
		upis.setTerminKursa(tk);
		assertEquals(tk, upis.getTerminKursa());
	}
	
	@Test
	public void setDatUpisa_whenDatUpisaNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> upis.setDatumUpis(null),
				"Datum upisa ne sme biti null vrednost!");
	}
	
	@Test
	public void setDatUpisa_successful() throws ParseException {
		String dat = "2021-09-15";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		upis.setDatumUpis(sdf.parse(dat));
		assertEquals(sdf.parse(dat), upis.getDatumUpis());
	}
	
	@Test
	public void setPolaznik_whenPolaznikNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> upis.setPolaznik(null),
				"Polaznik ne sme biti null vrednost!");
	}
	
	@Test
	public void setPolaznik_successful() {
		polaznik = new Polaznik(5l, "Marija", "Djukic", "1547995485333", "0621234444", "marija@gmail.com", "Uzicka 1");
		upis.setPolaznik(polaznik);
		assertEquals(polaznik, upis.getPolaznik());
	}
	
	@Test
	public void setAdministrator_whenAdministratorkNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> upis.setAdministrator(null),
				"Administrator ne sme biti null vrednost!");
	}
	
	@Test
	public void setAdministrator_successful() {
		admin = new Administrator(3l, "Milica", "Milic", "milica", "m123");		upis.setPolaznik(polaznik);
		upis.setAdministrator(admin);
		assertEquals(admin, upis.getAdministrator());
	}
	
	@Test
	public void testToString() throws ParseException {
		String s = upis.toString();
		
		String dat = "2021-10-10";
		String poc = "2021-10-11";
		String zav = "2021-12-10";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(s.contains(sdf.parse(dat)+""));
		assertTrue(s.contains("Maja Colovic"));
		assertTrue(s.contains(sdf.parse(poc)+""));
		assertTrue(s.contains(sdf.parse(zav)+""));
		assertTrue(s.contains("sreda"));	
	}
	
	@Test
	public void testEqualsObject() throws ParseException {
		polaznik = new Polaznik(5l, "Marija", "Djukic", "1547995485333", "0621234444", "marija@gmail.com", "Uzicka 1");
		String dat = "2021-10-10";
		Upis u = new Upis(sdf.parse(dat), polaznik, admin, tk);
		assertEquals(true, upis.equals(genericEntity));
		assertEquals(false, upis.equals(u));
	}
	
}
