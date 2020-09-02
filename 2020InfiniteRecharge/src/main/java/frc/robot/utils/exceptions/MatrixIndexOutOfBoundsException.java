package frc.robot.utils.exceptions;

public class MatrixIndexOutOfBoundsException extends RuntimeException {
    
    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }

    public MatrixIndexOutOfBoundsException(String message, Throwable err) {
        super(message, err);
    }
}