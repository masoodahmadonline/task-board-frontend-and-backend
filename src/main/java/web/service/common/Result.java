/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 *
 * @author syncsys
 */
public interface Result {
    
    public boolean getIsSuccessful();

    
    public void setIsSuccessful(boolean isSuccessful);
    public Object getObject();
    public void setObject(Object object);
    public List getMessageList();
    public void setMessageList(List errorList);
    public void setMessageSource(MessageSource messageSource);
}
