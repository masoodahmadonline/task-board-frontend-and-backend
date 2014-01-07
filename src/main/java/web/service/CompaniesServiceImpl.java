/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

/**
 *
 * @author syncsys
 */
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CompaniesDAO;
import web.dao.UsersDAO;
import web.entity.Companies;
import web.entity.Users;
import web.service.common.ResultImpl;

@Service
@Transactional(readOnly = true)
public class CompaniesServiceImpl implements CompaniesService{
    
    @Autowired
    private CompaniesDAO companiesDAO;
    @Autowired
    private ResultImpl result;
    
   
    
    @Transactional(readOnly = false)
    public ResultImpl save(Companies company){
        if( companiesDAO.doesCompanyExists(company.getName()) ){
          
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.companyNameAlreadyExists"/*,"string"*/));
           // setResult(false,null,Arrays.asList("error.emailAlreadyExists"/*,"string"*/) );
            return result;
        }else{
            Companies companyToBeReturned = companiesDAO.save(company);
            if(companyToBeReturned == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.companyCreationErrorUnknown"/*,"string"*/));
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(companyToBeReturned);
                result.setMessageList(Arrays.asList("success.companyCreated"/*,"string"*/));
                return result;
            }
           
            

//             
//         return new ResultImpl(true,userToBeReturned,Arrays.asList("success.userCreated"/*,"string"*/));
        }
       
    }
    
    @Transactional(readOnly = false)
    public ResultImpl getCompanyByName(String name){
        Companies company = companiesDAO.getCompanyByName(name);
        if (company != null){
            result.setIsSuccessful(true);
            result.setObject(company);
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.namedCompanyDoesNotExist"/*,"string"*/));
        }
        return result;
    }
}
