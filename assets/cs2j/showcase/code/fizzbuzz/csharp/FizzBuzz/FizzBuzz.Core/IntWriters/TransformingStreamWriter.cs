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
using System.IO;

namespace FizzBuzz.Core
{
    /// <summary>
    /// IIntWriter which transforms the numbers using an ITransformer object before writing them
    /// to it's underlying TextWriter.
    /// </summary>
    public class TransformingTextWriter : IWriter<Int32>
    {
        #region Private Members
        private ITransformer transform;
        private TextWriter writer;

        #endregion

        #region Public Constructor
        /// <summary>
        /// Initializes a new instance of the <see cref="TransformingTextWriter"/> class.
        /// </summary>
        /// <param name="streamWriter">The stream writer.</param>
        /// <param name="transformer">The transformer.</param>
        public TransformingTextWriter(TextWriter streamWriter, ITransformer transformer)
        {
            Helpers.Arguments.NotNull(streamWriter, "streamWriter");
            Helpers.Arguments.NotNull(transformer, "transformer");

            transform = transformer;
            writer = streamWriter;
        }

        #endregion

        #region IWriter<T> Members

        /// <summary>
        /// Writes the specified number.
        /// </summary>
        /// <param name="item">The number.</param>
        public void Write(Int32 item)
        {
            writer.WriteLine(transform.Transform(item));
        }

        #endregion
    }
}
