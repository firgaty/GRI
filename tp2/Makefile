JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		  TP2.java \
		  Memory.java \
		  GraphParser.java \
		  Graph.java \
		  FourSweep.java \
		  TwoSweep.java \
		  TakesKosterSweep.java \
		  SumSweep.java \
		  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java main