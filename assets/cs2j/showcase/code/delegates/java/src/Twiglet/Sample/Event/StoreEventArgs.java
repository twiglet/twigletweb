//

package Twiglet.Sample.Event;

import CS2JNet.JavaSupport.language.EventArgs;

/**
* EventArgs derived class to pass info about a request to add data to store
*/
public class StoreEventArgs <T> extends EventArgs 
{
    public StoreEventArgs(String req, T data) throws Exception {
        this.setRequestor(req);
        this.setData(data);
    }

    private String __Requestor;
    public String getRequestor() {
        return __Requestor;
    }

    public void setRequestor(String value) {
        __Requestor = value;
    }

    private T __Data;
    public T getData() {
        return __Data;
    }

    public void setData(T value) {
        __Data = value;
    }

}


