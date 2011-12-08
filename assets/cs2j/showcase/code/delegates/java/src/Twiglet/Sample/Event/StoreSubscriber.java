//

package Twiglet.Sample.Event;

import CS2JNet.System.LCC.__MultiEventHandler;
import CS2JNet.System.LCC.EventHandler;
import java.util.ArrayList;
import java.util.List;
import Twiglet.Sample.Event.ClearEventArgs;
import Twiglet.Sample.Event.StoreEventArgs;
import Twiglet.Sample.Event.StorePublisher;

public class StoreSubscriber <T>  
{
    /**
    * Name identifies this subscriber in the trace.
    * 
    * The name.
    */
    private String __Name;
    public String getName() {
        return __Name;
    }

    public void setName(String value) {
        __Name = value;
    }

    public StoreSubscriber(String n) throws Exception {
        setName(n);
    }

    /**
    * We subscribe to both StorePublisher events.
    * 
    *  @param store 
    * Store.
    */
    public void subscribeToStore(StorePublisher<T> store) throws Exception {
        store.RaiseClearedEvent = __MultiEventHandler.combine(store.RaiseClearedEvent,new EventHandler<ClearEventArgs>() 
          { 
            public void invoke(Object sender, ClearEventArgs e) throws Exception {
                onClearEvent(sender, e);
            }

            public List<EventHandler<ClearEventArgs>> getInvocationList() throws Exception {
                List<EventHandler<ClearEventArgs>> ret = new ArrayList<EventHandler<ClearEventArgs>>();
                ret.add(this);
                return ret;
            }
        
          });
        store.RaiseStoredEvent = __MultiEventHandler.combine(store.RaiseStoredEvent,new EventHandler<StoreEventArgs<T>>() 
          { 
            public void invoke(Object sender, StoreEventArgs<T> e) throws Exception {
                onStoreEvent(sender, e);
            }

            public List<EventHandler<StoreEventArgs<T>>> getInvocationList() throws Exception {
                List<EventHandler<StoreEventArgs<T>>> ret = new ArrayList<EventHandler<StoreEventArgs<T>>>();
                ret.add(this);
                return ret;
            }
        
          });
    }

    private void onClearEvent(Object sender, ClearEventArgs e) throws Exception {
        System.out.println(getName() + ": " + e.getRequestor() + " cleared the Store");
    }

    private void onStoreEvent(Object sender, StoreEventArgs<T> e) throws Exception {
        System.out.println(getName() + ": " + e.getRequestor() + " added " + e.getData().toString() + " to the Store");
    }

}


