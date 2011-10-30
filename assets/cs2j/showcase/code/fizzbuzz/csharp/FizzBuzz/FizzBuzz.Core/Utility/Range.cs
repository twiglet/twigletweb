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
using System.Collections;

using FizzBuzz.Core.Helpers;

namespace FizzBuzz.Core
{
    /// <summary>
    /// Represents a range of integers.
    /// </summary>
    [System.Diagnostics.CodeAnalysis.SuppressMessage(
        "Microsoft.Naming", 
        "CA1710", 
        Target="FizzBuzz.Core.Range", 
        Justification="A Range of integers is Enumerable but it isn't really a collection.")]
    public class Range : IEnumerable<Int32>
    {
        #region Private Members
        private int lowerBound;
        private int upperBound;
        #endregion

        #region Public Properties
        /// <summary>
        /// Gets the lower bound.
        /// </summary>
        /// <value>The lower bound.</value>
        public int LowerBound
        {
            get { return lowerBound; }
        }

        /// <summary>
        /// Gets the upper bound.
        /// </summary>
        /// <value>The upper bound.</value>
        public int UpperBound
        {
            get { return upperBound; }
        }

        #endregion

        #region Public Constructor
        /// <summary>
        /// Initializes a new instance of the <see cref="Range"/> class.
        /// </summary>
        /// <param name="lowerBound">The lower bound.</param>
        /// <param name="upperBound">The upper bound.</param>
        /// <exception cref="System.ArgumentException">
        /// If <paramref name="upperBound"/> is less than <paramref name="lowerBound"/>
        /// </exception>
        public Range(int lowerBound, int upperBound)
        {

            Arguments.Test(
                lowerBound > upperBound, 
                Properties.Resources.UpperBoundMustBeGreaterThanLowerBoundMessage, 
                "upperBound");

            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        #endregion

        #region Public Methods
        /// <summary>
        /// Performs the specified action on each integer in the range.
        /// </summary>
        /// <remarks>
        /// This is inclusive of both ends.
        /// </remarks>
        /// <param name="action">The action.</param>
        public void Each(Action<Int32> action)
        {
            if (action == null)
                return;
            foreach (Int32 i in this)
            {
                action(i);
            }
        }

        #endregion

        #region IEnumerable<int> Members

        #region Range Enumerator Class
 
        /// <summary>
        /// An Enumerator over a range of integers, returns each in sequence.
        /// </summary>
        public class RangeEnumerator : IEnumerator<int>
        {
            private int current = 0;
			private int lowerBound = 0;
			private int upperBound = 0;
			
            /// <summary>
            /// Initializes a new instance of an Enumerator over a range of integers.
            /// </summary>
            /// <param name="lower">The lower bound.</param>
            /// <param name="upper">The upper bound.</param>
			public RangeEnumerator(int lower, int upper) {
				lowerBound = lower;
				upperBound = upper;
				Reset();
			}
			
            /// <summary>
            /// Gets the element in the collection at the current position of the enumerator.
            /// </summary>
           	public int Current { get { return current;} }
       
            /// <summary>
            /// Gets the element in the collection at the current position of the enumerator (as object).
            /// </summary>
           	object IEnumerator.Current { get { return Current; } }
           
            /// <summary>
            /// Advances the enumerator to the next integer in the sequence. 
            /// </summary>
            /// <returns>true if the enumerator was successfully advanced to the next element; false if the enumerator has passed the end of the collection.</returns>
           	public bool MoveNext() {
             	current++;
              	return (current <= upperBound);
           	}
           
            /// <summary>
            /// Sets the enumerator to its initial position, which is before the first element in the collection.
            /// </summary>
           	public void Reset()
           	{
           		current = lowerBound - 1;
           	}
			
            /// <summary>
            /// Performs application-defined tasks associated with freeing, releasing, or resetting unmanaged resources (none here).
            /// </summary>
			public void Dispose()
			{
			}
        }
        
        #endregion

        /// <summary>
        /// Returns an enumerator that iterates through the collection.
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1"></see> that can be used to iterate through the collection.
        /// </returns>
        public IEnumerator<int> GetEnumerator()
        {
           return new RangeEnumerator(lowerBound, upperBound);
        }

        #endregion

        #region IEnumerable Members

        /// <summary>
        /// Returns an enumerator that iterates through a collection.
        /// </summary>
        /// <returns>
        /// An <see cref="T:System.Collections.IEnumerator"></see> object that can be used to iterate through the collection.
        /// </returns>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return ((IEnumerable<Int32>)this).GetEnumerator();
        }

        #endregion
    }
}
