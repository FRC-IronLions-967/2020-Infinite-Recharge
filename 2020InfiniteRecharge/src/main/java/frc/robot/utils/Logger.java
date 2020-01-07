package frc.robot.utils;

import java.io.*;

//class for logging data to help with failure analysis - which is sadly inevitable
public class Logger {

    //enumeration for the different types of data the Logger can log
    public enum LogTypes {
        VOLTAGE,
        CURRENT,
        SENSOR_DATA
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

    public void logAuto() throws IOException {
        for(int i = 0; i < types.length; i++) {
            //TODO get all of the log data and write it to the file
            switch(types[i]) {
                case VOLTAGE:
                    break;
                case CURRENT:
                    break;
                case SENSOR_DATA:
                    break;
                default:
                    break;
            }
        }
    }

    public void log(String[] data) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
        for(int i = 0; i < data.length; i++) {
            writer.append(data[i]);
            writer.append(delim);
        }
        writer.close();
    }
}