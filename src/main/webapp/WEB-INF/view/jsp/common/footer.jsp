    </div> <!-- content end -->       
        <%--<div id="footer" class="hide-menu">--%>
            <%--<div id="footer-on-off-button">+Menu</div>--%>
            <%--<div class="menu">--%>
                <%--<span>Admins</span>--%>
                <%--<span>OtherThings</span>--%>

                <%--<span>Terms & Conditions</span>--%>

                 <%--<span>Contact US</span>--%>
            <%--</div>     --%>
        <%--</div>  --%>
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
              $( ".jQeffect-show-clip" ).show( 'clip', options, 3000 );
            //  $(".jQeffect-show-clip").css("display", "inline-block");

            $("#user").click(function(){
                $('#user-options').toggleClass('user-options-show');
              });

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



              $("#wrapper").removeClass("hide");
              $("#wrapper").addClass("show");


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