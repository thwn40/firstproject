package com.sjboard.firstproject.Exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ExceptionController implements ErrorController {


    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {


        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);


        if(status != null){
            int statusCode = Integer.valueOf(status.toString());
            log.info("httpStatus: = {}",statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else {
                return "error/500";
            }
        }

        return "error/500";
    }

}

