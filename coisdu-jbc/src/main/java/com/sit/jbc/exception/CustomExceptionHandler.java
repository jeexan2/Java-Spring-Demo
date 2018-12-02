package com.sit.jbc.exception;

import com.sit.jbc.controller.security.AuthController;
import com.sit.jbc.logger.CustomLogger;
import com.sit.jbc.logger.CustomLoggerImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final CustomLogger logger = new CustomLoggerImpl(CustomExceptionHandler.class);

//    @ExceptionHandler(CustomException.class)
//    public void handleCustomException(CustomException ex) throws CustomException {
//        ex.printStackTrace();
//        logger.exceptionLog(ex);
////        String errorDetails = ex.toString()
////                + " at "
////                + (ex.getStackTrace()[0].getFileName() != null ? ex.getStackTrace()[0].getFileName() : "")
////                + ":"
////                + ex.getStackTrace()[0].getLineNumber();
////        return new ResponseEntity<String>(errorDetails, new HttpHeaders(), HttpStatus.OK);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public void handleException(Exception ex) throws Exception {
//        ex.printStackTrace();
//        logger.exceptionLog(ex);
////        String errorDetails = ex.toString()
////                + " at "
////                + (ex.getStackTrace()[0].getFileName() != null ? ex.getStackTrace()[0].getFileName() : "")
////                + ":"
////                + ex.getStackTrace()[0].getLineNumber();
////        return new ResponseEntity<String>(errorDetails, new HttpHeaders(), HttpStatus.OK);
//    }
}