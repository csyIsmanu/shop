package gudu.csy.service;

import gudu.csy.model.User;
import gudu.csy.utils.PageBean;

import java.util.List;

/**
 * 用户service接口
 */
public interface IUserService {

    /**
     * 保存用户信息
     * @throws Exception
     */
    public void save(User user)throws Exception;

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    public User login(User user)throws Exception;

    /**
     * 根据昵称查询用户
     * @param username
     * @return
     * @throws Exception
     */
    public User findByUsername(String username)throws Exception;


    /**
     * 根据激活码查询用户
     * @param code
     * @return
     * @throws Exception
     */
    public boolean findByCode(String code)throws Exception;

    /**
     * 更改用户状态
     * @param user
     * @throws Exception
     */
    public void updateCode(User user)throws Exception;

    public User findByUid(int uid)throws Exception;

    public PageBean<User> findPage(int page)throws Exception;


    public void update(User user)throws Exception;

    public void delete(int uid)throws Exception;
}
