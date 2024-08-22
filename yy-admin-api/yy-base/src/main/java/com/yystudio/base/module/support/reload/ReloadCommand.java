package com.yystudio.base.module.support.reload;

import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.module.support.reload.core.AbstractSmartReloadCommand;
import com.yystudio.base.module.support.reload.core.domain.SmartReloadItem;
import com.yystudio.base.module.support.reload.core.domain.SmartReloadResult;
import com.yystudio.base.module.support.reload.dao.ReloadItemDao;
import com.yystudio.base.module.support.reload.dao.ReloadResultDao;
import com.yystudio.base.module.support.reload.domain.ReloadItemEntity;
import com.yystudio.base.module.support.reload.domain.ReloadResultEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * reload 操作
 *
 * @Author yy-studio
 * @Date 2015-03-02 19:11:52
 *
 * 
 * @Copyright  远游工作室
 */
@Component
public class ReloadCommand extends AbstractSmartReloadCommand {

    @Resource
    private ReloadItemDao reloadItemDao;

    @Resource
    private ReloadResultDao reloadResultDao;

    /**
     * 读取数据库中SmartReload项
     *
     * @return List<ReloadItem>
     */
    @Override
    public List<SmartReloadItem> readReloadItem() {
        List<ReloadItemEntity> reloadItemEntityList = reloadItemDao.selectList(null);
        return SmartBeanUtil.copyList(reloadItemEntityList, SmartReloadItem.class);
    }


    /**
     * 保存reload结果
     *
     * @param smartReloadResult
     */
    @Override
    public void handleReloadResult(SmartReloadResult smartReloadResult) {
        ReloadResultEntity reloadResultEntity = SmartBeanUtil.copy(smartReloadResult, ReloadResultEntity.class);
        reloadResultDao.insert(reloadResultEntity);
    }
}
