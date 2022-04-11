package com.baomidou.controller;


import com.baomidou.commonutils.R;
import com.baomidou.entity.GasStation;
import com.baomidou.service.GasStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/gas-station")
public class GasStationController {
    @Autowired
    GasStationService gasStationService;
    //将report表格信息加载到gasStation
    //查询所有的加油站信息
    @GetMapping("/")
    public R updategasStation(){
        gasStationService.updateStation();
        return R.ok();
    }
    //查询所有的加油站信息
    @GetMapping("getAllgasStation")
    public R getAllgasStation(){
        List<GasStation> gasStations = gasStationService.list(null);
        return R.ok().data("result",gasStations);
    }
    @GetMapping("getRstation/{Radis}/{latitude}/{longitude}")
    public R getRStation(@PathVariable Double Radis,@PathVariable Double latitude,@PathVariable Double longitude){
        List<GasStation> gasRstations = gasStationService.listRstation(Radis,latitude,longitude);
        return R.ok().data("res",gasRstations);
    }

}

