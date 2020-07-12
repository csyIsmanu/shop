package gudu.csy.dao;

import gudu.csy.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao接口
 */
@Repository
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    /*@Select("select * from user")
    public List<User> findAll()throws Exception;*/


    /**
     * 根据用户昵称查询用户信息
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from user where username = #{username}")
    public User findByUsername(String username)throws Exception;


    /**
     * 保存用户信息
     * @throws Exception
     */
    @Insert("insert into user (username,password,name,email,phone,addr,state,code) values(#{username},#{password},#{name},#{email},#{phone},#{addr},#{state},#{code})")
    public void save(User user)throws Exception;

    /**
     * 根据用户激活码查询用户
     * @param code
     * @throws Exception
     */
    @Select("select * from user where code = #{code}")
    public User findByCode(String code)throws Exception;

    /**
     * 通过域控制器来管理域中的计算机
     * @param user
     * @throws Exception
     */
    @Update("update user set state=#{state},code=#{code} where username=#{username}")
    public void updateCode(User user)throws Exception;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from user where username = #{username}")
    public User login(String username)throws Exception;

    @Select("select * from user where uid = #{uid}")
    public User findByUid(int uid)throws Exception;

    @Select("select count(*) from user")
    public int findCount()throws Exception;

    @Select("select * from user limit #{arg0},#{arg1}")
    public List<User> findByPage(int begin, int limit)throws Exception;




    @Update("update user set username=#{username},password=#{password},name=#{name},email=#{email},addr=#{addr},phone=#{phone} where uid=#{uid}")
    public void update(User user)throws Exception;

    @Delete("delete  from user where uid = #{uid}")
    public void delete(int uid)throws Exception;
}
