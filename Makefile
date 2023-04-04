run: runProgram.class
	java runProgram

runProgram.class: runProgram.java
	javac runProgram.java

runProgram.java: MovieSearchApp.class Backend.class RBTList.class MovieRedBlackTree.class MovieReader.class
	javac MovieSearchApp.java

MovieSearchApp.class: MovieSearchApp.java
	javac MovieSearchApp.java

Backend.class: Backend.java
	javac Backend.java

RBTList.class: RBTList.java
	javac RBTList.java

MovieRedBlackTree.class: MovieRedBlackTree.java RedBlackTree.class
	javac MovieRedBlackTree.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

MovieReader.class: MovieReader.java Movie.class
	javac MovieReader.java

Movie.class: Movie.java
	javac Movie.java

runTests: AETests

AETests: AlgorithmEngineerTests.class
	java -jar lib/junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java MovieRedBlackTree.class
	javac -cp .:lib/junit5.jar $<


clean:
	rm *.class
