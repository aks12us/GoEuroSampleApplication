### Execution Step  ###

Execution files :

1) GoEuroTest.jar

2) lsa_feeder.properties

3) If the application is executed on linux enviroment then please use the run.bash file it will guide it itself .

4) For Microsoft developers please execute using command line .

##  Step ##

1) Copy GoEuroTest.jar and lsa_feeder.properties

2) Execute the application using below command 
   
   java -jar GoEuroTest.jar berlin

3) Some example execution  java -jar GoEuroTest.jar berlin , java -jar GoEuroTest.jar ber
	#1 java -jar GoEuroTest.jar berlin
    #2 java -jar GoEuroTest.jar ber
	#3 java -jar GoEuroTest.jar london

##  Assumption in input ##

1) It can be wildcard location for example if you search "ber" 
it will give output for all the location containing "ber"

## Output File 
## Output file will always be generated with data or a message for refining the 
## Search criteria in case results where not found for the current inputs
