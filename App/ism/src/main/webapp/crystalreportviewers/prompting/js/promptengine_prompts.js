
var debug=false;function dumpFormFields(a){theForm=document.forms[a];for(idx=0;idx<theForm.elements.length;++idx){alert(theForm.elements[idx].name+" - "+theForm.elements[idx].value)}}var isJava=false;var isNetscape=navigator.appName.indexOf("Netscape")!=-1;var LEFT_ARROW_KEY=37;var RIGHT_ARROW_KEY=39;var ENTER_KEY=13;var DateTimeFormatSetting={datePattern:"Y-M-D",isTwoDigitMonth:true,isTwoDigitDay:true,dateRegex:null,dateTimeRegex:null};function promptengine_getDatePattern(){return DateTimeFormatSetting.datePattern}function promptengine_setDatePattern(a){DateTimeFormatSetting.datePattern=a}function promptengine_getIsTwoDigitMonth(){return DateTimeFormatSetting.isTwoDigitMonth}function promptengine_setIsTwoDigitMonth(a){DateTimeFormatSetting.isTwoDigitMonth=a}function promptengine_getIsTwoDigitDay(){return DateTimeFormatSetting.isTwoDigitDay}function promptengine_setIsTwoDigitDay(a){DateTimeFormatSetting.isTwoDigitDay=a}function promptengine_getDateRegex(){return DateTimeFormatSetting.dateRegex}function promptengine_setDateRegex(a){DateTimeFormatSetting.dateRegex=a}function promptengine_getDateTimeRegex(){return DateTimeFormatSetting.dateTineRegex}function promptengine_setDateTimeRegex(a){DateTimeFormatSetting.dateTineRegex=a}function promptengine_encodePrompt(a){if(isJava){return encodeURIComponent(a)}else{return promptengine_urlEncode(a)}}function promptengine_addDiscreteValue(c,b,a){var d,e;d=e=c[a+"DiscreteValue"];if(e.type&&e.type.toLowerCase()!="text"&&e.type.toLowerCase()!="hidden"&&e.type.toLowerCase()!="password"){e=e.options[e.selectedIndex]}if(!promptengine_checkValue(e.value,b)){if(d.focus&&d.type.toLowerCase()!="hidden"){d.focus()}return false}promptValue=e.value;displayString=(e.text)?e.text:e.value;promptEntry=new Option(displayString,promptValue,false,false);theList=c[a+"ListBox"];theList.options[theList.length]=promptEntry;if(d.focus&&d.type.toLowerCase()!="hidden"&&!d.disabled){d.focus()}if(d.select){d.select()}if(d.type.toLowerCase!="text"&&d.type.toLowerCase!="hidden"&&d.type.toLowerCase!="password"){if(d.selectedIndex<d.length-1){d.selectedIndex=d.selectedIndex+1}}}function promptengine_deselectAllItems(a){for(var b=0;b<a.length;b++){a.options[b].selected=false}}function promptengine_addAvailableItem(d,b,e){for(var c=0;c<e.length;c++){if(e.options[c].value==d.options[b].value&&e.options[c].text==d.options[b].text){e.options[c].selected=true;return false}}var a=new Option(d.options[b].text,d.options[b].value,false,true);e.options[e.length]=a;return true}function promptengine_addValueFromPickList(a,e,j){var h,d;h=a[j+"AvailableList"];d=a[j+"ListBox"];promptengine_deselectAllItems(d);var b=false;var g=-1;for(var c=0;c<h.length;c++){if(h.options[c].selected){var f=promptengine_addAvailableItem(h,c,d);if(f==true){b=true}g=c}}if(g++>=0&&g<h.length){promptengine_deselectAllItems(h);h.options[g].selected=true}return b}function promptengine_addAllValues(f,e,b){var a,g;a=f[b+"AvailableList"];g=f[b+"ListBox"];promptengine_deselectAllItems(g);var h=false;for(var c=0;c<a.length;c++){var d=promptengine_addAvailableItem(a,c,g);if(d==true){h=true}}return h}function promptengine_addRangeValue(a,e,l){var c=a[l+"SelectLowerRangeValue"];var j=a[l+"SelectUpperRangeValue"];lowerBound=a[l+"LowerBound"];upperBound=a[l+"UpperBound"];if(lowerBound.type.toLowerCase()!="text"&&lowerBound.type.toLowerCase()!="hidden"&&lowerBound.type.toLowerCase()!="password"){lowerBound=lowerBound.options[lowerBound.selectedIndex];upperBound=upperBound.options[upperBound.selectedIndex]}lowerUnBounded=a[l+"NoLBoundCheck"].checked;upperUnBounded=a[l+"NoUBoundCheck"].checked;lvalue=uvalue="";if(!lowerUnBounded){if(!promptengine_checkRangeBoundValue(lowerBound.value,e)){if(lowerBound.focus&&lowerBound.type.toLowerCase()!="hidden"){lowerBound.focus()}return false}lvalue=lowerBound.value}if(!upperUnBounded){if(!promptengine_checkRangeBoundValue(upperBound.value,e)){if(upperBound.focus&&upperBound.type.toLowerCase()!="hidden"){upperBound.focus()}return false}uvalue=upperBound.value}var b="";var d="";var m=false;if(c!=null&&lvalue!=null&&lvalue.length>0){var h=c.length;for(var k=0;k<h;k++){var g=c.options[k].value;if(g!=null&&g.length>0&&g==lvalue){b=c.options[k].text;m=true;break}}}if(!m){b=(lowerBound.text&&!lowerUnBounded)?lowerBound.text:lvalue}m=false;if(j!=null&&uvalue!=null&&uvalue.length>0){var h=j.length;for(var k=0;k<h;k++){var g=j.options[k].value;if(g!=null&&g==uvalue){d=j.options[k].text;m=true;break}}}if(!m){d=(upperBound.text&&!upperUnBounded)?upperBound.text:uvalue}lowerChecked=a[l+"LowerCheck"].checked;upperChecked=a[l+"UpperCheck"].checked;g=(lowerChecked&&!lowerUnBounded)?"[":"(";if(!lowerUnBounded){g+=(lvalue)}g+="_crRANGE_";if(!upperUnBounded){g+=(uvalue)}g+=(upperChecked&&!upperUnBounded)?"]":")";if(debug){alert(g)}display=(lowerChecked&&!lowerUnBounded)?"[":"(";display+=b;display+=" .. ";display+=d;display+=(upperChecked&&!upperUnBounded)?"]":")";promptEntry=new Option(display,g,false,false);theList=a[l+"ListBox"];var f=promptengine_findOptionInList(theList,g);if(f>-1){theList.selectedIndex=f}else{theList.options[theList.length]=promptEntry}return true}function promptengine_findOptionInList(c,b){if(c==null||b==null||b==""){return -1}for(var a=0;a<c.options.length;a++){if(c.options[a].value==b){return a}}return -1}function promptengine_onNoBoundCheckClicked(b,a,c){if(c==0){if(b[a+"NoLBoundCheck"].checked){b[a+"NoUBoundCheck"].disabled=true;b[a+"LowerCheck"].disabled=true;b[a+"LowerBound"].disabled=true;if(b[a+"SelectLowerRangeValue"]!=null){b[a+"SelectLowerRangeValue"].disabled=true}}else{b[a+"NoUBoundCheck"].disabled=false;b[a+"LowerCheck"].disabled=false;b[a+"LowerBound"].disabled=false;if(b[a+"SelectLowerRangeValue"]!=null){b[a+"SelectLowerRangeValue"].disabled=false}}}else{if(c==1){if(b[a+"NoUBoundCheck"].checked){b[a+"NoLBoundCheck"].disabled=true;b[a+"UpperCheck"].disabled=true;b[a+"UpperBound"].disabled=true;if(b[a+"SelectUpperRangeValue"]!=null){b[a+"SelectUpperRangeValue"].disabled=true}}else{b[a+"NoLBoundCheck"].disabled=false;b[a+"UpperCheck"].disabled=false;b[a+"UpperBound"].disabled=false;if(b[a+"SelectUpperRangeValue"]!=null){b[a+"SelectUpperRangeValue"].disabled=false}}}}}function promptengine_onSetNullCheckClicked(b,a){if(b[a+"NULL"].checked){if(b[a+"DiscreteValue"]!=null){b[a+"DiscreteValue"].disabled=true}if(b[a+"SelectValue"]!=null){b[a+"SelectValue"].disabled=true}}else{if(b[a+"DiscreteValue"]!=null){b[a+"DiscreteValue"].disabled=false}if(b[a+"SelectValue"]!=null){b[a+"SelectValue"].disabled=false}}}function promptengine_selectValue(b,a,d){if(b[a].selectedIndex<0){return false}selectedOption=b[a].options[b[a].selectedIndex];if(selectedOption.value==null&&b[d].value==null){return false}var c=true;if(selectedOption.value==b[d].value){c=false}b[d].value=selectedOption.value;return c}function promptengine_hasValueInTextBox(b,a){if(b[a].value==null){return false}return true}function promptengine_setCascadingPID(c,a,b){valueField=c[a];curVal=valueField.value;if(curVal.length>0){curVal+="&"}curVal+="cascadingPID="+b;if(debug){alert(curVal)}valueField.value=curVal;return true}function promptengine_removeValue(d,c){var b=d[c+"ListBox"];var f=false;var e=-1;for(var a=0;a<b.options.length;){if(b.options[a].selected){b.options[a]=null;f=true;e=a}else{a++}}if(e>=0&&e<b.length){promptengine_deselectAllItems(b);b.options[e].selected=true}else{if(e==b.length&&e>0){promptengine_deselectAllItems(b);b.options[e-1].selected=true}}return f}function promptengine_onRemoveValue(b,a){promptengine_removeValue(b,a)}function promptengine_removeAllValues(d,c){var b=d[c+"ListBox"];var e=false;if(b.options.length>0){e=true}for(var a=0;a<b.options.length;){b.options[a]=null}return e}function promptengine_onRemoveAllValues(b,a){promptengine_removeAllValues(b,a)}function promptengine_updateValueField(c,a,b,d){valueField=c[a];curVal=valueField.value;if(curVal.length>0){curVal+="&"}curVal+=b+"="+d;if(debug){alert(curVal)}valueField.value=curVal;return true}function promptengine_resetValueField(b,a){valueField=b[a];valueField.value=""}function promptengine_updateDiscreteValue(e,a,b,d,c){var f="";if(e[b+"NULL"]!=null&&e[b+"NULL"].checked){f="_crNULL_"}else{valueField=e[b+"DiscreteValue"];if(valueField.type.toLowerCase()!="text"&&valueField.type.toLowerCase()!="hidden"&&valueField.type.toLowerCase()!="password"){f=valueField.options[valueField.selectedIndex].value}else{f=valueField.value}if(c&&!promptengine_checkValue(f,d)){if(valueField.focus&&valueField.type.toLowerCase()!="hidden"){valueField.focus()}return false}else{f=promptengine_encodePrompt(f)}}return promptengine_updateValueField(e,a,b,f)}function promptengine_updateRangeValue(e,a,b,d,c){if(e[b+"NULL"]!=null&&e[b+"NULL"].checked){value="_crNULL_"}else{lowerBound=e[b+"LowerBound"];upperBound=e[b+"UpperBound"];if(lowerBound.type.toLowerCase()!="text"&&lowerBound.type.toLowerCase()!="hidden"&&lowerBound.type.toLowerCase()!="password"){lowerBound=lowerBound.options[lowerBound.selectedIndex];upperBound=upperBound.options[upperBound.selectedIndex]}lowerUnBounded=e[b+"NoLBoundCheck"].checked;upperUnBounded=e[b+"NoUBoundCheck"].checked;lowerChecked=e[b+"LowerCheck"].checked;upperChecked=e[b+"UpperCheck"].checked;uvalue=lvalue="";if(!lowerUnBounded){if(c&&!promptengine_checkRangeBoundValue(lowerBound.value,d)){if(lowerBound.focus&&lowerBound.type.toLowerCase()!="hidden"){lowerBound.focus()}return false}lvalue=lowerBound.value}if(!upperUnBounded){if(c&&!promptengine_checkRangeBoundValue(upperBound.value,d)){if(upperBound.focus&&upperBound.type.toLowerCase()!="hidden"){upperBound.focus()}return false}uvalue=upperBound.value}value=(lowerChecked&&!lowerUnBounded)?"[":"(";if(!lowerUnBounded){value+=promptengine_encodePrompt(lvalue)}value+="_crRANGE_";if(!upperUnBounded){value+=promptengine_encodePrompt(uvalue)}value+=(upperChecked&&!upperUnBounded)?"]":")"}return promptengine_updateValueField(e,a,b,value)}function promptengine_updateMultiValue(e,a,b,d,c){values=e[b+"ListBox"].options;value="";if(e[b+"NULL"]!=null&&e[b+"NULL"].checked){value="_crNULL_"}else{if(values.length==0){value="_crEMPTY_"}else{for(i=0;i<values.length;i++){if(i!=0){value+="_crMULT_"}value+=promptengine_encodePrompt(values[i].value)}}}return promptengine_updateValueField(e,a,b,value)}var regNumber=/^(\+|-)?((\d+(\.|,|'| |\xA0)?\d*)+|(\d*(\.|,| |\xA0)?\d+)+)$/;var regCurrency=regNumber;var regDate=/^(D|d)(A|a)(T|t)(E|e) *\( *\d{4} *, *(0?[1-9]|1[0-2]) *, *((0?[1-9]|[1-2]\d)|3(0|1)) *\)$/;var regDateTime=/^(D|d)(A|a)(T|t)(E|e)(T|t)(I|i)(M|m)(E|e) *\( *\d{4} *, *(0?[1-9]|1[0-2]) *, *((0?[1-9]|[1-2]\d)|3(0|1)) *, *([0-1]?\d|2[0-3]) *, *[0-5]?\d *, *[0-5]?\d *\)$/;var regTime=/^(T|t)(I|i)(M|m)(E|e) *\( *([0-1]?\d|2[0-3]) *, *[0-5]?\d *, *[0-5]?\d *\)$/;var regDateTimeHTML=/^ *\d{4} *- *(0?[1-9]|1[0-2]) *- *((0?[1-9]|[1-2]\d)|3(0|1)) *  *([0-1]?\d|2[0-3]) *: *[0-5]?\d *: *[0-5]?\d *$/;var regDateHTML=/^ *\d{4} *- *(0?[1-9]|1[0-2]) *- *((0?[1-9]|[1-2]\d)|3(0|1)) *$/;var regTimeHTML=/^ *([0-1]?\d|2[0-3]) *: *[0-5]?\d *: *[0-5]?\d *$/;function promptengine_getDateSpec(){var a=promptengine_getDatePattern();a=a.replace("Y",L_YYYY);a=a.replace("M",L_MM);a=a.replace("D",L_DD);return a}function promptengine_checkValue(e,c){if(e==null){return false}if(e=="_crNULL_"){return true}if(c=="n"&&!regNumber.test(e)){if(e.length>0){alert(L_BadNumber)}return false}else{if(c=="c"&&!regCurrency.test(e)){if(e.length>0){alert(L_BadCurrency)}return false}else{if(c=="d"){var d=promptengine_getDateRegex();if((d==null||!d.test(e))&&!regDate.test(e)&&!regDateHTML.test(e)){if(e.length>0){var a=L_BadDate.replace("%1",promptengine_getDateSpec());alert(a)}return false}}else{if(c=="dt"){var d=promptengine_getDateTimeRegex();if((d==null||!d.test(e))&&!regDateTime.test(e)&&!regDateTimeHTML.test(e)){if(e.length>0){var b=L_BadDateTime.replace("%1",promptengine_getDateSpec());alert(b)}return false}}else{if(c=="t"&&!regTime.test(e)&&!regTimeHTML.test(e)){if(e.length>0){alert(L_BadTime)}return false}}}}}return true}function promptengine_checkRangeBoundValue(b,a){if(b==null||b.length==0){return false}return promptengine_checkValue(b,a)}function promptengine_isSubmitEvent(a){if(isNetscape){if(a.which==ENTER_KEY&&(a.target.type=="text"||a.target.type=="password")){return true}}else{if(window.event.keyCode==ENTER_KEY&&(window.event.srcElement.type=="text"||window.event.srcElement.type=="password")){return true}}return false}function promptengine_isEnterKey(a){if(isNetscape){if(a.which==ENTER_KEY&&a.target.tagName.toLowerCase()!="a"){return true}}else{if(window.event.keyCode==ENTER_KEY&&window.event.srcElement.tagName.toLowerCase()!="a"){return true}}}function promptengine_urlEncode(a){var c=new String("");for(var b=0;b<a.length;b++){var d=a.charAt(b);switch(d){case"%":c+="%25";break;case"+":c+="%2B";break;case" ":c+="%20";break;case"<":c+="%3C";break;case">":c+="%3E";break;case'"':c+="%22";break;case"'":c+="%27";break;case"#":c+="%23";break;case"{":c+="%7B";break;case"}":c+="%7D";break;case"|":c+="%7C";break;case"\\":c+="%5C";break;case"^":c+="%5E";break;case"~":c+="%7E";break;case"`":c+="%60";break;case"[":c+="%5B";break;case"]":c+="%5D";break;case";":c+="%3B";break;case"/":c+="%2F";break;case"?":c+="%3F";break;case":":c+="%3A";break;case"@":c+="%40";break;case"=":c+="%3D";break;case"&":c+="%26";break;default:c+=d;break}}return c}function promptengine_CancelRightClick(a){if(isNetscape){if(a.target.type!="text"&&a.target.type!="textarea"){a.preventDefault();a.cancelBubble=true;return true}}else{if(window.event.srcElement.type!="text"&&window.event.srcElement.type!="textarea"){window.event.cancelBubble=true;window.event.returnValue=false}}}function promptengine_showHidePromptByKey(b,d,g,f,a){var c=false;var e=document.getElementById(b);if(e==null){return}if(isNetscape){if((a.which==LEFT_ARROW_KEY&&e.style.display=="")||(a.which==RIGHT_ARROW_KEY&&e.style.display=="none")){c=true}}else{if((window.event.keyCode==LEFT_ARROW_KEY&&e.style.display=="")||(window.event.keyCode==RIGHT_ARROW_KEY&&e.style.display=="none")){c=true}}if(c==true){promptengine_showHidePrompt(b,d,g,f,a)}}function promptengine_showHidePrompt(b,c,g,f,a){var e;e=document.getElementById(c);if(e!=null&&b!=null){if(!e.origImage){e.origImage=e.src}var d=document.getElementById(b);if(d!=null){if(d.style.display==""){d.style.display="none"}else{d.style.display=""}if(!e.changed||e.changed==false){e.src=f;e.changed=true}else{e.src=g;e.changed=false}}}}function promptengine_scrollTo(a){if(!a){return}var b=a.offsetTop;if(!b){return}var c=a.offsetParent;while(c){b+=c.offsetTop;c=c.offsetParent}window.scrollTo(0,b)};