package com.ysps.ysps.mapper;

import java.util.List;
import com.ysps.ysps.domain.YspsExams;

/**
 * 考试信息Mapper接口
 * 
 * @author lins
 * @date 2024-04-13
 */
public interface YspsExamsMapper 
{
    /**
     * 查询考试信息
     * 
     * @param examid 考试信息主键
     * @return 考试信息
     */
    public YspsExams selectYspsExamsByExamid(Long examid);

    /**
     * 查询考试信息列表
     * 
     * @param yspsExams 考试信息
     * @return 考试信息集合
     */
    public List<YspsExams> selectYspsExamsList(YspsExams yspsExams);

    /**
     * 新增考试信息
     * 
     * @param yspsExams 考试信息
     * @return 结果
     */
    public int insertYspsExams(YspsExams yspsExams);

    /**
     * 修改考试信息
     * 
     * @param yspsExams 考试信息
     * @return 结果
     */
    public int updateYspsExams(YspsExams yspsExams);

    /**
     * 删除考试信息
     * 
     * @param examid 考试信息主键
     * @return 结果
     */
    public int deleteYspsExamsByExamid(Long examid);

    /**
     * 批量删除考试信息
     * 
     * @param examids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsExamsByExamids(Long[] examids);
}
