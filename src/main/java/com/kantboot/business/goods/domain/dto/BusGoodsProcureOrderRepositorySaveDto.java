package com.kantboot.business.goods.domain.dto;

import com.kantboot.business.goods.domain.entity.BusGoodsProcureOrder;
import com.kantboot.business.goods.domain.entity.BusGoodsProcureOrderIn;
import lombok.Data;

import java.util.List;

@Data
public class BusGoodsProcureOrderRepositorySaveDto {

    private BusGoodsProcureOrder order;

    private List<BusGoodsProcureOrderIn> inList;

}
