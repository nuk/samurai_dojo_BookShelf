package br.unb.erico.bookshelf;

@SuppressWarnings("serial")
public class MissingFieldException extends Exception {

	private String missingField;
	
	public MissingFieldException(String missingField) {
		this.missingField = missingField;
	}

	public String getMissingField() {
		return missingField;
	}

}
