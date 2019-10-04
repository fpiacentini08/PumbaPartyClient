package main.java.pumba.exceptions;

public class PumbaException extends Exception
{
    private static final long serialVersionUID = -5448077999646695145L;

    private String errorCode;

    public PumbaException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public PumbaException(String message, String errorCode, Throwable ex)
    {
        super(message, ex);
        this.errorCode = errorCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }
}
