package gudu.csy.service;

import gudu.csy.model.Category;

import java.util.List;

public interface ICategoryService {
    /**
     * 查询所有一级目录
     * @return
     * @throws Exception
     */
    public List<Category> findAll()throws Exception;

    public void save(String cname)throws Exception;

    public void delect(String cid)throws Exception;

    public void update(String cname,String cid)throws Exception;

    public Category findByCid(String cid)throws Exception;
}
