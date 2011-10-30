//

package FizzBuzz.Core;

import FizzBuzz.Core.Helpers.Arguments;
import FizzBuzz.Core.ITransformer;
import FizzBuzz.Core.IWriter;
import java.io.PrintStream;

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
* IIntWriter which transforms the numbers using an ITransformer object before writing them
* to it's underlying TextWriter.
*/
public class TransformingTextWriter   implements IWriter<Integer>
{
    private ITransformer transform;
    private PrintStream writer;
    /**
    * Initializes a new instance of the 
    *  {@link #TransformingTextWriter}
    *  class.
    * 
    *  @param streamWriter The stream writer.
    *  @param transformer The transformer.
    */
    public TransformingTextWriter(PrintStream streamWriter, ITransformer transformer) throws Exception {
        Arguments.notNull(streamWriter,"streamWriter");
        Arguments.notNull(transformer,"transformer");
        transform = transformer;
        writer = streamWriter;
    }

    /**
    * Writes the specified number.
    * 
    *  @param item The number.
    */
    public void write(Integer item) throws Exception {
        writer.println(transform.transform(item));
    }

}


