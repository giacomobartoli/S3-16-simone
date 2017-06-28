package pcd.lab11.actors_remote.pingpong;

import java.io.Serializable;

public class PongMsg implements Serializable {

	private long value;
	
	public PongMsg(long value){
		this.value = value;
	}
	
	public long getValue(){
		return value;
	}
}
