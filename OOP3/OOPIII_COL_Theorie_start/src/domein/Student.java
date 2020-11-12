package domein;

public class Student implements Comparable<Student>  
{

    private final long stamboeknr;
    private String naam, voornaam, woonplaats;

    public Student(long stamboeknr, String naam, String voornaam, String woonplaats)
    {
        controleStamboeknr(stamboeknr);
        this.stamboeknr=stamboeknr;
        setNaam(naam);
        setVoornaam(voornaam);
        setWoonplaats(woonplaats);
    }

    
    public long getStamboeknr()
    {
        return stamboeknr;
    }

    private void controleStamboeknr(long stamboeknr)
    {
        if(stamboeknr==0)
            throw new IllegalArgumentException("Geen correct stamboeknr");
    }
    
    public String getNaam()
    {
        return naam;
    }

    private void controleNullString(String string)
    {
        if(string==null)
            throw new IllegalArgumentException("Null");
    }
    
    private void setNaam(String naam)
    {
        controleNullString(naam);
        this.naam = naam;
    }

    public String getVoornaam()
    {
        return voornaam;
    }

    private void setVoornaam(String voornaam)
    {
        controleNullString(voornaam);
        this.voornaam = voornaam;
    }

    public String getWoonplaats()
    {
        return woonplaats;
    }

    private void setWoonplaats(String woonplaats)
    {
        controleNullString(woonplaats);
        this.woonplaats = woonplaats;
    }

    

    @Override
    public String toString()
    {
        return "\nStudent{" + "stamboeknr=" + stamboeknr + ", naam=" + naam + ", voornaam=" + voornaam + ", woonplaats=" + woonplaats + '}';
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (stamboeknr ^ (stamboeknr >>> 32));
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
		Student other = (Student) obj;
		if (stamboeknr != other.stamboeknr)
			return false;
		return true;
	}


	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
    
    

    
    

}
