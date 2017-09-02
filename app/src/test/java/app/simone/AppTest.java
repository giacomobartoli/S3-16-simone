package app.simone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import akka.actor.ActorRef;
import akka.testkit.TestKit;
import app.simone.shared.application.ActorDefinitor;
import app.simone.shared.application.App;
import app.simone.shared.utils.Utilities;

import static junit.framework.Assert.assertTrue;

/**
 * Created by nicola on 25/08/2017.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
public class AppTest extends ActorTests {

    @Test
    public void checkActorsCreation() {
        new TestKit(system) {{
            for(ActorDefinitor definitor : App.actorDefinitions) {
                ActorRef ref = Utilities.getActor(definitor.getActorName(), system);
                assertTrue(ref != null);
            }
        }};
    }

    @Test
    public void testUnknownActor() {
        new TestKit(system) {{
            //Check if a new actor is not included in the actors created in the system.
            String testActorName = "testActor";
            ActorRef ref = Utilities.getActor(testActorName, system);
            assertTrue(ref == null);
        }};
    }
}
