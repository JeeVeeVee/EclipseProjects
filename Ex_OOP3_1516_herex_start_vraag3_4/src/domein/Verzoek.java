package domein;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class Verzoek<T extends Serializable>   implements Serializable {
    private final String parameter;
    private final String query;
    private List<T> result;
    
    public Verzoek(String query, String text) {
        this.query=query;
        parameter=text;
    }

    public String getParameter() {
        return parameter;
    }
    public String getQuery() {
        return query;
    }
   // methoden getResultaat en setResult

	public List<T> getResult() {
		return Collections.unmodifiableList(result);
	}

	public void setResult(List<T> result) {
		this.result = result;
	}


}
