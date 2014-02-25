package web.utils;

import web.entity.Boxes;

import java.util.Comparator;

public class BoxComparator implements Comparator<Boxes>{

    @Override
    public int compare(Boxes b1, Boxes b2) {
        long idForB1 = b1.getId();
        long idForB2 = b2.getId();

        if (idForB1 > idForB2){
            return 1;
        }else{
            return -1;
        }
    }
}
