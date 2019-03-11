run: Trains.class
	java Trains
Trains.class: Trains.java
	javac Trains.java
Digraph.class: Digraph.java
	javac Digraph.java
clean:
	$(RM) *.class PRINTER*