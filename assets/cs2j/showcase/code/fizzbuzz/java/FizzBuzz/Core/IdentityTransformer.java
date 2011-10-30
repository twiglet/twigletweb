//

package FizzBuzz.Core;

import CS2JNet.JavaSupport.util.LocaleSupport;
import FizzBuzz.Core.ITransformer;
import java.text.NumberFormat;
import java.util.Locale;

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
* Simple Identity Transform. Just returns a direct string representation of the number given to it.
*/
public class IdentityTransformer   implements ITransformer
{
    /**
    * Transforms the specified number into a string.
    * 
    *  @param number The number to transform.
    *  @return A String repesentation of the number.
    */
    public String transform(int number) throws Exception {
        return NumberFormat.getInstance(LocaleSupport.INVARIANT).format(number);
    }

}


