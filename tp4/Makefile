JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		  TP4.java \
		  graph/parser/GraphParser.java \
		  graph/Edges.java \
		  graph/AdvancedGraph.java \
		  graph/Cluster.java \
		  graph/Triangles.java \
		  graph/Coeur.java \
		  utils/ProgressBar.java
		  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) */*.class
	$(RM) */*/*.class
	
run:
	java main