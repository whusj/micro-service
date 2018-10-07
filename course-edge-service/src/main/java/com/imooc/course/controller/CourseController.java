package com.imooc.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.service.ICourseService;
import com.imooc.thrift.user.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Michael on 2017/11/4.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Reference
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {
        logger.error("===CourseController===courseList()===");
        UserDTO user = (UserDTO)request.getAttribute("user");
        logger.error("===CourseController===courseList()===userInfo: "+user.toString());
        return courseService.courseList();
    }
}
