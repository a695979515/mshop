package net.mshop.dao.impl;

import net.mshop.dao.MemberRankDao;
import net.mshop.entity.MemberRank;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by Panfuhao on 2016/9/26.
 */
@Repository("memberRankDaoImpl")
public class MemberRankDaoImpl extends BaseDaoImpl<MemberRank, Long> implements MemberRankDao {
    @Override
    public boolean nameExists(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        String jpql = "select count(*) from MemberRank menberRank where lower(memberRank.name) = lower(:name)";
        Long count = entityManager.createQuery(jpql, Long.class).setParameter("name", name).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean amountExists(BigDecimal amount) {
        if (amount == null) {
            return false;
        }
        String jpql = "select count(*) from MemberRank memberRank where memberRank.amount = :amount";
        Long count = entityManager.createQuery(jpql, Long.class).setParameter("amount", amount).getSingleResult();
        return count > 0;
    }
}
