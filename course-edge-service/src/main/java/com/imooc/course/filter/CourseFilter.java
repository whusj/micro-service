package com.imooc.course.filter;

import com.imooc.course.controller.CourseController;
import com.imooc.thrift.user.dto.UserDTO;
import com.imooc.user.client.LoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 2017/11/4.
 */
public class CourseFilter extends LoginFilter {
    private Logger logger = LoggerFactory.getLogger(CourseFilter.class);
    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        logger.error("===CourseFilter===login()===");
        request.setAttribute("user", userDTO);
    }
}
