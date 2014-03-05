package web.utils;

import web.entity.Tasks;

import java.util.Comparator;
/**
 * Created with IntelliJ IDEA.
 * User: syncsys
 * Date: 2/26/14
 * Time: 12:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskComparator implements Comparator<Tasks> {

    @Override
    public int compare(Tasks t1, Tasks t2) {
        long idForT1 = t1.getId();
        long idForT2 = t2.getId();

        if (idForT1 > idForT2){
            return 1;
        }else{
            return -1;
        }
    }
}
