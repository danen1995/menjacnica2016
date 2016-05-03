package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.sistemskeoperacija.SODodajValutu;
import menjacnica.sistemskeoperacija.SOSacuvajUFajl;

public class GUIKontroler {
	private static MenjacnicaGUI glavnProzor;
	private static MenjacnicaInterface menjacnica;
	private static MenjacnicaTableModel model;
	private static DodajKursGUI prozorZaDodajKurs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prozorZaDodajKurs = new DodajKursGUI();
					menjacnica = new Menjacnica();
					glavnProzor = new MenjacnicaGUI();
					glavnProzor.setVisible(true);
					glavnProzor.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void ugasiAplikaciju() {
			int opcija = JOptionPane.showConfirmDialog(glavnProzor.getContentPane(),
					"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
					JOptionPane.YES_NO_OPTION);

			if (opcija == JOptionPane.YES_OPTION)
				System.exit(0);
	}
	public static void prikaziAboutProzor() {
		JOptionPane.showMessageDialog(glavnProzor.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
		
	}
	public static void ucitajIzFajla(JTable table) {
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(glavnProzor.getContentPane());

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					menjacnica.ucitajIzFajla(file.getAbsolutePath());
					prikaziSveValute(table);
				}	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavnProzor.getContentPane(), e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
	}
	public static void sacuvajUFajl() {
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(glavnProzor.getContentPane());

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					SOSacuvajUFajl.izvrsi(file.getAbsolutePath(), menjacnica.vratiKursnuListu());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavnProzor.getContentPane(), e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}
	public static void prikaziSveValute(JTable table){
			model = (MenjacnicaTableModel)(table.getModel());
			model.staviSveValuteUModel(menjacnica.vratiKursnuListu());
	}
	public static JTable vratiTabelu(){
		return glavnProzor.vratiTabelu();
	}
	public static void prikaziDodajKursGUI() {
		prozorZaDodajKurs = new DodajKursGUI();
		prozorZaDodajKurs.setLocationRelativeTo(glavnProzor.getContentPane());
		prozorZaDodajKurs.setVisible(true);
	}
	public static void prikaziObrisiKursGUI(JTable table) {
			if (table.getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel)(table.getModel());
				ObrisiKursGUI prozor = new ObrisiKursGUI(model.vratiValutu(table.getSelectedRow()));
				prozor.setLocationRelativeTo(glavnProzor.getContentPane());
				prozor.setVisible(true);
			}
		}
	public static void prikaziIzvrsiZamenuGUI(JTable table) {
			if (table.getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel)(table.getModel());
				IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(model.vratiValutu(table.getSelectedRow()));
				prozor.setLocationRelativeTo(glavnProzor.getContentPane());
				prozor.setVisible(true);
		}
		
	}
	public static void unesiKurs(String naziv, String skraceniNaziv, 
			int sifra, double prodajniKurs,
			double kupovniKurs, double srednjiKurs) {
			try {
				Valuta valuta = new Valuta();
				// Punjenje podataka o valuti
				valuta.setNaziv(naziv);
				valuta.setSkraceniNaziv(skraceniNaziv);
				valuta.setSifra(sifra);
				valuta.setProdajni(prodajniKurs);
				valuta.setKupovni(kupovniKurs);
				valuta.setSrednji(srednjiKurs);
				// Dodavanje valute u kursnu listu
				menjacnica.dodajValutu(valuta);
				// Osvezavanje glavnog prozora
				prikaziSveValute(vratiTabelu());
				//Zatvaranje DodajValutuGUI prozora
		
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(prozorZaDodajKurs.getContentPane(), e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
	}
	public static void prikaziValutu(JTextField prodajniKurs, JTextField kupovniKurs, JTextField valuta1, Valuta valuta) {
			prodajniKurs.setText(""+valuta.getProdajni());
			kupovniKurs.setText(""+valuta.getKupovni());
			valuta1.setText(valuta.getSkraceniNaziv());
		}
	public static void izvrsiZamenu(Valuta valuta, JTextField tfiznos, JTextField tfkonacniIznos, JRadioButton rbtnprodaja) {
		try{
			double konacniIznos = 
					menjacnica.izvrsiTransakciju(valuta,
							rbtnprodaja.isSelected(), 
							Double.parseDouble(tfiznos.getText()));
		
			tfkonacniIznos.setText(""+konacniIznos);
		} catch (Exception e1) {
		JOptionPane.showMessageDialog(null, e1.getMessage(),
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
	}
	public static void prikaziValutu(Valuta valuta, JTextField textFieldNaziv, JTextField textFieldSkraceniNaziv, 
			JTextField textFieldSifra, JTextField textFieldProdajniKurs, JTextField textFieldKupovniKurs, JTextField textFieldSrednjiKurs) {
			// Prikaz podataka o valuti
			textFieldNaziv.setText(valuta.getNaziv());
			textFieldSkraceniNaziv.setText(valuta.getSkraceniNaziv());
			textFieldSifra.setText(""+valuta.getSifra());
			textFieldProdajniKurs.setText(""+valuta.getProdajni());
			textFieldKupovniKurs.setText(""+valuta.getKupovni());
			textFieldSrednjiKurs.setText(""+valuta.getSrednji());				
		}
	public static void obrisiValutu(Valuta valuta) {
			try{
				menjacnica.obrisiValutu(valuta);
				GUIKontroler.prikaziSveValute(GUIKontroler.vratiTabelu());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
	}	
	
}
