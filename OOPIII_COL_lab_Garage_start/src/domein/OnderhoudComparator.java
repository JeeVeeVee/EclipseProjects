package domein;

import java.util.Comparator;

public class OnderhoudComparator implements Comparator<Onderhoud> {
    @Override
    public int compare(Onderhoud o1, Onderhoud o2) {
        if(o1.getNummerplaat() == o2.getNummerplaat()){
            return compare(o1.getBegindatum(), o2.getBegindatum());
        }
    }
}
