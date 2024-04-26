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
import com.ysps.ysps.domain.YspsCourses;
import com.ysps.ysps.service.IYspsCoursesService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 包含课程信息，如课程名称、教师、时间和地点Controller
 * 
 * @author lins
 * @date 2024-04-12
 */
@RestController
@RequestMapping("/ysps/courses")
public class YspsCoursesController extends BaseController
{
    @Autowired
    private IYspsCoursesService yspsCoursesService;

    /**
     * 查询包含课程信息，如课程名称、教师、时间和地点列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsCourses yspsCourses)
    {
        startPage();
        List<YspsCourses> list = yspsCoursesService.selectYspsCoursesList(yspsCourses);
        return getDataTable(list);
    }

    /**
     * 导出包含课程信息，如课程名称、教师、时间和地点列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:export')")
    @Log(title = "包含课程信息，如课程名称、教师、时间和地点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsCourses yspsCourses)
    {
        List<YspsCourses> list = yspsCoursesService.selectYspsCoursesList(yspsCourses);
        ExcelUtil<YspsCourses> util = new ExcelUtil<YspsCourses>(YspsCourses.class);
        util.exportExcel(response, list, "包含课程信息，如课程名称、教师、时间和地点数据");
    }

    /**
     * 获取包含课程信息，如课程名称、教师、时间和地点详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:query')")
    @GetMapping(value = "/{courseid}")
    public AjaxResult getInfo(@PathVariable("courseid") Long courseid)
    {
        return success(yspsCoursesService.selectYspsCoursesByCourseid(courseid));
    }

    /**
     * 新增包含课程信息，如课程名称、教师、时间和地点
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:add')")
    @Log(title = "包含课程信息，如课程名称、教师、时间和地点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsCourses yspsCourses)
    {
        return toAjax(yspsCoursesService.insertYspsCourses(yspsCourses));
    }

    /**
     * 修改包含课程信息，如课程名称、教师、时间和地点
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:edit')")
    @Log(title = "包含课程信息，如课程名称、教师、时间和地点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsCourses yspsCourses)
    {
        return toAjax(yspsCoursesService.updateYspsCourses(yspsCourses));
    }

    /**
     * 删除包含课程信息，如课程名称、教师、时间和地点
     */
    @PreAuthorize("@ss.hasPermi('ysps:courses:remove')")
    @Log(title = "包含课程信息，如课程名称、教师、时间和地点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseids}")
    public AjaxResult remove(@PathVariable Long[] courseids)
    {
        return toAjax(yspsCoursesService.deleteYspsCoursesByCourseids(courseids));
    }
}
