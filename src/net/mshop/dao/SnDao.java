package net.mshop.dao;


import net.mshop.entity.Sn;

/**
 * Dao - 序列号
 *
 */
public interface SnDao {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Sn.Type type);

}