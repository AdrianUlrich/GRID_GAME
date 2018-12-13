package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

import java.util.LinkedList;
import java.util.List;

public class LogicNumber extends LogicSignal {
	Logic[] e;
	private int nb;
	
	public LogicNumber(int nb, Logic... e) {
		this.nb = nb;
		this.e = e;
	}
	
	@Override
	public boolean isOn() {
		if (e.length > 12 || nb < 0 || nb > Math.pow(2,e.length))
			return false;
		int sum = 0;
		for (int i = 0; i < e.length; i++) {
			Logic l = e[i];
			if (l != null && l.isOn()) {
				sum += Math.pow(2, i);
			}
		}
		return sum == nb;
	}
}
