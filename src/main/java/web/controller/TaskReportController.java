package web.controller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.dao.UsersDAO;
import web.entity.Boards;
import web.entity.Tasks;
import web.entity.Users;
import web.service.BoardsService;
import web.service.ReportsService;
import web.service.TasksService;
import web.service.UsersService;
import web.service.common.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TaskReportController {
 
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
    @Autowired
    ReportsService reportService;
    @Autowired
    UsersService userService;
    @Autowired
    BoardsService boardService;
    @Autowired
    private Result result;
    @Autowired
    private ProviderManager authenticationManager;


    @PreAuthorize("@securityService.hasBoardViewPermission(#boardId)")
    @RequestMapping(value = "/report/pdf", method = RequestMethod.POST )
    public ModelAndView generatePdfReport(ModelAndView modelAndView, 
    					@RequestParam("boardId") Long boardId,
			    		@RequestParam("startDate") String startDateString,
			    		@RequestParam("endDate") String endDateString,
			    		@RequestParam(value = "orderByStatus",required = false, defaultValue = "off") String orderByStatus){
 
        logger.debug("--------------generate PDF report----------");
        
        System.out.println(boardId+" - "+startDateString+" - "+endDateString +" - "+ orderByStatus +"=========");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
//        Calendar calender = Calendar.getInstance();
//        calender.setTime(new Date());
//        calender.add(Calendar.HOUR_OF_DAY, -5);
// 
//        List<Tasks> taskList = taskService.getAllTasks(3L, "status", calender.getTime() , new Date() );
        
        List<String> orderByParamList = new ArrayList<String>(); //the order by params can be many. so making a list
        if (orderByStatus.equals("on")){
        	orderByParamList.add("status");
        }
        
        Date startDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Date endDate = null;
		try {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        List<Tasks> taskList = reportService.getTasksByDateRangeLong(3L, orderByParamList, startDate , endDate );

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(taskList);
 
        parameterMap.put("datasource", JRdataSource);
 
        //pdfReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("pdfReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/reports/cards/by-status" )
    public String userHome(ModelMap model, HttpServletRequest request) {
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername(); //get logged in username
        result = userService.getUserByLoginId(loginId); /////////////   to be edited
        Users user = (Users)result.getObject();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", user.getFirstName());
        result = boardService.getBoardListByUser(user);
        List<Boards> boardList = new ArrayList<Boards>((List)result.getObject());
        model.put("boards",boardList);
        System.out.println("boardList size: "+boardList.size());
        for(Boards b: boardList){
         System.out.println("The board id fetched was "+b.getId());
        }
//        model.addAttribute("username", user.getName());
        System.out.println("user-home shown--------------------");
        return "reports/cards/by-status";       
    }
 
 
 
    
 
 
}//ReportController


