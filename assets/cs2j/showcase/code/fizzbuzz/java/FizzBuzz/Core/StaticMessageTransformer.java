//

package FizzBuzz.Core;

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
* Transform which always returns a static message.
*/
public class StaticMessageTransformer   implements ITransformer
{
    private String _Message;
    /**
    * Gets the message.
    * The message.
    */
    public String getMessage() throws Exception {
        return _Message;
    }

    /**
    * Initializes a new instance of the 
    *  {@link #StaticMessageTransformer}
    *  class.
    * 
    *  @param message The message.
    */
    public StaticMessageTransformer(String message) throws Exception {
        Arguments.notNull(message,"message");
        _Message = message;
    }

    /**
    * Transforms the specified number into a string.
    * 
    *  @param number The number to transform.
    *  @return The static message provided at object instantiation.
    */
    public String transform(int number) throws Exception {
        return getMessage();
    }

}


