package gudu.csy.dao;

import gudu.csy.model.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDao {
    @Select("select * from adminuser where username = #{username}")
    public Admin findByUsername(String username)throws Exception;
}
