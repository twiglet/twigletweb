//

package Sestoft.GNfaToDfa;

import Sestoft.GNfaToDfa.Alt;
import Sestoft.GNfaToDfa.Dfa;
import Sestoft.GNfaToDfa.Eps;
import Sestoft.GNfaToDfa.Nfa;
import Sestoft.GNfaToDfa.Nfa.NameSource;
import Sestoft.GNfaToDfa.Regex;
import Sestoft.GNfaToDfa.Seq;
import Sestoft.GNfaToDfa.Star;
import Sestoft.GNfaToDfa.Sym;
import Sestoft.GNfaToDfa.TestNFA;

// Trying the RE->NFA->DFA translation on three regular expressions
public class TestNFA   
{
    public static void main(String[] args) throws Exception {
        TestNFA.Main(args);
    }

    public static void Main(String[] args) throws Exception {
        Regex a = new Sym("A");
        Regex b = new Sym("B");
        Regex c = new Sym("C");
        Regex abStar = new Star(new Alt(a,b));
        Regex bb = new Seq(b,b);
        Regex r = new Seq(abStar,new Seq(a,b));
        // The regular expression (a|b)*ab
        buildAndShow("dfa1.dot",r);
        // The regular expression ((a|b)*ab)*
        buildAndShow("dfa2.dot",new Star(r));
        // The regular expression ((a|b)*ab)((a|b)*ab)
        buildAndShow("dfa3.dot",new Seq(r,r));
        // The regular expression (a|b)*abb, from ASU 1986 p 136
        buildAndShow("dfa4.dot",new Seq(abStar,new Seq(a,bb)));
        // SML reals: sign?((digit+(\.digit+)?))([eE]sign?digit+)?
        Regex d = new Sym("digit");
        Regex dPlus = new Seq(d,new Star(d));
        Regex s = new Sym("sign");
        Regex sOpt = new Alt(s,new Eps());
        Regex dot = new Sym(".");
        Regex dotDigOpt = new Alt(new Eps(),new Seq(dot,dPlus));
        Regex mant = new Seq(sOpt,new Seq(dPlus,dotDigOpt));
        Regex e = new Sym("e");
        Regex exp = new Alt(new Eps(),new Seq(e,new Seq(sOpt,dPlus)));
        Regex smlReal = new Seq(mant,exp);
        buildAndShow("dfa5.dot",smlReal);
    }

    public static void buildAndShow(String filename, Regex r) throws Exception {
        Nfa nfa = r.mkNfa(new NameSource());
        System.out.println(nfa);
        System.out.println("---");
        Dfa dfa = nfa.toDfa();
        System.out.println(dfa);
        System.out.println("Writing DFA graph to file " + filename);
        dfa.writeDot(filename);
        System.out.println();
    }

}


