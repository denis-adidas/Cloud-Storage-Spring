package com.denis_adidas.cloudstorage.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadException {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleFileSizeLimitExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result = new ModelAndView();
        result.setViewName("result");
        String status = "File is too large, file size limit is 1Gb";
        result.addObject("errorMsg", true);
        result.addObject("message", status);
        return result;
    }
}
