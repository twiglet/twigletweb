//

package Sestoft.GNfaToDfa;

import CS2JNet.System.Collections.LCC.CSList;
import java.util.Map.Entry;
import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;
import Sestoft.GNfaToDfa.Regex;
import Sestoft.GNfaToDfa.Transition;

public class Alt  extends Regex 
{
    Regex r1, r2;
    public Alt(Regex r1, Regex r2) throws Exception {
        this.r1 = r1;
        this.r2 = r2;
    }

    // If   nfa1 has form s1s ----> s1e
    // and  nfa2 has form s2s ----> s2e
    // then nfa0 has form s0s -eps-> s1s ----> s1e -eps-> s0e
    //                    s0s -eps-> s2s ----> s2e -eps-> s0e
    public Nfa mkNfa(NameSource names) throws Exception {
        Nfa nfa1 = r1.mkNfa(names);
        Nfa nfa2 = r2.mkNfa(names);
        int s0s = names.next();
        int s0e = names.next();
        Nfa nfa0 = new Nfa(s0s,s0e);
        for (Entry<Integer,CSList<Transition>> entry : nfa1.getTrans().entrySet())
            nfa0.addTrans(entry);
        for (Entry<Integer,CSList<Transition>> entry : nfa2.getTrans().entrySet())
            nfa0.addTrans(entry);
        nfa0.addTrans(s0s,null,nfa1.getStart());
        nfa0.addTrans(s0s,null,nfa2.getStart());
        nfa0.addTrans(nfa1.getExit(),null,s0e);
        nfa0.addTrans(nfa2.getExit(),null,s0e);
        return nfa0;
    }

}


