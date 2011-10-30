//

package FizzBuzz.Core;

import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.ICollection;
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
* Abstract base class for containers of Transforms
*/
public abstract class AggregatedTransformer   implements ITransformer
{
    private ITransformer[] transforms;
    protected ICollection<ITransformer> getTransforms() throws Exception {
        return new CSList<ITransformer>(transforms);
    }

    /**
    * Initializes a new instance of the 
    *  {@link #AggregatedTransformer}
    *  class.
    * 
    *  @param children The child transforms.
    */
    protected AggregatedTransformer(ITransformer... children) throws Exception {
        if (children == null)
            children = new ITransformer[]{  };
         
        transforms = children;
    }

    /**
    * Transforms the specified number into a string.
    * 
    *  @param number The number to transform.
    *  @return A String repesentation of the number.
    */
    public abstract String transform(int number) throws Exception ;

}


