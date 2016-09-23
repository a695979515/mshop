package net.mshop.dao.impl;

import net.mshop.dao.MemberDao;
import net.mshop.entity.Member;
import org.springframework.stereotype.Repository;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {
}
