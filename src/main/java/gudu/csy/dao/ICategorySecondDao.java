package gudu.csy.dao;

import gudu.csy.model.Category;
import gudu.csy.model.CategorySecond;
import gudu.csy.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategorySecondDao {

    @Select("select * from categorysecond where cid = #{cid}")
    public List<CategorySecond> findByCid(String cid)throws Exception;

    @Select("select count(*) from categorysecond")
    public int findCount()throws Exception;

    @Select("select * from categorysecond limit #{arg0},#{arg1}")
    public List<CategorySecond> findByPage(int begin, int limit)throws Exception;

    @Insert("insert into categorysecond (csname,cid) value(#{arg0},#{arg1})")
    public void save(String csname,int cid)throws Exception;

    @Delete("delete  from categorysecond where csid = #{arg0}")
    public void delete(int csid)throws Exception;

    @Select("select * from categorysecond where csid = #{arg0}")
    @Results({
            @Result(id = true,column = "csid",property = "csid"),
            @Result(column = "csname",property = "csname"),
            @Result(column = "cid",property = "category",one = @One(select = "gudu.csy.dao.ICategoryDao.findByCid",fetchType = FetchType.LAZY))
    })
    public CategorySecond findByCsid(int csid)throws Exception;

    @Update("update categorysecond set csname=#{arg1},cid=#{arg2} where csid=#{arg0}")
    public void update(int csid,String csname, int cid)throws Exception;

    @Select("select * from categorysecond")
    public List<CategorySecond> findAll()throws Exception;
}
