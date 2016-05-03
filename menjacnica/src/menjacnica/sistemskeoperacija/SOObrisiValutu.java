package menjacnica.sistemskeoperacija;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SOObrisiValutu {
	public static void izvrsi(Valuta valuta, LinkedList<Valuta> kursnaLista) {
		if (!kursnaLista.contains(valuta))
			throw new RuntimeException("Valuta ne postoji u kursnoj listi");
		
		kursnaLista.remove(valuta);
	}
}
