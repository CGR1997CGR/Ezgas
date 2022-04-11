package com.baomidou.service.impl;

import com.baomidou.entity.GasStation;
import com.baomidou.entity.Report;
import com.baomidou.mapper.GasStationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.service.GasStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-10
 */
@Service
public class GasStationServiceImpl extends ServiceImpl<GasStationMapper, GasStation> implements GasStationService {
    @Autowired
    ReportService reportService;
    @Autowired
    GasStationService gasStationService;
    @Override
    public void updateStation() {
        List<Report> reports = reportService.list(null);
        reports.stream().map(report -> {GasStation gasstation = new GasStation();
            QueryWrapper<GasStation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("reportid",report.getId());
            queryWrapper.eq("gas_station_name",report.getGasStationName());
            if(gasStationService.count(queryWrapper) > 0){
                gasStationService.remove(queryWrapper);
            }
            gasstation.setGasStationName(report.getGasStationName());
            gasstation.setCleannesslevel(report.getCleannessLevel());
            gasstation.setReportid(report.getId());
            gasstation.setOpeningTime("9:00");
            gasstation.setClosingTime("17:00");
            gasstation.setWaitingtime(report.getWaitingTime());
            gasstation.setPriceOfGas(report.getPriceOfGas());
            gasstation.setPriceOfLpg(report.getPriceOfLpg());
            gasstation.setPriceOfDiesel(report.getPriceOfDiesel());
            gasstation.setPriceOfMethan(report.getPriceOfMethan());
            gasstation.setLatitude(report.getLatitude());
            gasstation.setLongitude(report.getLongitude());
            gasStationService.saveOrUpdate(gasstation);
            return gasstation;
        }).collect(Collectors.toList());
    }


    @Override
    public List<GasStation> listRstation(Double Radis, Double latitude, Double longitude) {
        List<GasStation> gasStations = gasStationService.list(null);
        List<GasStation> gasStations1 = gasStations.stream().filter(x->isDistanceR(Radis,latitude,longitude,x.getLatitude(),x.getLongitude())).collect(Collectors.toList());
        return gasStations1;
    }
    boolean isDistanceR(Double Radis, Double latpos, Double lopos, Double latm, Double lom){
        Double res = Math.pow((latpos-latm),2)+Math.pow((lopos-lom),2);
        Double dis = Math.sqrt(res);
        if(dis <= Radis){
            return true;
        }else {
            return false;
        }
    }

}
