package cn.qyd.blogroom.user.service.impl;

import cn.qyd.blogroom.user.dao.UserDao;
import cn.qyd.blogroom.user.entity.User;
import cn.qyd.blogroom.user.service.UserService;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-18 下午5:51
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertOne(User user) {
        return 0;
    }

    @Override
    public List<User> selectAll() {
        List<User> all = userDao.findAll();
        if(all.size() ==0 || all == null) {
            return ListUtils.EMPTY_LIST;
        }

        return all;
    }
}
