package com.yystudio.base.module.support.table;

import com.alibaba.fastjson.JSONArray;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.module.support.table.domain.TableColumnEntity;
import com.yystudio.base.module.support.table.domain.TableColumnUpdateForm;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 表格自定义列（前端用户自定义表格列，并保存到数据库里）
 *
 * @Author yy-studio
 * @Date 2022-08-12 22:52:21
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class TableColumnService {

    @Resource
    private TableColumnDao tableColumnDao;

    /**
     * 获取 - 表格列
     *
     * @return
     */
    public String getTableColumns(RequestUser requestUser, Integer tableId) {
        TableColumnEntity tableColumnEntity = tableColumnDao.selectByUserIdAndTableId(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        return tableColumnEntity == null ? null : tableColumnEntity.getColumns();
    }

    /**
     * 更新表格列
     *
     * @return
     */
    public ResponseDTO<String> updateTableColumns(RequestUser requestUser, TableColumnUpdateForm updateForm) {
        if (CollectionUtils.isEmpty(updateForm.getColumnList())) {
            return ResponseDTO.ok();
        }
        Integer tableId = updateForm.getTableId();
        TableColumnEntity tableColumnEntity = tableColumnDao.selectByUserIdAndTableId(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        if (tableColumnEntity == null) {
            tableColumnEntity = new TableColumnEntity();
            tableColumnEntity.setTableId(tableId);
            tableColumnEntity.setUserId(requestUser.getUserId());
            tableColumnEntity.setUserType(requestUser.getUserType().getValue());

            tableColumnEntity.setColumns(JSONArray.toJSONString(updateForm.getColumnList()));
            tableColumnDao.insert(tableColumnEntity);
        } else {
            tableColumnEntity.setColumns(JSONArray.toJSONString(updateForm.getColumnList()));
            tableColumnDao.updateById(tableColumnEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * 删除表格列
     *
     * @return
     */
    public ResponseDTO<String> deleteTableColumn(RequestUser requestUser, Integer tableId) {
        tableColumnDao.deleteTableColumn(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        return ResponseDTO.ok();
    }
}
