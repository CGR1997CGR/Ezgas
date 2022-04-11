package com.baomidou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    private String userid;

    private String gasStationName;

    private Double priceOfDiesel;

    private Double priceOfGas;

    private Double priceOfMethan;

    private Double priceOfLpg;

    private Integer cleannessLevel;

    private Integer courtesyLevel;

    private String waitingTime;

    private Date createTime;

    private Double latitude;

    private Double longitude;


}
