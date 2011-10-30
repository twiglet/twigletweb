//

package FizzBuzz.Core;

import CS2JNet.System.Collections.LCC.IEnumerable;
import FizzBuzz.Core.IRunnable;
import FizzBuzz.Core.IWriter;

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
* Runnable process which writes out all of the members of an Enumerable item.
* The type of the enumeration
*/
public class EnumerableWriter <T>  implements IRunnable
{
    private IWriter<T> writer;
    public IWriter<T> getWriter() throws Exception {
        return writer;
    }

    public void setWriter(IWriter<T> value) throws Exception {
        writer = value;
    }

    private IEnumerable<T> enumeration;
    public IEnumerable<T> getEnumeration() throws Exception {
        return enumeration;
    }

    public void setEnumeration(IEnumerable<T> value) throws Exception {
        enumeration = value;
    }

    public EnumerableWriter(IWriter<T> writer, IEnumerable<T> enumeration) throws Exception {
        this.writer = writer;
        this.enumeration = enumeration;
    }

    public void run() throws Exception {
        for (T item : enumeration)
            writer.write(item);
    }

}


