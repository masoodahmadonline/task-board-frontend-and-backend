/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Companies;
import web.entity.Users;
import web.service.common.ResultImpl;

/**
 *
 * @author syncsys
 */
public interface CompaniesService {
    ResultImpl save(Companies company);
    public ResultImpl getCompanyByName(String name);
}
