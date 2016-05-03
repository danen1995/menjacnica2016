package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.sistemskeoperacija.SODodajValutu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DodajKursGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblSifra;
	private JLabel lblNaziv;
	private JTextField textFieldNaziv;
	private JLabel lblProdajniKurs;
	private JLabel lblKupovniKurs;
	private JTextField textFieldProdajniKurs;
	private JTextField textFieldKupovniKurs;
	private JLabel lblSrednjiKurs;
	private JLabel lblSkraceniNaziv;
	private JTextField textFieldSrednjiKurs;
	private JTextField textFieldSkraceniNaziv;
	private JButton btnDodaj;
	private JButton btnOdus;
	private MenjacnicaGUI glavniProzor;
	private JSpinner spinnerSifra;
	/**
	 * Create the frame.
	 */
	public DodajKursGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DodajKursGUI.class.getResource("/icons/Screenshot.png")));
		setResizable(false);
		setTitle("Dodaj kurs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 10, 10));
		contentPane.add(getLblSifra());
		contentPane.add(getLblNaziv());
		contentPane.add(getSpinnerSifra());
		contentPane.add(getTextFieldNaziv());
		contentPane.add(getLblProdajniKurs());
		contentPane.add(getLblKupovniKurs());
		contentPane.add(getTextFieldProdajniKurs());
		contentPane.add(getTextFieldKupovniKurs());
		contentPane.add(getLblSrednjiKurs());
		contentPane.add(getLblSkraceniNaziv());
		contentPane.add(getTextFieldSrednjiKurs());
		contentPane.add(getTextFieldSkraceniNaziv());
		contentPane.add(getBtnDodaj());
		contentPane.add(getBtnOdus());
	}
	private JLabel getLblSifra() {
		if (lblSifra == null) {
			lblSifra = new JLabel("Sifra");
			lblSifra.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblSifra;
	}
	private JLabel getLblNaziv() {
		if (lblNaziv == null) {
			lblNaziv = new JLabel("Naziv");
			lblNaziv.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblNaziv;
	}
	private JTextField getTextFieldNaziv() {
		if (textFieldNaziv == null) {
			textFieldNaziv = new JTextField();
			textFieldNaziv.setColumns(10);
		}
		return textFieldNaziv;
	}
	private JLabel getLblProdajniKurs() {
		if (lblProdajniKurs == null) {
			lblProdajniKurs = new JLabel("Prodajni kurs");
			lblProdajniKurs.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblProdajniKurs;
	}
	private JLabel getLblKupovniKurs() {
		if (lblKupovniKurs == null) {
			lblKupovniKurs = new JLabel("Kupovni kurs");
			lblKupovniKurs.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblKupovniKurs;
	}
	private JTextField getTextFieldProdajniKurs() {
		if (textFieldProdajniKurs == null) {
			textFieldProdajniKurs = new JTextField();
			textFieldProdajniKurs.setColumns(10);
		}
		return textFieldProdajniKurs;
	}
	private JTextField getTextFieldKupovniKurs() {
		if (textFieldKupovniKurs == null) {
			textFieldKupovniKurs = new JTextField();
			textFieldKupovniKurs.setColumns(10);
		}
		return textFieldKupovniKurs;
	}
	private JLabel getLblSrednjiKurs() {
		if (lblSrednjiKurs == null) {
			lblSrednjiKurs = new JLabel("Srednji kurs");
			lblSrednjiKurs.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblSrednjiKurs;
	}
	private JLabel getLblSkraceniNaziv() {
		if (lblSkraceniNaziv == null) {
			lblSkraceniNaziv = new JLabel("Skraceni naziv");
			lblSkraceniNaziv.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblSkraceniNaziv;
	}
	private JTextField getTextFieldSrednjiKurs() {
		if (textFieldSrednjiKurs == null) {
			textFieldSrednjiKurs = new JTextField();
			textFieldSrednjiKurs.setColumns(10);
		}
		return textFieldSrednjiKurs;
	}
	private JTextField getTextFieldSkraceniNaziv() {
		if (textFieldSkraceniNaziv == null) {
			textFieldSkraceniNaziv = new JTextField();
			textFieldSkraceniNaziv.setColumns(10);
		}
		return textFieldSkraceniNaziv;
	}
	private JButton getBtnDodaj() {
		if (btnDodaj == null) {
			btnDodaj = new JButton("Dodaj");
			btnDodaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.unesiKurs(textFieldNaziv.getText(), textFieldSkraceniNaziv.getText(),
							(Integer) spinnerSifra.getValue(),
							Double.parseDouble(textFieldProdajniKurs.getText()),
							Double.parseDouble(textFieldKupovniKurs.getText()),
							Double.parseDouble(textFieldSrednjiKurs.getText()));
					dispose();
				}
			});
		}
		return btnDodaj;
	}
	private JButton getBtnOdus() {
		if (btnOdus == null) {
			btnOdus = new JButton("Odustani");
			btnOdus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnOdus;
	}
	private JSpinner getSpinnerSifra() {
		if (spinnerSifra == null) {
			spinnerSifra = new JSpinner();
			spinnerSifra.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		}
		return spinnerSifra;
	}	

}
