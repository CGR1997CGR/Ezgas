package com.baomidou.service.impl;

import com.baomidou.entity.Report;
import com.baomidou.mapper.ReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.service.GasStationService;
import com.baomidou.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-09
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    ReportService reportService;
    @Override
    public void uploadReport(Report report) {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",report.getUserid());
        queryWrapper.eq("gas_station_name",report.getGasStationName());
        if(reportService.count(queryWrapper) > 0){
            Report report1 = reportService.getOne(queryWrapper);
            reportService.removeById(report1.getId());
            report1.setUserid(report.getUserid());
            report1.setGasStationName(report.getGasStationName());
            report1.setPriceOfDiesel(report.getPriceOfDiesel());
            report1.setPriceOfGas(report.getPriceOfGas());
            report1.setPriceOfMethan(report.getPriceOfMethan());
            report1.setPriceOfLpg(report.getPriceOfLpg());
            report1.setCleannessLevel(report.getCleannessLevel());
            report1.setCourtesyLevel(report.getCourtesyLevel());
            report1.setWaitingTime(report.getWaitingTime());
            report1.setLatitude(report.getLatitude());
            report1.setLongitude(report.getLongitude());
            baseMapper.insert(report1);
        }else{
            baseMapper.insert(report);
        }
    }
}
