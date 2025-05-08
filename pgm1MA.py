'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: Implements the LegV8 commands MOVZ, ADD, ADDI, SUB, SUBI using Python. The commands are read in
from a txt file called pgm.txt. The commands are run and then the final values of the registers are output to
the txt file outMA.txt.
Variables:
    pgm.txt - the input file variable
    outMa.txt - the output file variable
    regLst - a list of size 3 containing the values for registers X0, X1, and X2 at indices 0, 1, and 2
            respectively
    inCommands - reads in commands from pgm.txt
    outResults - outputs results of commands to the outMA.txt file
    line - reads in lines from inCommands
    br - the break point in the string where the space is found
    command - holds the command parsed from the input
    paramLst - the list of parameters to pass to the command function
************************************************
'''

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: Sets the register at index reg1 to the value of num
Variables:
    reg1 - the index of the first register
    num - the number to set the register to
    lst - the list of registers
Returns: The updated list of registers
************************************************
'''

def movz(reg1, num, lst):
    lst[reg1] = num
    return lst

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: Adds the values of the registers at index reg2 and index reg3 and places the result in the
register at index reg1
Variables:
    reg1 - the index of the first register
    reg2 - the index of the second register
    reg3 - the index of the third register
    lst - the list of registers
Returns: The updated list of registers
************************************************
'''

def add(reg1, reg2, reg3, lst):
    lst[reg1] = lst[reg2] + lst[reg3]
    return lst

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description:
Variables: Adds an immediate to the register at index reg2 and places the result in the register at index reg1
    reg1 - the index of the first register
    reg2 - the index of the second register
    num - the number to add to the register at location reg2
    lst - the list of registers
Returns: The updated list of registers
************************************************
'''

def addi(reg1, reg2, num, lst):
    lst[reg1] = lst[reg2] + num
    return lst

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: subtracts the value of the register at index reg3 from the register at index reg2, placing the
value in reg1
Variables:
    reg1 - the index of the first register
    reg2 - the index of the second register
    reg3 - the index of the third register
    lst - the list of registers
Returns: The updated list of registers
************************************************
'''

def sub(reg1, reg2, reg3, lst):
    lst[reg1] = lst[reg2] - lst[reg3]
    return lst

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: Subtracts an immediate value (num) from the register at index reg2, placing the result in the
register at index reg1
Variables:
    reg1 - the index of the first register
    reg2 - the index of the second register
    num - the integer value to be added
    lst - the list of registers
Returns: The updated list of registers
************************************************
'''

def subi(reg1, reg2, num, lst):
    lst[reg1] = lst[reg2] - num
    return lst

'''
************************************************
Name: Michael Astfalk
Date Created: 2/4/25
Last Modified: 2/13/25
Description: Initializes the register list and then reads input from pgm.txt, runs commands, and outputs
results to outMA.txt
Variables:
    regLst - a list of size 3 containing the values for registers X0, X1, and X2 at indices 0, 1, and 2
           respectively
    inCommands - reads in commands from pgm.txt
    outResults - outputs results of commands to the outMA.txt file
    line - reads in lines from inCommands
    br - the break point in the string where the space is found
    command - holds the command parsed from the input
    paramLst - the list of parameters to pass to the command function
Returns: N/A
************************************************
'''

def Mn():

    #******************************************
    # Variable declarations - set registers in regLst to 0, open pgm.txt with read permissions, and open
    # outMA.txt with write permissions
    #******************************************

    regLst = [0, 0, 0]
    inCommands = open('pgm.txt', 'r')
    outResults = open('outMA.txt', 'w')

    #******************************************
    # Priming read
    #******************************************

    line = inCommands.readline()

    while line:

        #******************************************
        # Get the command from the line
        #******************************************

        br = line.index(' ')
        command = line[:br]
        print('Command:', command)

        #******************************************
        # Set line to everything after the command
        #******************************************

        line = line[br+1:]
        print('Parameters:', line)

        #******************************************
        # Initialize paramLst to an empty list
        #******************************************

        paramLst = []

        #******************************************
        # Loop through each character to the end of the line to get parameters
        #******************************************

        for i in range(0, len(line)):
                #******************************************
                # Get the register number if 'X' is found
                #******************************************

                if line[i] == 'X':
                    paramLst.append(int(line[i+1]))

                #******************************************
                # Get the immediate if '#' is found
                #******************************************

                if line[i] == '#':
                    paramLst.append(int(line[i+1:]))

        #******************************************
        # Determine the command and call the appropriate function, passing the appropriate parameters
        # and setting regLst to the returned value
        #******************************************

        if command == 'MOVZ':
            regLst = movz(paramLst[0], paramLst[1], regLst)
            print('Register List:', regLst, '\n')
        elif command == 'ADD':
            regLst = add(paramLst[0], paramLst[1], paramLst[2], regLst)
            print('Register List:', regLst, '\n')
        elif command == 'ADDI':
            regLst = addi(paramLst[0], paramLst[1], paramLst[2], regLst)
            print('Register List:', regLst, '\n')
        elif command == 'SUB':
            regLst = sub(paramLst[0], paramLst[1], paramLst[2], regLst)
            print('Register List:', regLst, '\n')
        elif command == 'SUBI':
            regLst = subi(paramLst[0], paramLst[1], paramLst[2], regLst)
            print('Register List:', regLst, '\n')
        else:
            print('ERROR: Command not found!')
        
        #******************************************
        # Clear paramLst
        #******************************************

        paramLst.clear()

        #******************************************
        # Read in the next line
        #******************************************

        line = inCommands.readline()

    #******************************************
    # Print out the final register values to outMA.txt
    #******************************************

    outResults.write('Register Values:\nX0: {}\nX1: {}\nX2: {}'.format(regLst[0], regLst[1], regLst[2]))

    #******************************************
    # Close the input and output files:
    #******************************************

    inCommands.close()
    outResults.close()

Mn()