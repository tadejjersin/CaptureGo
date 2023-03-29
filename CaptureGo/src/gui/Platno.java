package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class Platno extends JPanel implements MouseListener, MouseMotionListener {
	
	protected Plosca plosca;
	protected Color barvaMreze;
	protected Color barvaRobaUjetih;
	protected Stroke debelinaRobaUjetih;
	protected Stroke debelinaRobaMreze;
	protected Stroke debelinaRobaTrenutnega;
	protected Color barvaRobaTrenutnega;
	
	protected double polmer;
	protected Color barvaCrnih;
	protected Color barvaBelih;
	protected Set <Kamen> crniKamni;
	protected Set <Kamen> beliKamni;
	protected Set <Kamen> ujetiKamni;
	protected Kamen trenutniKamen;
	
	public Platno(int x, int y) {
		super();
		setPreferredSize(new Dimension(x,y));
		barvaMreze = Color.BLACK;
		barvaRobaUjetih = Color.PINK;
		barvaCrnih = Color.BLACK;
		barvaBelih = Color.WHITE;
		barvaRobaTrenutnega = Color.YELLOW;
		debelinaRobaUjetih = new BasicStroke(2);
		debelinaRobaMreze = new BasicStroke(1);
		debelinaRobaTrenutnega = new BasicStroke(1);
		polmer = 10;
		crniKamni = new HashSet<Kamen>();
		beliKamni = new HashSet<Kamen>();
		ujetiKamni = new HashSet<Kamen>();
		trenutniKamen = null;
		
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		nastaviPlosco(null);
	}
	public void nastaviPlosco() {
		this.plosca = p;
		
		repaint();
	}
	private int round(double x) {
		return (int)(x+0.5);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//narisemo mrezo -TODO
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(barvaCrnih);
		g2.setStroke(debelinaPovezave);
		for (Tocka u: this.graf.tocke.values()) {
			for (Tocka v: u.sosedi) {
				if (u.ime.compareTo(v.ime) < 0) {
					g.drawLine(round(u.x), round(u.y), round(v.x), round(v.y));
				}
			}
		}
		g2.setStroke(debelinaRoba);
		for (Tocka u: this.graf.tocke.values()) {
			if (u.equals(aktivnaTocka)) {g.setColor(barvaAktivneTocke);}
			else {if (oznaceneTocke.contains(u)) {g.setColor(barvaOznacenih);}
			else {g.setColor(barvaTocke);}
			}
			g.fillOval(round(u.x-polmer), round(u.y-polmer), round(polmer), round(polmer));
			g.setColor(barvaRoba);
			g.drawOval(round(u.x-polmer), round(u.y-polmer), round(polmer), round(polmer));
		}
	}
}
