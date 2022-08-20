<SCRIPT LANGUAGE="JavaScript">

<!-- Begin
  function checkdate(objName,fileldName)
  {
      var datefield = objName;
      if (chkdate(objName) == false)
      {
        alert(fileldName);
        return false;
      }
      else
      {
      return true;
     }
  }
  function chkdate(objName)
  {
    var strDate;
    var strDay;
    var strMonth;
    var strYear;
    var intday;
    var intMonth;
    var intYear;
    var booFound = false;
    var datefield = objName;
    var intElementNr;
    var err = 0;
    var strMonthArray = new Array(12);
    strMonthArray[0] = "01";
    strMonthArray[1] = "02";
    strMonthArray[2] = "03";
    strMonthArray[3] = "04";
    strMonthArray[4] = "05";
    strMonthArray[5] = "06";
    strMonthArray[6] = "07";
    strMonthArray[7] = "08";
    strMonthArray[8] = "09";
    strMonthArray[9] = "10";
    strMonthArray[10] = "11";
    strMonthArray[11] = "12";
    strDate = datefield.value;
    if (strDate.length < 1||strDate.length>8)
    {
      return true;
    }

    strYear = strDate.substr(0, 4);
    strMonth = strDate.substr(4, 2);
    strDay = strDate.substr(6);

    intday = parseInt(strDay, 10);
    if (isNaN(intday))
    {
      err = 2;
      return false;
    }

    intMonth = parseInt(strMonth, 10);
    if (isNaN(intMonth))
    {
      err = 3;
      return false;
    }

    intYear = parseInt(strYear, 10);
    if (isNaN(intYear))
    {
      err = 4;
      return false;
    }

    if (intMonth>12 || intMonth<1)
    {
      err = 5;
      return false;
    }

    if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1))
    {
      err = 6;
      return false;
    }

    if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1))
    {
      err = 7;
      return false;
    }

    if (intMonth == 2)
    {
      if (intday < 1)
      {
        err = 8;
        return false;
      }
      if (LeapYear(intYear) == true)
      {
        if (intday > 29)
        {
          err = 9;
          return false;
        }
      }else
      {
        if (intday > 28)
        {
          err = 10;
          return false;
        }
      }
    }
    return true;
  }
  function LeapYear(intYear)
  {
    if (intYear % 100 == 0)
    {
      if (intYear % 400 == 0)
      {
        return true;
      }
    }else
    {
      if ((intYear % 4) == 0)
      {
        return true;
      }
    }
    return false;
  }

  //  End -->
</script>

