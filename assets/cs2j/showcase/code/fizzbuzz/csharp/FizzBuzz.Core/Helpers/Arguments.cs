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

namespace FizzBuzz.Core.Helpers
{
    /// <summary>
    /// Helper class for testing arguments to methods.
    /// </summary>
    internal static class Arguments
    {
        /// <summary>
        /// Tests the specified failing condition.
        /// </summary>
        /// <param name="failingCondition">if set to <c>true</c> [failing condition].</param>
        /// <param name="message">The message.</param>
        /// <param name="paramName">Name of the param.</param>
        /// <exception cref="System.ArgumentException">
        /// If the <paramref name="failingCondition"/> is true.
        /// </exception>
        public static void Test(bool failingCondition, string message, string paramName)
        {
            if (failingCondition)
                throw new ArgumentException(message, paramName);
        }

        /// <summary>
        /// Ensures that the passed in parameter is not null.
        /// </summary>
        /// <param name="arg">The object to check.</param>
        /// <param name="paramName">Name of the param.</param>
        /// <exception cref="System.ArgumentException">
        /// If the <paramref name="arg"/> is null.
        /// </exception>
        public static void NotNull(object arg, string paramName)
        {
            Test((arg == null), FizzBuzz.Core.Properties.Resources.ArgumentMustNotBeNullMessage, paramName);
        }
    }
}
