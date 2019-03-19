package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.user.dao.AttentionDao;
import cn.qyd.blogroom.user.dto.AttentionDto;
import cn.qyd.blogroom.user.dto.AttentionQueryDto;
import cn.qyd.blogroom.user.entity.Attention;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.AttentionService;
import cn.qyd.blogroom.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        User user1 = userService.findById(dto.getUser1Id());
        User user2 = userService.findById(dto.getUser2Id());
        Attention attention = new Attention();
        attention.setUser1Id(dto.getUser1Id())
                .setUser1Image(user1.getHeadImage())
                .setUser1Name(user1.getNickName())
                .setUser2Id(dto.getUser2Id())
                .setUser2Image(user2.getHeadImage())
                .setUser2Name(user2.getNickName())
                .setAddTime(LocalDateTime.now());

        Attention result = attentionDao.save(attention);
        return result;
    }

    @Override
    public Page<Attention> query(AttentionQueryDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage()-1,dto.getLimit());
        AttentionQueryParam param = new AttentionQueryParam(dto);
        Page<Attention> attentionPage = attentionDao.findAll(param,pageable);
        return attentionPage;
    }

    @Override
    public Boolean delete(Long id) {
        attentionDao.deleteById(id);
        return true;
    }

    /**
     * 查询条件
     */
    class AttentionQueryParam implements Specification {
        AttentionQueryDto queryDto;
        public AttentionQueryParam(AttentionQueryDto dto ){
            this.queryDto=dto;
        }

        @Override
        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if(queryDto.getUser1Id() != null){
                list.add(criteriaBuilder.equal(root.get("user1Id").as(Long.class), queryDto.getUser1Id()));
            }
            if(queryDto.getUser2Id() != null){
                list.add(criteriaBuilder.equal(root.get("user2Id").as(Long.class), queryDto.getUser2Id()));
            }

            Predicate[] p = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(p));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("addTime").as(LocalDateTime.class)));
            return criteriaQuery.getRestriction();
        }
    }
}
