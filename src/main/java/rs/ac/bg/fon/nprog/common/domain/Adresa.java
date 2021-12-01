package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Adresu i implementira interfejs GenericEntity.
 *
 * Adresa ima svoj IDAdrese tipa long, kao i ulicu tipa String, broj tipa int i
 * grad u kom se nalazi tipa Grad.
 * 
 * @author Maja
 * @version 0.1
 */
public class Adresa implements GenericEntity {

	/**
	 * IDAdrese tipa long.
	 */
	private long IDAdrese;
	/**
	 * Ulica adrese tipa String.
	 */
	private String ulica;
	/**
	 * Broj adrese tipa int.
	 */
	private int broj;
	/**
	 * Grad u kom se adresa nalazi tipa Grad.
	 */
	private Grad grad;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Adresa i nista
	 * vise.
	 */
	public Adresa() {
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Adresa i postavlja
	 * pocetne vrednosti za atribute IDAdrese, ulica, broj, i grad.
	 * 
	 * @param IDAdrese Id adrese kao long.
	 * @param ulica    Ulica adrese kao String.
	 * @param broj     Broj ulice adrese kao int.
	 * @param grad     Grad kao Grad.
	 */
	public Adresa(long IDAdrese, String ulica, int broj, Grad grad) {
		this.IDAdrese = IDAdrese;
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
	}

	/**
	 * Vraca grad u kom se adresa nalazi.
	 * 
	 * @return grad adrese koji je tipa Grad.
	 */
	public Grad getGrad() {
		return grad;
	}

	/**
	 * Postavlja grad adrese ako on nema null vrednost.
	 * 
	 * @param grad Grad koji je tipa Grad.
	 * @throws java.lang.NullPointerException ako prosledjen objekat klase Grad ima
	 *                                        null vrednost.
	 */
	public void setGrad(Grad grad) {
		if (grad == null) {
			throw new NullPointerException("Grad ne sme biti null vrednost!");
		}
		this.grad = grad;
	}

	/**
	 * Vraca id adrese.
	 * 
	 * @return IDAdrese kao long.
	 */
	public long getIDAdrese() {
		return IDAdrese;
	}

	/**
	 * Postavlja id adrese.
	 * 
	 * @param IDAdrese Id adrese tipa long.
	 */
	public void setIDAdrese(long IDAdrese) {
		this.IDAdrese = IDAdrese;
	}

	/**
	 * Vraca ulicu adrese.
	 * 
	 * @return ulica adrese kao String.
	 */
	public String getUlica() {
		return ulica;
	}

	/**
	 * Postavlja ulicu adrese ako ona nije null i nije prazan String.
	 * 
	 * @param ulica Ulica adrese kao String.
	 * @throws java.lang.NullPointerException ako je prosledjena ulica null.
	 * @throws java.lang.RuntimeException     ako je prosledjena ulica prazan
	 *                                        String.
	 */
	public void setUlica(String ulica) {
		if (ulica == null) {
			throw new NullPointerException("Ulica ne sme biti null vrednost!");
		}
		if (ulica.isEmpty()) {
			throw new RuntimeException("Ulica ne sme biti prazan string!");
		}
		this.ulica = ulica;
	}

	/**
	 * Vraca broj adrese.
	 * 
	 * @return Broj kao int vrednost.
	 */
	public int getBroj() {
		return broj;
	}

	/**
	 * Postavlja broj adrese ako on nije negativan i nije 0.
	 * 
	 * @param broj Broj adrese kao int.
	 * @throws java.lang.RuntimeException ako je prosledjen broj negativan ili je 0
	 */
	public void setBroj(int broj) {
		if (broj <= 0) {
			throw new RuntimeException("Broj ne sme biti negativan ili 0!");
		}
		this.broj = broj;
	}

	/**
	 * Vraca ulicu, broj i grad adrese kao jedan String.
	 */
	@Override
	public String toString() {
		return ulica + " " + broj + ", " + grad;
	}

	@Override
	public String getTableName() {
		return "adresa";
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (int) (this.IDAdrese ^ (this.IDAdrese >>> 32));
		hash = 37 * hash + Objects.hashCode(this.ulica);
		hash = 37 * hash + this.broj;
		hash = 37 * hash + Objects.hashCode(this.grad);
		return hash;
	}

	/**
	 * Poredi dve adrese i vraca true ako su iste, a false ako nisu. Adrese se
	 * porede po id-u, ulici, broju i gradu i sva 4 kriterijuma moraju da budu ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Adresa i imaju iste id-jeve, ulice,
	 *         brojeve i gradove
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
		final Adresa other = (Adresa) obj;
		if (this.IDAdrese != other.IDAdrese) {
			return false;
		}
		if (this.broj != other.broj) {
			return false;
		}
		if (!Objects.equals(this.ulica, other.ulica)) {
			return false;
		}
		if (!Objects.equals(this.grad, other.grad)) {
			return false;
		}
		return true;
	}

	@Override
	public String getColumnNamesForInsert() {
		return "Ulica, Broj, IDGrada";
	}

	@Override
	public String getInsertValues() {
		return "'" + ulica + "', " + broj + ", " + grad.getIDGrada();
	}

	@Override
	public void setId(Long id) {
		IDAdrese = id;
	}

	@Override
	public String getConditionForOne() {
		return "a.IDAdrese = " + IDAdrese;
	}

	@Override
	public String setUpdateValues() {
		return "Ulica = '" + ulica + "', Broj = " + broj + ", IDGrada = " + grad.getIDGrada();
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		if (rs == null) {
			throw new Exception("rs ne moze biti null!");
		}
		
		if (rs.next()) {
			IDAdrese = rs.getLong("a.IDAdrese");
			ulica = rs.getString("a.Ulica");
			broj = rs.getInt("a.Broj");
			long id = rs.getLong("g.IDGrada");
			String naziv = rs.getString("g.Naziv");
			grad = new Grad(id, naziv, null);

			return this;
		}
		throw new Exception("Adresa ne postoji u bazi!");																
	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		if (rs == null) {
			throw new Exception("rs ne moze biti null!");
		}
		
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idA = rs.getLong("a.IDAdrese");
			String ulica = rs.getString("a.Ulica");
			int broj = rs.getInt("a.Broj");

			long idG = rs.getLong("g.IDGrada");
			String naziv = rs.getString("g.Naziv");
			Grad g = new Grad(idG, naziv, null);

			Adresa a = new Adresa(idA, ulica, broj, g);

			list.add(a);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return " a JOIN grad g ON a.IDGrada = g.IDGrada";
	}

	@Override
	public String getConditionForMore() {
		return " where a.IDGrada = " + grad.getIDGrada();
	}

	@Override
	public String getSort() {
		return " order by a.IDAdrese";
	}
}
