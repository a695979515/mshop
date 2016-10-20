package net.mshop.service.impl;


import net.mshop.dao.MemberDao;
import net.mshop.entity.Member;
import net.mshop.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

    @Resource(name = "memberDaoImpl")
    private MemberDao memberDao;

    @Override
    @Transactional
    public Member save(Member member) {
        Assert.notNull(member);
        Member pMember = super.save(member);
        return pMember;
    }

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    public boolean usernameExists(String username) {
        return memberDao.usernameExists(username);
    }
}
