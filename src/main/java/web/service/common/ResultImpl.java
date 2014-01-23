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
import org.springframework.stereotype.Service;


/**
 *
 * @author syncsys
 */
@Service
public class ResultImpl implements MessageSourceAware, Result{
    private boolean isSuccessful;
    private Object object;
    private List messageList = new ArrayList();
    private String message;
    @Autowired
    private MessageSource messageSource;


    /**
     * @return the isSuccessful
     */
    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    /**
     * @param isSuccessful the isSuccessful to set
     */
    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @return the messageList
     */
    public List getMessageList() {
        return messageList;
    }

    /**
     * @param errorList the messageList to set
     */
    public void setMessageList(List errorList) {
        if(errorList != null){
            for (Object error : errorList){
                String errorMessage = messageSource.getMessage((String)error,  new Object [] {"error-"}, "error-", null);
                this.messageList.add(errorMessage);
            }
        }
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
}
