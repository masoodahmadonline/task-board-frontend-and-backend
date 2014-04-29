/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Users;
import web.service.common.ResultImpl;
import web.wrapper.UserWrapper;

/**
 *
 * @author syncsys
 */
public interface BoxesService {
    public ResultImpl save(Boxes box);
    public ResultImpl getBoxById(Long id);
    public ResultImpl getBoxByIdToDisplay(Long id);
    public Boxes setParent(Boxes childBox, Boards parentBoard);//queue- make return object as result {M-A}
    public Boxes setParent(Boxes childBox, Boxes parentBox); //queue- make return object as reustl; {M-A}
    public ResultImpl deleteBox(Long id);
    public ResultImpl editBox(Long id, String type, String title, String description, UserWrapper wrapper);
}
