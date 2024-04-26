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
import com.ysps.ysps.domain.YspsReplies;
import com.ysps.ysps.service.IYspsRepliesService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 存储用户对帖子的回复，包括回复内容和时间Controller
 * 
 * @author lins
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/ysps/replies")
public class YspsRepliesController extends BaseController
{
    @Autowired
    private IYspsRepliesService yspsRepliesService;

    /**
     * 查询存储用户对帖子的回复，包括回复内容和时间列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsReplies yspsReplies)
    {
        startPage();
        List<YspsReplies> list = yspsRepliesService.selectYspsRepliesList(yspsReplies);
        return getDataTable(list);
    }

    /**
     * 导出存储用户对帖子的回复，包括回复内容和时间列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:export')")
    @Log(title = "存储用户对帖子的回复，包括回复内容和时间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsReplies yspsReplies)
    {
        List<YspsReplies> list = yspsRepliesService.selectYspsRepliesList(yspsReplies);
        ExcelUtil<YspsReplies> util = new ExcelUtil<YspsReplies>(YspsReplies.class);
        util.exportExcel(response, list, "存储用户对帖子的回复，包括回复内容和时间数据");
    }

    /**
     * 获取存储用户对帖子的回复，包括回复内容和时间详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:query')")
    @GetMapping(value = "/{replyid}")
    public AjaxResult getInfo(@PathVariable("replyid") Long replyid)
    {
        return success(yspsRepliesService.selectYspsRepliesByReplyid(replyid));
    }

    /**
     * 新增存储用户对帖子的回复，包括回复内容和时间
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:add')")
    @Log(title = "存储用户对帖子的回复，包括回复内容和时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsReplies yspsReplies)
    {
        return toAjax(yspsRepliesService.insertYspsReplies(yspsReplies));
    }

    /**
     * 修改存储用户对帖子的回复，包括回复内容和时间
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:edit')")
    @Log(title = "存储用户对帖子的回复，包括回复内容和时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsReplies yspsReplies)
    {
        return toAjax(yspsRepliesService.updateYspsReplies(yspsReplies));
    }

    /**
     * 删除存储用户对帖子的回复，包括回复内容和时间
     */
    @PreAuthorize("@ss.hasPermi('ysps:replies:remove')")
    @Log(title = "存储用户对帖子的回复，包括回复内容和时间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{replyids}")
    public AjaxResult remove(@PathVariable Long[] replyids)
    {
        return toAjax(yspsRepliesService.deleteYspsRepliesByReplyids(replyids));
    }
}
