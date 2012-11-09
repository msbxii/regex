JUNIT_CORE=org.junit.runner.JUnitCore
JUNIT_PATH=$(shell pwd)/junit-4.10.jar

JFLAGS=-classpath $$CLASSPATH:$(JUNIT_PATH):.
JAVA=java $(JFLAGS)
JAVAC=javac $(JFLAGS)
JAVADOC=javadoc

TARNAME=emy16_hw09_eecs293.tar.gz
TAR_TOPNAME=emy16
#README=README
README=
MAKEFILE=Makefile

TESTCLASSES= RegexTester \

CLASSES = \
	  Regex \
	  $(TESTCLASSES)  \


%.class: %.java
	$(JAVAC) $<


.PHONY: doc clean ready tar test

all: $(CLASSES:=.class)

test: all
	$(JAVA) $(JUNIT_CORE) $(TESTCLASSES)

clean:
	rm -f *.class

ready: clean
	rm -f .*.un .*~ .*.swp .*.swo

doc: 
	mkdir -p doc
	$(JAVADOC) -d doc *.java $(JFLAGS)

docprivate:
	mkdir -p private
	$(JAVADOC) -d private -private *.java $(JFLAGS)

tags:
	ctags -R . *.java

tar:
	mkdir -p $(TAR_TOPNAME)
	cp $(CLASSES:=.java) $(README) $(MAKEFILE) $(TAR_TOPNAME)/
	tar czvf $(TARNAME) $(TAR_TOPNAME)
	rm -rf $(TAR_TOPNAME)
