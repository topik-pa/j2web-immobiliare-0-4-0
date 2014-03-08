public class HttpCommunicationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;
	String cause;
	Exception e;

	public HttpCommunicationException(HttpWrongResponseHeaderException e)
	  {
	    super();             // call superclass constructor
	    this.e=e;
	    this.cause = e.getCauseDescription();
	  }
	
	public HttpCommunicationException(HttpWrongResponseStatusCodeException e)
	  {
	    super();             // call superclass constructor
	    this.e=e;
	    this.cause = e.getCauseDescription();
	  }
	
	public HttpCommunicationException(HttpWrongResponseBodyException e)
	  {
	    super();             // call superclass constructor
	    this.e=e;
	    this.cause = e.getCauseDescription();
	  }
	
	public HttpCommunicationException()
	  {
	    super();             // call superclass constructor
	    e = new Exception();
	  }
	
	public HttpCommunicationException(Exception e)
	  {
	    super();             // call superclass constructor
	    this.e=e;
	  }
	
	public String getCatchedExceptionType()
	  {
	    return e.getClass().getName();
	  }
	
	public String getCauseDescription()
	  {
	    return cause;
	  }
    
}