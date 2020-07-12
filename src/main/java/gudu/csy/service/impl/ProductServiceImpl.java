package gudu.csy.service.impl;

import gudu.csy.dao.IProductDao;
import gudu.csy.model.Product;
import gudu.csy.service.IProductService;
import gudu.csy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 商品service类
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 查询最热们商品（10个）
     * @param is_hot
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findByIsHot(int is_hot) throws Exception {
        return productDao.findByIsHot(is_hot);
    }

    /**
     * 查询最新商品10个
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findNew() throws Exception {
        return productDao.findNew();
    }

    /**
     * 根据商品id查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public Product findByPid(int pid) throws Exception {
        return productDao.findByPid(pid);
    }

    /**
     * 根据一级分类进行分页查询
     * @param cid
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Product> findByPageCid(int cid, int page) throws Exception {
        PageBean<Product> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 8;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = productDao.findCountCid(cid);
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
        List<Product> list = productDao.findByPageCid(cid, begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据二级分类进行分页查询
     * @param csid
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Product> findByPageCsid(int csid, int page) throws Exception {
        PageBean<Product> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 8;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = productDao.findCountCsid(csid);
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
        List<Product> list = productDao.findByPageCsid(csid, begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public PageBean<Product> findByPage(int page) throws Exception {
        PageBean<Product> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = productDao.findCount();
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
        List<Product> list = productDao.findByPage( begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void delete(int pid) throws Exception {
        productDao.delete(pid);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }
}
