using System;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Security.Permissions;

// General Information about an assembly is controlled through the following 
// set of attributes. Change these attribute values to modify the information
// associated with an assembly.
[assembly: AssemblyTitle("FizzBuzz.Core")]
[assembly: AssemblyDescription("The worker classes used in the FizzBuzz application.")]

// Setting ComVisible to false makes the types in this assembly not visible 
// to COM components.  If you need to access a type in this assembly from 
// COM, set the ComVisible attribute to true on that type.
[assembly: ComVisible(false)]

// The following GUID is for the ID of the typelib if this project is exposed to COM
[assembly: Guid("cdc1e3ea-26ea-4e8b-8478-991c5696d269")]

// Version information for an assembly consists of the following four values:
//
//      Major Version
//      Minor Version 
//      Build Number
//      Revision
//
// You can specify all the values or you can default the Revision and Build Numbers 
// by using the '*' as shown below:
[assembly: AssemblyVersion("1.0.0.0")]
[assembly: AssemblyFileVersion("1.0.0.0")]

#if(DEBUG)
[assembly: InternalsVisibleTo("FizzBuzz.Core.Test, PublicKey=00240000048000009400000006020000002400005253413100040000010001006173a5840d16dfe4eaa99a2d026dfbf1101a6a3f73697f54b7f7e561eb5b19b85dec04c3559724b5a38ecc2bac7ff3fb0bc26b58daa5c23f045a222fe63620c05fec7e688e9aca217340d7356804b7e1b97151900fb8d1465e9e01af2c769dce283a5af999abeb93dfd075ef4f5fe7d2e4bdcf68df9f64f6bc5942fb0f881295")]
#endif

#if(RELEASE)
// Code Access Security - Requesting Nothing but Execution

[assembly: SecurityPermission(
   SecurityAction.RequestMinimum, Execution = true)]
[assembly: PermissionSet(
   SecurityAction.RequestOptional, Name = "Nothing")]
#endif

// Ensure CLS Compliancy
[assembly: CLSCompliant(true)]
