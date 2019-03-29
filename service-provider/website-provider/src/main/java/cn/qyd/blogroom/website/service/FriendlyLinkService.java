package cn.qyd.blogroom.website.service;

import cn.qyd.blogroom.website.dto.FriendlyLinkDto;
import cn.qyd.blogroom.website.entity.FriendlyLink;
import java.util.List;

public interface FriendlyLinkService {
    List<FriendlyLink> allLinks();

    FriendlyLink addOne(FriendlyLinkDto dto);

    Boolean updateOne(FriendlyLinkDto dto);

    Boolean delOne(Long id);

    Long countAll();
}
