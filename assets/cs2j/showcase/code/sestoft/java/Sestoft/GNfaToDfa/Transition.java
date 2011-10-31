//

package Sestoft.GNfaToDfa;


// Class Transition, a transition from one state to another ----------
public class Transition   
{
    public String lab;
    public int target;
    public Transition(String lab, int target) throws Exception {
        this.lab = lab;
        this.target = target;
    }

    public String toString() {
        try
        {
            return "-" + lab + "-> " + target;
        }
        catch (Exception __dummyCatchVar4)
        {
            throw new RuntimeException(__dummyCatchVar4);
        }
    
    }

}


