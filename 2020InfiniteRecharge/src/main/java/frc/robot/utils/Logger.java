package frc.robot.utils;

import java.io.*;

import edu.wpi.first.wpilibj.RobotController;

//class for logging data to help with failure analysis - which is sadly inevitable
public class Logger {

    //enumeration for the different types of data the Logger can log
    public enum LogTypes {
        VOLTAGE,
        CURRENT,
        SENSOR_DATA,
    };

    private File logFile;
    private char delim;
    private LogTypes[] types;

    //constructor #1, should be a plaintext file, preferably .csv but the delimiter can be changed
    public Logger(String filename, char logDelim, LogTypes[] logTypes) throws IOException {
        logFile = new File(filename);
        delim = logDelim;
        types = logTypes;
    }

    //constructor #2, should be the same type of file as the #1 constructor
    public Logger(File file, char logDelim, LogTypes[] logTypes) throws IOException {
        logFile = file;
        delim = logDelim;
        types = logTypes;
    }

    //automatically logs all data from the array of data to log to the specified file
    public void logAuto() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
        for(int i = 0; i < types.length; i++) {
            //TODO get all of the log data and write it to the file
            switch(types[i]) {
                case VOLTAGE:
                    Double voltage = RobotController.getBatteryVoltage();
                    writer.append(voltage.toString() + delim);
                    break;
                case CURRENT:
                    Double current = RobotController.getInputCurrent();
                    writer.append(current.toString() + delim);
                    break;
                case SENSOR_DATA:
                    break;
                default:
                    break;
            }
        }
        writer.append("\n");
        writer.close();
    }

    //writes the specified data in the array to the file, separated by whatever delimiter was specified in the construction of the object
    public void log(String[] data) throws IOException {
        //TODO check and see if append is necessary since the filewriter is already set to append mode in the constructor
        PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
        for(int i = 0; i < data.length; i++) {
            writer.append(data[i]);
            writer.append(delim);
        }
        writer.append("\n");
        writer.close();
    }

    public File getLogFile() {
        return logFile;
    }

    public char getDelim() {
        return delim;
    }

    public LogTypes[] getLogTypes() {
        return types;
    }
}