if(typeof(bobj.crv.StackedPanel)=="undefined"){bobj.crv.StackedPanel={}}if(typeof(bobj.crv.StackedTab)=="undefined"){bobj.crv.StackedTab={}}bobj.crv.newStackedPanel=function(b){var e=MochiKit.Base;var d=e.update;var a=e.bind;b=d({id:bobj.uniqueId(),width:null,height:null,isAnimated:true},b);var c=newWidget(b.id);c.widgetType="StackedPanel";bobj.fillIn(c,b);c._tabs=[];c._initWidget=c.init;c._resizeWidget=c.resize;d(c,bobj.crv.StackedPanel);c._onTabCollapse=a(c._onTabCollapse,c);c._onTabExpand=a(c._onTabExpand,c);return c};bobj.crv.StackedPanel.init=function(){this._initWidget();var d=this._tabs;var b=this._numTabsWritten;while(b<d.length){append(this.layer,d[b].getHTML(),document);b++}for(var c=0,a=d.length;c<a;++c){d[c].init()}};bobj.crv.StackedPanel.getHTML=function(){var b=bobj.html.DIV;var a={overflow:"auto",position:"relative"};if(this.height){a.height=bobj.unitValue(this.height)}if(this.width){a.width=bobj.unitValue(this.width)}return b({id:this.id,style:a,tabIndex:"-1"},this._getTabsHTML())};bobj.crv.StackedPanel._getTabsHTML=function(){var c="";var b=this._tabs;var d=b.length;for(var a=0;a<d;++a){c+=b[a].getHTML()}this._numTabsWritten=d;return c};bobj.crv.StackedPanel.addTab=function(a){if(a){a.collapseCB=this._onTabCollapse;a.expandCB=this._onTabExpand;this._tabs.push(a);if(this.layer){append(this.layer,a.getHTML());a.init()}}};bobj.crv.StackedPanel.getNumTabs=function(){return this._tabs.length};bobj.crv.StackedPanel.getTab=function(a){return this._tabs[a]};bobj.crv.StackedPanel.removeTab=function(a){if(a>=0&&a<this._tabs.length){var b=this._tabs[a];this._tabs.splice(a,1);delete _widgets[this._tabs.widx];if(b.layer){b.layer.parentNode.removeChild(b.layer)}}};bobj.crv.StackedPanel.resize=function(a,f){var c=!_saf;bobj.setOuterSize(this.layer,a,f,c);var e=this._tabs;var g=e.length;if(g){var b=this.layer.clientWidth;e[0].resize(b);if(b!=this.layer.clientWidth){b=this.layer.clientWidth;e[0].resize(b)}for(var d=1;d<g;++d){e[d].resize(b)}}};bobj.crv.StackedPanel._onTabCollapse=function(a){this.resize()};bobj.crv.StackedPanel._onTabExpand=function(a){this.resize()};bobj.crv.newStackedTab=function(a){var c=MochiKit.Base.update;a=c({id:bobj.uniqueId(),label:"",width:300,height:null,isAnimated:true,expandCB:null,openAdvCB:null,selectCB:null,collapseCB:null,expandImgPos:"right"},a);var b=newWidget(a.id);b.widgetType="StackedTab";bobj.fillIn(b,a);b._content=null;b._leftIcons=[];b._rightIcons=[];b._IMG_WIDTH=16;b._IMG_HEIGHT=16;b._ICON_WIDTH=20;b._initWidget=b.init;b._resizeWidget=b.resize;c(b,bobj.crv.StackedTab);return b};bobj.crv.StackedTab.init=function(){var a=MochiKit.Signal.connect;this._initWidget();if(this._content){this._content.init()}this._labelCtn=document.getElementById(this.id+"_labelCtn");this._textCtn=document.getElementById(this.id+"_textCtn");this._expandCtn=document.getElementById(this.id+"_expandCtn");this._expandImg=document.getElementById(this.id+"_expand");this._contentCtn=document.getElementById(this.id+"_contentCtn");if(this.openAdvCB){a(this._labelCtn,"ondblclick",this.openAdvCB)}if(this.selectCB){a(this._labelCtn,"onclick",this.selectCB)}this.setMinMaxIconToolTip()};bobj.crv.StackedTab.getHTML=function(){var g=bobj.html.DIV;var f={width:this.width+"px",overflow:"hidden"};var a={id:this.id+"_labelCtn","class":"stackedTabLabel crvnoselect thumbtxt "};var d={id:this.id+"_expandCtn",onclick:"bobj.crv.StackedTab._onExpandClick('"+this.id+"')","class":"stackedTabIconCtn",style:{cursor:"pointer"}};if(this.expandImgPos==="left"){d.style.left="0px"}else{d.style.right="-1px"}var e=this._content?this._content.getHTML():"";var c=this._IMG_WIDTH;var h=this._IMG_HEIGHT;var b=g({id:this.id,style:f},g(a,g(d,imgOffset(bobj.crvUri("images/param_panel.gif"),c,h,c,16,this.id+"_expand")),g({"class":"stackedTabText",style:this._getTextCtnStyle(),id:this.id+"_textCtn"},this.label),this._getIconsHtml(this._leftIcons,true)+this._getIconsHtml(this._rightIcons,false)),g({id:this.id+"_contentCtn"},e));return b};bobj.crv.StackedTab.setMinMaxIconToolTip=function(){var f=this._rightIcons;for(var d=0,a=f.length;d<a;d++){var c=f[d].id;var b=f[d].tooltip;if(c.indexOf("_icnInfo")>=0){var e=getLayer(c);this._setIconTooltip(e,b)}}};bobj.crv.StackedTab._setIconTooltip=function(a,b){if(a!==null&&b!==null){bobj.crv.Tooltip.setElementTooltip(a,b)}};bobj.crv.StackedTab._getIconsHtml=function(d,h){var g=bobj.html.DIV;var f="";for(var c=0,b=d.length;c<b;++c){var e=d[c];var a=(e.id.indexOf("_icnInfo")>=0)?true:false;f+=g({id:e.id,"class":"stackedTabIconCtn",style:this._getIconCtnStyle(e,h,c)},imgOffset(e.url,this._IMG_WIDTH,this._IMG_HEIGHT,e.dx,e.dy,null,null,(a?null:e.tooltip)))}return f};bobj.crv.StackedTab._countVisibleIcons=function(b){var a=MochiKit.Base.filter(function(c){return c.isVisible},b);return a.length};bobj.crv.StackedTab._getIconCtnStyle=function(c,f,a){var d=f?this._leftIcons:this._rightIcons;var e=this._countVisibleIcons(bobj.slice(d,0,a))*this._ICON_WIDTH-1;var b={display:c.isVisible?"block":"none"};if(f){if(this.expandImgPos==="left"){e+=this._ICON_WIDTH}b.left=e+"px"}else{if(this.expandImgPos==="right"){e+=this._ICON_WIDTH}b.right=e+"px"}return b};bobj.crv.StackedTab._getTextCtnStyle=function(b){var e=0;var a=0;var d=this._ICON_WIDTH;if(this.expandImgPos==="left"){e+=d}else{a+=d}e+=this._countVisibleIcons(this._leftIcons)*d;a+=this._countVisibleIcons(this._rightIcons)*d;e=Math.max(e,2);a=Math.max(a,2);var c={};if(b){c.marginLeft=e+"px";c.marginRight=a+"px"}else{c["margin-left"]=e+"px";c["margin-right"]=a+"px"}if(MochiKit.Base.isIE()&&bobj.isQuirksMode()){c.width="100%"}return c};bobj.crv.StackedTab.addIcon=function(d,c,b,f,h,a,g){g=g||bobj.uniqueId();var e={url:d,dx:c,dy:b,tooltip:f,isVisible:a,id:g};if(h){this._leftIcons.push(e)}else{this._rightIcons.push(e)}return g};bobj.crv.StackedTab._findIcon=function(e){var d=this._leftIcons;do{for(var b=0,a=d.length;b<a;++b){var c=d[b];if(c.id==e){return c}}d=(d===this._leftIcons)?this._rightIcons:null}while(d);return null};bobj.crv.StackedTab.showIcon=function(d){var b=this._findIcon(d);if(b){b.isVisible=true;var c=document.getElementById(d);if(c){var a=c.style.display!="block";c.style.display="block";if(a){this._paintLabel()}}}};bobj.crv.StackedTab.isIconShowing=function(b){var a=this._findIcon(b);if(a){return a.isVisible}return false};bobj.crv.StackedTab.hideIcon=function(d){var b=this._findIcon(d);if(b){b.isVisible=false;var c=document.getElementById(d);if(c){var a=c.style.display!="none";c.style.display="none";if(a){this._paintLabel()}}}};bobj.crv.StackedTab.setIconTooltip=function(d,b){var a=this._findIcon(d);if(a){a.tooltip=b;var c=document.getElementById(d);if(c){c.title=b}}};bobj.crv.StackedTab._paintLabel=function(){var d=this._leftIcons;do{for(var b=0,a=d.length;b<a;++b){var c=d[b];var e=document.getElementById(c.id);if(e){MochiKit.Base.update(e.style,this._getIconCtnStyle(c,d===this._leftIcons,b))}}d=(d===this._leftIcons)?this._rightIcons:null}while(d);MochiKit.Base.update(this._textCtn.style,this._getTextCtnStyle(true))};bobj.crv.StackedTab.resize=function(a){if(this._labelCtn){var b=!_saf;bobj.setOuterSize(this._labelCtn,a,b)}if(this._content){this._content.resize(a)}bobj.setOuterSize(this.layer,a)};bobj.crv.StackedTab.setContent=function(a){this._content=a};bobj.crv.StackedTab.getContent=function(){return this._content};bobj.crv.StackedTab.expand=function(){if(!this.isExpanded()){changeOffset(this._expandImg,16,16);var a=MochiKit.Base.partial(this.expandCB||MochiKit.Base.noop,this);if(this.isAnimated){var b={duration:0.2,afterFinish:a};if(MochiKit.Base.isIE()){b.restoreAfterFinish=false;b.scaleContent=true}MochiKit.Visual.blindDown(this._contentCtn,b);this.resize()}else{this._contentCtn.style.display="";this.resize();a()}}};bobj.crv.StackedTab.isExpanded=function(){return this._contentCtn.style.display!="none"};bobj.crv.StackedTab.collapse=function(){changeOffset(this._expandImg,0,16);var a=MochiKit.Base.partial(this.collapseCB||MochiKit.Base.noop,this);if(this.isAnimated){MochiKit.Visual.blindUp(this._contentCtn,{duration:0.2,afterFinish:a,scaleContent:true})}else{this._contentCtn.style.display="none";a()}};bobj.crv.StackedTab._onExpandClick=function(b){var a=getWidgetFromID(b);if(a.isExpanded()){a.collapse()}else{a.expand()}};bobj.crv.StackedTab._onMouseOver=function(b){var a=getWidgetFromID(b);MochiKit.DOM.addElementClass(a._labelCtn,"stackedTabLabelHover")};bobj.crv.StackedTab._onMouseOut=function(b){var a=getWidgetFromID(b);MochiKit.DOM.removeElementClass(a._labelCtn,"stackedTabLabelHover")};if(!MochiKit.Visual.Scale.prototype._crvSetDimensions){MochiKit.Visual.Scale.prototype._crvSetDimensions=MochiKit.Visual.Scale.prototype.setDimensions;MochiKit.Visual.Scale.prototype.setDimensions=function(a,b){a=Math.round(a)||1;b=Math.round(b)||1;MochiKit.Visual.Scale.prototype._crvSetDimensions.call(this,a,b)}};