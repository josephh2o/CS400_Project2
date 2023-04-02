run: runProgram.class
	java runProgram

runProgram.class: runProgram.java
	javac runProgram.java

runProgram.java: MovieSearchApp.class
	javac MovieSearchApp.java

MovieSearchApp.class: Backend.class
	javac Backend.java

Backend.class: RBTList.java MovieRedBlackTree.java MovieReader.java
	javac RBTList.java
	javac MovieRedBlackTree.java
	javac MovieReader.java
# runTest:

clean:
	rm *.class
