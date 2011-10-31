//

package Sestoft.GNfaToDfa;

import CS2JNet.JavaSupport.Collections.Generic.IteratorSupport;
import CS2JNet.JavaSupport.Collections.Generic.LCC.CollectionSupport;
import CS2JNet.JavaSupport.Collections.Generic.LCC.EnumeratorSupport;
import CS2JNet.System.Collections.Generic.KeyCollectionSupport;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.ICollection;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.Collections.LCC.IEnumerator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import Sestoft.GNfaToDfa.Set;

// RegExp -> NFA -> DFA -> Graph in Generic C#
// This program requires .Net version 2.0.
// Peter Sestoft (sestoft@dina.kvl.dk)
// Java 2000-10-07, GC# 2001-10-23, 2003-09-03, 2004-07-26, 2006-03-05
// This file contains, in order:
//   * A class Nfa for representing an NFA (a nondeterministic finite
//     automaton), and for converting it to a DFA (a deterministic
//     finite automaton).  Most complexity is in this class.
//   * A class Dfa for representing a DFA, a deterministic finite
//     automaton, and for writing a dot input file representing the DFA.
//   * Classes for representing regular expressions, and for building an
//     NFA from a regular expression
//   * A test class that creates an NFA, a DFA, and a dot input file
//     for a number of small regular expressions.  The DFAs are
//     not minimized.
// A set represented as the collection of keys of a Dictionary
public class Set <T>  implements ICollection<T>
{
    // Only the keys matter; the type bool used for the value is arbitrary
    private HashMap<T,Boolean> dict;
    public Set() throws Exception {
        dict = new HashMap<T,Boolean>();
    }

    public Set(T x) throws Exception {
        this();
        add(x);
    }

    public Set(IEnumerable<T> coll) throws Exception {
        this();
        for (T x : coll)
            add(x);
    }

    public Set(T[] arr) throws Exception {
        this();
        for (T x : arr)
            add(x);
    }

    public boolean Contains(T x) throws Exception {
        return dict.containsKey(x);
    }

    public void Add(T x) throws Exception {
        if (!contains(x))
            dict.put(x, false);
         
    }

    public boolean Remove(T x) throws Exception {
        return dict.remove(x);
    }

    public void Clear() throws Exception {
        dict.clear();
    }

    public boolean getIsReadOnly() throws Exception {
        return false;
    }

    public IEnumerator<T> getEnumerator() throws Exception {
        return new EnumeratorSupport<T>(CollectionSupport.mk(dict.keySet()).iterator());
    }

    IEnumerator iEnumerable___GetEnumerator() throws Exception {
        return getEnumerator();
    }

    public int getCount() throws Exception {
        return dict.size();
    }

    public void copyTo(T[] arr, int i) throws Exception {
        CollectionSupport.mk(dict.keySet()).copyTo(arr, i);
    }

    // Is this set a subset of that?
    public boolean subset(Set<T> that) throws Exception {
        for (T x : this)
            if (!that.contains(x))
                return false;
             
        return true;
    }

    // Create new set as intersection of this and that
    public Set<T> intersection(Set<T> that) throws Exception {
        Set<T> res = new Set<T>();
        for (T x : this)
            if (that.contains(x))
                res.add(x);
             
        return res;
    }

    // Create new set as union of this and that
    public Set<T> union(Set<T> that) throws Exception {
        Set<T> res = new Set<T>(this);
        for (T x : that)
            res.add(x);
        return res;
    }

    // Compute hash code -- should be cached for efficiency
    public int hashCode() {
        try
        {
            int res = 0;
            for (T x : this)
                res ^= x.hashCode();
            return res;
        }
        catch (Exception __dummyCatchVar0)
        {
            throw new RuntimeException(__dummyCatchVar0);
        }
    
    }

    public boolean equals(Object that) {
        try
        {
            if (that instanceof Set)
            {
                Set<T> thatSet = (Set<T>)that;
                return thatSet.getCount() == this.getCount() && thatSet.subset(this) && this.subset(thatSet);
            }
            else
                return false; 
        }
        catch (Exception __dummyCatchVar1)
        {
            throw new RuntimeException(__dummyCatchVar1);
        }
    
    }

    public String toString() {
        try
        {
            StringBuilder res = new StringBuilder();
            res.append("{ ");
            boolean first = true;
            for (T x : this)
            {
                if (!first)
                    res.append(", ");
                 
                res.append(x);
                first = false;
            }
            res.append(" }");
            return res.toString();
        }
        catch (Exception __dummyCatchVar2)
        {
            throw new RuntimeException(__dummyCatchVar2);
        }
    
    }

    public Iterator<T> iterator() {
        Iterator<T> ret = null;
        try
        {
            ret = IteratorSupport.mk(this.getEnumerator());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public boolean add(T el) {
        try
        {
            this.Add(el);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        for (Object __dummyForeachVar0 : c)
        {
            T el = (T)__dummyForeachVar0;
            this.add(el);
        }
        return true;
    }

    public void clear() {
        try
        {
            this.Clear();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    }

    public boolean contains(Object o) {
        boolean ret = false;
        try
        {
            ret = this.Contains((T)o);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public boolean containsAll(Collection<?> c) {
        boolean ret = true;
        for (Object __dummyForeachVar1 : c)
        {
            Object el = (Object)__dummyForeachVar1;
            if (!this.contains(el))
            {
                ret = false;
                break;
            }
             
        }
        return ret;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean remove(Object o) {
        boolean ret = false;
        try
        {
            ret = this.Remove((T)o);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public boolean removeAll(Collection<?> c) {
        boolean ret = false;
        for (Object __dummyForeachVar2 : c)
        {
            Object el = (Object)__dummyForeachVar2;
            ret = ret | this.remove(el);
        }
        return ret;
    }

    public boolean retainAll(Collection<?> c) {
        boolean ret = false;
        Object[] thisCopy = this.toArray();
        for (Object el : thisCopy)
        {
            if (!c.contains(el))
            {
                ret = ret | this.remove(el);
            }
             
        }
        return ret;
    }

    public int size() {
        int ret = -1;
        try
        {
            return this.getCount();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public Object[] toArray() {
        Object[] ret = new Object[this.size()];
        int i = 0;
        for (Object __dummyForeachVar4 : this)
        {
            Object el = (Object)__dummyForeachVar4;
            ret[i] = el;
            i++;
        }
        return ret;
    }

    public <T__S>T__S[] toArray(T__S[] a) {
        List<T> ret = new CSList<T>(this.size());
        for (Object __dummyForeachVar5 : this)
        {
            T el = (T)__dummyForeachVar5;
            ret.add(el);
        }
        return ret.toArray(a);
    }

}


