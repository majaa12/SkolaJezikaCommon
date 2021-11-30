package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Kurs koji je u ponudi skole jezike i implementira
 * interfejs GenericEntity.
 *
 * Kurs ima svoj IDKursa tipa long, naziv tipa String, nivo koji je enum Nivo,
 * tipKursa koji je enum TipKursa, jezik tipa Jezik, kao i listu termina koji
 * cine taj kurs.
 * 
 * @author Maja
 * @version 0.1
 */
public class Kurs implements GenericEntity {

	/**
	 * IDKursa kao long.
	 */
	private long IDKursa;
	/**
	 * Naziv kursa kao String.
	 */
	private String naziv;
	/**
	 * Nivo kursa kao enum Nivo.
	 */
	private Nivo nivo;
	/**
	 * Tip kursa kao enum TipKursa.
	 */
	private TipKursa tipKursa;
	/**
	 * Jezik kursa kao objekat klase Jezik.
	 */
	private Jezik jezik;
	/**
	 * Lista termina tipa ArrayList.
	 */
	private ArrayList<TerminKursa> termini;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Kurs i
	 * inicijalizuje listu termina tog kursa.
	 */
	public Kurs() {
		this.termini = new ArrayList<>();
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Kurs i postavlja
	 * pocetne vrednosti za atribute IDKursa, naziv, nivo, tipKursa, jezik i
	 * termini.
	 * 
	 * @param IDKursa  Id kursa kao long.
	 * @param naziv    Naziv kursa kao String.
	 * @param nivo     Nivo kursa kao enum Nivo.
	 * @param tipKursa Tip kursa kao enum TipKursa.
	 * @param jezik    Jezik kursa kao objekat klase jezik.
	 * @param termini  Termini kursa kao lista tipa ArrayList.
	 */
	public Kurs(long IDKursa, String naziv, Nivo nivo, TipKursa tipKursa, Jezik jezik, ArrayList<TerminKursa> termini) {
		this.IDKursa = IDKursa;
		this.naziv = naziv;
		this.nivo = nivo;
		this.tipKursa = tipKursa;
		this.jezik = jezik;
		this.termini = termini;
	}

	/**
	 * Vraca jezik kursa.
	 * 
	 * @return jezik kursa kao Jezik.
	 */
	public Jezik getJezik() {
		return jezik;
	}

	/**
	 * Postavlja jezik kursa ako on nije null.
	 * 
	 * @param jezik Jezik kursa kao objekat klase Jezik.
	 * @throws java.lang.NullPointerException ako je prosledjen jezik null.
	 */
	public void setJezik(Jezik jezik) {
		if (jezik == null) {
			throw new NullPointerException("Jezik kursa ne sme biti null vrednost!");
		}
		this.jezik = jezik;
	}

	/**
	 * Vraca id kursa.
	 * 
	 * @return IDKursa kao long.
	 */
	public long getIDKursa() {
		return IDKursa;
	}

	/**
	 * Postavlja id kurs.
	 * 
	 * @param IDKursa Id kursa kao long.
	 */
	public void setIDKursa(long IDKursa) {
		this.IDKursa = IDKursa;
	}

	/**
	 * Vraca naziv kursa.
	 * 
	 * @return naziv kursa kao String.
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv kursa ako on nije null i nije prazan String.
	 * 
	 * @param naziv Naziv kursa kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen naziv null.
	 * @throws java.lang.RuntimeException     ako je prosledjen naziv prazan String.
	 */
	public void setNaziv(String naziv) {
		if (naziv == null) {
			throw new NullPointerException("Naziv kursa ne sme biti null vrednost!");
		}
		if (naziv.isEmpty()) {
			throw new RuntimeException("Naziv kursa ne sme biti prazan string!");
		}
		this.naziv = naziv;
	}

	/**
	 * Vraca nivo kursa.
	 * 
	 * @return nivo kursa kao enum Nivo.
	 */
	public Nivo getNivo() {
		return nivo;
	}

	/**
	 * Postavlja nivo kursa ako on nije null.
	 * 
	 * @param nivo Nivo kursa kao enum Nivo.
	 * @throws java.lang.NullPointerException ako je prosledjen nivo null.
	 */
	public void setNivo(Nivo nivo) {
		if (nivo == null) {
			throw new NullPointerException("Nivo kursa ne sme biti null vrednost!");
		}
		this.nivo = nivo;
	}

	/**
	 * Vraca tip kursa.
	 * 
	 * @return tipKursa kao enum TipKursa.
	 */
	public TipKursa getTipKursa() {
		return tipKursa;
	}

	/**
	 * Postavlja tip kursa ako on nije null.
	 * 
	 * @param tipKursa Tip kursa kao enum TipKursa.
	 */
	public void setTipKursa(TipKursa tipKursa) {
		if (tipKursa == null) {
			throw new NullPointerException("Tip kursa ne sme biti null vrednost!");
		}
		this.tipKursa = tipKursa;
	}

	/**
	 * Vraca listu termina kursa.
	 * 
	 * @return termini kao lista tipa ArrayList.
	 */
	public ArrayList<TerminKursa> getTermini() {
		return termini;
	}

	/**
	 * Postavlja listu termina ako ona nije null i nije prazna.
	 * 
	 * @param termini Termini kursa kao lista tipa ArrayList.
	 * @throws java.lang.NullPointerException ako je prosledjena lista termina null.
	 * @throws java.lang.RuntimeException     ako je prosledjena lista termina
	 *                                        prazna.
	 */
	public void setTermini(ArrayList<TerminKursa> termini) {
		if (termini == null) {
			throw new NullPointerException("Lista termina kursa ne sme biti null vrednost!");
		}
		if (termini.isEmpty()) {
			throw new RuntimeException("Kurs mora imati makar jedan termin!");
		}
		this.termini = termini;
	}

	/**
	 * Proverava da li prosledjeni termin kursa odgovara nekom od vec unetih termina
	 * tog kursa i vraca true ako vec postoji takav termin u listi termina kursa, a
	 * false ako ne postoji. Termini se porede po kapacitetu, broju casova,
	 * rasporedu, ceni, datumu pocetka, datumu zavrsetka, adresi i profesoru i svi
	 * kriterijumi moraju biti isti.
	 * 
	 * @param termin termin kursa kao objekat klase TerminKursa.
	 * @return
	 *         <ul>
	 *         <li>true ako objekta klase TerminKursa imaju iste kapacitete, broj
	 *         casova, raspored, cene, datume pocetka i zavrsetka, adrese i
	 *         profesore
	 *         <li>false u svim ostalim slucajevima
	 *         </ul>
	 */
	public boolean exist(TerminKursa termin) {

		for (TerminKursa t : termini) {
			if (t.getKapacitet() == termin.getKapacitet() && (t.getCena().compareTo(termin.getCena()) == 0)
					&& t.getBrojCasova() == termin.getBrojCasova() && t.getRaspored().equals(termin.getRaspored())
					&& (t.getDatumPocetka().compareTo(termin.getDatumPocetka()) == 0)
					&& (t.getDatumZavrsetka().compareTo(termin.getDatumZavrsetka()) == 0)
					&& t.getAdresa().equals(termin.getAdresa()) && t.getProfesor().equals(termin.getProfesor())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Vraca naziv kursa kao String.
	 */
	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (int) (this.IDKursa ^ (this.IDKursa >>> 32));
		hash = 71 * hash + Objects.hashCode(this.naziv);
		hash = 71 * hash + Objects.hashCode(this.nivo);
		hash = 71 * hash + Objects.hashCode(this.tipKursa);
		hash = 71 * hash + Objects.hashCode(this.jezik);
		hash = 71 * hash + Objects.hashCode(this.termini);
		return hash;
	}

	/**
	 * Poredi dva kursa i vraca true ako su isti, a false ako nisu. Kursevi se
	 * porede po id-u, nazivu, nivou, tipu, jeziku i listi termina i svi kriterijumi
	 * moraju da budu isti.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Kurs i imaju iste id-jeve, nazive,
	 *         nivoe, tipove, jezike i liste termina
	 *         <li>false u svim ostalim slucajevima
	 *         </ul>
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Kurs other = (Kurs) obj;
		if (this.IDKursa != other.IDKursa) {
			return false;
		}
		if (!Objects.equals(this.naziv, other.naziv)) {
			return false;
		}
		if (this.nivo != other.nivo) {
			return false;
		}
		if (this.tipKursa != other.tipKursa) {
			return false;
		}
		if (!Objects.equals(this.jezik, other.jezik)) {
			return false;
		}
		if (!Objects.equals(this.termini, other.termini)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "kurs";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "Naziv, Nivo, TipKursa, IDJezika";
	}

	@Override
	public String getInsertValues() {
		return "'" + naziv + "', '" + nivo.toString() + "', '" + tipKursa.toString() + "', " + jezik.getIDJezika();
	}

	@Override
	public void setId(Long id) {
		IDKursa = id;
	}

	@Override
	public String getConditionForOne() {
		return "IDKursa = " + IDKursa;
	}

	@Override
	public String setUpdateValues() {
		return "Naziv = '" + naziv + "', Nivo = '" + nivo.toString() + "', TipKursa = '" + tipKursa.toString()
				+ "', IDJezika = " + jezik.getIDJezika();
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		if (rs.next()) {
			IDKursa = rs.getLong("k.IDKursa");
			naziv = rs.getString("k.Naziv");
			nivo = Nivo.valueOf(rs.getString("k.Nivo"));
			tipKursa = TipKursa.valueOf(rs.getString("k.TipKursa"));

			long idJ = rs.getLong("j.IDJezika");
			String nazivJ = rs.getString("j.Naziv");
			jezik = new Jezik(idJ, nazivJ);

			return this;
		}
		throw new Exception("Kurs ne postoji u bazi!");
	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idK = rs.getLong("k.IDKursa");
			String nazivK = rs.getString("k.Naziv");
			Nivo nivoK = Nivo.valueOf(rs.getString("k.Nivo"));
			TipKursa tipK = TipKursa.valueOf(rs.getString("k.TipKursa"));

			long idJ = rs.getLong("j.IDJezika");
			String nazivJ = rs.getString("j.Naziv");
			Jezik j = new Jezik(idJ, nazivJ);

			Kurs k = new Kurs(idK, nazivK, nivoK, tipK, j, null);
			list.add(k);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return " k join jezik j on k.IDJezika = j.IDJezika";
	}

	@Override
	public String getConditionForMore() {
		String rez;
		if (jezik == null && nivo == null && tipKursa == null && (naziv == null || naziv.equals(""))) {
			return "";
		} else {
			rez = " where";
			if (jezik != null) {
				rez += " j.IDJezika = " + jezik.getIDJezika() + " and";
			}
			if (nivo != null) {
				rez += " k.Nivo = '" + nivo.toString() + "' and";
			}
			if (tipKursa != null) {
				rez += " k.TipKursa = '" + tipKursa.toString() + "' and";
			}
			if (naziv != null && !naziv.equals("")) {
				rez += " k.Naziv like '%" + naziv + "%' and";
			}

		}
		return rez.substring(0, rez.length() - 4);
	}

	@Override
	public String getSort() {
		return " order by k.Nivo, k.TipKursa";
	}
}
