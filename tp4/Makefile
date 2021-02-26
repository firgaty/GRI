JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
		  TP4.java \
		  Exemple.java \
		  Racine.java \
		  Puissance.java
		  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) */*.class
	$(RM) */*/*.class
	
run:
	java main