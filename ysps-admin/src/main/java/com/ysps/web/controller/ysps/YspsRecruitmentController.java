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
import com.ysps.ysps.domain.YspsRecruitment;
import com.ysps.ysps.service.IYspsRecruitmentService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 招聘信息Controller
 * 
 * @author lins
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/ysps/recruitment")
public class YspsRecruitmentController extends BaseController
{
    @Autowired
    private IYspsRecruitmentService yspsRecruitmentService;

    /**
     * 查询招聘信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsRecruitment yspsRecruitment)
    {
        startPage();
        List<YspsRecruitment> list = yspsRecruitmentService.selectYspsRecruitmentList(yspsRecruitment);
        return getDataTable(list);
    }

    /**
     * 导出招聘信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:export')")
    @Log(title = "招聘信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsRecruitment yspsRecruitment)
    {
        List<YspsRecruitment> list = yspsRecruitmentService.selectYspsRecruitmentList(yspsRecruitment);
        ExcelUtil<YspsRecruitment> util = new ExcelUtil<YspsRecruitment>(YspsRecruitment.class);
        util.exportExcel(response, list, "招聘信息数据");
    }

    /**
     * 获取招聘信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(yspsRecruitmentService.selectYspsRecruitmentByJobId(jobId));
    }

    /**
     * 新增招聘信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:add')")
    @Log(title = "招聘信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsRecruitment yspsRecruitment)
    {
        return toAjax(yspsRecruitmentService.insertYspsRecruitment(yspsRecruitment));
    }

    /**
     * 修改招聘信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:edit')")
    @Log(title = "招聘信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsRecruitment yspsRecruitment)
    {
        return toAjax(yspsRecruitmentService.updateYspsRecruitment(yspsRecruitment));
    }

    /**
     * 删除招聘信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:recruitment:remove')")
    @Log(title = "招聘信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(yspsRecruitmentService.deleteYspsRecruitmentByJobIds(jobIds));
    }
}
