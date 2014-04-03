<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    Create groups
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body
    style=" border: transparent 1px solid;
/*	background-image: url('${resourcesDir}/images/handprint.png');*/
	background-repeat: no-repeat;
	background-attachment: fixed;
	    
	    ">
    <div id="wrapper">
        <div id="circle" class="circle">
          <span style="position:absolute; display:block; left:75px; bottom:60px;">Menu</span>
        </div>
        <div id="header">
          <img id="company-logo" src="${resourcesDir}/images/logo.png" />

        </div>
        

        <div id="content"> 
            <h3>Create a Group</h3>
            <form method="post" action="/">
                <input type="text" name="name" value="abc"/>
                <input type="password" value="abc"><span>some text with styles</span></input>
                <input type="text" name="description" />
                <input type="submit" value="submit" asdf/>
            </form>
       
        </div> <!-- content end -->
         
        
        <div id="footer" class="hide-menu">
            <div id="footer-on-off-button">+Menu</div>
            <div class="menu">
                <span>Admins</span>
                <span>OtherThings</span>

                <span>Terms & Conditions</span>

                 <span>Contact US</span>
            </div>     
        </div>  
    </div> <!-- wrapper -->
       
    <script type="text/javascript">
        $(document).ready(function(){
        var menuOnn = false; 
        


          $('.blur').blurjs({
                  draggable: true,
                  overlay: 'rgba(255,255,255,0.1)',
                  radius:40
          });



          var options = {percent: 100};
          $(".jQeffect-show-clip").css("display", "none");
          $( ".jQeffect-show-clip" ).show( 'clip', options, 7000 );
        //  $(".jQeffect-show-clip").css("display", "inline-block");


        $(".circle").click(function(){
            $('.circle').toggleClass('expanded');
          });

//         $("#circle").click(function(){
//
//              if(menuOnn){
//                  $("#circle").animate({
//                     backgroundColor: "rgba(70,130,180, 0.1)",
//                    //opacity: 0.4,
//                    top:"-328px",
//                    right:"-290px"
////                    marginRight: "-180px",
////                    marginTop: "-192px"
//
//                  }, 1500 );
//                  menuOnn = false;
//                  
//              }else{
//                  
//                  $("#circle").animate({
//                   
//                    backgroundColor: "rgba(176,196,222, 0.7)",
//                    //opacity: 0.4,
//                    top:"-70px",
//                    right:"-70px"
////                    marginRight: "-40px",
////                    marginTop: "-40px"
//
//
//                  }, 1500 );
//                  menuOnn = true;
//              }
//
//          });
//      
      
      
      
          
          $("#footer-on-off-button").click(function(){
            $('#footer').toggleClass('hide-menu');
          });




        });
			  
			  
				   
    </script>

  
</body>
</html>


<!--
http://www.malsup.com/jquery/corner/
http://blurjs.com/simpledemo.html
http://www.functionn.in/2012/01/blurjs-jquery-plugin-producing-psuedo.html#.UfGjOj5BXZs
http://www.blurjs.com/
http://codecanyon.net/item/translucent-responsive-banner-rotator-slider/801607?WT.ac=search_item&WT.seg_1=search_item&WT.z_author=VF
http://vectorflower.com/preview/trans_banner/index3.html?s_sel=3&&t_sel=4

    style="background-image: url('${resourcesDir}/images/3.jpg');" 


filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#a8e9ff, endColorstr=#052afc);
  background-image: -moz-linear-gradient(top, rgba(70, 130, 180, 0.3) 0%, rgba(41, 77, 107, 0.3) 81%, lightgrey 100%);
  background-image: linear-gradient(top, rgba(70, 130, 180, 0.3) 0%, rgba(41, 77, 107, 0.3) 81%, lightgrey 100%);
  background-image: -webkit-linear-gradient(top, rgba(70, 130, 180, 0.3) 0%, rgba(41, 77, 107, 0.3) 81%, lightgrey 100%);
  background-image: -o-linear-gradient(top, rgba(70, 130, 180, 0.3) 0%, rgba(41, 77, 107, 0.3) 81%, lightgrey 100%);
  background-image: -ms-linear-gradient(top, rgba(70, 130, 180, 0.3) 0%, rgba(41, 77, 107, 0.3) 81%, lightgrey 100%);
  background-image: -webkit-gradient(linear, right top, right bottom, color-stop(0%, rgba(70, 130, 180, 0.3)), color-stop(81%, rgba(41, 77, 107, 0.3)), color-stop(100%, lightgrey));
-->