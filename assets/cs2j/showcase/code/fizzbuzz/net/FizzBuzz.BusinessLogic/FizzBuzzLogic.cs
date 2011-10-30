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

using FizzBuzz.Core;

namespace FizzBuzz.BusinessLogic
{
    /// <summary>
    /// Contains all of the Business Logic for the FizzBuzz Problem
    /// </summary>
    public class FizzBuzzLogic
    {
        private readonly Int32 Start = 1;
        private readonly Int32 Finish = 100;


        #region Public Methods
        /// <summary>
        /// Creates the range.
        /// </summary>
        /// <returns>The Range object to perform the FizzBuzz operation over.</returns>
        public Range CreateRange()
        {
            return new Range(Start, Finish);
        }

        /// <summary>
        /// Creates the transform.
        /// </summary>
        /// <returns>The Transform to apply to each number. This is really the FizzBuzz Operation.</returns>
        public ITransformer CreateTransformer()
        {
            return new CoalescingTransformer(
                        new ConcatenatingTransformer(
                            new PredicatedTransformer(
                               (Predicate<int>) delegate(int i) { return i % 3 == 0; },
                                new StaticMessageTransformer("Fizz"),
                                new NullTransformer()),
                            new PredicatedTransformer(
                                (Predicate<int>) delegate(int i) { return i % 5 == 0; },
                                new StaticMessageTransformer("Buzz"),
                                new NullTransformer())),
                        new IdentityTransformer());
        }

        /// <summary>
        /// Creates the fizz buzz process.
        /// </summary>
        /// <param name="writer">The writer which output will go to.</param>
        /// <returns>A Runnable process which executes the FizzBuzz behavior</returns>
        public IRunnable CreateFizzBuzzProcess(TextWriter writer)
        {
            return new EnumerableWriter<Int32>(
                new TransformingTextWriter(
                    writer,
                    CreateTransformer()),
                CreateRange());
        }

        #endregion
    }
}
