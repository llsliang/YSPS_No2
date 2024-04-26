package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsCoursesMapper;
import com.ysps.ysps.domain.YspsCourses;
import com.ysps.ysps.service.IYspsCoursesService;

/**
 * 包含课程信息，如课程名称、教师、时间和地点Service业务层处理
 * 
 * @author lins
 * @date 2024-04-12
 */
@Service
public class YspsCoursesServiceImpl implements IYspsCoursesService 
{
    @Autowired
    private YspsCoursesMapper yspsCoursesMapper;

    /**
     * 查询包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param courseid 包含课程信息，如课程名称、教师、时间和地点主键
     * @return 包含课程信息，如课程名称、教师、时间和地点
     */
    @Override
    public YspsCourses selectYspsCoursesByCourseid(Long courseid)
    {
        return yspsCoursesMapper.selectYspsCoursesByCourseid(courseid);
    }

    /**
     * 查询包含课程信息，如课程名称、教师、时间和地点列表
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 包含课程信息，如课程名称、教师、时间和地点
     */
    @Override
    public List<YspsCourses> selectYspsCoursesList(YspsCourses yspsCourses)
    {
        return yspsCoursesMapper.selectYspsCoursesList(yspsCourses);
    }

    /**
     * 新增包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 结果
     */
    @Override
    public int insertYspsCourses(YspsCourses yspsCourses)
    {
        return yspsCoursesMapper.insertYspsCourses(yspsCourses);
    }

    /**
     * 修改包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 结果
     */
    @Override
    public int updateYspsCourses(YspsCourses yspsCourses)
    {
        return yspsCoursesMapper.updateYspsCourses(yspsCourses);
    }

    /**
     * 批量删除包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param courseids 需要删除的包含课程信息，如课程名称、教师、时间和地点主键
     * @return 结果
     */
    @Override
    public int deleteYspsCoursesByCourseids(Long[] courseids)
    {
        return yspsCoursesMapper.deleteYspsCoursesByCourseids(courseids);
    }

    /**
     * 删除包含课程信息，如课程名称、教师、时间和地点信息
     * 
     * @param courseid 包含课程信息，如课程名称、教师、时间和地点主键
     * @return 结果
     */
    @Override
    public int deleteYspsCoursesByCourseid(Long courseid)
    {
        return yspsCoursesMapper.deleteYspsCoursesByCourseid(courseid);
    }
}
