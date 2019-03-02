package cn.qyd.blogroom.website.service.impl;

import cn.qyd.blogroom.website.dao.FriendlyLinkDao;
import cn.qyd.blogroom.website.entity.FriendlyLink;
import cn.qyd.blogroom.website.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:29
 **/
@Service
public class FriendlyLinkServicelImpl implements FriendlyLinkService {
    @Autowired
    private FriendlyLinkDao friendlyLinkDao;

    @Override
    public List<FriendlyLink> allLinks() {
        List<FriendlyLink> links = friendlyLinkDao.findAll();
        return links;
    }
}
