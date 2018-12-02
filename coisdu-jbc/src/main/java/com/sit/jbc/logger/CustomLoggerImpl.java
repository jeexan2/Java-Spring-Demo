package com.sit.jbc.logger;

import com.sit.jbc.exception.CustomException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CustomLoggerImpl extends LogManager implements CustomLogger {
    Logger logger;

    public CustomLoggerImpl (Class className) {
        logger = super.getLogger(className);
    }

    @Override
    public void debugLog(Object message) {
        logger.debug(message);
    }

    @Override
    public void errorLog(CustomException ex) {
        logger.error("[ErrorMessage]: " + ex.getErrorMessage() + " [ErrorCode]: " + ex.getErrorCode()
                + " [ErrorDetails]: " + ex.getErrorDetails() + " [Stacktrace]: " + ex);
    }

    @Override
    public void errorLog(Exception ex) {
        logger.error("[ErrorRootCause]: " + ex.toString() + " [Stacktrace]: " + ex);
    }

    @Override
    public void errorLog (Object message) {
        logger.error(message);
    }

    @Override
    public void exceptionLog(Exception ex) {
        logger.error( "Exception Stack: ", ex);
    }

    @Override
    public void exceptionLog(CustomException ex) {
        logger.error( "Custom Exception Stack: ", ex);
    }
}