﻿using System;
using System.Reflection;
using System.Runtime.InteropServices;
using System.Security.Permissions;

// General Information about an assembly is controlled through the following 
// set of attributes. Change these attribute values to modify the information
// associated with an assembly.
[assembly: AssemblyTitle("FizzBuzz.Console")]
[assembly: AssemblyDescription("Command Line Interface for the FizzBuzz Problem")]

// Setting ComVisible to false makes the types in this assembly not visible 
// to COM components.  If you need to access a type in this assembly from 
// COM, set the ComVisible attribute to true on that type.
[assembly: ComVisible(false)]

// The following GUID is for the ID of the typelib if this project is exposed to COM
[assembly: Guid("50f61666-11c7-46e9-9703-c38eb957eae4")]

// Version information for an assembly consists of the following four values:
//
//      Major Version
//      Minor Version 
//      Build Number
//      Revision
//
[assembly: AssemblyVersion("1.0.0.0")]
[assembly: AssemblyFileVersion("1.0.0.0")]

#if(RELEASE)
// Code Access Security - Requesting Nothing but Execution and Console Access
[assembly: SecurityPermission(SecurityAction.RequestMinimum, Execution = true)]
[assembly: UIPermission(SecurityAction.RequestMinimum, Window=UIPermissionWindow.AllWindows)]
#endif

// Ensure CLS Compliancy
[assembly: CLSCompliant(true)]