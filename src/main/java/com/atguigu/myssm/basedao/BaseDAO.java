package com.atguigu.myssm.basedao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 定义一个用来被继承的对数据库进行基本操作的Dao
 */
public abstract class BaseDAO<T> {

  private QueryRunner queryRunner = new QueryRunner();
  // 定义一个变量来接收泛型的类型
  private Class<T> type;

  // 获取 T 的 Class 对象，获取泛型的类型，泛型是在被子类继承时才确定
  public BaseDAO() {
    // 获取子类类型
    Class clazz = this.getClass();
    // 获取父类的类型
    // getGenericSuperclass() 用来获取当前类的父类的类型
    // ParameterizedType 表示的是带泛型的类型
    ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
    // 获取具体的泛型类型 getActualTypeArguments
    // 这个方法会返回一个 Type 的数组
    Type[] types = parameterizedType.getActualTypeArguments();
    // 获取具体的泛型的类型
    this.type = (Class<T>) types[0];
  }

  /**
   * 通用的增删改操作
   *
   * @param conn
   * @param sql
   * @param params
   * @return
   */
  public int update(Connection conn, String sql, Object... params) {
    int count = 0;
    try {
      count = queryRunner.update(conn, sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("Fail to update");
    }
    return count;
  }

  /**
   * 获取一个对象
   *
   * @param conn
   * @param sql
   * @param params
   * @return
   */
  public T getBean(Connection conn, String sql, Object... params) {
    T t = null;
    try {
      t = queryRunner.query(conn, sql, new BeanHandler<>(type), params);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("Fail to query bean");
    }
    return t;
  }

  /**
   * 获取所有对象
   *
   * @param conn
   * @param sql
   * @param params
   * @return
   */
  public List<T> getBeanList(Connection conn, String sql, Object... params) {
    List<T> list = null;
    try {
      list = queryRunner.query(conn, sql, new BeanListHandler<>(type), params);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("Fail to query bean list");
    }
    return list;
  }

  /**
   * 获取一个单一值的方法，专门用来执行像 select count(*) ... 这样的sql语句
   *
   * @param conn
   * @param sql
   * @param params
   * @return
   */
  public Object getValue(Connection conn, String sql, Object... params) {
    Object count = null;
    try {
      // 调用querRunner的query方法获取一个单一的值
      count = queryRunner.query(conn, sql, new ScalarHandler<>(), params);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("Fail to get value");
    }
    return count;
  }

}
