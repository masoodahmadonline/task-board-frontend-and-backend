<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="${resourcesDir}/scripts/jquery-ui-1.10.3/themes/base/minified/jquery-ui.min.css" />
        <script src="${resourcesDir}/scripts/jquery-1.10.2.min.js"></script>
        <script src="${resourcesDir}/scripts/blur.js"></script>
        <script src="${resourcesDir}/scripts/jquery-corner.js"></script>

        <script src="${resourcesDir}/scripts/jquery-ui-1.10.3/ui/minified/jquery-ui.min.js"></script>
        <!-- not avaialabe <link rel="stylesheet" href="/resources/demos/style.css" />  -->
        <link rel="stylesheet" href="${resourcesDir}/css/main.css" />
        <link type="text/css" rel="stylesheet" href="${resourcesDir}/css/GridStyle.css"  />
        <title>${pageTitle}</title>

        <script>

            function doNavigate(pstrWhere, pintTot)
            {
                var strTmp;
                var intPg;
                strTmp = document.frmMain.txtCurr.value;
                intPg = parseInt(strTmp);
                if (isNaN(intPg)) intPg = 1;
                if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1)
                {
                    alert("You are already viewing first page!");
                    return;
                }
                else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot)
                {
                    alert("You are already viewing last page!");
                    return;
                }
                if (pstrWhere == 'F')
                    intPg = 1;
                else if (pstrWhere == 'P')
                    intPg = intPg - 1;
                else if (pstrWhere == 'N')
                    intPg = intPg + 1;
                else if (pstrWhere == 'L')
                    intPg = pintTot;
                if (intPg < 1) intPg = 1;
                if (intPg > pintTot) intPg = pintTot;
                document.frmMain.txtCurr.value = intPg;
                document.frmMain.submit();
            }
            function doSort(pstrFld, pstrOrd)
            {
                document.frmMain.txtSortCol.value = pstrFld;
                document.frmMain.txtSortAsc.value = pstrOrd;
                document.frmMain.submit();
            }

            $(function() {

              var icons = {
                header: "ui-icon-circle-arrow-e",
                activeHeader: "ui-icon-circle-arrow-s"
              };

              $( ".accordion" ).accordion({
                icons:icons,
                heightStyle: "fill"
              });
            });
            $(function() {
              $( ".accordion-resizer" ).resizable({
                minHeight: 140,
                minWidth: 200,
                resize: function() {
                  $( ".accordion" ).accordion( "refresh" );
                }
              });
            });

            $(function() {
              $( ".draggable" ).draggable({ cursor: "move" });


            });
        </script>
    </head>
    