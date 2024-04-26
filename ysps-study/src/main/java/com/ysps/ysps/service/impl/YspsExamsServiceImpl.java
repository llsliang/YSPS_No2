package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsExamsMapper;
import com.ysps.ysps.domain.YspsExams;
import com.ysps.ysps.service.IYspsExamsService;

/**
 * 考试信息Service业务层处理
 * 
 * @author lins
 * @date 2024-04-13
 */
@Service
public class YspsExamsServiceImpl implements IYspsExamsService 
{
    @Autowired
    private YspsExamsMapper yspsExamsMapper;

    /**
     * 查询考试信息
     * 
     * @param examid 考试信息主键
     * @return 考试信息
     */
    @Override
    public YspsExams selectYspsExamsByExamid(Long examid)
    {
        return yspsExamsMapper.selectYspsExamsByExamid(examid);
    }

    /**
     * 查询考试信息列表
     * 
     * @param yspsExams 考试信息
     * @return 考试信息
     */
    @Override
    public List<YspsExams> selectYspsExamsList(YspsExams yspsExams)
    {
        return yspsExamsMapper.selectYspsExamsList(yspsExams);
    }

    /**
     * 新增考试信息
     * 
     * @param yspsExams 考试信息
     * @return 结果
     */
    @Override
    public int insertYspsExams(YspsExams yspsExams)
    {
        return yspsExamsMapper.insertYspsExams(yspsExams);
    }

    /**
     * 修改考试信息
     * 
     * @param yspsExams 考试信息
     * @return 结果
     */
    @Override
    public int updateYspsExams(YspsExams yspsExams)
    {
        return yspsExamsMapper.updateYspsExams(yspsExams);
    }

    /**
     * 批量删除考试信息
     * 
     * @param examids 需要删除的考试信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsExamsByExamids(Long[] examids)
    {
        return yspsExamsMapper.deleteYspsExamsByExamids(examids);
    }

    /**
     * 删除考试信息信息
     * 
     * @param examid 考试信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsExamsByExamid(Long examid)
    {
        return yspsExamsMapper.deleteYspsExamsByExamid(examid);
    }
}
