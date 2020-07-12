package gudu.csy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车封装类
 */
public class Cat implements Serializable{
    private static final long serialVersionUID = 1L;

    //购物车属性
    Map<Integer,CatItem> map = new LinkedHashMap<>();//购物项的集合，key为商品id，value为购物项，为了方便删除，采用map集合和有序LinkedHashMap

    // Cart对象中有一个叫cartItems属性.
    public Collection<CatItem> getCatItems(){
        return map.values();
    }

    private double total;//购物总计
    public double getTotal() {
        return total;
    }
    //购物车功能（方法）
    // 购物车的功能:
    // 1.将购物项添加到购物车
    public void addCat(CatItem catItem) {
        // 判断购物车中是否已经存在该购物项:
        /*
         *  * 如果存在:
         *  	* 数量增加
         *  	* 总计 = 总计 + 购物项小计
         *  * 如果不存在:
         *  	* 向map中添加购物项
         *  	* 总计 = 总计 + 购物项小计
         */
        // 获得商品id.
        Integer pid = catItem.getProduct().getPid();
        // 判断购物车中是否已经存在该购物项:
        if(map.containsKey(pid)){
            // 存在
            CatItem _catItem = map.get(pid);// 获得购物车中原来的购物项
            _catItem.setCount(_catItem.getCount()+catItem.getCount());
        }else{
            // 不存在
            map.put(pid, catItem);
        }
        // 设置总计的值
        total += catItem.getSubtotal();
    }

    // 2.从购物车移除购物项
    public void removeCat(Integer pid) {
        // 将购物项移除购物车:
        CatItem catItem = map.remove(pid);
        // 总计 = 总计 -移除的购物项小计:
        total -= catItem.getSubtotal();
    }

    // 3.清空购物车
    public void clearCat() {
        // 将所有购物项清空
        map.clear();
        // 将总计设置为0
        total = 0;
    }
}
