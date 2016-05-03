package menjacnica.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnObrisiKurs;
	private JButton btnIzvrsiZamenu;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnAbout;
	private JMenuItem mntmNew;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private static JTable table;


	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIKontroler.ugasiAplikaciju();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenjacnicaGUI.class.getResource("/icons/Screenshot.png")));
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 722, 470);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
		
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			panel.setPreferredSize(new Dimension(160, 10));
			panel.add(getBtnNewButton());
			panel.add(getBtnObrisiKurs());
			panel.add(getBtnIzvrsiZamenu());
		}
		return panel;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Dodaj kurs");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.prikaziDodajKursGUI();
				}
			});
			btnNewButton.setPreferredSize(new Dimension(140, 25));
		}
		return btnNewButton;
	}
	private JButton getBtnObrisiKurs() {
		if (btnObrisiKurs == null) {
			btnObrisiKurs = new JButton("Obrisi kurs");
			btnObrisiKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.prikaziObrisiKursGUI(table);
				}
			});
			btnObrisiKurs.setPreferredSize(new Dimension(140, 25));
		}
		return btnObrisiKurs;
	}
	private JButton getBtnIzvrsiZamenu() {
		if (btnIzvrsiZamenu == null) {
			btnIzvrsiZamenu = new JButton("Izvrsi zamenu");
			btnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.prikaziIzvrsiZamenuGUI(table);
				}
			});
			btnIzvrsiZamenu.setPreferredSize(new Dimension(140, 25));
		}
		return btnIzvrsiZamenu;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnAbout());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmNew());
			mnFile.add(getMntmSave());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenu getMnAbout() {
		if (mnAbout == null) {
			mnAbout = new JMenu("Help");
			mnAbout.setMnemonic(KeyEvent.VK_A);
			mnAbout.add(getMntmAbout());
		}
		return mnAbout;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("Open");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.ucitajIzFajla(getTable());
				}
			});
			mntmNew.setIcon(new ImageIcon(MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
			mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return mntmNew;
	}
	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.sacuvajUFajl();
				}
			});mntmSave.setIcon(new ImageIcon(MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
			mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}
		return mntmSave;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.ugasiAplikaciju();
				}
			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		}
		return mntmExit;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.prikaziAboutProzor();
				}
			});
		}
		return mntmAbout;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new MenjacnicaTableModel());
		}
		return table;
	}
	public JTable vratiTabelu(){
		return getTable();
	}
}
