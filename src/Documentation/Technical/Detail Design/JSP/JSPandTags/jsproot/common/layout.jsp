<%

/* Layout parameters setting  - For plasticplanets Layout Setting */
	int mainTableWidth;       /*main table width*/
	//int controlTableWidth	  /*for the outmost TABLE width*/
    int leftMenuBarWidth;
    //int leftMenuBarWidth1   /*for the side menu TD width of first design*/
    //int leftMenuBarWidth2   /*for the side menu TD width of second design*/
	int leftSpace;			  /*for the space between left menu bar and content*/
	int rightSpace;			  /*for the space between right border and content*/
	int mainContentHeight;	  /*for the height of the empty image within the left spacing TD*/
	int selectHeight;
    int LeftHeight;
    int contentWidth;
    int tableWidth;
    //String leftMenuColor 	  /*for the background color of the left menu bar*/
    //int contentWidth1
    //int contentWidth		  /*for the main content width of first design*/
    //int contentWidth2		  /*for the main content width of second design*/

		mainTableWidth=779;

		leftSpace=10;
		leftMenuBarWidth=160;
		//leftMenuBarWidth2=170;
		mainContentHeight=400;
		LeftHeight=180;
		contentWidth = 600;
		tableWidth = 550;
	    //contentWidth2 = mainTableWidth - leftMenuBarWidth1 - rightSpace-leftSpace;
	    //leftMenuColor="#2C71C9";
%>

<script language="JavaScript">
function js_call(address) {
  var newwin=window.open(address,"homewin","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=300");
  newwin.focus();
  return false;
}
</script>

<script language="JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>

<title>Eschool</title>
<Link REL="stylesheet" HREF="/common/style.css" type="text/css">

