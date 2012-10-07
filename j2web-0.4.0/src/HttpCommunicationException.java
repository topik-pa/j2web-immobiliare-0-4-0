public class HttpCommunicationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;
	Exception e;

	public HttpCommunicationException(Exception e)
	  {
	    super();             // call superclass constructor
	    this.e=e;
	  }
	
	public HttpCommunicationException()
	  {
	    super();             // call superclass constructor
	    e = new Exception();
	  }
	
	public String getExceptionType()
	  {
	    return e.getClass().getName();
	  }
    
}
