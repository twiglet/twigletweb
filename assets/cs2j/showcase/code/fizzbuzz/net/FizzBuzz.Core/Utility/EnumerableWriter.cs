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
    /// Runnable process which writes out all of the members of an Enumerable item.
    /// </summary>
    /// <typeparam name="T">The type of the enumeration</typeparam>
    public class EnumerableWriter<T> : IRunnable
    {
        #region Private Members

        private IWriter<T> writer;

        #endregion

        
        public IWriter<T> Writer
        {
            get { return writer; }
            set { writer = value; }
        }

        private IEnumerable<T> enumeration;

        public IEnumerable<T> Enumeration
        {
            get { return enumeration; }
            set { enumeration = value; }
        }



        public EnumerableWriter(IWriter<T> writer, IEnumerable<T> enumeration)
        {
            this.writer = writer;
            this.enumeration = enumeration;
        }


        #region IRunnable Members

        public void Run()
        {
            foreach (T item in enumeration)
                writer.Write(item);
        }

        #endregion
    }
}
