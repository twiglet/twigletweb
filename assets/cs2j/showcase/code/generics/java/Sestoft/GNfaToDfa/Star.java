//

package Sestoft.GNfaToDfa;

import CS2JNet.System.Collections.LCC.CSList;
import java.util.Map.Entry;
import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;
import Sestoft.GNfaToDfa.Regex;
import Sestoft.GNfaToDfa.Transition;

public class Star  extends Regex 
{
    Regex r;
    public Star(Regex r) throws Exception {
        this.r = r;
    }

    // If   nfa1 has form s1s ----> s1e
    // then nfa0 has form s0s ----> s0s
    //                    s0s -eps-> s1s
    //                    s1e -eps-> s0s
    public Nfa mkNfa(NameSource names) throws Exception {
        Nfa nfa1 = r.mkNfa(names);
        int s0s = names.next();
        Nfa nfa0 = new Nfa(s0s,s0s);
        for (Entry<Integer,CSList<Transition>> entry : nfa1.getTrans().entrySet())
            nfa0.addTrans(entry);
        nfa0.addTrans(s0s,null,nfa1.getStart());
        nfa0.addTrans(nfa1.getExit(),null,s0s);
        return nfa0;
    }

}


