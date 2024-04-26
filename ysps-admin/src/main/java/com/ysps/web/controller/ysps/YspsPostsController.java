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
import com.ysps.ysps.domain.YspsPosts;
import com.ysps.ysps.service.IYspsPostsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 论坛Controller
 * 
 * @author lins
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/ysps/posts")
public class YspsPostsController extends BaseController
{
    @Autowired
    private IYspsPostsService yspsPostsService;

    /**
     * 查询论坛列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:posts:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsPosts yspsPosts)
    {
        startPage();
        List<YspsPosts> list = yspsPostsService.selectYspsPostsList(yspsPosts);
        return getDataTable(list);
    }

    /**
     * 导出论坛列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:posts:export')")
    @Log(title = "论坛", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsPosts yspsPosts)
    {
        List<YspsPosts> list = yspsPostsService.selectYspsPostsList(yspsPosts);
        ExcelUtil<YspsPosts> util = new ExcelUtil<YspsPosts>(YspsPosts.class);
        util.exportExcel(response, list, "论坛数据");
    }

    /**
     * 获取论坛详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:posts:query')")
    @GetMapping(value = "/{postid}")
    public AjaxResult getInfo(@PathVariable("postid") Long postid)
    {
        return success(yspsPostsService.selectYspsPostsByPostid(postid));
    }

    /**
     * 新增论坛
     */
    @PreAuthorize("@ss.hasPermi('ysps:posts:add')")
    @Log(title = "论坛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsPosts yspsPosts)
    {
        return toAjax(yspsPostsService.insertYspsPosts(yspsPosts));
    }

    /**
     * 修改论坛
     *//*
    @PreAuthorize("@ss.hasPermi('ysps:posts:edit')")
    @Log(title = "论坛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsPosts yspsPosts)
    {
        return toAjax(yspsPostsService.updateYspsPosts(yspsPosts));
    }*/
    @PreAuthorize("@ss.hasPermi('ysps:posts:edit')")
    @Log(title = "论坛", businessType = BusinessType.UPDATE)
    @PutMapping("/{postid}")
    public AjaxResult edit(@PathVariable Long postid, @RequestBody YspsPosts yspsPosts)
    {
        yspsPosts.setPostid(postid); // 确保帖子ID被正确设置
        return toAjax(yspsPostsService.updateYspsPosts(yspsPosts));
    }

    /**
     * 删除论坛
     */
    @PreAuthorize("@ss.hasPermi('ysps:posts:remove')")
    @Log(title = "论坛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postids}")
    public AjaxResult remove(@PathVariable Long[] postids)
    {
        return toAjax(yspsPostsService.deleteYspsPostsByPostids(postids));
    }
}
