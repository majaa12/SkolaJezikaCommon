package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Grad i implementira interfejs GenericEntity.
 *
 * Grad ima svoj IDGrada tipa long, naziv tipa String, kao i listu adresa koje
 * se nalaze u njemu.
 * 
 * @author Maja
 * @version 0.1
 */
public class Grad implements GenericEntity {

	/**
	 * IDGrada tipa long.
	 */
	private long IDGrada;
	/**
	 * Naziv grada tiipa String.
	 */
	private String naziv;
	/**
	 * Lista adresa tipa ArrayList.
	 */
	private ArrayList<Adresa> adrese;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Grad i
	 * inicijalizuje listu adresa tog grada.
	 */
	public Grad() {
		this.adrese = new ArrayList<>();
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Grad i postavlja
	 * pocetne vrednosti za atribute IDGrada, naziv, adrese.
	 * 
	 * @param IDGrada Id grada kao long.
	 * @param naziv   Naziv grada kao String.
	 * @param adrese  Lista adresa tipa ArrayList.
	 */
	public Grad(long IDGrada, String naziv, ArrayList<Adresa> adrese) {
		this.IDGrada = IDGrada;
		this.naziv = naziv;
		this.adrese = adrese;
	}

	/**
	 * Vraca naziv grada.
	 * 
	 * @return Naziv grada kao String.
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv grada ako on nije null i nije prazan String.
	 * 
	 * @param naziv Naziv grada kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen naziv null.
	 * @throws java.lang.RuntimeException     ako je prosledjen naziv prazan String.
	 */
	public void setNaziv(String naziv) {
		if (naziv == null) {
			throw new NullPointerException("Naziv grada ne sme biti null vrednost!");
		}
		if (naziv.isEmpty()) {
			throw new RuntimeException("Naziv grada ne sme biti prazan string!");
		}
		this.naziv = naziv;
	}

	/**
	 * Vraca id grada.
	 * 
	 * @return IDGrada kao long.
	 */
	public long getIDGrada() {
		return IDGrada;
	}

	/**
	 * Postavlja id grada.
	 * 
	 * @param IDGrada Id grada kao long.
	 */
	public void setIDGrada(long IDGrada) {
		this.IDGrada = IDGrada;
	}

	/**
	 * Vraca listu adresa tog grada.
	 * 
	 * @return adrese kao ArrayList.
	 */
	public ArrayList<Adresa> getAdrese() {
		return adrese;
	}

	/**
	 * Postavlja listu adresa ako ona nije null i nije prazna.
	 * 
	 * @param adrese Adrese grada tipa ArrayList.
	 * @throws java.lang.NullPointerException ako je prosledjena lista adresa null.
	 * @throws java.lang.RuntimeException     ako je prosledjena lista adresa
	 *                                        prazna.
	 * 
	 */
	public void setAdrese(ArrayList<Adresa> adrese) {
		if (adrese == null) {
			throw new NullPointerException("Lista adresa grada ne sme biti null vrednost!");
		}
		if (adrese.isEmpty()) {
			throw new RuntimeException("Grad mora imati makar jednu adresu!");
		}
		this.adrese = adrese;
	}

	/**
	 * Vraca naziv grada kao String.
	 */
	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + (int) (this.IDGrada ^ (this.IDGrada >>> 32));
		hash = 41 * hash + Objects.hashCode(this.naziv);
		hash = 41 * hash + Objects.hashCode(this.adrese);
		return hash;
	}

	/**
	 * Poredi dva grada i vraca true ako su isti, a false ako nisu. Gradovi se
	 * porede po id-u, nazivu i listi adresa i sva 3 kriterijuma moraju da budu
	 * ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Grad i imaju iste id-jeve, nazive i
	 *         liste adresa
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
		final Grad other = (Grad) obj;
		if (this.IDGrada != other.IDGrada) {
			return false;
		}
		if (!Objects.equals(this.naziv, other.naziv)) {
			return false;
		}
		if (!Objects.equals(this.adrese, other.adrese)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "grad";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "IDGrada, Naziv";
	}

	@Override
	public String getInsertValues() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void setId(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getConditionForOne() {
		return "IDGrada = " + IDGrada;
	}

	@Override
	public String setUpdateValues() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idG = rs.getLong("IDGrada");
			String nazivG = rs.getString("Naziv");

			Grad g = new Grad(idG, nazivG, null);
			list.add(g);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		// return " g JOIN adresa a ON g.IDGrada = a.IDGrada";
		return "";
	}

	@Override
	public String getConditionForMore() {
		return "";
	}

	@Override
	public String getSort() {
		return " order by IDGrada";
	}

}
