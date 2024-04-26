package com.ysps.web.controller.ysps;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ysps.common.annotation.Log;
import com.ysps.common.core.controller.BaseController;
import com.ysps.common.core.domain.AjaxResult;
import com.ysps.common.enums.BusinessType;
import com.ysps.ysps.domain.YspsExams;
import com.ysps.ysps.service.IYspsExamsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 考试信息Controller
 * 
 * @author lins
 * @date 2024-04-13
 */
@RestController
@RequestMapping("/ysps/exams")
public class YspsExamsController extends BaseController
{
    @Autowired
    private IYspsExamsService yspsExamsService;

    /**
     * 查询考试信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsExams yspsExams)
    {
        startPage();
        List<YspsExams> list = yspsExamsService.selectYspsExamsList(yspsExams);
        return getDataTable(list);
    }

    /**
     * 导出考试信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:export')")
    @Log(title = "考试信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsExams yspsExams)
    {
        List<YspsExams> list = yspsExamsService.selectYspsExamsList(yspsExams);
        ExcelUtil<YspsExams> util = new ExcelUtil<YspsExams>(YspsExams.class);
        util.exportExcel(response, list, "考试信息数据");
    }

    /**
     * 获取考试信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:query')")
    @GetMapping(value = "/{examid}")
    public AjaxResult getInfo(@PathVariable("examid") Long examid)
    {
        return success(yspsExamsService.selectYspsExamsByExamid(examid));
    }

    /**
     * 新增考试信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:add')")
    @Log(title = "考试信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsExams yspsExams)
    {
        return toAjax(yspsExamsService.insertYspsExams(yspsExams));
    }

    /**
     * 修改考试信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:edit')")
    @Log(title = "考试信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsExams yspsExams)
    {
        return toAjax(yspsExamsService.updateYspsExams(yspsExams));
    }

    /**
     * 删除考试信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:exams:remove')")
    @Log(title = "考试信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{examids}")
    public AjaxResult remove(@PathVariable Long[] examids)
    {
        return toAjax(yspsExamsService.deleteYspsExamsByExamids(examids));
    }
}
