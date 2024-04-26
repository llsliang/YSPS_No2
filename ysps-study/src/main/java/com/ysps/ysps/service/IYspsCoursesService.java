package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsCourses;

/**
 * 包含课程信息，如课程名称、教师、时间和地点Service接口
 * 
 * @author lins
 * @date 2024-04-12
 */
public interface IYspsCoursesService 
{
    /**
     * 查询包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param courseid 包含课程信息，如课程名称、教师、时间和地点主键
     * @return 包含课程信息，如课程名称、教师、时间和地点
     */
    public YspsCourses selectYspsCoursesByCourseid(Long courseid);

    /**
     * 查询包含课程信息，如课程名称、教师、时间和地点列表
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 包含课程信息，如课程名称、教师、时间和地点集合
     */
    public List<YspsCourses> selectYspsCoursesList(YspsCourses yspsCourses);

    /**
     * 新增包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 结果
     */
    public int insertYspsCourses(YspsCourses yspsCourses);

    /**
     * 修改包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param yspsCourses 包含课程信息，如课程名称、教师、时间和地点
     * @return 结果
     */
    public int updateYspsCourses(YspsCourses yspsCourses);

    /**
     * 批量删除包含课程信息，如课程名称、教师、时间和地点
     * 
     * @param courseids 需要删除的包含课程信息，如课程名称、教师、时间和地点主键集合
     * @return 结果
     */
    public int deleteYspsCoursesByCourseids(Long[] courseids);

    /**
     * 删除包含课程信息，如课程名称、教师、时间和地点信息
     * 
     * @param courseid 包含课程信息，如课程名称、教师、时间和地点主键
     * @return 结果
     */
    public int deleteYspsCoursesByCourseid(Long courseid);
}
