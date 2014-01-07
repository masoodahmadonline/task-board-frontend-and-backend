/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Users;
import web.service.common.ResultImpl;

/**
 *
 * @author syncsys
 */
public interface BoxesService {
    public ResultImpl save(Boxes box);
    public ResultImpl getBoxById(Long id);
    public ResultImpl getBoxByIdToDisplay(Long id);
    public Boxes setParent(Boxes childBox, Boards parentBoard);//make return object as result
    public Boxes setParent(Boxes childBox, Boxes parentBox); // make return object as reustl;
    public ResultImpl deleteBox(Long id);
}
