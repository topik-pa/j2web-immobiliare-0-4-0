public class HttpWrongResponseHeaderException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cause;

	public HttpWrongResponseHeaderException(String cause)
	  {
	    super();             // call superclass constructor
	    this.cause=cause;
	  }
	
	public HttpWrongResponseHeaderException()
	  {
	    super();             // call superclass constructor
	    cause="unknown";
	  }
	
	public String getCauseDescription()
	  {
	    return cause;
	  }
    
}
