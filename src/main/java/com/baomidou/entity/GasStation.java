package com.baomidou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GasStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    private String reportid;

    private String gasStationName;

    @TableField("openingTime")
    private String openingTime;

    @TableField("closingTime")
    private String closingTime;

    private Integer cleannesslevel;

    private String waitingtime;

    private Double priceOfDiesel;

    private Double priceOfGas;

    private Double priceOfMethan;

    private Double priceOfLpg;

    private Double latitude;

    private Double longitude;


}
