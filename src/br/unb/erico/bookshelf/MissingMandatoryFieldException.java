package br.unb.erico.bookshelf;

@SuppressWarnings("serial")
public class MissingMandatoryFieldException extends Exception {

	private String missingField;
	
	public MissingMandatoryFieldException(String missingField) {
		this.missingField = missingField;
	}

	public String getMissingField() {
		return missingField;
	}

}
