package frc.robot.utils.matrix;

import frc.robot.utils.exceptions.DimensionMismatchException;

public abstract class MatrixOperations {

    //adds m1 and m2, throws a DimensionMismatchException if the operation cannot be performed
    public static Matrix add(Matrix m1, Matrix m2) throws DimensionMismatchException {
        if(m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new DimensionMismatchException("Error adding matrices: dimensions do not match");
        }
        double[][] result = new double[m1.getRows()][m1.getColumns()];
        for(int i = 0; i < m1.getRows(); i++) {
            for(int j = 0; j < m1.getColumns(); j++) {
                result[i][j] = m1.getElement(i, j) + m2.getElement(i, j);
            }
        }
        return new Matrix(result);
    }

    //subtracts m2 from m1, throws a DimensionMismatchException if the operation cannot be performed
    public static Matrix subtract(Matrix m1, Matrix m2) throws DimensionMismatchException {
        if(m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new DimensionMismatchException("Error subtracting matrices: dimensions do not match");
        }
        double[][] result = new double[m1.getRows()][m1.getColumns()];
        for(int i = 0; i < m1.getRows(); i++) {
            for(int j = 0; j < m1.getColumns(); j++) {
                result[i][j] = m1.getElement(i, j) + m2.getElement(i, j);
            }
        }
        return new Matrix(result);
    }

    //multiplies m1 times m2, throws a DimensionMismatchException if the number of columns in m1 != to the number of rows in m2
    //public static Matrix multiply(Matrix m1, Matrix m2) throws DimensionMismatchException {

    //}
}