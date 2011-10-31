//

package Sestoft.GNfaToDfa;

import CS2JNet.JavaSupport.Collections.Generic.LCC.CollectionSupport;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.ICollection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import Sestoft.GNfaToDfa.Dfa;
import Sestoft.GNfaToDfa.Set;
import Sestoft.GNfaToDfa.Transition;

// ----------------------------------------------------------------------
// Regular expressions, NFAs, DFAs, and dot graphs
// sestoft@dina.kvl.dk *
// Java 2001-07-10 * C# 2001-10-22 * Gen C# 2001-10-23, 2003-09-03
// In the Generic C# 2.0 version we
//  use Queue<int> and Queue<Set<int>> for worklists
//  use Set<int> for pre-DFA states
//  use List<Transition> for NFA transition relations
//  use Dictionary<Set<int>, Dictionary<String, Set<int>>>
//  and Dictionary<int, Dictionary<String, int>> for DFA transition relations
/* Class Nfa and conversion from NFA to DFA ---------------------------
  A nondeterministic finite automaton (NFA) is represented as a
  Map from state number (int) to a List of Transitions, a
  Transition being a pair of a label lab (a String, null meaning
  epsilon) and a target state (an int).
  A DFA is created from an NFA in two steps:
    (1) Construct a DFA whose each of whose states is composite,
        namely a set of NFA states (Set of int).  This is done by
        methods CompositeDfaTrans and EpsilonClose.
    (2) Replace composite states (Set of int) by simple states
        (int).  This is done by methods Rename and MkRenamer.
  Method CompositeDfaTrans works as follows: 
    Create the epsilon-closure S0 (a Set of ints) of the start
    state s0, and put it in a worklist (a Queue).  Create an
    empty DFA transition relation, which is a Map from a
    composite state (an epsilon-closed Set of ints) to a Map
    from a label (a non-null String) to a composite state.
    Repeatedly choose a composite state S from the worklist.  If it is
    not already in the keyset of the DFA transition relation, compute
    for every non-epsilon label lab the set T of states reachable by
    that label from some state s in S.  Compute the epsilon-closure
    Tclose of every such state T and put it on the worklist.  Then add
    the transition S -lab-> Tclose to the DFA transition relation, for
    every lab.
  Method EpsilonClose works as follows: 
    Given a set S of states.  Put the states of S in a worklist.
    Repeatedly choose a state s from the worklist, and consider all
    epsilon-transitions s -eps-> s' from s.  If s' is in S already,
    then do nothing; otherwise add s' to S and the worklist.  When the
    worklist is empty, S is epsilon-closed; return S.
  Method MkRenamer works as follows: 
    Given a Map from Set of int to something, create an
    injective Map from Set of int to int, by choosing a fresh
    int for every value of the map.
  Method Rename works as follows:
    Given a Map from Set of int to Map from String to Set of
    int, use the result of MkRenamer to replace all Sets of ints
    by ints.
*/
public class Nfa   
{
    private int startState;
    private int exitState;
    // This is the unique accept state
    private Map<Integer,CSList<Transition>> trans;
    public Nfa(int startState, int exitState) throws Exception {
        this.startState = startState;
        this.exitState = exitState;
        trans = new HashMap<Integer,CSList<Transition>>();
        if (!((Integer)startState).equals(exitState))
            trans.put(exitState, new CSList<Transition>());
         
    }

    public int getStart() throws Exception {
        return startState;
    }

    public int getExit() throws Exception {
        return exitState;
    }

    public Map<Integer,CSList<Transition>> getTrans() throws Exception {
        return trans;
    }

    public void addTrans(int s1, String lab, int s2) throws Exception {
        CSList<Transition> s1Trans;
        if (trans.containsKey(s1))
            s1Trans = trans.get(s1);
        else
        {
            s1Trans = new CSList<Transition>();
            trans.put(s1, s1Trans);
        } 
        s1Trans.add(new Transition(lab,s2));
    }

    public void addTrans(Entry<Integer,CSList<Transition>> tr) throws Exception {
        // Assumption: if tr is in trans, it maps to an empty list (end state)
        trans.remove(tr.getKey());
        trans.put(tr.getKey(), tr.getValue());
    }

    public String toString() {
        try
        {
            return "NFA start=" + startState + " exit=" + exitState;
        }
        catch (Exception __dummyCatchVar3)
        {
            throw new RuntimeException(__dummyCatchVar3);
        }
    
    }

    // Construct the transition relation of a composite-state DFA
    // from an NFA with start state s0 and transition relation
    // trans (a Map from int to List of Transition).  The start
    // state of the constructed DFA is the epsilon closure of s0,
    // and its transition relation is a Map from a composite state
    // (a Set of ints) to a Map from label (a String) to a
    // composite state (a Set of ints).
    static Map<Set<Integer>,Map<String,Set<Integer>>> compositeDfaTrans(int s0, Map<Integer,CSList<Transition>> trans) throws Exception {
        Set<Integer> S0 = epsilonClose(new Set<Integer>(s0),trans);
        LinkedList<Set<Integer>> worklist = new LinkedList<Set<Integer>>();
        worklist.add(S0);
        // The transition relation of the DFA
        Map<Set<Integer>,Map<String,Set<Integer>>> res = new HashMap<Set<Integer>,Map<String,Set<Integer>>>();
        while (worklist.size() != 0)
        {
            Set<Integer> S = worklist.removeLast();
            if (!res.containsKey(S))
            {
                // The S -lab-> T transition relation being constructed for a given S
                Map<String,Set<Integer>> STrans = new HashMap<String,Set<Integer>>();
                for (int s : S)
                {
                    for (Transition tr : trans.get(s))
                    {
                        // For all s in S, consider all transitions s -lab-> t
                        // For all non-epsilon transitions s -lab-> t, add t to T
                        if (tr.lab != null)
                        {
                            // Already a transition on lab
                            Set<Integer> toState;
                            if (STrans.containsKey(tr.lab))
                                toState = STrans.get(tr.lab);
                            else
                            {
                                // No transitions on lab yet
                                toState = new Set<Integer>();
                                STrans.put(tr.lab, toState);
                            } 
                            toState.add(tr.target);
                        }
                         
                    }
                }
                // Epsilon-close all T such that S -lab-> T, and put on worklist
                HashMap<String,Set<Integer>> STransClosed = new HashMap<String,Set<Integer>>();
                for (Entry<String,Set<Integer>> entry : STrans.entrySet())
                {
                    Set<Integer> Tclose = epsilonClose(entry.getValue(),trans);
                    STransClosed.put(entry.getKey(), Tclose);
                    worklist.add(Tclose);
                }
                res.put(S, STransClosed);
            }
             
        }
        return res;
    }

    // Compute epsilon-closure of state set S in transition relation trans.
    static Set<Integer> epsilonClose(Set<Integer> S, Map<Integer,CSList<Transition>> trans) throws Exception {
        // The worklist initially contains all S members
        LinkedList<Integer> worklist = new LinkedList<Integer>(S);
        Set<Integer> res = new Set<Integer>(S);
        while (worklist.size() != 0)
        {
            int s = worklist.removeLast();
            for (Transition tr : trans.get(s))
            {
                if (tr.lab == null && !res.contains(tr.target))
                {
                    res.add(tr.target);
                    worklist.add(tr.target);
                }
                 
            }
        }
        return res;
    }

    // Compute a renamer, which is a Map from Set of int to int
    static Map<Set<Integer>,Integer> mkRenamer(ICollection<Set<Integer>> states) throws Exception {
        Map<Set<Integer>,Integer> renamer = new HashMap<Set<Integer>,Integer>();
        int count = 0;
        for (Set<Integer> k : states)
            renamer.put(k, count++);
        return renamer;
    }

    // Using a renamer (a Map from Set of int to int), replace
    // composite (Set of int) states with simple (int) states in
    // the transition relation trans, which is assumed to be a Map
    // from Set of int to Map from String to Set of int.  The
    // result is a Map from int to Map from String to int.
    static Map<Integer,Map<String,Integer>> rename(Map<Set<Integer>,Integer> renamer, Map<Set<Integer>,Map<String,Set<Integer>>> trans) throws Exception {
        Map<Integer,Map<String,Integer>> newtrans = new HashMap<Integer,Map<String,Integer>>();
        for (Entry<Set<Integer>,Map<String,Set<Integer>>> entry : trans.entrySet())
        {
            Set<Integer> k = entry.getKey();
            Map<String,Integer> newktrans = new HashMap<String,Integer>();
            for (Entry<String,Set<Integer>> tr : entry.getValue().entrySet())
                newktrans.put(tr.getKey(), renamer.get(tr.getValue()));
            newtrans.put(renamer.get(k), newktrans);
        }
        return newtrans;
    }

    static Set<Integer> acceptStates(ICollection<Set<Integer>> states, Map<Set<Integer>,Integer> renamer, int exit) throws Exception {
        Set<Integer> acceptStates = new Set<Integer>();
        for (Set<Integer> state : states)
            if (state.contains(exit))
                acceptStates.add(renamer.get(state));
             
        return acceptStates;
    }

    public Dfa toDfa() throws Exception {
        Map<Set<Integer>,Map<String,Set<Integer>>> cDfaTrans = compositeDfaTrans(startState,trans);
        Set<Integer> cDfaStart = epsilonClose(new Set<Integer>(startState),trans);
        ICollection<Set<Integer>> cDfaStates = CollectionSupport.mk(cDfaTrans.keySet());
        Map<Set<Integer>,Integer> renamer = mkRenamer(cDfaStates);
        Map<Integer,Map<String,Integer>> simpleDfaTrans = rename(renamer,cDfaTrans);
        int simpleDfaStart = renamer.get(cDfaStart);
        Set<Integer> simpleDfaAccept = acceptStates(cDfaStates,renamer,exitState);
        return new Dfa(simpleDfaStart,simpleDfaAccept,simpleDfaTrans);
    }

    // Nested class for creating distinctly named states when constructing NFAs
    public static class NameSource   
    {
        private static int nextName = 0;
        public int next() throws Exception {
            return nextName++;
        }
    
    }

}


