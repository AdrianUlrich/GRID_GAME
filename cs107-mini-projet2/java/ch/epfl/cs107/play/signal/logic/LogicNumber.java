package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

import java.util.LinkedList;
import java.util.List;

public class LogicNumber extends LogicSignal {
	List<Logic> e;
	private float nb;
	
	public LogicNumber(float nb, Logic... e) {
		this.nb = nb;
		this.e = new LinkedList<>();
		for (Logic l : e) {
			this.e.add(l);
		}
	}
	
	@Override
	public boolean isOn() {
		if (e.size() > 12 || nb < 0 || nb > Math.pow(2,e.size()))
			return false;
		float sum = 0.f;
		int i = 0;
		for (Logic s : e) {
			if (s != null && s.isOn())
				sum += Math.pow(2, i++);
		}
		return sum > nb - Signal.EPSILON && sum < nb + Signal.EPSILON;
	}
}
