cf-baesd-recsys
===============

Recommend System based on Collaborative Filter with the Netflix Data 

1. How to compile and run the code?

Use command 'make' to compile your code.
Use command 'make test' to compile and run your code (which will call the main function of your code).
We will use command 'make validation' to test your code. So please make sure that 'make validation' will do the similar thing as 'make test' if you make some changes to 'make test'. Also, please do not change TRAINFILE, VALIDATIONFILE, OUTPUTFILE in makefile. You can change TESTFILE to test different test files.

2. How to start?

Firstly, run 'make test'!
Then, if you see the following outputs, it means you have successfully compiled and run the code.

Training File : training_set.csv
Test File : test-all.csv
Output File : predictions.txt

Finally, copy the training file and test file to this directory, and add your implementations into 'Recommender.java' or create some of your own java files if you want. But please remember to change makefile accordingly if you create your own java files.

3. Errors you might see (especially when you work on Windows environment).

1) 'make' is not recognized as an internal or external command,
operable program or batch file.

This is because your environment lacks 'make' command. You can go to http://gnuwin32.sourceforge.net/packages/make.htm to download installation file.

2) javac Recommender.java
process_begin: CreateProcess(NULL, javac Recommender.java, ...) failed.
make (e=2): The system cannot find the file specified.
make: *** [Recommender.class] Error 2

This is probably because that 'javac' command cannot be found. What you need to do is to configure PATH system variable. You can refer to http://www.java.com/en/download/help/path.xml for some help.



