package com.sit.jbc.logger;

import com.sit.jbc.exception.CustomException;

public interface CustomLogger {
    public void debugLog(Object message);
    public void errorLog(CustomException ex);
    public void errorLog(Exception ex);
    public void errorLog (Object message);
    public void exceptionLog(Exception ex);
    public void exceptionLog(CustomException ex);
}