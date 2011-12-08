//

package Twiglet.Sample.Event;

import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.LCC.EventHandler;
import Twiglet.Sample.Event.ClearEventArgs;
import Twiglet.Sample.Event.StoreEventArgs;

//   Copyright (c) 2011 Kevin Glynn (http://www.twigletsoftware.com)
//
// The MIT License (Expat)
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software
// and associated documentation files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
// and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial
// portions of the Software.
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
// LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
// IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
// SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
/**
* Sample class to show off CS2J's translations for events.
* 
* A StorePublisher object stores a list of thingummys.  Thingummys
* can be added to the store and the store can be cleared. (For brevity
* we omit methods to retrieve thingummys from the store!).
* 
* The StorePublisher has two events, one fires when items are added, the
* other fires when the store is cleared. Callers to Add and Clear pass
* a name, and this name is sent to the event subscribers.
*/
public class StorePublisher <T>  
{
    private CSList<T> store = null;
    // The events raised by this Store Publisher
    public EventHandler<ClearEventArgs> RaiseClearedEvent;
    public EventHandler<StoreEventArgs<T>> RaiseStoredEvent;
    // raise the Clear event
    protected void onRaiseClearedEvent(ClearEventArgs e) throws Exception {
        if (RaiseClearedEvent != null)
        {
            // invoke the waiting subscribers.
            RaiseClearedEvent.invoke(this, e);
        }
         
    }

    // raise the Store event
    protected void onRaiseStoredEvent(StoreEventArgs<T> e) throws Exception {
        if (RaiseStoredEvent != null)
        {
            // invoke the waiting subscribers.
            RaiseStoredEvent.invoke(this, e);
        }
         
    }

    public void clear(String requestor) throws Exception {
        store = new CSList<T>();
        onRaiseClearedEvent(new ClearEventArgs(requestor));
    }

    public void add(String requestor, T data) throws Exception {
        if (store == null)
        {
            clear("Add");
        }
         
        store.add(data);
        onRaiseStoredEvent(new StoreEventArgs<T>(requestor,data));
    }

}


