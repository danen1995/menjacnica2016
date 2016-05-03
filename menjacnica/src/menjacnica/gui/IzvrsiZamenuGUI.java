package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

import menjacnica.Valuta;
import java.awt.Toolkit;

public class IzvrsiZamenuGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblKupovni;
	private JLabel lblProdajniKurs;
	private JTextField textFieldKupovniKurs;
	private JTextField textFieldProdajniKurs;
	private JLabel lblValuta;
	private JLabel lblIznos;
	private JRadioButton rdbtnKupovina;
	private JRadioButton rdbtnProdaja;
	private JLabel lblVrstaTransakcije;
	private JButton btnIzvrsiZamenu;
	private JButton btnOdustani;
	private JTextField textFieldIznos;
	private JSlider slider;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldValuta;
	private JLabel lblKonacniIznos;
	private JTextField textFieldKonacniIznos;

	private MenjacnicaGUI glavniProzor;
	private Valuta valuta;

	/**
	 * Create the frame.
	 */
	public IzvrsiZamenuGUI(Valuta valuta) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IzvrsiZamenuGUI.class.getResource("/icons/Screenshot.png")));
		setTitle("Izvrsi zamenu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblKupovni());
		contentPane.add(getLabel_1());
		contentPane.add(getTextFieldKupovniKurs());
		contentPane.add(getTextFieldProdajniKurs());
		contentPane.add(getLblValuta());
		contentPane.add(getLblIznos());
		contentPane.add(getRdbtnKupovina());
		contentPane.add(getRdbtnProdaja());
		contentPane.add(getLblVrstaTransakcije());
		contentPane.add(getBtnIzvrsiZamenu());
		contentPane.add(getBtnOdustani());
		contentPane.add(getTextFieldIznos());
		contentPane.add(getSlider());
		contentPane.add(getTextFieldValuta());
		contentPane.add(getLblKonacniIznos());
		contentPane.add(getTextFieldKonacniIznos());
		this.valuta = valuta;
				
		GUIKontroler.prikaziValutu(textFieldProdajniKurs, textFieldKupovniKurs, textFieldValuta, valuta);
	}
	private JLabel getLblKupovni() {
		if (lblKupovni == null) {
			lblKupovni = new JLabel("Kupovni kurs");
			lblKupovni.setPreferredSize(new Dimension(91, 25));
			lblKupovni.setBounds(10, 10, 108, 25);
		}
		return lblKupovni;
	}
	private JLabel getLabel_1() {
		if (lblProdajniKurs == null) {
			lblProdajniKurs = new JLabel("Prodajni kurs");
			lblProdajniKurs.setBounds(276, 10, 134, 25);
		}
		return lblProdajniKurs;
	}
	private JTextField getTextFieldKupovniKurs() {
		if (textFieldKupovniKurs == null) {
			textFieldKupovniKurs = new JTextField();
			textFieldKupovniKurs.setEditable(false);
			textFieldKupovniKurs.setBounds(10, 34, 160, 25);
			textFieldKupovniKurs.setColumns(10);
		}
		return textFieldKupovniKurs;
	}
	private JTextField getTextFieldProdajniKurs() {
		if (textFieldProdajniKurs == null) {
			textFieldProdajniKurs = new JTextField();
			textFieldProdajniKurs.setEditable(false);
			textFieldProdajniKurs.setBounds(276, 34, 160, 25);
			textFieldProdajniKurs.setColumns(10);
		}
		return textFieldProdajniKurs;
	}
	private JLabel getLblValuta() {
		if (lblValuta == null) {
			lblValuta = new JLabel("Valuta");
			lblValuta.setBounds(177, 10, 79, 25);
		}
		return lblValuta;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setBounds(10, 60, 160, 25);
		}
		return lblIznos;
	}
	private JRadioButton getRdbtnKupovina() {
		if (rdbtnKupovina == null) {
			rdbtnKupovina = new JRadioButton("Kupovina");
			buttonGroup.add(rdbtnKupovina);
			rdbtnKupovina.setSelected(true);
			rdbtnKupovina.setBounds(220, 81, 149, 23);
		}
		return rdbtnKupovina;
	}
	private JRadioButton getRdbtnProdaja() {
		if (rdbtnProdaja == null) {
			rdbtnProdaja = new JRadioButton("Prodaja");
			buttonGroup.add(rdbtnProdaja);
			rdbtnProdaja.setBounds(220, 108, 149, 23);
		}
		return rdbtnProdaja;
	}
	private JLabel getLblVrstaTransakcije() {
		if (lblVrstaTransakcije == null) {
			lblVrstaTransakcije = new JLabel("Vrsta transakcije");
			lblVrstaTransakcije.setBounds(220, 60, 200, 25);
		}
		return lblVrstaTransakcije;
	}
	private JButton getBtnIzvrsiZamenu() {
		if (btnIzvrsiZamenu == null) {
			btnIzvrsiZamenu = new JButton("Izracunaj iznos");
			btnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.izvrsiZamenu(valuta, textFieldIznos, textFieldKonacniIznos, rdbtnProdaja);
				}
			});
			btnIzvrsiZamenu.setBounds(24, 234, 160, 25);
		}
		return btnIzvrsiZamenu;
	}
	private JButton getBtnOdustani() {
		if (btnOdustani == null) {
			btnOdustani = new JButton("Zatvori prozor");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnOdustani.setBounds(250, 234, 160, 25);
		}
		return btnOdustani;
	}
	private JTextField getTextFieldIznos() {
		if (textFieldIznos == null) {
			textFieldIznos = new JTextField();
			textFieldIznos.setBounds(10, 91, 200, 25);
			textFieldIznos.setColumns(10);
		}
		return textFieldIznos;
	}
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					textFieldIznos.setText(""+slider.getValue());
				}
			});
			slider.setPaintLabels(true);
			slider.setSnapToTicks(true);
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(5);
			slider.setBounds(10, 139, 426, 42);
		}
		return slider;
	}
	private JTextField getTextFieldValuta() {
		if (textFieldValuta == null) {
			textFieldValuta = new JTextField();
			textFieldValuta.setEditable(false);
			textFieldValuta.setBounds(182, 34, 82, 25);
			textFieldValuta.setColumns(10);
		}
		return textFieldValuta;
	}
	private JLabel getLblKonacniIznos() {
		if (lblKonacniIznos == null) {
			lblKonacniIznos = new JLabel("Konacni iznos");
			lblKonacniIznos.setBounds(10, 193, 126, 29);
		}
		return lblKonacniIznos;
	}
	private JTextField getTextFieldKonacniIznos() {
		if (textFieldKonacniIznos == null) {
			textFieldKonacniIznos = new JTextField();
			textFieldKonacniIznos.setBounds(154, 193, 256, 25);
			textFieldKonacniIznos.setColumns(10);
		}
		return textFieldKonacniIznos;
	}
}
