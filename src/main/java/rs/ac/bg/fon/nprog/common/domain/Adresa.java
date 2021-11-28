package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Adresa implements GenericEntity {

	private long IDAdrese;
	private String ulica;
	private int broj;
	private Grad grad;

	public Adresa() {
	}

	public Adresa(long IDAdrese, String ulica, int broj, Grad grad) {
		this.IDAdrese = IDAdrese;
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public long getIDAdrese() {
		return IDAdrese;
	}

	public void setIDAdrese(long IDAdrese) {
		this.IDAdrese = IDAdrese;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

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
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
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
