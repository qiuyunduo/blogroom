package cn.qyd.blogroom.website.service.impl;

import cn.qyd.blogroom.website.dao.FriendlyLinkDao;
import cn.qyd.blogroom.website.dto.FriendlyLinkDto;
import cn.qyd.blogroom.website.entity.FriendlyLink;
import cn.qyd.blogroom.website.service.FriendlyLinkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public FriendlyLink addOne(FriendlyLinkDto dto) {
        FriendlyLink friendlyLink = new FriendlyLink();
        friendlyLink.setUrl(dto.getUrl())
                .setContent(dto.getContent())
                .setAddTime(LocalDateTime.now());
        friendlyLink.setUpdateTime(LocalDateTime.now());

        FriendlyLink result = friendlyLinkDao.save(friendlyLink);
        return result;
    }

    @Override
    public Boolean updateOne(FriendlyLinkDto dto) {
        FriendlyLink friendlyLink = friendlyLinkDao.findById(dto.getId()).get();
        if(StringUtils.isNotBlank(dto.getUrl())){
            friendlyLink.setUrl(dto.getUrl());
        }

        if(StringUtils.isNotBlank(dto.getContent())){
            friendlyLink.setContent(dto.getContent());
        }

        friendlyLinkDao.save(friendlyLink);
        return true;
    }

    @Override
    public Boolean delOne(Long id) {
        friendlyLinkDao.deleteById(id);
        return true;
    }

    @Override
    public Long countAll() {
        return friendlyLinkDao.count();
    }
}
