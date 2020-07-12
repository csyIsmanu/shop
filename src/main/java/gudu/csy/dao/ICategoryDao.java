package gudu.csy.dao;

import gudu.csy.model.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDao {
    /**
     * 查询所有一级目录
     * @return
     * @throws Exception
     */
    @Select("select * from category")
    @Results({
            @Result(id=true,column="cid",property="cid"),
            @Result(column = "cname", property = "cname"),
            @Result(column = "cid", property = "categorySeconds",many = @Many(select="gudu.csy.dao.ICategorySecondDao.findByCid", fetchType= FetchType.LAZY))
    })
    public List<Category> findAll()throws Exception;

    @Insert("insert into category (cname) value(#{cname})")
    public void save(String cname)throws Exception;

    @Delete("delete from category where cid = #{cid}")
    public void delect(String cid)throws Exception;

    @Select("select * from category where cid = #{cid}")
    public Category findByCid(String cid)throws Exception;

    @Update("update category set cname=#{arg0} where cid=#{arg1}")
    public void update(String cname,String cid)throws Exception;
}
