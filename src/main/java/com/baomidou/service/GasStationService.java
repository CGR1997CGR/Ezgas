package com.baomidou.service;

import com.baomidou.entity.GasStation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-10
 */
public interface GasStationService extends IService<GasStation> {

    List<GasStation> listRstation(Double Radis, Double latitude, Double longitude);

    void updateStation();
}
