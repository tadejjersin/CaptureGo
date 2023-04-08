package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;

import logika.Igra;
import logika.Koordinati;
import logika.Polje;
import logika.SkupinaZetonov;
import logika.Zeton;
import splosno.Poteza;

public class Platno extends JPanel implements MouseListener, MouseMotionListener {
	
	//protected Plosca plosca;
	protected Igra igra;
	protected int dimPolja;
	protected Color barvaMreze;
	protected Color barvaRobaUjetih;
	protected Stroke debelinaRobaUjetih;
	protected Stroke debelinaRobaMreze;
	protected Stroke debelinaRobaTrenutnega;
	protected Color barvaRobaTrenutnega;
	
	protected double polmer;
	protected Color barvaCrnih;
	protected Color barvaBelih;
	protected Set <Zeton> crniZetoni;
	protected Set <Zeton> beliZetoni;
	protected Set <Zeton> ujetiZetoni;
	protected Zeton trenutniZeton;
	
	public Platno(int x, int y) {
		super();
		igra = new Igra();
		setPreferredSize(new Dimension(x,y));
		dimPolja = min(x,y)/(igra.dimMreze + 2);
		barvaMreze = Color.BLACK;
		barvaRobaUjetih = Color.PINK;
		barvaCrnih = Color.BLACK;
		barvaBelih = Color.WHITE;
		barvaRobaTrenutnega = Color.YELLOW;
		debelinaRobaUjetih = new BasicStroke(2);
		debelinaRobaMreze = new BasicStroke(1);
		debelinaRobaTrenutnega = new BasicStroke(1);
		polmer = 0.8*dimPolja;
		crniZetoni = new HashSet<Zeton>();
		beliZetoni = new HashSet<Zeton>();
		ujetiZetoni = new HashSet<Zeton>();
		trenutniZeton = null;
		
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		nastaviIgro(null);
	}
	private int min(int x, int y) {
		if (x < y) return x;
		return y;
	}
	public void nastaviIgro(Igra igra) {
		this.igra = igra;
		repaint();
	}
	private int round(double x) {
		return (int)(x+0.5);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(230,188,132));
		
		//narišemo mrežo
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(barvaMreze);
		g2.setStroke(debelinaRobaMreze);
		int d = igra.dimMreze;
		for (int i = 0; i < igra.dimMreze; i++) {
			for (int j = 0; j < igra.dimMreze; j++) {
				g.drawLine((i+1)*dimPolja, dimPolja, (i+1)*dimPolja, dimPolja*(d));
				g.drawLine(dimPolja, (j+1)*dimPolja, dimPolja*(d), (j+1)*dimPolja);
			}
		}
		
		//narišemo mesta fore
		if (igra.dimMreze == 9) {
			g.fillOval(3*dimPolja-5, 3*dimPolja-5, 10, 10);
			g.fillOval(3*dimPolja-5, 7*dimPolja-5, 10, 10);
			g.fillOval(7*dimPolja-5, 3*dimPolja-5, 10, 10);
			g.fillOval(7*dimPolja-5, 7*dimPolja-5, 10, 10);
			g.fillOval(5*dimPolja-5, 5*dimPolja-5, 10, 10);
		}
		
		//narišemo žetone
		for (Entry<Koordinati, Zeton> entry: this.igra.mreza.entrySet()) {
			Zeton o = entry.getValue();
			int x = o.koordinati.getX();
			int y = o.koordinati.getY();
			if (o.barva == Polje.CRNO) {
				g.setColor(barvaCrnih);
				g.fillOval(round(dimPolja*x-polmer/2), round(dimPolja*y-polmer/2), round(polmer), round(polmer));
				}
			else if (o.barva == Polje.BELO) {
				g.setColor(barvaBelih);
				g.fillOval(round(dimPolja*x-polmer/2), round(dimPolja*y-polmer/2), round(polmer), round(polmer));
				}
		}
		
		//g2.setStroke(debelinaRobaUjetih);
		//g.setColor(barvaRobaUjetih);
		//obrobimo obkoljene
		//for (Zeton z: this.igra.obkoljena.skupina) {
		//	HashSet<Koordinati> pobarvane = new HashSet<Koordinati>();
		//	for (Zeton o: z.
		//}
		repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int klikX = e.getX();
		int klikY = e.getY();
		if (klikX < dimPolja/2 || klikX > 10.5*dimPolja) return;
		if (klikY < dimPolja/2 || klikY > 10.5*dimPolja) return;
		else {
			int x = (klikX+(dimPolja/2))/dimPolja;
			int y = (klikY+(dimPolja/2))/dimPolja;
			this.igra.narediPotezo(new Poteza(x,y));
		}
		repaint();
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
