package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

public class TerminKursaTest extends GenericEntityTest {

	private TerminKursa tk;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private Kurs kurs;
	private SimpleDateFormat sdf;
	
	@BeforeEach
	void setUp() throws Exception {
		jezik = new Jezik(1l, "Francuski");
		profesor = new Profesor(1l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
		ArrayList<Adresa> adrese = new ArrayList<>();
		Grad grad = new Grad(1l, "Beograd", adrese);
		a = new Adresa(1l, "Makedonska", 2, grad);
		adrese.add(a);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		ArrayList<TerminKursa> termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(1l, "Kurs", Nivo.A1, TipKursa.Opsti, jezik, termini);
		tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs,
				profesor, a);
		genericEntity = new TerminKursa(2l, sdf.parse(poc), sdf.parse(zav), "petak", 10, 60, new BigDecimal("10000"),
				kurs, profesor, a);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
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
		.when(rs).getString(Mockito.anyString());
		

		Mockito.doReturn(8)
				.doReturn(55)
				.doReturn(2)
				.when(rs).getInt(Mockito.anyString());
		
		Mockito.doReturn(new BigDecimal("15000")).when(rs).getBigDecimal(Mockito.anyString());
		
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Mockito.doReturn(new java.sql.Date(sdf.parse(poc).getTime()))
				.doReturn(new java.sql.Date(sdf.parse(zav).getTime()))
				.when(rs).getDate(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		Date pocetak = new java.sql.Date(sdf.parse(poc).getTime());
		Date kraj = new java.sql.Date(sdf.parse(zav).getTime());
		tk.setDatumPocetka(pocetak);
		tk.setDatumZavrsetka(kraj);
		assertEquals(1, list.size());
		assertEquals(tk, list.get(0));
	}
	*/
	
	@Test
	public void getTableName_successful() {
		assertEquals("terminkursa", tk.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("DatumPocetka, DatumZavrsetka, Raspored, Kapacitet, BrojCasova, Cena, IDKursa, IDProfesora, IDAdrese", tk.getColumnNamesForInsert());
	}
	
	@Test
	public void getInsertValues_successful() {
		assertEquals("'2021-10-10', '2021-12-10', 'sreda', 8, 55, 15000, 1, 1, 1",
				tk.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		kurs.setId(3l);
		assertEquals(3l, kurs.getIDKursa());
	}
	
	@Test
	public void getConditionForOne_successful() {
		assertEquals("IDTermina = 1", tk.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("DatumPocetka = '2021-10-10', DatumZavrsetka = '2021-12-10', Raspored = 'sreda', Kapacitet = 8, BrojCasova = 55, Cena = 15000, "
				+ "IDKursa = 1, IDProfesora = 1, IDAdrese = 1",
				tk.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals(" tk join kurs k on tk.IDKursa = k.IDKursa join profesor p on tk.IDProfesora = p.IDProfesora join jezik j on "
				+ "k.IDJezika = j.IDJezika join adresa a on tk.IDAdrese = a.IDAdrese join grad g on a.IDGrada = g.IDGrada", tk.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals(" where tk.IDKursa = 1", tk.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by tk.DatumPocetka", tk.getSort());
	}
	
	@Test
	public void setAdresa_whenAdresNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setAdresa(null),
				"Adresa termina kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setAdresa_successful() {
		Grad grad = new Grad(1l, "Beograd", new ArrayList<Adresa>());
		a = new Adresa(1l, "Makedonska", 2, grad);
		tk.setAdresa(a);
		assertEquals(a, tk.getAdresa());
	}

	@Test
	public void setIDTermina_successful() {
		tk.setIDTermina(2l);

		assertEquals(2l, tk.getIDTermina());
	}
	
	@Test
	public void setDatPoc_whenDatPocNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setDatumPocetka(null),
				"Datum pocetka ne sme biti null vrednost!");
	}
	
	@Test
	public void setDatPoc_successful() throws ParseException {
		String poc = "2021-12-15";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		tk.setDatumPocetka(sdf.parse(poc));
		assertEquals(sdf.parse(poc), tk.getDatumPocetka());
	}
	
	@Test
	public void setDatZav_whenDatZavNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setDatumZavrsetka(null),
				"Datum zavrsetka ne sme biti null vrednost!");
	}
	
	@Test
	public void setDatZav_successful() throws ParseException {
		String zav = "2021-12-15";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		tk.setDatumZavrsetka(sdf.parse(zav));
		assertEquals(sdf.parse(zav), tk.getDatumZavrsetka());
	}
	
	@Test
	public void setRaspored_whenRasporedNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setRaspored(null),
				"Raspored ne sme biti null vrednost!");
	}

	@Test
	public void setUlica_whenUlicaEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> tk.setRaspored(""), "Raspored ne sme biti prazan string!");
	}

	@Test
	public void setUlica_successful() {
		tk.setRaspored("sreda");

		assertEquals("sreda", tk.getRaspored());
	}
	
	@Test
	public void setKapacitet_whenKapacitetOutOfRange_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> tk.setKapacitet(4),
				"Kapacitet mora biti izmedju 6 i 10!");
	}

	@Test
	public void setKapacitet_successful() {
		tk.setKapacitet(8);

		assertEquals(8, tk.getKapacitet());
	}
	
	@Test
	public void setBrojCasova_whenBrojCasovaOutOfRange_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> tk.setBrojCasova(4),
				"Termini kurseva moraju imati izmedju 35 i 60 casova!");
	}

	@Test
	public void setBrojCasova_successful() {
		tk.setBrojCasova(55);

		assertEquals(55, tk.getBrojCasova());
	}
	
	@Test
	public void setCena_whenCenaBellowZero_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> tk.setCena(new BigDecimal("-15000")),
				"Cena mora biti veca od 0!");
	}

	@Test
	public void setCena_successful() {
		tk.setCena(new BigDecimal("15000"));

		assertEquals(new BigDecimal("15000"), tk.getCena());
	}
	
	@Test
	public void setKurs_whenKursNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setKurs(null),
				"Kurs ne sme biti null vrednost!");
	}
	
	@Test
	public void setKurs_successful() {
		tk.setKurs(kurs);
		assertEquals(kurs, tk.getKurs());
	}
	
	@Test
	public void setProfesor_whenProfesorNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> tk.setProfesor(null),
				"Profesor ne sme biti null vrednost!");
	}
	
	@Test
	public void setProfesor_successful() {
		tk.setProfesor(profesor);
		assertEquals(profesor, tk.getProfesor());
	}
	
	@Test
	public void testToString() throws ParseException {
		String s = tk.toString();
		
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(s.contains(sdf.parse(poc)+""));
		assertTrue(s.contains(sdf.parse(zav)+""));
		assertTrue(s.contains("sreda"));
	}
}
