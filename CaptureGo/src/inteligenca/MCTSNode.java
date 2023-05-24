package inteligenca;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


import logika.Igra;
import logika.Igralec;

import logika.Stanje;

import splosno.Poteza;

public class MCTSNode extends Igra {
	protected MCTSNode parent;
	protected LinkedList<MCTSNode> children;
	protected int numberOfVisits;
	protected Map<Igralec, Integer> results;
	protected LinkedList<Poteza> untriedActions;
	protected Poteza last;
	protected Random random;
	
	public MCTSNode(Igra igra) {
		super(igra.mreza, igra.na_potezi, igra.skupine_zetonov);
		parent = null;
		children = new LinkedList<MCTSNode>();
		numberOfVisits = 0;
		untriedActions = poteze();
		results = new HashMap<Igralec, Integer>();
		results.put(Igralec.BELI, 0);
		results.put(Igralec.CRNI, 0);
		last = null;
		random = new Random();
	}
	
	public MCTSNode(MCTSNode parent, Poteza poteza) {
		super(parent.mreza, parent.na_potezi, parent.skupine_zetonov);
		odigraj(poteza);
		this.parent = parent;
		children = new LinkedList<MCTSNode>();
		numberOfVisits = 0;
		untriedActions = poteze();
		this.results = new HashMap<Igralec, Integer>();
		this.results.put(Igralec.BELI, 0);
		this.results.put(Igralec.CRNI, 0);
		last = poteza;
		random = new Random();
	}
	
	public int n() {
		return numberOfVisits;
	}
	
	public int q(Igralec p) {
		int wins = results.get(p);
		int loses = results.get(p.nasprotnik());
		return wins - loses;
	}
	
	public MCTSNode expand() {
		Poteza action = untriedActions.pollFirst();
		MCTSNode child = new MCTSNode(this, action);
		children.addFirst(child);
		return child;
	}
	
	public boolean isNodeTerminal() {
		return !(stanje() == Stanje.V_TEKU);
	}
	
	public Stanje rollout() {
		Igra current_game = new Igra(mreza, na_potezi, skupine_zetonov);
		while (current_game.stanje() == Stanje.V_TEKU) {
			LinkedList<Poteza> possibleMoves = current_game.poteze();
			Poteza move = rollout_policy(possibleMoves);
			current_game = new Igra(current_game.mreza, current_game.na_potezi, current_game.skupine_zetonov);
			current_game.odigraj(move);
		}
		return current_game.stanje();
	}
	
	
	public Poteza rollout_policy(LinkedList<Poteza> moves) {
		int randomIndex = random.nextInt(moves.size());
		return moves.get(randomIndex);
	}
	
	public void backpropagate(Stanje result) {
		numberOfVisits++;
		switch(result) {
		case ZMAGA_BELI:
			int prevResB = this.results.get(Igralec.BELI);
			prevResB++;
			this.results.put(Igralec.BELI, prevResB);
			break;
		case ZMAGA_CRNI:
			int prevResR = this.results.get(Igralec.CRNI);
			prevResR++;
			this.results.put(Igralec.CRNI, prevResR);
			break;
		case NEODLOCENO:
		case V_TEKU:
			break;
		}
		if (parent != null) {
			parent.backpropagate(result);
		}
	}
	
	public boolean isFullyExpanded() {
		return (untriedActions.size() == 0);
	}
	
	public MCTSNode bestChild(double c) {
		Double[] weights = new Double[children.size()];
		for (int i = 0; i < children.size(); i++) {
			MCTSNode child = children.get(i);
			double weight = (child.q(this.na_potezi) / child.n()) + c * Math.sqrt(Math.log(n()) / child.n());
			weights[i] = weight;
		}
		double maxValue = 0;
		int maxIndex = 0;
		for (int i = 0; i < weights.length; i++) {
			if (weights[i] > maxValue) {
				maxValue = weights[i];
				maxIndex = i;
			}
		}
		return children.get(maxIndex);
	}
}
