package br.unb.erico.bookshelf;

@SuppressWarnings("serial")
public class UniqueFieldViolationException extends Exception {

	private String duplicatedField;
	
	public UniqueFieldViolationException(String duplicatedField) {
		this.duplicatedField = duplicatedField;
	}

	public String getDuplicatedField() {
		return duplicatedField;
	}

}
