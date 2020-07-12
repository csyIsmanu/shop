package gudu.csy.dao;

import gudu.csy.model.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IOrderItemDao {

    @Insert("insert into orderItem(count,subtotal,pid,oid) value(#{count},#{subtotal},#{product.pid},#{order.oid})")
    public void save(OrderItem orderItem)throws Exception;

    @Select("select * from orderitem where oid = #{oid}")
    @Results({
            @Result(id=true,column="itemid",property="itemid"),
            @Result(column = "count", property = "count"),
            @Result(column = "subtotal", property = "subtotal"),
            @Result(column = "pid", property = "product",one = @One(select="gudu.csy.dao.IProductDao.findByPid", fetchType= FetchType.LAZY)),
            @Result(column = "oid", property = "order.oid")
    })
    public Set<OrderItem> findByOid(int oid)throws Exception;

    @Select("select * from orderitem where oid = #{oid}")
    @Results({
            @Result(id=true,column="itemid",property="itemid"),
            @Result(column = "count", property = "count"),
            @Result(column = "subtotal", property = "subtotal"),
            @Result(column = "pid", property = "product",one = @One(select="gudu.csy.dao.IProductDao.findByPid", fetchType= FetchType.LAZY)),
            @Result(column = "oid", property = "order.oid", many = @Many(select = "gudu.csy.dao.IOrderDao.findByOid",fetchType = FetchType.LAZY))
    })
    public List<OrderItem> findOrderItem(int oid)throws Exception;
}
