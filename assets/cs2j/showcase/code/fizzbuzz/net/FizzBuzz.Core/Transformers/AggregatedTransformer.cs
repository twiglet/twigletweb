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

namespace FizzBuzz.Core
{
    /// <summary>
    /// Abstract base class for containers of Transforms
    /// </summary>
    public abstract class AggregatedTransformer : ITransformer
    {
        private readonly ITransformer[] transforms;

        protected ICollection<ITransformer> Transforms
        {
            get
            {
                return new List<ITransformer>(transforms);
            }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="AggregatedTransformer"/> class.
        /// </summary>
        /// <param name="children">The child transforms.</param>
        protected AggregatedTransformer(params ITransformer[] children)
        {
            if (children == null)
                children = new ITransformer[] { };
            
            transforms = children;
        }

        #region ITransform Members

        /// <summary>
        /// Transforms the specified number into a string.
        /// </summary>
        /// <param name="number">The number to transform.</param>
        /// <returns>A String repesentation of the number.</returns>
        public abstract string Transform(int number);

        #endregion
    }
}
