//

package FizzBuzz.Core;

import FizzBuzz.Core.AggregatedTransformer;
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
* AggregatedTransformer which returns the first of it's children return a non-empty string.
*/
public class CoalescingTransformer  extends AggregatedTransformer 
{
    /**
    * Initializes a new instance of the 
    *  {@link #CoalescingTransformer}
    *  class.
    * 
    *  @param childTransforms The child transforms.
    */
    public CoalescingTransformer(ITransformer... childTransforms) throws Exception {
        super(childTransforms);
    }

    /**
    * Transforms the specified number into a string.
    * 
    *  @param number The number to transform.
    *  @return A String repesentation of the number.
    */
    public String transform(int number) throws Exception {
        for (ITransformer transform : getTransforms())
        {
            String transformed = transform.transform(number);
            if (transformed.length() > 0)
                return transformed;
             
        }
        return "";
    }

}


