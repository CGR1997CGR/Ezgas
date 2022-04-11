package com.baomidou.controller;


import com.baomidou.commonutils.R;
import com.baomidou.entity.Report;
import com.baomidou.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    //获取所有的report信息
    @GetMapping("/getAllreport")
    public R getAllreport(){
        List<Report> reports = reportService.list(null);
        return R.ok().data("reports",reports);
    }
    //根据用户id查询report信息
    @GetMapping("/getUserreport/{id}")
    public R getUserreport(@PathVariable String id){
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",id);
        Report report = reportService.getOne(queryWrapper);
        return R.ok().data("report",report);
    }

    //根据用户id上传加油站信息
    @PostMapping("upReport")
    public R publishCourse(@RequestBody(required = false) Report report) {
       reportService.uploadReport(report);
       return R.ok();
    }
    //删除报告
    @DeleteMapping("deleteReport/{id}")
    public R deleteUser(@PathVariable String id){
        boolean removeById = reportService.removeById(id);
        if (removeById) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

