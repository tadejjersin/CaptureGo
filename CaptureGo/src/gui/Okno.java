package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	protected Platno platno;
	private JMenuItem menuIgralecIgralec;
	private JMenuItem menuRacIgralec;
	private JMenuItem menuIgralecRac;
	
	public Okno() {
		super();
		setTitle("Capture-GO");
		this.platno = new Platno(800, 800);
		add(platno);
	
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuNovaIgra = dodajMenu(menubar, "Nova igra...");
		
		menuIgralecIgralec = dodajMenuItem(menuNovaIgra, "Igralec : igralec");
		menuRacIgralec = dodajMenuItem(menuNovaIgra, "Računalnik : igralec");
		menuIgralecRac = dodajMenuItem(menuNovaIgra, "Igralec : računalnik");
	}
	
	private JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}
	
	private JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objekt = e.getSource();
		if (objekt == menuIgralecIgralec) {
			}
		else if (objekt == menuRacIgralec) {
		}
		else if (objekt == menuIgralecRac) {
		}
		
	}


}

