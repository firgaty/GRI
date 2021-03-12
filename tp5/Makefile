JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		  TP5.java \
		  graph/parser/GraphParser.java \
		  graph/Graph.java \
		  graph/Edges.java \
		  utils/ProgressBar.java \
		  Community.java
		  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) */*.class
	$(RM) */*/*.class
	
run:
	java main