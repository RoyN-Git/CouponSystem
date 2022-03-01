package exception;

public class CouponSystemException extends Exception {

    private ErrorType errorType;

    public CouponSystemException(Exception e) {
        super(e);
    }

    public CouponSystemException(Exception e, String message) {
        super(message, e);
    }

    public CouponSystemException(Exception e, ErrorType errorType, String message) {
        super(message, e);
        this.errorType=errorType;
    }

    public CouponSystemException(ErrorType errorType, String message) {
        super(message);
        this.errorType=errorType;
    }

    public ErrorType getErrorType(){
        return this.errorType;
    }

}
