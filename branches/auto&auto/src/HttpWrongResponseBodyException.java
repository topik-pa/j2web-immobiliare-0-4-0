public class HttpWrongResponseBodyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cause;

	public HttpWrongResponseBodyException(String cause)
	  {
	    super();             // call superclass constructor
	    this.cause=cause;
	  }
	
	public HttpWrongResponseBodyException()
	  {
	    super();             // call superclass constructor
	    cause="unknown";
	  }
	
	public String getCauseDescription()
	  {
	    return cause;
	  }
    
}