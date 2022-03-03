package exception;

import beans.ErrorType;

public class CouponSystemException extends Exception {
    public CouponSystemException() {}

    public CouponSystemException(String message) {
        super(message);
    }
}
