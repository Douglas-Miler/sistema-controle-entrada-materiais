package util;

public enum StringConstantUtil {

	WINDOWS_TITLE("Sistema de Controle de Entrada de Materiais"),
	INSTRUCTION_TO_SEACH_NAME("Insira abaixo o nome do idoso");
	
	private String constantValue;
	
	StringConstantUtil(String constantValue) {
		this.constantValue = constantValue;
	}
	
	public String getConstantValue() {
		return this.constantValue;
	}
	
}
