package gudu.csy.service.impl;

import gudu.csy.dao.IAdminDao;
import gudu.csy.model.Admin;
import gudu.csy.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class IAdminServiceImpl implements IAdminService{
    @Autowired
    private IAdminDao adminDao;

    @Override
    public Admin login(String username, String password)throws Exception {
        Admin admin = adminDao.findByUsername(username);
        return admin;
    }
}
