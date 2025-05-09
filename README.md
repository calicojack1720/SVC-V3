# SVC-V3
An implementation of the SVCv3 language (a subset of LEGv8) from the Programming Languages course.

## svcv3.py
Implements the LegV8 commands MOVZ, ADD, ADDI, SUB, SUBI using Python. The commands are read in
from a txt file called pgm.txt. The commands are run and then the final values of the registers are output to
the txt file outMA.txt.

## lexicalAnalyzer.py
A Lexical Analyzer for SVC-V3. Given code written in SVC-V3, the program recognizes the tokens and prints each token out on a seperate line in prog2MA.txt.

## Prog4MA.java
Full Java implementation of a recursive descent parser for the SVC-V3 language using the grammar rules shown below.

START -> {COMMANDS}
COMMANDS -> MOVE|MATH|IMMEDIATE
MOVE -> 'MOVZ'REGISTER','IMM_VALUE
MATH -> ('ADD'|'SUB')REGISTER','REGISTER','REGISTER
IMMEDIATE -> ('ADDI'|'SUBI')REGISTER','REGISTER','IMM_VALUE
REGISTER -> 'X0'|'X1'|'X2'
IMM_VALUE -> '#'DIGITS
DIGITS -> DIGIT{DIGIT}
DIGIT -> '0'|..|'9'
