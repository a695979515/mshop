package net.mshop.service.impl;

import net.mshop.dao.MemberRankDao;
import net.mshop.entity.MemberRank;
import net.mshop.service.MemberRankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Panfuhao on 2016/9/26.
 */
@Service("memberRankServiceImpl")
public class MemberRankServiceImpl extends BaseServiceImpl<MemberRank, Long> implements MemberRankService {
    @Resource(name = "memberRankDaoImpl")
    private MemberRankDao memberRankDao;

    /**
     * 判断名称是否存在
     *
     * @param name 名称(忽略大小写)
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean nameExists(String name) {
        return memberRankDao.nameExists(name);
    }

    /**
     * 判断消费金额是否存在
     *
     * @param amount 消费金额
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean amountExists(BigDecimal amount) {
        return memberRankDao.amountExists(amount);
    }
}
