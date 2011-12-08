//

package Twiglet.Sample.Event;

import CS2JNet.JavaSupport.language.EventArgs;
import Twiglet.Sample.Event.SubscriptionType;

/**
* EventArgs derived class to pass info about a new subscriber
*/
public class SubscribeEventArgs  extends EventArgs 
{
    public SubscribeEventArgs(String sub, SubscriptionType ty) throws Exception {
        this.setSubscriber(sub);
        this.setSubnType(ty);
    }

    private String __Subscriber;
    public String getSubscriber() {
        return __Subscriber;
    }

    public void setSubscriber(String value) {
        __Subscriber = value;
    }

    private SubscriptionType __SubnType = SubscriptionType.NewSubscriber;
    public SubscriptionType getSubnType() {
        return __SubnType;
    }

    public void setSubnType(SubscriptionType value) {
        __SubnType = value;
    }

}


