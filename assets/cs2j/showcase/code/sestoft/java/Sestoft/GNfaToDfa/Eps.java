//

package Sestoft.GNfaToDfa;

import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;
import Sestoft.GNfaToDfa.Regex;

public class Eps  extends Regex 
{
    // The resulting nfa0 has form s0s -eps-> s0e
    public Nfa mkNfa(NameSource names) throws Exception {
        int s0s = names.next();
        int s0e = names.next();
        Nfa nfa0 = new Nfa(s0s,s0e);
        nfa0.addTrans(s0s,null,s0e);
        return nfa0;
    }

}


