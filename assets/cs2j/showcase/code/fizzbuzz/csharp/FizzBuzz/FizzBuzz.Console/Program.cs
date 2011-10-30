
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

using FizzBuzz.BusinessLogic;
using FizzBuzz.Core;

namespace FizzBuzz.Console
{
    /// <summary>
    /// Container class for the program entry point.
    /// </summary>
    static class Program
    {
        /// <summary>
        /// The main entry point for the program.
        /// </summary>
        static void Main()
        {
            // Create a new Logic Component 
            FizzBuzzLogic logic = new FizzBuzzLogic();

            // Use newly created logic component it to create a Runnable FizzBuzz Process
            IRunnable fizzBuzzProcess = logic.CreateFizzBuzzProcess(System.Console.Out);
            
            // Run the Process
            fizzBuzzProcess.Run();
        }
    }
}
