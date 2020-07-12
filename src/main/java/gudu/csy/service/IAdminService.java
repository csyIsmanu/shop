package gudu.csy.service;

import gudu.csy.model.Admin;

public interface IAdminService {
    public Admin login(String username, String password)throws Exception;
}
