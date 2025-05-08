'''
************************************************
Name: Michael Astfalk
Date Created: 2/25/25
Last Modified: 3/18/25
Description: A Lexical Analyzer for SVC-V3. Given code written in SVC-V3, the program recognizes the
            tokens and prints each token out on a seperate line in prog2MA.txt.
Variables:
    prog2.txt - The input file variable containing code writteen in SVC-V3
    prog2MAout.txt - The output file variable which has tokens printed out to it line by line
    token - Stores the token that has been found or '' for no token
    inputFile - Input stream for the prog2.txt file
    outputFile - Output stream for the prog2MAout.txt file
    line - String variable for program lines from inputFile
    i - The current index of the line string
************************************************
'''

'''
************************************************
Name: Michael Astfalk
Date Created: 2/25/25
Last Modified: 3/18/25
Description: Reads in the program in prog2.txt line by line and identifies the tokens in the program,
            ignoring spaces and comments. Each token is printed out on its own line to the
            prog2MAout.txt file.
Variables:
    token - Stores the token that has been found or '' for no token
    inputFile - Input stream for the prog2.txt file
    outputFile - Output stream for the prog2MAout.txt file
    line - String variable for program lines from inputFile
    i - Variable for string indices in line
************************************************
'''

def Mn():

    #************************************************
    # Declare variables and open inputFile and outputFile
    #************************************************
    
    token = ''

    inputFile = open('prog2.txt', 'r')
    outputFile = open('prog2MAout.txt', 'w')

    #************************************************
    # Priming read
    #************************************************

    line = inputFile.readline()

    #************************************************
    # Loop while there are still lines left
    #************************************************

    while line:
    
        #************************************************
        # Skip blank lines
        #************************************************

        if line == '\n':
            line = inputFile.readline()
        
        #************************************************
        # Loop through the characters of the string to
        # identify tokens
        #************************************************

        i = 0

        while i < len(line):
            
            #************************************************
            # Identify and skip spaces
            #************************************************

            if line[i] == ' ':
                print('Hit a space')
                token = ''
                i += 1
            
            #************************************************
            # Identify and skip comments
            #************************************************

            elif line[i] == '/' and line[i+1] == '/':
                print('Hit a comment')
                token = ''
                break
                

            #************************************************
            # Identify and obtain symbol tokens
            #************************************************

            elif line[i] == ',':
                token = line[i]
                print('Hit a symbol:', token)
                i += 1

            #************************************************
            # Identify and obtain name tokens.
            #************************************************

            elif line[i].isalpha():
                while line[i] != ' ' and line[i] != '\n' and line[i] != ',':
                    token += line[i]
                    i += 1
                    if i >= len(line):
                        break
                print('Hit a name:', token)
            
            #************************************************
            # Identify and obtain number tokens
            #************************************************

            elif line[i] == '#':
                while line[i] != ' ' and line[i] != '\n' and line[i] != ',':
                    token += line[i]
                    i += 1
                    if i >= len(line):
                        break

                print('Hit a number:', token)
            
            #************************************************
            # If no token is found, increment i
            #************************************************
            
            else:
                i += 1

            #************************************************
            # If a token was found, write it to ouptutFile
            #************************************************

            if token != '':
                outputFile.write(token + '\n')
            
            token = ''

        #************************************************
        # Read in the next line from inputFile
        #************************************************

        line = inputFile.readline()
        
    #************************************************
    # Close the input and output files
    #************************************************

    inputFile.close()
    outputFile.close()