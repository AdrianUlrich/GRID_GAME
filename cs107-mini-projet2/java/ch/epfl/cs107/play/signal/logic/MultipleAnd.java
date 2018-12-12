package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class MultipleAnd extends LogicSignal {
	private List<Logic> s;
	
	public MultipleAnd(Logic... s) {
		this.s = new LinkedList<>();
		for (Logic l : s) {
			this.s.add(l);
		}
	}
	
	@Override
	public boolean isOn() {
		for (Logic l : s) {
			if (!l.isOn()) return false;
		}
		return true;
	}
}
