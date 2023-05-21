package inteligenca;

import logika.Igra;
import logika.Stanje;
import splosno.Poteza;

public class MCTS {
	protected int n;
	
	public MCTS(int n) {
		this.n = n;
	}
	
	public Poteza izberiPotezo(Igra game) {
		Poteza move = mcts(game, this.n);
		return move;
	}
	
	public Poteza mcts(Igra game, int n) {
		MCTSNode root = new MCTSNode(game);
		int i = 0;
		while (i < this.n) {
			MCTSNode leaf = traverse(root);
			Stanje simulationResult = leaf.rollout();
			leaf.backpropagate(simulationResult);
			i++;
		}
		return root.bestChild(0).last;
	}
	
	private MCTSNode traverse(MCTSNode root) {
		MCTSNode currentNode = root;
		while (!currentNode.isNodeTerminal()) {
			if (!currentNode.isFullyExpanded()) {
				return currentNode.expand();
			} else currentNode = currentNode.bestChild(1.4);
		}
		return currentNode;
	}
}
