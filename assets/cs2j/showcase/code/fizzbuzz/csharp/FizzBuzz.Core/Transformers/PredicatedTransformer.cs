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

namespace FizzBuzz.Core
{
    /// <summary>
    /// Implements simple decision making logic in a transform.
    /// </summary>
    public class PredicatedTransformer : ITransformer
    {
        #region Private Members
        private Predicate<Int32> _Test;
        private ITransformer _PassesTransform;
        private ITransformer _FailsTrasnform;

        #endregion

        #region Public Constructor
        /// <summary>
        /// Initializes a new instance of the <see cref="PredicatedTransformer"/> class.
        /// </summary>
        /// <param name="test">The test.</param>
        /// <param name="passesTransform">The passes transform.</param>
        /// <param name="failsTransform">The fails transform.</param>
        public PredicatedTransformer(Predicate<Int32> test, ITransformer passesTransform, ITransformer failsTransform)
        {
            Helpers.Arguments.NotNull(test, "test");
            Helpers.Arguments.NotNull(passesTransform, "passesTransform");
            Helpers.Arguments.NotNull(failsTransform, "failsTransform");

            _Test = test;
            _PassesTransform = passesTransform;
            _FailsTrasnform = failsTransform;
        }

        #endregion

        #region ITransformer Members

        /// <summary>
        /// Transforms the specified number into a string.
        /// </summary>
        /// <param name="number">The number to transform.</param>
        /// <returns>A String repesentation of the number.</returns>
        public string Transform(int number)
        {
            if (_Test(number) == true)
            {
                return _PassesTransform.Transform(number);
            }
            else
            {
                return _FailsTrasnform.Transform(number);
            }
            
        }

        #endregion
    }
}
