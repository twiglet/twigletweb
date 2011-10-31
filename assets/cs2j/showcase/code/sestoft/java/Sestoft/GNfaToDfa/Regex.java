//

package Sestoft.GNfaToDfa;

import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;

// Regular expressions ----------------------------------------------
//
// Abstract syntax of regular expressions
//    r ::= A | r1 r2 | (r1|r2) | r*
//
abstract public class Regex   
{
    abstract public Nfa mkNfa(NameSource names) throws Exception ;

}


