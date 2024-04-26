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
import com.ysps.ysps.domain.YspsRatings;
import com.ysps.ysps.service.IYspsRatingsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 活动评价Controller
 * 
 * @author lins
 * @date 2024-04-04
 */
@RestController
@RequestMapping("/ysps/ratings")
public class YspsRatingsController extends BaseController
{
    @Autowired
    private IYspsRatingsService yspsRatingsService;

    /**
     * 查询活动评价列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsRatings yspsRatings)
    {
        startPage();
        List<YspsRatings> list = yspsRatingsService.selectYspsRatingsList(yspsRatings);
        return getDataTable(list);
    }

    /**
     * 导出活动评价列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:export')")
    @Log(title = "活动评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsRatings yspsRatings)
    {
        List<YspsRatings> list = yspsRatingsService.selectYspsRatingsList(yspsRatings);
        ExcelUtil<YspsRatings> util = new ExcelUtil<YspsRatings>(YspsRatings.class);
        util.exportExcel(response, list, "活动评价数据");
    }

    /**
     * 获取活动评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:query')")
    @GetMapping(value = "/{ratingid}")
    public AjaxResult getInfo(@PathVariable("ratingid") Long ratingid)
    {
        return success(yspsRatingsService.selectYspsRatingsByRatingid(ratingid));
    }

    /**
     * 新增活动评价
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:add')")
    @Log(title = "活动评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsRatings yspsRatings)
    {
        return toAjax(yspsRatingsService.insertYspsRatings(yspsRatings));
    }

    /**
     * 修改活动评价
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:edit')")
    @Log(title = "活动评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsRatings yspsRatings)
    {
        return toAjax(yspsRatingsService.updateYspsRatings(yspsRatings));
    }

    /**
     * 删除活动评价
     */
    @PreAuthorize("@ss.hasPermi('ysps:ratings:remove')")
    @Log(title = "活动评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ratingids}")
    public AjaxResult remove(@PathVariable Long[] ratingids)
    {
        return toAjax(yspsRatingsService.deleteYspsRatingsByRatingids(ratingids));
    }
}
