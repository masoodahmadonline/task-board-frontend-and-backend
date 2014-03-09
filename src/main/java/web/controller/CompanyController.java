/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.entity.Companies;
import web.entity.Users;
import web.service.CompaniesService;
import web.service.UsersService;
import web.service.common.Result;
import web.service.common.ResultImpl;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

/**
 *
 * @author syncsys
 */

@Controller
public class CompanyController {
    @Autowired
    private CompaniesService companyService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;

    //@PreAuthorize("@securityService.hasCompanyCreatePermission()")  /*Needs to add a new field in Users table if required functionality*/
    @PreAuthorize("@securityService.hasBoardCreatePermission()")
    @RequestMapping (value = "/companies/create")
    public String createCompany(ModelMap model){
        Companies company = new Companies();
        company.setName("Accu Reference");
        result = companyService.save(company);
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        return "/companies/create";
    }
   
}
