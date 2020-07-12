package gudu.csy.dao;

import gudu.csy.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品Dao
 */
@Repository
public interface IProductDao {

    /**
     * 查询热门商品10个
     * @return
     * @throws Exception
     */
    @Select("select * from product where is_hot = #{is_hot}  limit 0,10")
    List<Product> findByIsHot(int is_hot)throws Exception;

    /**
     *查询最新商品10个
     * @return
     * @throws Exception
     */
    @Select("select * from product  order by pdate desc limit 10")
    public List<Product> findNew()throws Exception;


    /**
     * 根据商品id查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Select("select * from product where pid = #{pid}")
    @Results({
            @Result(column = "csid",property = "categorySecond",one = @One(select = "gudu.csy.dao.ICategorySecondDao.findByCsid",fetchType = FetchType.LAZY))
    })
    public Product findByPid(int pid)throws Exception;

    /**
     * 根据一级分类的id查询商品个数
     * @param cid
     * @return
     * @throws Exception
     */
    @Select("select count(*) from product where csid in (select csid from categorysecond where cid = #{cid})")
    public int findCountCid(int cid)throws Exception;

    /**
     * 根据一级分类的id查询商品的集合
     * @param cid
     * @param begin
     * @param limit
     * @return
     * @throws Exception
     */
    @Select("select * from product where csid in (select csid from categorysecond where cid = #{arg0}) limit #{arg1},#{arg2}")
    public List<Product> findByPageCid(int cid, int begin, int limit)throws Exception;

    /**
     * 根据二级分类的id查询商品个数
     * @param csid
     * @return
     * @throws Exception
     */
    @Select("select count(*) from product where csid = #{csid}")
    public int findCountCsid(int csid)throws Exception;


    /**
     * 根据二级分类的id查询商品集合
     * @param csid
     * @return
     * @throws Exception
     */
    @Select("select * from product where csid = #{arg0} limit #{arg1},#{arg2}")
    public List<Product> findByPageCsid(int csid, int begin, int limit)throws Exception;

    @Select("select count(*) from product")
    public int findCount()throws Exception;

    @Select("select * from product order by pdate desc  limit #{arg0},#{arg1}")
    public List<Product> findByPage(int begin, int limit)throws Exception;

    @Insert("insert into product(pname,market_price,shop_price,image,pdesc,is_hot,pdate,csid) value(#{pname},#{market_price},#{shop_price},#{image},#{pdesc},#{is_hot},#{pdate},#{categorySecond.csid})")
    public void save(Product product)throws Exception;

    @Delete("delete from product where pid = #{arg0}")
    public void delete(int pid)throws Exception;

    @Update("update product set pname=#{pname},market_price=#{market_price},shop_price=#{shop_price},image=#{image},pdesc=#{pdesc},is_hot=#{is_hot},pdate=#{pdate},csid=#{categorySecond.csid} where pid=#{pid}")
    public void update(Product product)throws Exception;
}
