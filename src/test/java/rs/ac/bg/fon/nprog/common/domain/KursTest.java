package rs.ac.bg.fon.nprog.common.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KursTest extends GenericEntityTest{

	private Kurs kurs;
	private ArrayList<TerminKursa> termini;
	private Jezik jezik;
	private Nivo nivo;
	private TipKursa tip;
	
	@BeforeEach
	void setUp() throws Exception {
		termini = new ArrayList<TerminKursa>();
		jezik = new Jezik(1l, "Francuski");
		nivo = Nivo.A1;
		tip = TipKursa.Opsti;
		genericEntity = new Kurs(1l, "Novi kurs", nivo, tip, jezik, termini);
		kurs = new Kurs(2l, "Drugi kurs", nivo, tip, jezik, termini);
	}

	@AfterEach
	void tearDown() throws Exception {
		genericEntity = null;
		kurs = null;
		termini = null;
		jezik = null;
	}

	@Test
	public void fillFromRS_successful() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		Mockito.doReturn(true).when(rs).next();

		Mockito.doReturn(1L).doReturn(1L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Novi kurs")
				.doReturn("A1")
				.doReturn("Opsti")
				.doReturn("Francuski")
				.when(rs).getString(Mockito.anyString());

		genericEntity = genericEntity.fillFromRS(rs);
		Kurs expectedKurs = new Kurs(1l, "Novi kurs", Nivo.A1, TipKursa.Opsti, jezik, new ArrayList<TerminKursa>());
		assertEquals(expectedKurs, genericEntity);
	}
	
	@Test
	public void fillListFromRS_successful() throws Exception{
		ResultSet rs = mock(ResultSet.class);
		
		Mockito.doReturn(true).doReturn(false).when(rs).next();
		
		Mockito.doReturn(1L).doReturn(1L).when(rs).getLong(Mockito.anyString());
		Mockito.doReturn("Novi kurs")
				.doReturn("A1")
				.doReturn("Opsti")
				.doReturn("Francuski")
				.when(rs).getString(Mockito.anyString());
		
		// Test
		List<GenericEntity> list = genericEntity.fillListFromRS(rs);
		
		// Assertions
		Kurs expectedKurs = new Kurs(1l, "Novi kurs", Nivo.A1, TipKursa.Opsti, jezik, null);
		
		assertEquals(1, list.size());
		assertEquals(expectedKurs, list.get(0));
	}
	
	@Test
	public void getTableName_successful() {
		assertEquals("kurs", kurs.getTableName());
	}
	
	@Test
	public void getColumnNamesForInsert_successful() {
		assertEquals("Naziv, Nivo, TipKursa, IDJezika", kurs.getColumnNamesForInsert());
	}

	@Test
	public void getInsertValues_successful() {
		assertEquals("'Drugi kurs', 'A1', 'Opsti', 1",
				kurs.getInsertValues());
	}
	
	@Test
	public void setId_successful() {
		kurs.setId(3l);
		assertEquals(3l, kurs.getIDKursa());
	}

	@Test
	public void getConditionForOne_successful() {
		assertEquals("IDKursa = 2", kurs.getConditionForOne());
	}
	
	@Test
	public void setUpdateValues_successful() {
		assertEquals("Naziv = 'Drugi kurs', Nivo = 'A1', TipKursa = 'Opsti', IDJezika = 1",
				kurs.setUpdateValues());
	}
	
	@Test
	public void getJoinCondition() {
		assertEquals(" k join jezik j on k.IDJezika = j.IDJezika", kurs.getJoinCondition());
	}
	
	@Test
	public void getConditionForMore_successful() {
		assertEquals(" where j.IDJezika = 1 and k.Nivo = 'A1' and k.TipKursa = 'Opsti' and k.Naziv like '%Drugi kurs%'", kurs.getConditionForMore());
	}
	
	@Test
	public void getSort_successful() {
		assertEquals(" order by k.Nivo, k.TipKursa", kurs.getSort());
	}
	
	@Test
	public void setJezik_whenJezikNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> kurs.setJezik(null),
				"Jezik kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setJezik_successful() {
		Jezik j = new Jezik(2l, "Nemacki");
		kurs.setJezik(j);
		assertEquals(j, kurs.getJezik());
	}
	
	@Test
	public void setIDGrada_successful() {
		kurs.setIDKursa(7l);

		assertEquals(7l, kurs.getIDKursa());
	}
	
	@Test
	public void setNaziv_whenNazivNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> kurs.setNaziv(null),
				"Naziv kursa ne sme biti null vrednost!");
	}

	@Test
	public void setNaziv_whenNazivEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> kurs.setNaziv(""), "Naziv kursa ne sme biti prazan string!");
	}

	@Test
	public void setNaziv_successful() {
		kurs.setNaziv("Kurs");

		assertEquals("Kurs", kurs.getNaziv());
	}
	
	@Test
	public void setNivo_whenNivoNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> kurs.setNivo(null),
				"Nivo kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setNivo_successful() {
		kurs.setNivo(Nivo.B1);
		assertEquals(Nivo.B1, kurs.getNivo());
	}
	
	@Test
	public void setTip_whenTipNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> kurs.setTipKursa(null),
				"Tip kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setTip_successful() {
		kurs.setTipKursa(TipKursa.Konverzacijski);
		assertEquals(TipKursa.Konverzacijski, kurs.getTipKursa());
	}
	
	@Test
	public void setTermini_whenTerminiNull_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> kurs.setTermini(null),
				"Lista termina kursa ne sme biti null vrednost!");
	}
	
	@Test
	public void setTermini_whenTerminiEmpty_thenRunTimeException() {
		assertThrows(RuntimeException.class, () -> kurs.setTermini(new ArrayList<TerminKursa>()), "Kurs mora imati makar jedan termin!");
	}
	
	@Test
	public void setTermini_successful() throws ParseException {
		Profesor profesor = new Profesor(5l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
		ArrayList<Adresa> adrese = new ArrayList<>();
		Grad grad = new Grad();		
		Adresa a = new Adresa(1l, "Makedonska", 2, grad);
		adrese.add(a);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		TerminKursa tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs, profesor, a);
		termini.add(tk);
		kurs.setTermini(termini);
		
		assertEquals(termini, kurs.getTermini());
	}
	
	@Test
	public void testToString() {
		String s = kurs.toString();
		
		assertTrue(s.contains("Drugi kurs"));
	}
	
	@Test
	public void testEqualsObject() {
		Kurs k = new Kurs(2l, "Drugi kurs", nivo, tip, jezik, termini);		
		assertEquals(false, kurs.equals(genericEntity));
		assertEquals(true, kurs.equals(k));
	}
	
	@Test
	public void exist_successful() throws ParseException {
		Profesor profesor = new Profesor(5l, "Ivana", "Ilic", "0612365456", "ivana@gmail.com", jezik);
		ArrayList<Adresa> adrese = new ArrayList<>();
		Grad grad = new Grad();		
		Adresa a = new Adresa(1l, "Makedonska", 2, grad);
		adrese.add(a);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		TerminKursa tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs, profesor, a);
		termini.add(tk);
		kurs.setTermini(termini);
		
		TerminKursa novi = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs, profesor, a);
		TerminKursa novi1 = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "petak", 8, 60, new BigDecimal("20000"), kurs, profesor, a);

		assertEquals(true, kurs.exist(novi));
		assertEquals(false, kurs.exist(novi1));
	}
}
