package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Jezik i implementira interfejs GenericEntity.
 *
 * Jezik ima svoj IDJezika tipa long i naziv tipa String
 * 
 * @author Maja
 * @version 0.1
 */
public class Jezik implements GenericEntity {

	/**
	 * IDJezika kao long.
	 */
	private long IDJezika;
	/**
	 * Naziv jezika kao String.
	 */
	private String naziv;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Jezik i nista
	 * vise.
	 */
	public Jezik() {
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Jezik i postavlja
	 * pocetne vrednosti za atribute IDJezika i naziv.
	 * 
	 * @param IDJezika Id jezika kao long.
	 * @param naziv    Naziv jezika kao String.
	 */
	public Jezik(long IDJezika, String naziv) {
		this.IDJezika = IDJezika;
		this.naziv = naziv;
	}

	/**
	 * Vraca naziv jezika.
	 * 
	 * @return naziv jezika kao String.
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv jezika ako on nije null i nije prazan String.
	 * 
	 * @param naziv Naziv jezika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen naziv null.
	 * @throws java.lang.RuntimeException     ako je prosledjen naziv prazan String.
	 */
	public void setNaziv(String naziv) {
		if (naziv == null) {
			throw new NullPointerException("Naziv jezika ne sme biti null vrednost!");
		}
		if (naziv.isEmpty()) {
			throw new RuntimeException("Naziv jezika ne sme biti prazan string!");
		}
		this.naziv = naziv;
	}

	/**
	 * Vraca id jezika.
	 * 
	 * @return IDJezika kao long.
	 */
	public long getIDJezika() {
		return IDJezika;
	}

	/**
	 * Postavlja id jezika.
	 * 
	 * @param IDJezika Id jezika kao long.
	 */
	public void setIDJezika(long IDJezika) {
		this.IDJezika = IDJezika;
	}

	/**
	 * Vraca naziv jezika kao String.
	 */
	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 31 * hash + (int) (this.IDJezika ^ (this.IDJezika >>> 32));
		hash = 31 * hash + Objects.hashCode(this.naziv);
		return hash;
	}

	/**
	 * Poredi dva jezika i vraca true ako su ista, a false ako nisu. Jezici se
	 * porede po id-u i nazivu i oba kriterijuma moraju da budu ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Jezik i imaju iste id-jeve i nazive
	 *         <li>false u svim ostalim slucajevima
	 *         </ul>
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
		final Jezik other = (Jezik) obj;
		if (this.IDJezika != other.IDJezika) {
			return false;
		}
		if (!Objects.equals(this.naziv, other.naziv)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "jezik";
	}

	@Override
	public String getColumnNamesForInsert() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
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
		return "IDJezika = " + IDJezika;
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
			long idJ = rs.getLong("IDJezika");
			String nazivJ = rs.getString("Naziv");

			Jezik j = new Jezik(idJ, nazivJ);
			list.add(j);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return "";
	}

	@Override
	public String getConditionForMore() {
		return "";
	}

	@Override
	public String getSort() {
		return " order by IDJezika";
	}
}
