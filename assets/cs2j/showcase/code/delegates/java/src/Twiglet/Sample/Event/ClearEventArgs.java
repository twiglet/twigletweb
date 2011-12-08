//

package Twiglet.Sample.Event;

import CS2JNet.JavaSupport.language.EventArgs;

/**
* EventArgs derived class to pass info about a request to clear store
*/
public class ClearEventArgs  extends EventArgs 
{
    public ClearEventArgs(String req) throws Exception {
        this.setRequestor(req);
    }

    private String __Requestor;
    public String getRequestor() {
        return __Requestor;
    }

    public void setRequestor(String value) {
        __Requestor = value;
    }

}


