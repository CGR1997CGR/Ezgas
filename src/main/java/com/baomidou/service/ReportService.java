package com.baomidou.service;

import com.baomidou.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-09
 */
public interface ReportService extends IService<Report> {

    void uploadReport(Report report);
}
