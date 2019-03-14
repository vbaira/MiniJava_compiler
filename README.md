# MiniJava_compiler
Implementation of a compiler front end for the MiniJava language(MiniJava -> LLVM IR) using javaCC and JTB tools. MiniJava language is a small subset of Java .
Semantic analysis and LLVM IR code generation is done utilizing the visitor pattern.

## Compilation
~~~
make
~~~

## Execution
To execute the program: 
~~~
java Main file1.java file2.java ... fileN.java
~~~
The program will compile all .java files to the their respective LLVM IR files with names **file1.ll file2.ll** etc.

In order to compile the produced LLVM IR files you will need Clang with version>=4.0.0.Compilation and execution is performed with:
~~~
clang-4.0 -o out1 file1.ll
./out1
~~~
