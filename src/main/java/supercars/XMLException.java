package supercars;

public class XMLException extends Exception {
	  private Throwable cause = null;

	  public XMLException() {
	    super();
	  }

	  public XMLException(String message) {
	    super(message);
	  }

	  public XMLException(String message, Throwable
	    cause) {
	      super(message);
	      this.cause = cause;
	  }

	  public Throwable getCause() {
	    return cause;
	  }

	  public void printStackTrace(java.io.PrintStream ps)
	  {
	    super.printStackTrace(ps);
	    if (cause != null) {
	      ps.println("Caused by:");
	      cause.printStackTrace(ps);
	    }
	  }

	  public void printStackTrace(java.io.PrintWriter pw)
	  {
	    super.printStackTrace(pw);
	    if (cause != null) {
	      pw.println("Caused by:");
	      cause.printStackTrace(pw);
	    }
	  }
	}
