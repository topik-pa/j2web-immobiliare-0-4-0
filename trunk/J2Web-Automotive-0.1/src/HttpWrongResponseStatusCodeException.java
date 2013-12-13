public class HttpWrongResponseStatusCodeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cause;

	public HttpWrongResponseStatusCodeException(String cause)
	  {
	    super();             // call superclass constructor
	    this.cause=cause;
	  }
	
	public HttpWrongResponseStatusCodeException()
	  {
	    super();             // call superclass constructor
	    cause="unknown";
	  }
	
	public String getCauseDescription()
	  {
	    return cause;
	  }
    
}