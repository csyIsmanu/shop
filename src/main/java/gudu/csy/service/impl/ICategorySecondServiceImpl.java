package gudu.csy.service.impl;

import gudu.csy.dao.ICategorySecondDao;
import gudu.csy.model.CategorySecond;
import gudu.csy.model.Product;
import gudu.csy.service.ICategorySecondService;
import gudu.csy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categorySecondService")
public class ICategorySecondServiceImpl implements ICategorySecondService{

    @Autowired
    private ICategorySecondDao categorySecondDao;
    @Override
    public PageBean<CategorySecond> findByPage(int page) throws Exception {
        PageBean<CategorySecond> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = categorySecondDao.findCount();
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
        List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(String csname,int cid) throws Exception {
        categorySecondDao.save(csname, cid);
    }

    @Override
    public void delete(int csid) throws Exception {
        categorySecondDao.delete(csid);
    }

    @Override
    public CategorySecond findByCsid(int csid) throws Exception {
        return categorySecondDao.findByCsid(csid);
    }

    @Override
    public void update(int csid,String csname, int cid) throws Exception {
        categorySecondDao.update( csid,csname,cid);
    }

    @Override
    public List<CategorySecond> findAll() throws Exception {
        return categorySecondDao.findAll();
    }

}
