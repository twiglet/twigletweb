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
    /// Transform which always returns a static message.
    /// </summary>
    public class StaticMessageTransformer : ITransformer
    {
        #region Private Members
        private string _Message;

        #endregion

        #region Public Properties
        /// <summary>
        /// Gets the message.
        /// </summary>
        /// <value>The message.</value>
        public string Message
        {
            get { return _Message; }
        }

        #endregion

        #region Public Constructor
        /// <summary>
        /// Initializes a new instance of the <see cref="StaticMessageTransformer"/> class.
        /// </summary>
        /// <param name="message">The message.</param>
        public StaticMessageTransformer(string message)
        {
            Helpers.Arguments.NotNull(message, "message");
            _Message = message;
        }

        #endregion

        #region ITransformer Members

        /// <summary>
        /// Transforms the specified number into a string.
        /// </summary>
        /// <param name="number">The number to transform.</param>
        /// <returns>The static message provided at object instantiation.</returns>
        public string Transform(int number)
        {
            return Message;
        }

        #endregion
    }
}
