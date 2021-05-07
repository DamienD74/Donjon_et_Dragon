package exception;

public class EntreeInvalideException extends Throwable{
	private static final long serialVersionUID = 1L;

	public EntreeInvalideException() {
        super();
    }
    
    public EntreeInvalideException(String message) {
        super(message);
    }
}
