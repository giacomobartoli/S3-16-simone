package RemoteActors;

import akka.actor.*;

public class Ponger extends UntypedActor {
	
	  @Override
	  public void onReceive(Object msg) {
		  PingMsg mess = (PingMsg) msg;
		  System.out.println("PING received: "+  mess.getValue());
		  getSender().tell(new PongMsg( mess.getValue() + 1), getSelf());
	  }

}
