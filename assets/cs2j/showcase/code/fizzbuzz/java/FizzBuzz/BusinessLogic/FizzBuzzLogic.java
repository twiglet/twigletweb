//

package FizzBuzz.BusinessLogic;

import CS2JNet.System.LCC.Predicate;
import FizzBuzz.Core.CoalescingTransformer;
import FizzBuzz.Core.ConcatenatingTransformer;
import FizzBuzz.Core.EnumerableWriter;
import FizzBuzz.Core.IdentityTransformer;
import FizzBuzz.Core.IRunnable;
import FizzBuzz.Core.ITransformer;
import FizzBuzz.Core.NullTransformer;
import FizzBuzz.Core.PredicatedTransformer;
import FizzBuzz.Core.Range;
import FizzBuzz.Core.StaticMessageTransformer;
import FizzBuzz.Core.TransformingTextWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
* Contains all of the Business Logic for the FizzBuzz Problem
*/
public class FizzBuzzLogic   
{
    private int Start = 1;
    private int Finish = 100;
    /**
    * Creates the range.
    * 
    *  @return The Range object to perform the FizzBuzz operation over.
    */
    public Range createRange() throws Exception {
        return new Range(Start,Finish);
    }

    /**
    * Creates the transform.
    * 
    *  @return The Transform to apply to each number. This is really the FizzBuzz Operation.
    */
    public ITransformer createTransformer() throws Exception {
        return new CoalescingTransformer(new ConcatenatingTransformer(new PredicatedTransformer((Predicate<Integer>)new Predicate<Integer>() 
          { 
            public boolean invoke(Integer i) throws Exception {
                return i % 3 == 0;
            }

            public List<Predicate<Integer>> getInvocationList() throws Exception {
                List<Predicate<Integer>> ret = new ArrayList<Predicate<Integer>>();
                ret.add(this);
                return ret;
            }
        
          },new StaticMessageTransformer("Fizz"),new NullTransformer()),new PredicatedTransformer((Predicate<Integer>)new Predicate<Integer>() 
          { 
            public boolean invoke(Integer i) throws Exception {
                return i % 5 == 0;
            }

            public List<Predicate<Integer>> getInvocationList() throws Exception {
                List<Predicate<Integer>> ret = new ArrayList<Predicate<Integer>>();
                ret.add(this);
                return ret;
            }
        
          },new StaticMessageTransformer("Buzz"),new NullTransformer())),new IdentityTransformer());
    }

    /**
    * Creates the fizz buzz process.
    * 
    *  @param writer The writer which output will go to.
    *  @return A Runnable process which executes the FizzBuzz behavior
    */
    public IRunnable createFizzBuzzProcess(PrintStream writer) throws Exception {
        return new EnumerableWriter<Integer>(new TransformingTextWriter(writer,createTransformer()),createRange());
    }

}


