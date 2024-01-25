# MostActiveCookie

How to use:
1) Be in root directory
2) Compiled files included however to recompile in console: 
- javac -cp "./lib/*" src/Main.java src/Tests.java

3) To run the console command is:
- java -cp src Main -f csvFiles/filename -d date

4) To run the tests the console command is:
- (On Windows): java -cp ".\lib\*;.\src" org.junit.runner.JUnitCore Tests
- (On Linux): java -cp "./lib/*:./src" org.junit.runner.JUnitCore Tests
