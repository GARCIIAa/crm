package org.crm.controller;

import java.sql.SQLException;

import org.crm.exceptions.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;	

@Controller    
public class BaseController {

	//处理500错误
	@ExceptionHandler(ConversionNotSupportedException.class)
	public ModelAndView handlerConversionNotSupportedException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		ConversionNotSupportedException error = (ConversionNotSupportedException)ex;
		mv.addObject("errorType",error.getErrorType());
		mv.addObject("errorMessage",error.getErrorMessage());
		mv.setViewName("error");
		return mv;
	}
	
	//处理406错误
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ModelAndView handlerHttpMediaTypeNotAcceptableException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		HttpMediaTypeNotAcceptableException error = (HttpMediaTypeNotAcceptableException)ex;
		mv.addObject("errorType",error.getErrorType());
		mv.addObject("errorMessage",error.getErrorMessage());
		mv.setViewName("error");
		return mv;
	}
	
	//处理404错误
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handlerResourceNotFoundException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		ResourceNotFoundException error = (ResourceNotFoundException)ex;
		mv.addObject("errorType",error.getErrorType());
		mv.addObject("errorMessage",error.getErrorMessage());
		mv.setViewName("error");
		return mv;
	}
	
	//处理400错误
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ModelAndView handlerHttpMessageNotReadableException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		HttpMessageNotReadableException error = (HttpMessageNotReadableException)ex;
		mv.addObject("errorType",error.getErrorType());
		mv.addObject("errorMessage",error.getErrorMessage());
		mv.setViewName("error");
		return mv;
	}
	
	//处理普通异常
	@ExceptionHandler(SQLException.class)
	public ModelAndView handlerSqlException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorType", "数据库异常");
		mv.addObject("errorMessage","你碰见了没人可怕的数据库异常");
		mv.setViewName("error");
		return mv;
	}
	
	//处理普通异常
	@ExceptionHandler
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorType", "未知的异常");
		mv.addObject("errorMessage","你碰见了没人可怕的未知异常");
		mv.setViewName("error");
		return mv;
	}
	

}
