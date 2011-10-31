//

package Sestoft.GNfaToDfa;

import CS2JNet.JavaSupport.Collections.Generic.LCC.CollectionSupport;
import CS2JNet.System.IO.FileAccess;
import CS2JNet.System.IO.FileMode;
import CS2JNet.System.IO.FileStreamSupport;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import Sestoft.GNfaToDfa.Set;

// Class Dfa, deterministic finite automata --------------------------
/*
  A deterministic finite automaton (DFA) is represented as a Map
  from state number (int) to a Map from label (a String,
  non-null) to a target state (an int).  
*/
public class Dfa   
{
    private int startState;
    private Set<Integer> acceptStates;
    private Map<Integer,Map<String,Integer>> trans;
    public Dfa(int startState, Set<Integer> acceptStates, Map<Integer,Map<String,Integer>> trans) throws Exception {
        this.startState = startState;
        this.acceptStates = acceptStates;
        this.trans = trans;
    }

    public int getStart() throws Exception {
        return startState;
    }

    public Set<Integer> getAccept() throws Exception {
        return acceptStates;
    }

    public Map<Integer,Map<String,Integer>> getTrans() throws Exception {
        return trans;
    }

    public String toString() {
        try
        {
            return "DFA start=" + startState + "\naccept=" + acceptStates;
        }
        catch (Exception __dummyCatchVar5)
        {
            throw new RuntimeException(__dummyCatchVar5);
        }
    
    }

    // Write an input file for the dot program.  You can find dot at
    // http://www.research.att.com/sw/tools/graphviz/
    public void writeDot(String filename) throws Exception {
        PrintStream wr = new PrintStream(new FileStreamSupport(filename, FileMode.Create, FileAccess.Write).getOutputStream());
        wr.println("// Format this file as a Postscript file with ");
        wr.println("//    dot " + filename + " -Tps -o out.ps\n");
        wr.println("digraph dfa {");
        wr.println("size=\"11,8.25\";");
        wr.println("rotate=90;");
        wr.println("rankdir=LR;");
        wr.println("n999999 [style=invis];");
        // Invisible start node
        wr.println("n999999 -> n" + startState);
        for (int state : CollectionSupport.mk(trans.keySet()))
            // Edge into start state
            // Accept states are double circles
            if (acceptStates.contains(state))
                wr.println("n" + state + " [peripheries=2];");
             
        for (Entry<Integer,Map<String,Integer>> entry : trans.entrySet())
        {
            // The transitions
            int s1 = entry.getKey();
            for (Entry<String,Integer> s1Trans : entry.getValue().entrySet())
            {
                String lab = s1Trans.getKey();
                int s2 = s1Trans.getValue();
                wr.println("n" + s1 + " -> n" + s2 + " [label=\"" + lab + "\"];");
            }
        }
        wr.println("}");
        wr.close();
    }

}


