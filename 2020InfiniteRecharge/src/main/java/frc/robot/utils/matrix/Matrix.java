/*
So I (Nathan) wrote this before realizing that I didn't want and/or need to rewrite some complex matrix math and adding the
apache commons math library to handle some of that.  Probably should rewrite the Kalman filter class to use those instead but
I'm lazy and this is just a proof of concept
*/

package frc.robot.utils.matrix;

import frc.robot.utils.exceptions.*;

public class Matrix {
    private int rows;
    private int columns;
    private double[][] mat;

    //create a new matrix object of dimensions rows x columns and initialize all values to 0
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.mat = new double[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                mat[i][j] = 0.0;
            }
        }
    }

    //create a new matrix object using the 2d array
    public Matrix(double[][] value) {
        this.rows = value.length;
        this.columns = value[0].length;
        this.mat = value;
    }

    //returns the number of rows in this matrix
    public int getRows() {
        return rows;
    }

    //returns the number of columns in this matrix
    public int getColumns() {
        return columns;
    }

    //returns the 2d double array that is this matrix
    public double[][] getMat() {
        return mat;
    }

    //sets the specified row to values
    //if values exceeds the length of the row, any extra values will be ignored
    //if values is shorter than the length of the row, the rest of the row will be initialized to 0
    public int setRow(int rowNum, double[] values) {
        if(rowNum >= rows) return -1;
        for(int i = 0; i < mat[rowNum].length; i++) {
            mat[rowNum][i] = (i < values.length) ? values[i] : 0.0;
        }
        return 0;
    }

    //sets the specified column to values
    //if values exceeds the length of the column, any extra values will be ignored
    //if values is shorter than the length of the column, the rest of the row will be initialized to 0
    public int setColumn(int columnNum, double[] values) {
        if(columnNum >= columns) return -1;
        for(int i = 0; i < mat[columnNum].length; i++) {
            mat[i][columnNum] = (i < values.length) ? values[i] : 0.0;
        }
        return 0;
    }

    //sets the element at the specified position to the new value
    public void setElement(int row, int column, double value) {
        if(row >= rows || column >= columns) return;
        mat[row][column] = value;
    }

    //get the element at the specified position
    //returns the element, or 0.0 if the element does not exist
    public double getElement(int row, int column) {
        if(row >= rows || column >= columns) return 0.0;
        return mat[row][column];
    }

    //returns the specified row, or null if it doesn't exist
    public double[] getRow(int rowNum) {
        if(rowNum >= rows) return null;
        return mat[rowNum];
    }

    //returns the specified column, or null if it doesn't exist
    public double[] getColumn(int columnNum) {
        if(columnNum >= columns) return null;
        return mat[columnNum];
    }

    //adds otherMatrix to this matrix object
    //if you wish to add two matrices without changing either, use MatrixOperations
    public void addMatrixToThis(Matrix otherMatrix) throws DimensionMismatchException {
        if(rows != otherMatrix.getRows() || columns != otherMatrix.getColumns()) {
            throw new DimensionMismatchException("Error adding matrices: dimensions do not match");
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                mat[i][j] = mat[i][j] + otherMatrix.getElement(i, j);
            }
        }
    }

    //subtracts otherMatrix from this matrix object
    //if you wish to subtract two matrices without changing either, use MatrixOperations
    public void subtractMatrixFromThis(Matrix otherMatrix) throws DimensionMismatchException {
        if(rows != otherMatrix.getRows() || columns != otherMatrix.getColumns()) {
            throw new DimensionMismatchException("Error subtracting matrices: dimensions do not match");
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                mat[i][j] = mat[i][j] - otherMatrix.getElement(i, j);
            }
        }
    }

    //returns the transposition of this matrix
    public Matrix transpose() {
        double d[][] = new double[columns][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                d[j][i] = mat[i][j];
            }
        }
        return new Matrix(d);
    }

}