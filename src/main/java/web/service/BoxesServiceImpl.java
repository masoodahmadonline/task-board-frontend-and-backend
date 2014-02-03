/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

/**
 *
 * @author syncsys
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.BoardsDAO;
import web.dao.BoxesDAO;
import web.dao.UsersDAO;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Users;
import web.service.common.ResultImpl;

@Service
@Transactional(readOnly = true)
public class BoxesServiceImpl implements BoxesService{
    
    @Autowired
    private BoxesDAO boxDAO;
    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private ResultImpl result;
    
   
    
    @Transactional(readOnly = false)
    public ResultImpl save(Boxes box){
        Boxes boardToBeReturned = boxDAO.save(box);
        if(boardToBeReturned == null){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boxCreationErrorUnknown"/*,"more.messages"*/));
        }else{
            result.setIsSuccessful(true);
            result.setObject(boardToBeReturned);
            result.setMessageList(Arrays.asList("success.boxCreated"/*,"more.messages"*/));
        }
        return result;
    }
    
    @Transactional(readOnly = false)
    public ResultImpl deleteBox(Long id){
        if(boxDAO.deleteBox(id)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.boxDeleted"));
        }else{
            result.setIsSuccessful(false);
        }   result.setMessageList(Arrays.asList("error.boxDeletionFailed"));
        return result;
    }
    
    @Transactional
    public ResultImpl getBoxById(Long id){
        Boxes box = boxDAO.getBoxById(id);
        if (box != null){
            result.setIsSuccessful(true);
            result.setObject(box);
            System.out.println("== child box list size of "+id+" is "+box.getChildBoxList().size());
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
        }
        return result;
    }
    
    @Transactional
    public ResultImpl getBoxByIdToDisplay(Long id){
        Boxes box = boxDAO.getBoxById(id);
        if (box != null){
            crawlBoxes(box.getChildBoxList());
            System.out.println(" ------- boxid : "+box.getId()+" and its childBoxList size: "+ box.getChildBoxList().size());
            result.setIsSuccessful(true);
            result.setObject(box);
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
        }
        return result;
    }

   //now duplicated in boardsService{M-A}
    void crawlBoxes(Collection boxList){
        for( Object o : boxList ){
           Boxes box = (Boxes)o;    
           box.getTaskList();
           List<Boxes> childBoxList = new ArrayList<Boxes> ( box.getChildBoxList() ); // list of sub child boxes of this box
            System.out.println(" ======== boxid : "+box.getId()+" and its childBoxList size: "+ childBoxList.size());
           if(!childBoxList.isEmpty()){
               crawlBoxes(childBoxList);
           }
        }
    }

    @Transactional(readOnly = false)
    public Boxes setParent(Boxes childBox, Boards parentBoard){
        childBox.setParentBoard(parentBoard);
        return childBox;
    }

    @Transactional(readOnly = false)
    public Boxes setParent(Boxes childBox, Boxes parentBox){
        childBox.setParentBox(parentBox);
        return childBox;
    }


    @Transactional(readOnly = false)
    public ResultImpl editBox(Long id, String type, String title, String description){
        result = getBoxById(id);
        if(result.getIsSuccessful()){
            Boxes box = (Boxes)result.getObject();
            box.setType(type);
            box.setTitle(title);
            box.setDescription(description);
            result.setObject(box);
            result.setMessageList(Arrays.asList("success.boxEdited"));
        }else{
            result.setMessageList(Arrays.asList("error.boxEditFailed"));
        }
        return result;
    }
    
    
}
