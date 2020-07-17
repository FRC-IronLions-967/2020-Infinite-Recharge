package frc.robot.utils.exceptions;

public class DimensionMismatchException extends RuntimeException {
    
    public DimensionMismatchException(String message) {
        super(message);
    }

    public DimensionMismatchException(String message, Throwable err) {
        super(message, err);
    }
}