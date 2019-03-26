package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import cn.qyd.blogroom.common.utils.PagingUtil;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import cn.qyd.blogroom.user.dao.AttentionDao;
import cn.qyd.blogroom.user.dto.AttentionDto;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.AttentionService;
import cn.qyd.blogroom.user.service.UserService;
import cn.qyd.blogroom.user.vo.SimpleUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-26 下午1:12
 **/
@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    private AttentionDao attentionDao;

    @Autowired
    private UserService userService;

    @Override
    public Attention save(AttentionDto dto) {
        Attention attention = new Attention();
        attention.setUser1Id(dto.getUser1Id())
                .setUser2Id(dto.getUser2Id())
                .setAddTime(LocalDateTime.now());

        Attention result = attentionDao.save(attention);
        return result;
    }

    @Override
    public Attention selectOne(AttentionDto dto) {
        return attentionDao.findByUser1IdAndUser2Id(dto.getUser1Id(),dto.getUser2Id());
    }

    @Override
    public PagingInfo pageFans(AttentionQueryDto queryDto) {
        List<SimpleUserVo> attentionVos = fansAll(queryDto.getUserId());

        return PagingUtil.paging(queryDto.getPage(),queryDto.getLimit(),attentionVos);
    }

    @Override
    public PagingInfo pageFollowers(AttentionQueryDto queryDto) {
        List<SimpleUserVo> attentionVos = followersAll(queryDto.getUserId());

        return PagingUtil.paging(queryDto.getPage(),queryDto.getLimit(),attentionVos);
    }

    @Override
    public List<SimpleUserVo> followersAll(Long user1Id) {
        List<Long> ids = attentionDao.findIdsByUser1Id(user1Id);
        return transAttentions(ids);
    }

    @Override
    public List<SimpleUserVo> fansAll(Long user2Id) {
        List<Long> ids = attentionDao.findIdsByUser2Id(user2Id);
        return transAttentions(ids);
    }

    @Override
    public Long countFansOfUser(Long id) {
        return attentionDao.countByUser2Id(id);
    }


    @Override
    @Transactional
    public void delete(AttentionDto dto) {
        attentionDao.deleteByUser1IdAndUser2Id(dto.getUser1Id(),dto.getUser2Id());
    }

    //将关注记录转为某用户所有的
    public List<SimpleUserVo> transAttentions(List<Long> userIds) {
        List<SimpleUserVo> attentionVos = new ArrayList<>();
        for(int i = 0; i < userIds.size(); i++) {
            String userId= String.valueOf(userIds.get(i));
            User user = userService.findById(Long.valueOf(userId));
            attentionVos.add(BeanMapper.map(user,SimpleUserVo.class));
        }
        return attentionVos;
    }

//    @Override
//    public Page<Attention> query(AttentionQueryDto dto) {
//        Pageable pageable = PageRequest.of(dto.getPage()-1,dto.getLimit());
//        AttentionQueryParam param = new AttentionQueryParam(dto);
//        Page<Attention> attentionPage = attentionDao.findAll(param,pageable);
//        return attentionPage;
//    }

//    /**
//     * 查询条件
//     */
//    class AttentionQueryParam implements Specification {
//        AttentionQueryDto queryDto;
//        public AttentionQueryParam(AttentionQueryDto dto ){
//            this.queryDto=dto;
//        }
//
//        @Override
//        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
//            List<Predicate> list = new ArrayList<Predicate>();
//            if(queryDto.getUser1Id() != null){
//                list.add(criteriaBuilder.equal(root.get("user1Id").as(Long.class), queryDto.getUser1Id()));
//            }
//            if(queryDto.getUser2Id() != null){
//                list.add(criteriaBuilder.equal(root.get("user2Id").as(Long.class), queryDto.getUser2Id()));
//            }
//
//            Predicate[] p = new Predicate[list.size()];
//            criteriaQuery.where(list.toArray(p));
//            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("addTime").as(LocalDateTime.class)));
//            return criteriaQuery.getRestriction();
//        }
//    }
}
