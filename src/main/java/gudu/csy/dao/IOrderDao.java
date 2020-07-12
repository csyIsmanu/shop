package gudu.csy.dao;

import gudu.csy.model.Order;
import gudu.csy.model.User;
import gudu.csy.utils.PageBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单模块：持久层
 */
@Repository
public interface IOrderDao {
    /**
     * 保存订单
     * @param order
     * @throws Exception
     */
    @Insert("insert into orders (oid,total,ordertime,state,name,phone,addr,uid) values(#{oid},#{total},#{ordertime},#{user.state},#{user.name},#{user.phone},#{user.addr},#{user.uid})")
    public void save(Order order)throws Exception;

    /**
     * 根据用户的id查询订单总数
     * @param uid
     * @return
     * @throws Exception
     */
    @Select("select count(*) from orders where uid = #{uid}")
    public int findCountUid(int uid)throws Exception;


    /**
     * 根据用户的id查询
     * @param uid
     * @param begin
     * @param limit
     * @return
     */
    @Select("select * from orders where uid = #{arg0} order by ordertime desc limit #{arg1},#{arg2}")
    List<Order> findByPageUid(int uid, int begin, int limit);

    @Select("select * from orders where oid = #{oid}")
    @Results({
            @Result(id=true,column="oid",property="oid"),
            @Result(column = "total", property = "total"),
            @Result(column = "ordertime", property = "ordertime"),
            @Result(column = "state", property = "state"),
            @Result(column = "name", property = "name"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "addr", property = "addr"),
            @Result(column = "uid", property = "user",one = @One(select="gudu.csy.dao.IUserDao.findByUid", fetchType= FetchType.LAZY)),
            @Result(column = "oid", property = "orderItems",many = @Many(select="gudu.csy.dao.IOrderItemDao.findByOid", fetchType= FetchType.LAZY))
    })
    public Order findByOid(int oid)throws Exception;

    @Update("update  orders set state=#{state},name=#{name},phone=#{phone},addr=#{addr} where oid = #{oid}")
    public void update(Order order)throws Exception;

    @Select("select * from orders where uid = #{uid} order by oid desc")
    List<Order> findByUid(int uid);

    @Select("select count(*) from orders")
    public int findCount()throws Exception;

    @Select("select * from orders order by ordertime desc  limit #{arg0},#{arg1}")
    public List<Order> findByPage(int begin, int limit)throws Exception;
}
