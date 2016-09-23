package net.mshop.service.impl;

import net.mshop.entity.BaseEntity;
import net.mshop.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Transactional
public abstract class MemberServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T ,ID>{

}
