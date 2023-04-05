Tests = AETests, FDTests, BDTests

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

TextUITester.class: TextUITester.java
	javac $^

runTests: $(Tests)

AETests: AlgorithmEngineerTests.class
	java -jar lib/junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java MovieRedBlackTree.class RBTList.class RedBlackTree.class Backend.class
	javac -cp .:lib/junit5.jar $<

BDTests: BackendDeveloperTests.class
	java -jar lib/junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java Backend.class TextUITester.class MovieSearchApp.class RBTList.class MovieRedBlackTree.class
	javac -cp .:lib/junit5.jar $<

FDTests: FrontendDeveloperTests.class
	java -jar lib/junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java MovieSearchApp.class Backend.class RBTList.class MovieRedBlackTree.class MovieReader.class Movie.class
    javac -cp .:lib/junit5.jar $<

DWTests: DataWranglerTests.class
	java -jar lib/junit5.jar -cp . --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java Movie.class MovieReader.class MovieRedBlackTree.class Backend.class  MovieSearchApp.class
	javac -cp .:lib/junit5.jar $<


clean:
	rm *.class 2>/dev/null || true # Suppress errors
