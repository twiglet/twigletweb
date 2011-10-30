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

using System;
using System.Collections.Generic;
using System.Text;

namespace FizzBuzz.Core
{
    /// <summary>
    /// AggregatedTransformer which returns the first of it's children return a non-empty string.
    /// </summary>
    public class CoalescingTransformer : AggregatedTransformer
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="CoalescingTransformer"/> class.
        /// </summary>
        /// <param name="childTransforms">The child transforms.</param>
        public CoalescingTransformer(params ITransformer[] childTransforms): base(childTransforms) { }

        /// <summary>
        /// Transforms the specified number into a string.
        /// </summary>
        /// <param name="number">The number to transform.</param>
        /// <returns>A String repesentation of the number.</returns>
        public override string Transform(int number)
        {
            foreach (ITransformer transform in Transforms)
            {
                string transformed = transform.Transform(number);
                if (transformed.Length > 0)
                    return transformed;
            }
            return String.Empty;
        }
    }
}
