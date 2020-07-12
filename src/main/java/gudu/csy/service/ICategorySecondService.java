package gudu.csy.service;

import gudu.csy.model.CategorySecond;
import gudu.csy.model.Product;
import gudu.csy.utils.PageBean;

import java.util.List;

public interface ICategorySecondService{
    public PageBean<CategorySecond> findByPage(int page)throws Exception;

    public void save(String cansme,int cid)throws Exception;

    public void delete(int csid)throws Exception;

    public CategorySecond findByCsid(int csid)throws Exception;

    public void update(int csid,String csname, int cid)throws Exception;

    public List<CategorySecond> findAll()throws Exception;
}
