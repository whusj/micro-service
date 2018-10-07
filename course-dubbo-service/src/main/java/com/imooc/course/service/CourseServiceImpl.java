package com.imooc.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.mapper.CourseMapper;
import com.imooc.thrift.user.UserInfo;
import com.imooc.thrift.user.dto.TeacherDTO;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Michael on 2017/11/3.
 */
@Service
public class CourseServiceImpl implements ICourseService {

    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> courseList() {
        logger.error("===CourseServiceImpl===courseList()===start...");
        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if(courseDTOS!=null) {
            for(CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if(teacherId!=null) {
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        logger.error("===CourseServiceImpl===courseList()===exception");
                        logger.error(e.getMessage());
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
