/*
 * Created on 13-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Engine {

	private long engineId;
	private int capacity;
	private int bhp;
	private String torque;
	private int topSpeed;
	private float zeroSixty;
	
	
	public int getBhp() {
		return bhp;
	}
	public void setBhp(int bhp) {
		this.bhp = bhp;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public long getEngineId() {
		return engineId;
	}
	public void setEngineId(long engineId) {
		this.engineId = engineId;
	}
	public int getTopSpeed() {
		return topSpeed;
	}
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}
	public String getTorque() {
		return torque;
	}
	public void setTorque(String torque) {
		this.torque = torque;
	}
	public float getZeroSixty() {
		return zeroSixty;
	}
	public void setZeroSixty(float zeroSixty) {
		this.zeroSixty = zeroSixty;
	}
}
