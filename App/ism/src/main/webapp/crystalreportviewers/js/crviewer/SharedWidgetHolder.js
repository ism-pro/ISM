if(typeof(bobj.crv.SharedWidgetHolder)=="undefined"){bobj.crv.SharedWidgetHolder={};bobj.crv.SharedWidgetHolder._registry={}}bobj.crv.newSharedWidgetHolder=function(b){var d=MochiKit.Base;var a=MochiKit.Signal;b=d.update({id:bobj.uniqueId(),group:"SharedWidgetHolder",width:null,height:null,resizeWidget:true},b);var c=newWidget(b.id);c.widgetType="SharedWidgetHolder";bobj.fillIn(c,b);c._setVisible=c.show;c._resizeHolder=c.resize;c._initHolder=c.init;d.update(c,bobj.crv.SharedWidgetHolder);c._register();return c};bobj.crv.SharedWidgetHolder.init=function(){this._initHolder();var a=this._regInfo;if(a.managedWidget&&this===a.visibleHolder){a.managedWidget.init();this.resize()}};bobj.crv.SharedWidgetHolder.getHTML=function(){var b=bobj.isNumber;var g=bobj.isString;var e=this.isHoldingWidget()?"visible":"hidden";var f=this.isHoldingWidget()?this._regInfo.managedWidget.getHTML():"";var d={visibility:e};var c=this.width;if(b(c)){c=c+"px"}if(g(c)){d.width=c}var a=this.height;if(b(a)){a=a+"px"}if(g(a)){d.height=a}return bobj.html.DIV({id:this.id,style:d},f)};bobj.crv.SharedWidgetHolder._register=function(){var a=bobj.crv.SharedWidgetHolder._registry;var b=a[this.group];if(!b){b={managedWidget:null,visibleHolder:this};a[this.group]=b}this._regInfo=b};bobj.crv.SharedWidgetHolder.isHoldingWidget=function(){var a=this._regInfo;return((a.visibleHolder===this)&&a.managedWidget)};bobj.crv.SharedWidgetHolder.getManagedWidget=function(){return this._regInfo.managedWidget};bobj.crv.SharedWidgetHolder.setManagedWidget=function(c){var d=this._regInfo;var b=d.managedWidget;d.managedWidget=c;if(b&&b.layer){MochiKit.DOM.removeElement(b.layer)}if(!d.visibleHolder){d.visibleHolder=this}var a=d.visibleHolder;if(a.layer&&c&&c.layer){a.layer.appendChild(c.layer);a.resize()}};bobj.crv.SharedWidgetHolder.addChild=bobj.crv.SharedWidgetHolder.setManagedWidget;bobj.crv.SharedWidgetHolder.show=function(a){var b=this._regInfo;if(a&&(b.visibleHolder!==this)&&b.managedWidget){this.layer.appendChild(b.managedWidget.layer);b.visibleHolder._setVisible(false);b.visibleHolder=this;this.resize()}this._setVisible(a)};bobj.crv.SharedWidgetHolder.resize=function(a,b){this._resizeHolder(a,b);if(this.resizeWidget&&this.isHoldingWidget()){this._regInfo.managedWidget.resize(this.getWidth(),this.getHeight())}};