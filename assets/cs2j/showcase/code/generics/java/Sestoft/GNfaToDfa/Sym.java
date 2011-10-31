//

package Sestoft.GNfaToDfa;

import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;
import Sestoft.GNfaToDfa.Regex;

public class Sym  extends Regex 
{
    String sym;
    public Sym(String sym) throws Exception {
        this.sym = sym;
    }

    // The resulting nfa0 has form s0s -sym-> s0e
    public Nfa mkNfa(NameSource names) throws Exception {
        int s0s = names.next();
        int s0e = names.next();
        Nfa nfa0 = new Nfa(s0s,s0e);
        nfa0.addTrans(s0s,sym,s0e);
        return nfa0;
    }

}


