package domein;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Docenten")
public class Docent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "personeelsnr")
	
	
	private int docentNr;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	
	public Docent(int docentNr, String voornaam, String familienaam, BigDecimal wedde) {
		this.docentNr = docentNr;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.wedde = wedde;
	}
	
	protected Docent() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDocentNr() {
		return docentNr;
	}

	public void setDocentNr(int docentNr) {
		this.docentNr = docentNr;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public void setWedde(BigDecimal wedde) {
		this.wedde = wedde;
	}

	@Override
	public String toString() {
		return "Docent [id=" + id + ", docentNr=" + docentNr + ", voornaam=" + voornaam + ", familienaam=" + familienaam
				+ ", wedde=" + wedde + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + docentNr;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Docent other = (Docent) obj;
		if (docentNr != other.docentNr)
			return false;
		return true;
	}
}
