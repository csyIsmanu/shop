package gudu.csy.service.impl;

import gudu.csy.dao.IUserDao;
import gudu.csy.model.User;
import gudu.csy.service.IUserService;
import gudu.csy.utils.MailUitls;
import gudu.csy.utils.PageBean;
import gudu.csy.utils.UUIDUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户service类
 */
@Service("userService")//定义为service类并交给spring容器处理
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;

/*    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();

    }*/

    @Override
    public void save(User user) throws Exception {
        user.setState(0);
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setCode(code);
        userDao.save(user);
        MailUitls.sendMail(user.getEmail(),code);
    }

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception{
        User user1=userDao.login(user.getUsername());
        if (user1==null){
            return null;
        }
        if(user1.getState()==1&&user1.getPassword().equals(user.getPassword())){
            return user1;
        }
       return null;
    }

    /**
     * 根据昵称查询用户
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public User findByUsername(String username) throws Exception {
        User user = userDao.findByUsername(username);
        if(user!=null){
            return user;
        }
        return null;
    }

    /**
     * 根据激活码查询用户
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public boolean findByCode(String code) throws Exception {
        User userExit = userDao.findByCode(code);
        if (userExit != null){
            userExit.setState(1);
            userExit.setCode(null);
            updateCode(userExit);
            return true;
        }
        return false;
    }

    /**
     * 更改用户状态
     * @param user
     * @throws Exception
     */
    @Override
    public void updateCode(User user) throws Exception {
        userDao.updateCode(user);
    }

    @Override
    public User findByUid(int uid) throws Exception {
        return userDao.findByUid(uid);
    }

    @Override
    public PageBean<User> findPage(int page) throws Exception {
        PageBean<User> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 2;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = userDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 每页显示的数据集合:
        // 从哪开始:
        int begin = (page - 1) * limit;
        List<User> list = userDao.findByPage(begin, limit);
        pageBean.setList(list);
        return pageBean;
    }



    @Override
    public void update(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public void delete(int uid) throws Exception {
        userDao.delete(uid);
    }

}
