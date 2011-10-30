//

package FizzBuzz.Core.Helpers;

import CS2JNet.System.ArgumentException;
import FizzBuzz.Core.Properties.Resources;

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
* Helper class for testing arguments to methods.
*/
public class Arguments   
{
    /**
    * Tests the specified failing condition.
    * 
    *  @param failingCondition if set to 
    *  {@code true}
    *  [failing condition].
    *  @param message The message.
    *  @param paramName Name of the param.
    *  @throws System.ArgumentException 
    * If the 
    *  {@code failingCondition}
    *  is true.
    */
    public static void test(boolean failingCondition, String message, String paramName) throws Exception {
        if (failingCondition)
            throw new ArgumentException(message, paramName);
         
    }

    /**
    * Ensures that the passed in parameter is not null.
    * 
    *  @param arg The object to check.
    *  @param paramName Name of the param.
    *  @throws System.ArgumentException 
    * If the 
    *  {@code arg}
    *  is null.
    */
    public static void notNull(Object arg, String paramName) throws Exception {
        test(arg == null,Resources.getArgumentMustNotBeNullMessage(),paramName);
    }

}


