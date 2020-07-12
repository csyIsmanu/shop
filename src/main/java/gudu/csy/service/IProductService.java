package gudu.csy.service;

import gudu.csy.model.Product;
import gudu.csy.utils.PageBean;

import java.util.List;

public interface IProductService {
    /**
     * 查询最热们商品（10个）
     * @param is_hot
     * @return
     * @throws Exception
     */
    public List<Product> findByIsHot(int is_hot)throws Exception;

    /**
     * 查询最新商品10个
     * @return
     * @throws Exception
     */
    public List<Product> findNew()throws Exception;


    /**
     * 根据商品id查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    public Product findByPid(int pid)throws Exception;

    /**
     * 根据一级分类进行分页查询
     * @param cid
     * @param page
     * @return
     * @throws Exception
     */
    public PageBean<Product> findByPageCid(int cid, int page)throws Exception;

    /**
     * 根据二级分类进行分页查询
     * @param csid
     * @param page
     * @return
     * @throws Exception
     */
    public PageBean<Product> findByPageCsid(int csid, int page)throws Exception;

    public PageBean<Product> findByPage(int page)throws Exception;

    public void save(Product product)throws Exception;

    public void delete(int pid)throws Exception;

    public void update(Product product)throws Exception;
}
