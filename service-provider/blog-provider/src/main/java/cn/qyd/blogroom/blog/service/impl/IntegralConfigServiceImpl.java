package cn.qyd.blogroom.blog.service.impl;

import cn.qyd.blogroom.blog.dao.IntegralConfigDao;
import cn.qyd.blogroom.blog.entity.IntegralConfig;
import cn.qyd.blogroom.blog.service.IntegralCongfigService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author qyd
 * @Date 19-2-27 上午11:35
 **/
@Service
public class IntegralConfigServiceImpl implements IntegralCongfigService {
    @Autowired
    private IntegralConfigDao integralConfigDao;
    @Override
    public IntegralConfig findById(Long id) {
        return integralConfigDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.INTEGRAL_CONFIG_ERROR));
    }

    @Override
    public Boolean update(IntegralConfig integralConfig) {
        return null;
    }
}