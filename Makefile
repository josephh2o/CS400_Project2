Tests = AETests, FDTests

run: runProgram.class
	java runProgram

runProgram.class: runProgram.java
	javac $^

runProgram.java: MovieSearchApp.class Backend.class RBTList.class MovieRedBlackTree.class MovieReader.class

MovieSearchApp.class: MovieSearchApp.java
	javac $^

Backend.class: Backend.java
	javac $^

RBTList.class: RBTList.java
	javac $^

MovieRedBlackTree.class: MovieRedBlackTree.java RedBlackTree.class
	javac $<

RedBlackTree.class: RedBlackTree.java
	javac $^

MovieReader.class: MovieReader.java Movie.class
	javac $<

Movie.class: Movie.java
	javac $^

runTests: $(Tests)

AETests: AlgorithmEngineerTests.class
	java -jar lib/junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java MovieRedBlackTree.class RBTList.class RedBlackTree.class
	javac -cp .:lib/junit5.jar $<

FDTests: FrontendDeveloperTests.class
    java -jar lib/junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java MovieSearchApp.class Backend.class RBTList.class MovieRedBlackTree.class MovieReader.class
    javac -cp .:lib/junit5.jar $<

clean:
	rm *.class 2>/dev/null || true # Suppress errors
