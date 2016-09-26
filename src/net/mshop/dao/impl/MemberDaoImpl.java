package net.mshop.dao.impl;

import net.mshop.dao.MemberDao;
import net.mshop.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

    public boolean usernameExists(String username){
        if(StringUtils.isEmpty(username)){
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
        Long count = entityManager.createQuery(jpql,Long.class).setParameter("username",username).getSingleResult();
        return count > 0;
    }
}
