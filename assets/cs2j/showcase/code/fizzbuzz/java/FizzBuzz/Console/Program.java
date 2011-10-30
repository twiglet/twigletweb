//

package FizzBuzz.Console;

import FizzBuzz.BusinessLogic.FizzBuzzLogic;
import FizzBuzz.Console.Program;
import FizzBuzz.Core.IRunnable;

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
* Container class for the program entry point.
*/
public class Program   
{
    /**
    * The main entry point for the program.
    */
    public static void main(String[] args) throws Exception {
        Program.Main();
    }

    static void Main() throws Exception {
        // Create a new Logic Component
        FizzBuzzLogic logic = new FizzBuzzLogic();
        // Use newly created logic component it to create a Runnable FizzBuzz Process
        IRunnable fizzBuzzProcess = logic.createFizzBuzzProcess(System.out);
        // Run the Process
        fizzBuzzProcess.run();
    }

}


