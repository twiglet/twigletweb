//

package FizzBuzz.Core;

import CS2JNet.System.LCC.Predicate;
import FizzBuzz.Core.Helpers.Arguments;
import FizzBuzz.Core.ITransformer;

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
* Implements simple decision making logic in a transform.
*/
public class PredicatedTransformer   implements ITransformer
{
    private Predicate<Integer> _Test;
    private ITransformer _PassesTransform;
    private ITransformer _FailsTrasnform;
    /**
    * Initializes a new instance of the 
    *  {@link #PredicatedTransformer}
    *  class.
    * 
    *  @param test The test.
    *  @param passesTransform The passes transform.
    *  @param failsTransform The fails transform.
    */
    public PredicatedTransformer(Predicate<Integer> test, ITransformer passesTransform, ITransformer failsTransform) throws Exception {
        Arguments.notNull(test,"test");
        Arguments.notNull(passesTransform,"passesTransform");
        Arguments.notNull(failsTransform,"failsTransform");
        _Test = test;
        _PassesTransform = passesTransform;
        _FailsTrasnform = failsTransform;
    }

    /**
    * Transforms the specified number into a string.
    * 
    *  @param number The number to transform.
    *  @return A String repesentation of the number.
    */
    public String transform(int number) throws Exception {
        if (_Test.invoke(number) == true)
        {
            return _PassesTransform.transform(number);
        }
        else
        {
            return _FailsTrasnform.transform(number);
        } 
    }

}


