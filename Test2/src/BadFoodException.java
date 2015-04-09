public class BadFoodException extends Exception {
	private String name;

	public BadFoodException(String name) {
		this.name = name;
	}

	public String toString() {
		return "it's not for eat the " + name;
	}
}
