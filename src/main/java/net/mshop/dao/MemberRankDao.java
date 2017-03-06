package net.mshop.dao;

import net.mshop.entity.MemberRank;

import java.math.BigDecimal;

/**
 * Created by Panfuhao on 2016/9/26.
 */
public interface MemberRankDao extends BaseDao<MemberRank, Long> {
    /**
     * 判断名称是否存在
     *
     * @param name 名称(忽略大小写)
     * @return 名称是否存在
     */
    boolean nameExists(String name);

    /**
     * 判断消费金额是否存在
     *
     * @param amount 消费金额
     * @return 消费金额是否存在
     */
    boolean amountExists(BigDecimal amount);
}
