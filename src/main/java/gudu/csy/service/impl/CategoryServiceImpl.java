package gudu.csy.service.impl;

import gudu.csy.dao.ICategoryDao;
import gudu.csy.model.Category;
import gudu.csy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;

    /**
     * 查询所有一级目录
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> findAll() throws Exception {
        return categoryDao.findAll();
    }

    @Override
    public void save(String cname) throws Exception {
        categoryDao.save(cname);
    }

    @Override
    public void delect(String cid) throws Exception {
        categoryDao.delect(cid);
    }

    @Override
    public void update(String cname,String cid) throws Exception {
        categoryDao.update(cname,cid);
    }

    @Override
    public Category findByCid(String cid) throws Exception {
        return categoryDao.findByCid(cid);
    }
}
