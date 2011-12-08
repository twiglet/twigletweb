//

package Twiglet.Sample.Delegate;

import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.JavaSupport.util.ListSupport;
import CS2JNet.System.StringSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Twiglet.Sample.Delegate.DelegateSampler;

//   Copyright (c) 2011 Kevin Glynn (http://www.twigletsoftware.com)
//
// The MIT License (Expat)
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software
// and associated documentation files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
// and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial
// portions of the Software.
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
// LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
// IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
// SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
/**
* Sample class to show off CS2J's translations for delegates and events
*/
public class DelegateSampler   
{
    //
    /**
    * LogWriters take a string and record it.
    * 
    *  @param logMessage the message to be recorded
    */
    public static class __MultiLogWriter   implements LogWriter
    {
        public void invoke(String logMessage) throws Exception {
            List<LogWriter> copy, members = this.getInvocationList();
            synchronized (members)
            {
                copy = new LinkedList<LogWriter>(members);
            }
            for (LogWriter d : copy)
            {
                d.invoke(logMessage);
            }
        }

        private List<LogWriter> _invocationList = new ArrayList<LogWriter>();
        public static LogWriter combine(LogWriter a, LogWriter b) throws Exception {
            if (a == null)
                return b;
             
            if (b == null)
                return a;
             
            /// <summary>
            /// A chain of delegates can't pass values to each other, (only the value
            /// returned from the final delegate is returned to the caller, the
            __MultiLogWriter ret = new __MultiLogWriter();
            /// return values from intermediate delegates are just dropped on the floor).
            /// However the delegates can communicate if we use ref parameters.
            /// </summary>
            /// <param name="value">the variable that we are processing</param>
            ret._invocationList = a.getInvocationList();
            // This variable is captured by the delegates,  if we change this variable
            // then it changes what they print.
            ret._invocationList.addAll(b.getInvocationList());
            return ret;
        }

        public static LogWriter remove(LogWriter a, LogWriter b) throws Exception {
            if (a == null || b == null)
                return a;
             
            List<LogWriter> aInvList = a.getInvocationList();
            // First we create some delegates using the many syntaxes supported by C#
            // Old fashioned delegate creation
            // initialize delegate with a named method.
            // We can just also just assign a method group to a delegate variable
            // Since C# 2.0 a delegate can be initialized with
            // an "anonymous method."
            List<LogWriter> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
            if (aInvList == newInvList)
            {
                return a;
            }
            else
            {
                __MultiLogWriter ret = new __MultiLogWriter();
                // Since C# 3.0 a delegate can be initialized with
                // a lambda expression.
                ret._invocationList = newInvList;
                return ret;
            } 
        }

        public List<LogWriter> getInvocationList() throws Exception {
            return _invocationList;
        }
    
    }

    // Since C# 3.0 a delegate can be initialized with
    // a lambda expression, the type of the argument is inferred by the compiler.
    public static interface LogWriter   
    {
        void invoke(String logMessage) throws Exception ;

        List<LogWriter> getInvocationList() throws Exception ;
    
    }

    public static class __MultiProcessor <T>  implements Processor<T>
    {
        public void invoke(RefSupport<T> value) throws Exception {
            List<Processor<T>> copy, members = this.getInvocationList();
            synchronized (members)
            {
                copy = new LinkedList<Processor<T>>(members);
            }
            for (Processor<T> d : copy)
            {
                RefSupport<T> refVar___0 = new RefSupport<T>(value.getValue());
                d.invoke(refVar___0);
                value.setValue(refVar___0.getValue());
            }
        }

        private List<Processor<T>> _invocationList = new ArrayList<Processor<T>>();
        public static <T>Processor<T> combine(Processor<T> a, Processor<T> b) throws Exception {
            if (a == null)
                return b;
             
            if (b == null)
                return a;
             
            __MultiProcessor<T> ret = new __MultiProcessor<T>();
            ret._invocationList = a.getInvocationList();
            ret._invocationList.addAll(b.getInvocationList());
            return ret;
        }

        public static <T>Processor<T> remove(Processor<T> a, Processor<T> b) throws Exception {
            if (a == null || b == null)
                return a;
             
            List<Processor<T>> aInvList = a.getInvocationList();
            List<Processor<T>> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
            if (aInvList == newInvList)
            {
                return a;
            }
            else
            {
                __MultiProcessor<T> ret = new __MultiProcessor<T>();
                // Invoke the delegates.
                ret._invocationList = newInvList;
                return ret;
            } 
        }

        // Change the captured parameter and run them again
        public List<Processor<T>> getInvocationList() throws Exception {
            return _invocationList;
        }
    
    }

    public static interface Processor <T>  
    {
        void invoke(RefSupport<T> value) throws Exception ;

        List<Processor<T>> getInvocationList() throws Exception ;
    
    }

    private String _captured_string = "none";
    private void methodDelegate(String s) throws Exception {
        System.out.println("MethodDelegate[" + _captured_string + "]:\t\t\t" + s);
    }

    private void methodDelegateTwo(String s) throws Exception {
        System.out.println("MethodDelegateTwo[" + _captured_string + "]:\t\t" + s);
    }

    public void runIt() throws Exception {
        LogWriter delA = new LogWriter() 
          { 
            public void invoke(String logMessage) throws Exception {
                methodDelegate(logMessage);
            }

            public List<LogWriter> getInvocationList() throws Exception {
                List<LogWriter> ret = new ArrayList<LogWriter>();
                ret.add(this);
                return ret;
            }
        
          };
        LogWriter delB = new LogWriter() 
          { 
            public void invoke(String logMessage) throws Exception {
                methodDelegateTwo(logMessage);
            }

            public List<LogWriter> getInvocationList() throws Exception {
                List<LogWriter> ret = new ArrayList<LogWriter>();
                ret.add(this);
                return ret;
            }
        
          };
        LogWriter delC = new LogWriter() 
          { 
            public void invoke(String s) throws Exception {
                System.out.println("AnonymousMethodDelegate[" + _captured_string + "]:\t\t" + s);
            }

            public List<LogWriter> getInvocationList() throws Exception {
                List<LogWriter> ret = new ArrayList<LogWriter>();
                ret.add(this);
                return ret;
            }
        
          };
        LogWriter delD = new LogWriter() 
          { 
            public void invoke(String s) throws Exception {
                System.out.println("LambdaExpressionDelegate[" + _captured_string + "]:\t\t" + s);
            }

            public List<LogWriter> getInvocationList() throws Exception {
                List<LogWriter> ret = new ArrayList<LogWriter>();
                ret.add(this);
                return ret;
            }
        
          };
        LogWriter delE = new LogWriter() 
          { 
            public void invoke(String s) throws Exception {
                System.out.println("InferredLambdaExpressionDelegate[" + _captured_string + "]:\t" + s);
            }

            public List<LogWriter> getInvocationList() throws Exception {
                List<LogWriter> ret = new ArrayList<LogWriter>();
                ret.add(this);
                return ret;
            }
        
          };
        delA.invoke("Peter Piper");
        delB.invoke("picked a peck");
        delC.invoke("of pickled peppers.");
        delD.invoke("A peck of pickled peppers");
        delE.invoke("Peter Piper picked.");
        this._captured_string = "aaaa";
        delA.invoke("Peter Piper");
        delB.invoke("picked a peck");
        delC.invoke("of pickled peppers.");
        delD.invoke("A peck of pickled peppers");
        delE.invoke("Peter Piper picked.");
        // Now Combine the delegates
        LogWriter chainDelegates = __MultiLogWriter.combine(__MultiLogWriter.combine(__MultiLogWriter.combine(__MultiLogWriter.combine(delA,delB),delC),delD),delE);
        // and invoke it
        chainDelegates.invoke("Chained Delegates");
        // remove delB and rerun
        chainDelegates = __MultiLogWriter.remove(chainDelegates,delB);
        chainDelegates.invoke("Chained without MethodDelegateTwo");
        Processor<Integer> calcIt = new Processor<Integer>() 
          { 
            // Calculate (4 * (x^x)) + 1
            public void invoke(RefSupport<Integer> x) throws Exception {
                x.setValue(x.getValue() * x.getValue());
            }

            public List<Processor<Integer>> getInvocationList() throws Exception {
                List<Processor<Integer>> ret = new ArrayList<Processor<Integer>>();
                ret.add(this);
                return ret;
            }
        
          };
        calcIt = __MultiProcessor.combine(calcIt,new Processor<Integer>() 
          { 
            public void invoke(RefSupport<Integer> x) throws Exception {
                x.setValue(4 * x.getValue());
            }

            public List<Processor<Integer>> getInvocationList() throws Exception {
                List<Processor<Integer>> ret = new ArrayList<Processor<Integer>>();
                ret.add(this);
                return ret;
            }
        
          });
        calcIt = __MultiProcessor.combine(calcIt,new Processor<Integer>() 
          { 
            public void invoke(RefSupport<Integer> x) throws Exception {
                x.setValue(x.getValue() + 1);
            }

            public List<Processor<Integer>> getInvocationList() throws Exception {
                List<Processor<Integer>> ret = new ArrayList<Processor<Integer>>();
                ret.add(this);
                return ret;
            }
        
          });
        int val = 5;
        RefSupport<Integer> refVar___1 = new RefSupport<Integer>(val);
        calcIt.invoke(refVar___1);
        val = refVar___1.getValue();
        System.out.println("(4 * (5^5)) + 1 = " + val);
    }

    static class __MultiDelC   implements DelC
    {
        public void invoke(String s) throws Exception {
            List<DelC> copy, members = this.getInvocationList();
            synchronized (members)
            {
                copy = new LinkedList<DelC>(members);
            }
            for (DelC d : copy)
            {
                d.invoke(s);
            }
        }

        private List<DelC> _invocationList = new ArrayList<DelC>();
        public static DelC combine(DelC a, DelC b) throws Exception {
            if (a == null)
                return b;
             
            if (b == null)
                return a;
             
            __MultiDelC ret = new __MultiDelC();
            ret._invocationList = a.getInvocationList();
            ret._invocationList.addAll(b.getInvocationList());
            return ret;
        }

        public static DelC remove(DelC a, DelC b) throws Exception {
            if (a == null || b == null)
                return a;
             
            List<DelC> aInvList = a.getInvocationList();
            List<DelC> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
            if (aInvList == newInvList)
            {
                return a;
            }
            else
            {
                __MultiDelC ret = new __MultiDelC();
                ret._invocationList = newInvList;
                return ret;
            } 
        }

        public List<DelC> getInvocationList() throws Exception {
            return _invocationList;
        }
    
    }

    static interface DelC   
    {
        void invoke(String s) throws Exception ;

        List<DelC> getInvocationList() throws Exception ;
    
    }

    static void hello(String s) throws Exception {
        System.out.printf(StringSupport.CSFmtStrToJFmtStr("  Hello, {0}!") + "\n",s);
    }

    static void goodbye(String s) throws Exception {
        System.out.printf(StringSupport.CSFmtStrToJFmtStr("  Goodbye, {0}!") + "\n",s);
    }

    void helloGoodbye() throws Exception {
        DelC a, b, c, d;
        a = new DelC() 
          { 
            // Create the delegate object a that references
            // the method Hello:
            public void invoke(String s) throws Exception {
                hello(s);
            }

            public List<DelC> getInvocationList() throws Exception {
                List<DelC> ret = new ArrayList<DelC>();
                ret.add(this);
                return ret;
            }
        
          };
        b = new DelC() 
          { 
            // Create the delegate object b that references
            // the method Goodbye:
            public void invoke(String s) throws Exception {
                goodbye(s);
            }

            public List<DelC> getInvocationList() throws Exception {
                List<DelC> ret = new ArrayList<DelC>();
                ret.add(this);
                return ret;
            }
        
          };
        // The two delegates, a and b, are composed to form c:
        c = __MultiDelC.combine(a,b);
        // Remove a from the composed delegate, leaving d,
        // which calls only the method Goodbye:
        d = __MultiDelC.remove(c,a);
        System.out.println("Invoking delegate a:");
        a.invoke("A");
        System.out.println("Invoking delegate b:");
        b.invoke("B");
        System.out.println("Invoking delegate c:");
        c.invoke("C");
        System.out.println("Invoking delegate d:");
        d.invoke("D");
        d = __MultiDelC.combine(d,c);
        d = __MultiDelC.combine(d,c);
        d = __MultiDelC.remove(d,c);
        System.out.println("Invoking composed delegate d:");
        d.invoke("DA");
    }

    public static void delegateSamplerMain() throws Exception {
        DelegateSampler sampler = new DelegateSampler();
        sampler.runIt();
    }

}


