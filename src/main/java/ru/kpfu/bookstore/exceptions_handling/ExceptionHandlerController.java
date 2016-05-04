package ru.kpfu.bookstore.exceptions_handling;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.kpfu.bookstore.entities.User;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(Throwable.class)
  public ModelAndView exception(HttpServletRequest req, Exception exception) throws Exception {

    if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
      throw exception;
    }

    ModelAndView mav = new ModelAndView("404except");

    if (exception instanceof NoHandlerFoundException){
      mav.addObject("url", req.getRequestURL());
      mav.addObject("title","Not Found 404");
      mav.setViewName("404except");
      mav.addObject("user", new User());
      mav.addObject("modal_visible",false);

      return mav;
    }

    return mav;
  }

}
