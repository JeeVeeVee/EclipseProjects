package ui;

import domein.Rekening;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectie_vb
{
    public static void main(String[] args)
    {
        /*Een programma dat financiële transacties voor de klanten
        * van een bank verricht kan uit meerdere threads bestaan
        * om verschillende klanten tegelijkertijd hun transactie te laten uitvoeren.*/
        List<Rekening> lijst = 
                Collections.synchronizedList(new ArrayList<>());
    }
}
