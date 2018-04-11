# IbanValidator

The app provides possibility to check IBAN either by single IBAN entry or by multiple ones if the are stored in a text file. In the latter case the user is asked to provide path to the file. After validation the output file is created and saved in users Home directory. 
IBAN validation is done according to the algorithms discussed here: https://en.wikipedia.org/wiki/International_Bank_Account_Number 

Running application:

-Open command line in the root folder and type: mvn spring-boot:run 	

-In browser type http://localhost:8080/ibanvalidator in order to access user interface.
