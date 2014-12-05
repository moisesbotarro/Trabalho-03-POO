all:
	@echo "Compilando ProgramaPrincipal.class e as Classes Necessárias"
	@javac ProgramaPrincipal.java
	@echo "Compilação Concluida"
	
run:
	@java ProgramaPrincipal
	
clean:
	@rm *.class
	@echo "Arquivos .class apagados"
