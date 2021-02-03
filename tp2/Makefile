JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		  TP2.java \
		  memory/Memory.java \
		  graph/parser/GraphParser.java \
		  graph/Graph.java \
		  graph/sweep/FourSweep.java \
		  graph/sweep/TwoSweep.java \
		  graph/sweep/TakesKostersSweep.java \
		  graph/sweep/SumSweep.java \
		  graph/algorithms/ConnectCalculator.java \
		  graph/sweep/DefaultSweep.java \
		  graph/Edges.java \
		  graph/AdvancedGraph.java
		  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) */*.class
	$(RM) */*/*.class
	
run:
	java main