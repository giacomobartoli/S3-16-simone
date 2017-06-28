package RemoteActors;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainPinger {

  public static void main(String[] args) {
	Config config = ConfigFactory.parseFile(new File("/pinger.conf"));
	ActorSystem system = ActorSystem.create("MySystem",config);
	system.actorOf(Props.create(Pinger.class),"pinger");
  }
}
