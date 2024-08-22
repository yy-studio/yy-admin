package com.yystudio.base.module.support.datatracer.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystudio.base.module.support.datatracer.dao.DataTracerDao;
import com.yystudio.base.module.support.datatracer.domain.entity.DataTracerEntity;
import org.springframework.stereotype.Service;

/**
 * manager层
 *
 * @Author yy-studio
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class DataTracerManger extends ServiceImpl<DataTracerDao, DataTracerEntity> {
}
