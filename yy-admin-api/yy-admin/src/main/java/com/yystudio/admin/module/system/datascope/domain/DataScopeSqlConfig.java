package com.yystudio.admin.module.system.datascope.domain;

import lombok.Data;
import com.yystudio.admin.module.system.datascope.constant.DataScopeTypeEnum;
import com.yystudio.admin.module.system.datascope.constant.DataScopeWhereInTypeEnum;

/**
 * 数据范围
 *
 * @Author yy-studio
 * @Date 2020/11/28  20:59:17
 *
 * 
 * @Copyright  远游工作室
 */
@Data
public class DataScopeSqlConfig {

    /**
     * 数据范围类型
     * {@link DataScopeTypeEnum}
     */
    private DataScopeTypeEnum dataScopeType;

    /**
     * join sql 具体实现类
     */
    private Class joinSqlImplClazz;

    private String joinSql;

    private Integer whereIndex;

    private String paramName;

    /**
     * whereIn类型
     * {@link DataScopeWhereInTypeEnum}
     */
    private DataScopeWhereInTypeEnum dataScopeWhereInType;
}
