all:
	@echo "Compilando ProgramaPrincipal.class e as Classes Necessárias"
	@javac mainProgram.java
	@echo "Compilação Concluida"
	
run:
	@java mainProgram
	
clean:
	@rm *.class
	@echo "Arquivos .class apagados"
