//

package FizzBuzz.Core;

import CS2JNet.JavaSupport.Collections.Generic.IteratorSupport;
import CS2JNet.System.Action;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.Collections.LCC.IEnumerator;
import FizzBuzz.Core.Helpers.Arguments;
import FizzBuzz.Core.Properties.Resources;
import java.util.Iterator;

//   Copyright 2007 Michael Minutillo (http://wolfbyte-net.blogspot.com)
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
/**
* Represents a range of integers.
*/
public class Range   implements IEnumerable<Integer>
{
    private int lowerBound;
    private int upperBound;
    /**
    * Gets the lower bound.
    * The lower bound.
    */
    public int getLowerBound() throws Exception {
        return lowerBound;
    }

    /**
    * Gets the upper bound.
    * The upper bound.
    */
    public int getUpperBound() throws Exception {
        return upperBound;
    }

    /**
    * Initializes a new instance of the 
    *  {@link #Range}
    *  class.
    * 
    *  @param lowerBound The lower bound.
    *  @param upperBound The upper bound.
    *  @throws System.ArgumentException 
    * If 
    *  {@code upperBound}
    *  is less than 
    *  {@code lowerBound}
    */
    public Range(int lowerBound, int upperBound) throws Exception {
        Arguments.test(lowerBound > upperBound,Resources.getUpperBoundMustBeGreaterThanLowerBoundMessage(),"upperBound");
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
    * Performs the specified action on each integer in the range.
    * 
    * This is inclusive of both ends.
    * 
    *  @param action The action.
    */
    public void each(Action<Integer> action) throws Exception {
        if (action == null)
            return ;
         
        for (int i : this)
        {
            action.Invoke(i);
        }
    }

    /**
    * An Enumerator over a range of integers, returns each in sequence.
    */
    public static class RangeEnumerator   implements IEnumerator<Integer>
    {
        private int current = 0;
        private int lowerBound = 0;
        private int upperBound = 0;
        /**
        * Initializes a new instance of an Enumerator over a range of integers.
        * 
        *  @param lower The lower bound.
        *  @param upper The upper bound.
        */
        public RangeEnumerator(int lower, int upper) throws Exception {
            lowerBound = lower;
            upperBound = upper;
            reset();
        }

        /**
        * Gets the element in the collection at the current position of the enumerator.
        */
        public Integer getCurrent() throws Exception {
            return current;
        }

        /**
        * Gets the element in the collection at the current position of the enumerator (as object).
        */
        Object getIEnumerator() throws Exception {
            return getCurrent();
        }

        /**
        * Advances the enumerator to the next integer in the sequence.
        * 
        *  @return true if the enumerator was successfully advanced to the next element; false if the enumerator has passed the end of the collection.
        */
        public boolean moveNext() throws Exception {
            current++;
            return current <= upperBound;
        }

        /**
        * Sets the enumerator to its initial position, which is before the first element in the collection.
        */
        public void reset() throws Exception {
            current = lowerBound - 1;
        }

        /**
        * Performs application-defined tasks associated with freeing, releasing, or resetting unmanaged resources (none here).
        */
        public void dispose() throws Exception {
        }
    
    }

    /**
    * Returns an enumerator that iterates through the collection.
    * 
    *  @return 
    * A 
    *  {@link #T:System.Collections.Generic.IEnumerator`1}
    *  that can be used to iterate through the collection.
    */
    public IEnumerator<Integer> getEnumerator() throws Exception {
        return new RangeEnumerator(lowerBound,upperBound);
    }

    /**
    * Returns an enumerator that iterates through a collection.
    * 
    *  @return 
    * An 
    *  {@link #T:System.Collections.IEnumerator}
    *  object that can be used to iterate through the collection.
    */
    IEnumerator iEnumerable___GetEnumerator() throws Exception {
        return ((IEnumerable<Integer>)this).getEnumerator();
    }

    public Iterator<Integer> iterator() {
        Iterator<Integer> ret = null;
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

}


