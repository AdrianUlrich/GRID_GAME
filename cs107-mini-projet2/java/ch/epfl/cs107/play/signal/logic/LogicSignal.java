package ch.epfl.cs107.play.signal.logic;

public abstract class LogicSignal implements Logic {
	
	@Override
	final public float getIntensity() {
		return (isOn() ? 1.f : 0.f);
	}
	
	@Override
	final public float getIntensity(float t) {
		return getIntensity();
	}
}
