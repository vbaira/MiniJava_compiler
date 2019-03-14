all: compile

compile:
	java -jar ./jtb132di.jar ./minijava.jj
	java -jar ./javacc5.jar ./minijava-jtb.jj
	javac Main.java

clean:
	rm -f *.class *~
	rm -f  -r ./syntaxtree
	rm -f  -r ./visitor
	rm -f ./JavaCharStream.java minijava-jtb.jj MiniJavaParser.java MiniJavaParserConstants.java MiniJavaParserTokenManager.java ParseException.java Token.java TokenMgrError.java
	rm -f ./output/*