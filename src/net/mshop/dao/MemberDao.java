package net.mshop.dao;

import net.mshop.entity.Member;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public interface MemberDao extends BaseDao<Member, Long> {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);
}
