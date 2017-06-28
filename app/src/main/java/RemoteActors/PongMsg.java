package RemoteActors;

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
