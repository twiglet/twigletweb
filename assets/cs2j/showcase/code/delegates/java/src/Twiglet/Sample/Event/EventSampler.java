//

package Twiglet.Sample.Event;

import Twiglet.Sample.Event.StorePublisher;
import Twiglet.Sample.Event.StoreSubscriber;

public class EventSampler   
{
    public static String MYID = "StoreUser";
    public static void main(String[] args) throws Exception {
        // Create StorePublisher instance
        StorePublisher<String> s = new StorePublisher<String>();
        // Add first subscriber
        new StoreSubscriber<String>("Subscriber A").subscribeToStore(s);
        // Generates events for initial clear and the add
        s.add(MYID,"Store Ham");
        // Generates an add event
        s.add(MYID,"Store Eggs");
        // Add second subscriber
        new StoreSubscriber<String>("Subscriber B").subscribeToStore(s);
        // Both subscribers are notified of add
        s.add(MYID,"Store Milk");
        // Both subscribers are notified of clear
        s.clear(MYID);
    }

}


