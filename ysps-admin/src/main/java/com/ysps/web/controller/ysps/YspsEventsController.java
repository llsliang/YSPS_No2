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
import com.ysps.ysps.domain.YspsEvents;
import com.ysps.ysps.service.IYspsEventsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 活动信息Controller
 * 
 * @author lins
 * @date 2024-04-04
 */
@RestController
@RequestMapping("/ysps/events")
public class YspsEventsController extends BaseController
{
    @Autowired
    private IYspsEventsService yspsEventsService;

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsEvents yspsEvents)
    {
        startPage();
        List<YspsEvents> list = yspsEventsService.selectYspsEventsList(yspsEvents);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsEvents yspsEvents)
    {
        List<YspsEvents> list = yspsEventsService.selectYspsEventsList(yspsEvents);
        ExcelUtil<YspsEvents> util = new ExcelUtil<YspsEvents>(YspsEvents.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:query')")
    @GetMapping(value = "/{eventid}")
    public AjaxResult getInfo(@PathVariable("eventid") Long eventid)
    {
        return success(yspsEventsService.selectYspsEventsByEventid(eventid));
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsEvents yspsEvents)
    {
        return toAjax(yspsEventsService.insertYspsEvents(yspsEvents));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsEvents yspsEvents)
    {
        return toAjax(yspsEventsService.updateYspsEvents(yspsEvents));
    }

    /**
     * 删除活动信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:events:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eventids}")
    public AjaxResult remove(@PathVariable Long[] eventids)
    {
        return toAjax(yspsEventsService.deleteYspsEventsByEventids(eventids));
    }
}
