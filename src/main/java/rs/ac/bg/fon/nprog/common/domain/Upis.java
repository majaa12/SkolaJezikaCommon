package rs.ac.bg.fon.nprog.common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Upis implements GenericEntity {

	private Date datumUpis;
	private Polaznik polaznik;
	private Administrator administrator;
	private TerminKursa terminKursa;

	public Upis() {
	}

	public Upis(Date datumUpis, Polaznik polaznik, Administrator administrator, TerminKursa terminKursa) {
		this.datumUpis = datumUpis;
		this.polaznik = polaznik;
		this.administrator = administrator;
		this.terminKursa = terminKursa;
	}

	public TerminKursa getTerminKursa() {
		return terminKursa;
	}

	public void setTerminKursa(TerminKursa terminKursa) {
		if (terminKursa == null) {
			throw new NullPointerException("Termin kursa ne sme biti null vrednost!");
		}
		this.terminKursa = terminKursa;
	}

	public Date getDatumUpis() {
		return datumUpis;
	}

	public void setDatumUpis(Date datumUpis) {
		if (datumUpis == null) {
			throw new NullPointerException("Datum upisa ne sme biti null vrednost!");
		}
		this.datumUpis = datumUpis;
	}

	public Polaznik getPolaznik() {
		return polaznik;
	}

	public void setPolaznik(Polaznik polaznik) {
		if (polaznik == null) {
			throw new NullPointerException("Polaznik ne sme biti null vrednost!");
		}
		this.polaznik = polaznik;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		if (administrator == null) {
			throw new NullPointerException("Administrator ne sme biti null vrednost!");
		}
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return datumUpis + " " + polaznik + " " + terminKursa;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.datumUpis);
		hash = 23 * hash + Objects.hashCode(this.polaznik);
		hash = 23 * hash + Objects.hashCode(this.administrator);
		hash = 23 * hash + Objects.hashCode(this.terminKursa);
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
		final Upis other = (Upis) obj;
		if (!Objects.equals(this.datumUpis, other.datumUpis)) {
			return false;
		}
		if (!Objects.equals(this.polaznik, other.polaznik)) {
			return false;
		}
		if (!Objects.equals(this.administrator, other.administrator)) {
			return false;
		}
		if (!Objects.equals(this.terminKursa, other.terminKursa)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "upis";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "DatumUpisa, IDAdministratora, IDTermina, IDPolaznika, IDKursa";
	}

	@Override
	public String getInsertValues() {
		return "'" + new java.sql.Date(datumUpis.getTime()) + "', " + administrator.getIDAdministratora() + ", "
				+ terminKursa.getIDTermina() + ", " + polaznik.getIDPolaznika() + ", "
				+ terminKursa.getKurs().getIDKursa();
	}

	@Override
	public void setId(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getConditionForOne() {
		return "IDTermina = " + terminKursa.getIDTermina() + " and IDPolaznika = " + polaznik.getIDPolaznika()
				+ " and IDKursa = " + terminKursa.getKurs().getIDKursa();
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
			long idTK = rs.getLong("tk.IDTermina");
			Date datPoc = rs.getDate("tk.DatumPocetka");
			Date datZav = rs.getDate("tk.DatumZavrsetka");
			String rasporedTK = rs.getString("tk.Raspored");
			int kapacitetTK = rs.getInt("tk.Kapacitet");
			int brCasovaTK = rs.getInt("tk.BrojCasova");
			BigDecimal cenaTK = rs.getBigDecimal("tk.Cena");

			long idK = rs.getLong("k.IDKursa");
			String nazivK = rs.getString("k.Naziv");
			Nivo nivoK = Nivo.valueOf(rs.getString("k.Nivo"));
			TipKursa tipK = TipKursa.valueOf(rs.getString("k.TipKursa"));

			long idJ = rs.getLong("j.IDJezika");
			String nazivJ = rs.getString("j.Naziv");
			Jezik j = new Jezik(idJ, nazivJ);

			long idP = rs.getLong("tk.IDProfesora");
			String imeP = rs.getString("prof.Ime");
			String prezimeP = rs.getString("prof.Prezime");
			String telefonP = rs.getString("prof.Telefon");
			String emailP = rs.getString("prof.Email");

			long idA = rs.getLong("tk.IDAdrese");
			String ulicaA = rs.getString("adr.Ulica");
			int brojA = rs.getInt("adr.Broj");

			long idG = rs.getLong("adr.IDGrada");
			String nazivG = rs.getString("g.Naziv");

			long idAdmin = rs.getLong("a.IDAdministratora");
			String imeA = rs.getString("a.Ime");
			String prezimeA = rs.getString("a.Prezime");
			String korIme = rs.getString("a.KorisnickoIme");
			String loz = rs.getString("a.Lozinka");

			long idPol = rs.getLong("p.IDPolaznika");
			String imePol = rs.getString("p.Ime");
			String prezimePol = rs.getString("p.Prezime");
			String jmbgPol = rs.getString("p.Jmbg");
			String telefonPol = rs.getString("p.Telefon");
			String emailPol = rs.getString("p.Email");
			String adresaPol = rs.getString("p.Adresa");

			Date datUpisa = rs.getDate("u.DatumUpisa");

			Polaznik pol = new Polaznik(idPol, imePol, prezimePol, jmbgPol, telefonPol, emailPol, adresaPol);
			Administrator admin = new Administrator(idAdmin, imeA, prezimeA, korIme, loz);
			Grad g = new Grad(idG, nazivG, null);
			Adresa a = new Adresa(idA, ulicaA, brojA, g);
			Profesor p = new Profesor(idP, imeP, prezimeP, telefonP, emailP, j);
			Kurs k = new Kurs(idK, nazivK, nivoK, tipK, j, null);
			TerminKursa tk = new TerminKursa(idTK, datPoc, datZav, rasporedTK, kapacitetTK, brCasovaTK, cenaTK, k, p,
					a);
			Upis u = new Upis(datUpisa, pol, admin, tk);

			list.add(u);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return " u JOIN administrator a ON u.IDAdministratora = a.IDAdministratora JOIN terminkursa tk ON (u.IDKursa = tk.IDKursa AND u.IDTermina = tk.IDTermina) JOIN polaznik p ON u.IDPolaznika = p.IDPolaznika\n"
				+ "JOIN profesor prof ON tk.IDProfesora = prof.IDProfesora JOIN jezik j ON prof.IDJezika = j.IDJezika JOIN adresa adr ON tk.IDAdrese = adr.IDAdrese JOIN\n"
				+ "grad g ON adr.IDGrada = g.IDGrada JOIN kurs k ON u.IDKursa = k.IDKursa";
	}

	@Override
	public String getConditionForMore() {
		String rez = " where";
		if (polaznik != null && polaznik.getIme() != null) {
			rez += " p.Ime like '%" + polaznik.getIme() + "%' and";
		}
		if (polaznik != null && polaznik.getPrezime() != null) {
			rez += " p.Prezime like '%" + polaznik.getPrezime() + "%' and";
		}
		if (terminKursa != null && terminKursa.getKurs().getNaziv() != null
				&& !terminKursa.getKurs().getNaziv().equals("")) {
			rez += " k.Naziv like '%" + terminKursa.getKurs().getNaziv() + "%' and";
		}
		if (terminKursa != null && terminKursa.getKurs().getNivo() != null) {
			rez += " k.Nivo = '" + terminKursa.getKurs().getNivo().toString() + "' and";
		}
		if (terminKursa != null && terminKursa.getKurs().getTipKursa() != null) {
			rez += " k.TipKursa = '" + terminKursa.getKurs().getTipKursa().toString() + "' and";
		}
		if (terminKursa != null && terminKursa.getKurs().getJezik() != null) {
			rez += " j.IDJezika = " + terminKursa.getKurs().getJezik().getIDJezika() + " and";
		}
		if (datumUpis != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String datum = sdf.format(datumUpis);
			rez += " u.DatumUpisa = '" + datum + "' and";
		}
		if (rez.equals(" where")) {
			return "";
		}
		return rez.substring(0, rez.length() - 4);
	}

	@Override
	public String getSort() {
		return " order by u.DatumUpisa";
	}
}
