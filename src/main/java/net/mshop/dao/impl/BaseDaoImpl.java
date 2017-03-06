package net.mshop.dao.impl;

import net.mshop.dao.BaseDao;
import net.mshop.entity.BaseEntity;
import net.mshop.entity.OrderEntity;
import net.mshop.operator.Filter;
import net.mshop.operator.Order;
import net.mshop.operator.Page;
import net.mshop.operator.Pageable;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;


import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Panfuhao on 2016/9/23.
 */
public abstract class BaseDaoImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseDao<T, ID> {
    /**
     * 属性分隔符
     */
    private static final String PROPERTY_SEPARATOR = ".";
    /**
     * 别名前缀
     */
    private static final String ALIAS_PREFIX = "mshopGeneratedAlias";
    /**
     * 别名数
     */
    private static volatile long aliasCount = 0L;
    /**
     * 实体类类型
     */
    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        entityClass = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
    }

    public T findById(ID id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(entityClass, id);
    }

    public T findById(ID id, LockModeType lockModeType) {
        if (id == null) {
            return null;
        }
        if (lockModeType != null) {
            return entityManager.find(entityClass, id, lockModeType);
        } else {
            return entityManager.find(entityClass, id);
        }
    }

    public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return findList(criteriaQuery, first, count, filters, orders);
    }

    public Page<T> findPage(Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return findPage(criteriaQuery, pageable);
    }


    public long count(Filter... filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return count(criteriaQuery, ArrayUtils.isNotEmpty(filters) ? Arrays.asList(filters) : null);
    }

    public void persist(T entity) {
        Assert.notNull(entity);

        entityManager.persist(entity);
    }



    public boolean isManaged(T entity) {
        Assert.notNull(entity);
        return entityManager.contains(entity);
    }

    public ID getIdentifier(T entity) {
        Assert.notNull(entity);
        return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public T merge(T entity) {
        Assert.notNull(entity);

        return entityManager.merge(entity);
    }

    public void remove(T entity) {
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public void refresh(T entity) {
        if (entity != null) {
            entityManager.refresh(entity);
        }
    }

    public void refresh(T entity, LockModeType lockModeType) {
        if (entity != null) {
            if (lockModeType != null) {
                entityManager.refresh(entity, lockModeType);
            } else {
                entityManager.refresh(entity);
            }
        }
    }

    public boolean isLoaded(T entity) {
        Assert.notNull(entity);

        return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(entity);
    }

    public boolean isLoaded(T entity, String attributeName) {
        Assert.notNull(entity);
        Assert.hasText(attributeName);

        return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(entity, attributeName);
    }


    public void detach(T entity) {
        if (entity != null) {
            entityManager.detach(entity);
        }
    }

    public LockModeType getLockMode(T entity) {
        Assert.notNull(entity);

        return entityManager.getLockMode(entity);
    }

    public void lock(T entity, LockModeType lockModeType) {
        if (entity != null && lockModeType != null) {
            entityManager.lock(entity, lockModeType);
        }
    }

    public void clear() {
        entityManager.clear();
    }

    public void flush() {
        entityManager.flush();
    }

    protected List<T> findList(CriteriaQuery<T> criteriaQuery, Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        Assert.notNull(criteriaQuery);
        Assert.notNull(criteriaQuery.getSelection());
        Assert.notEmpty(criteriaQuery.getRoots());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        Predicate predicate = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        predicate = criteriaBuilder.and(predicate, toPredicate(root, filters));
        criteriaQuery.where(predicate);

        List<javax.persistence.criteria.Order> orderList = new ArrayList<>();
        orderList.addAll(criteriaQuery.getOrderList());
        orderList.addAll(toOrders(root, orders));
        if (orderList.isEmpty()) {
            if (OrderEntity.class.isAssignableFrom(entityClass)) {
                orderList.add(criteriaBuilder.asc(getPath(root, OrderEntity.ORDER_PROPERTY_NAME)));
            } else {
                orderList.add(criteriaBuilder.desc(getPath(root, OrderEntity.CREATE_DATE_PROPERTY_NAME)));
            }
        }
        criteriaQuery.orderBy(orderList);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        if (first != null) {
            query.setFirstResult(first);
        }
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();

    }

    protected Page<T> findPage(CriteriaQuery<T> criteriaQuery, Pageable pageable) {
        Assert.notNull(criteriaQuery);
        Assert.notNull(criteriaQuery.getSelection());
        Assert.notEmpty(criteriaQuery.getRoots());
        if (pageable == null) {
            pageable = new Pageable();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        Predicate predicate = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        predicate = criteriaBuilder.and(predicate, toPredicate(root, pageable.getFilters()));
        String searchProperty = pageable.getSearchProperty();
        String searchValue = pageable.getSearchValue();
        if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)) {
            Path<String> searchPath = getPath(root, searchProperty);
            if (searchPath != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(searchPath, "%" + searchValue + "%"));
            }
        }
        criteriaQuery.where(predicate);
        List<javax.persistence.criteria.Order> orderList = new ArrayList<>();
        orderList.addAll(criteriaQuery.getOrderList());
        orderList.addAll(toOrders(root, pageable.getOrders()));
        String orderProperty = pageable.getOrderProperty();
        Order.Direction orderDirection = pageable.getOrderDirection();
        if (StringUtils.isNotEmpty(orderProperty) && orderDirection != null) {
            Path<?> orderPath = getPath(root, orderProperty);
            if (orderPath != null) {
                switch (orderDirection) {
                    case asc:
                        orderList.add(criteriaBuilder.asc(orderPath));
                        break;
                    case desc:
                        orderList.add(criteriaBuilder.desc(orderPath));
                        break;
                }
            }
        }
        if (orderList.isEmpty()) {
            if (OrderEntity.class.isAssignableFrom(entityClass)) {
                orderList.add(criteriaBuilder.asc(getPath(root, OrderEntity.ORDER_PROPERTY_NAME)));
            } else {
                orderList.add(criteriaBuilder.desc(getPath(root, OrderEntity.CREATE_DATE_PROPERTY_NAME)));
            }
        }
        criteriaQuery.orderBy(orderList);

        long total = count(criteriaQuery, null);
        int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
        if (totalPages < pageable.getPageNumber()) {
            pageable.setPageNumber(totalPages);
        }
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return new Page<>(query.getResultList(), total, pageable);
    }

    protected Long count(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
        Assert.notNull(criteriaQuery);
        Assert.notNull(criteriaQuery.getSelection());
        Assert.notEmpty(criteriaQuery.getRoots());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        Predicate predicate = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        predicate = criteriaBuilder.and(predicate, toPredicate(root, filters));
        criteriaQuery.where(predicate);
        CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        for (Root<?> r : criteriaQuery.getRoots()) {
            Root<?> dest = countCriteriaQuery.from(r.getJavaType());
            dest.alias(getAlias(r));
            copyJoins(r, dest);
        }
        Root<?> countRoot = getRoot(countCriteriaQuery, criteriaQuery.getResultType());
        if (criteriaQuery.isDistinct()) {
            countCriteriaQuery.select(criteriaBuilder.countDistinct(countRoot));
        } else {
            countCriteriaQuery.select(criteriaBuilder.count(countRoot));
        }
        if (criteriaQuery.getGroupList() != null) {
            countCriteriaQuery.groupBy(criteriaQuery.getGroupList());
        }
        if (criteriaQuery.getGroupRestriction() != null) {
            countCriteriaQuery.having(criteriaQuery.getGroupRestriction());
        }
        if (criteriaQuery.getRestriction() != null) {
            countCriteriaQuery.where(criteriaQuery.getRestriction());
        }
        return entityManager.createQuery(countCriteriaQuery).getSingleResult();
    }

    /**
     * 获取别名
     *
     * @param selection Selection
     * @return 别名
     */
    private synchronized String getAlias(Selection<?> selection) {
        if (selection == null) {
            return null;
        }
        String alias = selection.getAlias();
        if (alias == null) {
            if (aliasCount >= 1000) {
                aliasCount = 0;
            }
            alias = ALIAS_PREFIX + aliasCount++;
            selection.alias(alias);
        }
        return alias;
    }

    /**
     * 拷贝Join
     *
     * @param from 源
     * @param to   目标
     */
    private void copyJoins(From<?, ?> from, From<?, ?> to) {
        for (Join<?, ?> join : from.getJoins()) {
            Join<?, ?> toJoin = to.join(join.getAttribute().getName(), join.getJoinType());
            toJoin.alias(getAlias(join));
            copyJoins(join, toJoin);
        }
        for (Fetch<?, ?> fetch : from.getFetches()) {
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            copyFetches(fetch, toFetch);
        }
    }

    /**
     * 拷贝Fetch
     *
     * @param from 源
     * @param to   目标
     */
    private void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {
        for (Fetch<?, ?> fetch : from.getFetches()) {
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            copyFetches(fetch, toFetch);
        }
    }

    /**
     * 获取Root
     *
     * @param criteriaQuery 查询条件
     * @return Root
     */
    private Root<T> getRoot(CriteriaQuery<T> criteriaQuery) {
        if (criteriaQuery == null) {
            return null;
        }
        return getRoot(criteriaQuery, criteriaQuery.getResultType());
    }

    /**
     * 获取Root
     *
     * @param criteriaQuery 查询条件
     * @param clazz         类型
     * @return Root
     */
    private Root<T> getRoot(CriteriaQuery<?> criteriaQuery, Class<T> clazz) {
        if (criteriaQuery == null || CollectionUtils.isEmpty(criteriaQuery.getRoots()) || clazz == null) {
            return null;
        }
        for (Root<?> root : criteriaQuery.getRoots()) {
            if (clazz.equals(root.getJavaType())) {
                return (Root<T>) root.as(clazz);
            }
        }
        return null;
    }

    /**
     * 获取Path
     *
     * @param path         Path
     * @param propertyPath 属性路径
     * @return Path
     */
    @SuppressWarnings("unchecked")
    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
    }

    /**
     * 转换为Predicate
     *
     * @param root    Root
     * @param filters 筛选
     * @return Predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate toPredicate(Root<T> root, List<Filter> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate restrictions = criteriaBuilder.conjunction();
        if (root == null || CollectionUtils.isEmpty(filters)) {
            return restrictions;
        }
        for (Filter filter : filters) {
            if (filter == null) {
                continue;
            }
            String property = filter.getProperty();
            Filter.Operator operator = filter.getOperator();
            Object value = filter.getValue();
            Boolean ignoreCase = filter.getIgnoreCase();
            Path<?> path = getPath(root, property);
            if (path == null) {
                continue;
            }
            switch (operator) {
                case eq:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(path, value));
                        }
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, path.isNull());
                    }
                    break;
                case ne:
                    if (value != null) {
                        if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(path, value));
                        }
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, path.isNotNull());
                    }
                    break;
                case gt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.gt((Path<Number>) path, (Number) value));
                    }
                    break;
                case lt:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lt((Path<Number>) path, (Number) value));
                    }
                    break;
                case ge:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.ge((Path<Number>) path, (Number) value));
                    }
                    break;
                case le:
                    if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.le((Path<Number>) path, (Number) value));
                    }
                    break;
                case like:
                    if (String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                        if (BooleanUtils.isTrue(ignoreCase)) {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase()));
                        } else {
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like((Path<String>) path, (String) value));
                        }
                    }
                    break;
                case in:
                    restrictions = criteriaBuilder.and(restrictions, path.in(value));
                    break;
                case isNull:
                    restrictions = criteriaBuilder.and(restrictions, path.isNull());
                    break;
                case isNotNull:
                    restrictions = criteriaBuilder.and(restrictions, path.isNotNull());
                    break;
            }
        }
        return restrictions;
    }

    /**
     * 转换为Order
     *
     * @param root   Root
     * @param orders 排序
     * @return Order
     */
    private List<javax.persistence.criteria.Order> toOrders(Root<T> root, List<Order> orders) {
        List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>();
        if (root == null || CollectionUtils.isEmpty(orders)) {
            return orderList;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        for (Order order : orders) {
            if (order == null) {
                continue;
            }
            String property = order.getProperty();
            Order.Direction direction = order.getDirection();
            Path<?> path = getPath(root, property);
            if (path == null || direction == null) {
                continue;
            }
            switch (direction) {
                case asc:
                    orderList.add(criteriaBuilder.asc(path));
                    break;
                case desc:
                    orderList.add(criteriaBuilder.desc(path));
                    break;
            }
        }
        return orderList;
    }

}
