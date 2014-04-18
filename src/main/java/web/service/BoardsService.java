/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Boards;
import web.entity.Users;
import web.service.common.ResultImpl;
import web.wrapper.BoardWrapper;
import web.wrapper.UserWrapper;

/**
 *
 * @author syncsys
 */
public interface BoardsService {
    public ResultImpl save(Boards board);
    public ResultImpl getBoardById(Long id);
    public ResultImpl getBoardByIdToDisplay(Long id);
    public ResultImpl getBoardListByUser(Users user);
    public ResultImpl saveBoard(UserWrapper wrapper);
    public BoardWrapper getUpdatedBoardWrapper(BoardWrapper wrapper);
}
