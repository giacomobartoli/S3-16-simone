package pcd.lab11.actors_remote.pingpong;
import akka.actor.*;

public class Pinger extends UntypedActor {

	 public void preStart() {
		  final ActorSelection ponger = getContext().actorSelection("akka.tcp://MySystem@127.0.0.1:4552/user/ponger");
		  ponger.tell(new PingMsg(0), getSelf());
	  }	
	 
	  @Override
	  public void onReceive(Object msg) {
		  PongMsg mess = (PongMsg) msg;
		  System.out.println("PONG received: "+  mess.getValue());
		  getSender().tell(new PingMsg( mess.getValue() + 1), getSelf());
	  }
	
}
