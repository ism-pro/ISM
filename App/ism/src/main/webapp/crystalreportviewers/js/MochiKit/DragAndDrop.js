if(typeof(dojo)!="undefined"){dojo.provide("MochiKit.DragAndDrop");dojo.require("MochiKit.Base");dojo.require("MochiKit.DOM");dojo.require("MochiKit.Iter");dojo.require("MochiKit.Visual");dojo.require("MochiKit.Signal")}if(typeof(JSAN)!="undefined"){JSAN.use("MochiKit.Base",[]);JSAN.use("MochiKit.DOM",[]);JSAN.use("MochiKit.Visual",[]);JSAN.use("MochiKit.Iter",[]);JSAN.use("MochiKit.Signal",[])}try{if(typeof(MochiKit.Base)=="undefined"||typeof(MochiKit.DOM)=="undefined"||typeof(MochiKit.Visual)=="undefined"||typeof(MochiKit.Signal)=="undefined"||typeof(MochiKit.Iter)=="undefined"){throw""}}catch(e){throw"MochiKit.DragAndDrop depends on MochiKit.Base, MochiKit.DOM, MochiKit.Visual, MochiKit.Signal and MochiKit.Iter!"}if(typeof(MochiKit.DragAndDrop)=="undefined"){MochiKit.DragAndDrop={}}MochiKit.DragAndDrop.NAME="MochiKit.DragAndDrop";MochiKit.DragAndDrop.VERSION="1.4";MochiKit.DragAndDrop.__repr__=function(){return"["+this.NAME+" "+this.VERSION+"]"};MochiKit.DragAndDrop.toString=function(){return this.__repr__()};MochiKit.DragAndDrop.EXPORT=["Droppable","Draggable"];MochiKit.DragAndDrop.EXPORT_OK=["Droppables","Draggables"];MochiKit.DragAndDrop.Droppables={drops:[],remove:function(a){this.drops=MochiKit.Base.filter(function(b){return b.element!=MochiKit.DOM.getElement(a)},this.drops)},register:function(a){this.drops.push(a)},unregister:function(a){this.drops=MochiKit.Base.filter(function(b){return b!=a},this.drops)},prepare:function(a){MochiKit.Base.map(function(b){if(b.isAccepted(a)){if(b.options.activeclass){MochiKit.DOM.addElementClass(b.element,b.options.activeclass)}b.options.onactive(b.element,a)}},this.drops)},findDeepestChild:function(a){deepest=a[0];for(i=1;i<a.length;++i){if(MochiKit.DOM.isParent(a[i].element,deepest.element)){deepest=a[i]}}return deepest},show:function(a,b){if(!this.drops.length){return}var c=[];if(this.last_active){this.last_active.deactivate()}MochiKit.Iter.forEach(this.drops,function(d){if(d.isAffected(a,b)){c.push(d)}});if(c.length>0){drop=this.findDeepestChild(c);MochiKit.Position.within(drop.element,a.page.x,a.page.y);drop.options.onhover(b,drop.element,MochiKit.Position.overlap(drop.options.overlap,drop.element));drop.activate()}},fire:function(b,a){if(!this.last_active){return}MochiKit.Position.prepare();if(this.last_active.isAffected(b.mouse(),a)){this.last_active.options.ondrop(a,this.last_active.element,b)}},reset:function(a){MochiKit.Base.map(function(b){if(b.options.activeclass){MochiKit.DOM.removeElementClass(b.element,b.options.activeclass)}b.options.ondesactive(b.element,a)},this.drops);if(this.last_active){this.last_active.deactivate()}}};MochiKit.DragAndDrop.Droppable=function(b,a){this.__init__(b,a)};MochiKit.DragAndDrop.Droppable.prototype={__class__:MochiKit.DragAndDrop.Droppable,__init__:function(f,c){var g=MochiKit.DOM;var a=MochiKit.Base;this.element=g.getElement(f);this.options=a.update({greedy:true,hoverclass:null,activeclass:null,hoverfunc:a.noop,accept:null,onactive:a.noop,ondesactive:a.noop,onhover:a.noop,ondrop:a.noop,containment:[],tree:false},c||{});this.options._containers=[];a.map(MochiKit.Base.bind(function(b){this.options._containers.push(g.getElement(b))},this),this.options.containment);g.makePositioned(this.element);MochiKit.DragAndDrop.Droppables.register(this)},isContained:function(a){if(this._containers){var b;if(this.options.tree){b=a.treeNode}else{b=a.parentNode}return MochiKit.Iter.some(this._containers,function(d){return b==d})}else{return true}},isAccepted:function(a){return((!this.options.accept)||MochiKit.Iter.some(this.options.accept,function(b){return MochiKit.DOM.hasElementClass(a,b)}))},isAffected:function(a,b){return((this.element!=b)&&this.isContained(b)&&this.isAccepted(b)&&MochiKit.Position.within(this.element,a.page.x,a.page.y))},deactivate:function(){if(this.options.hoverclass){MochiKit.DOM.removeElementClass(this.element,this.options.hoverclass)}this.options.hoverfunc(this.element,false);MochiKit.DragAndDrop.Droppables.last_active=null},activate:function(){if(this.options.hoverclass){MochiKit.DOM.addElementClass(this.element,this.options.hoverclass)}this.options.hoverfunc(this.element,true);MochiKit.DragAndDrop.Droppables.last_active=this},destroy:function(){MochiKit.DragAndDrop.Droppables.unregister(this)},repr:function(){return"["+this.__class__.NAME+", options:"+MochiKit.Base.repr(this.options)+"]"}};MochiKit.DragAndDrop.Draggables={drags:[],observers:[],register:function(a){if(this.drags.length===0){var b=MochiKit.Signal.connect;this.eventMouseUp=b(document,"onmouseup",this,this.endDrag);this.eventMouseMove=b(document,"onmousemove",this,this.updateDrag);this.eventKeypress=b(document,"onkeypress",this,this.keyPress)}this.drags.push(a)},unregister:function(a){this.drags=MochiKit.Base.filter(function(c){return c!=a},this.drags);if(this.drags.length===0){var b=MochiKit.Signal.disconnect;b(this.eventMouseUp);b(this.eventMouseMove);b(this.eventKeypress)}},activate:function(a){window.focus();this.activeDraggable=a},deactivate:function(){this.activeDraggable=null},updateDrag:function(a){if(!this.activeDraggable){return}var b=a.mouse();if(this._lastPointer&&(MochiKit.Base.repr(this._lastPointer.page)==MochiKit.Base.repr(b.page))){return}this._lastPointer=b;this.activeDraggable.updateDrag(a,b)},endDrag:function(a){if(!this.activeDraggable){return}this._lastPointer=null;this.activeDraggable.endDrag(a);this.activeDraggable=null},keyPress:function(a){if(this.activeDraggable){this.activeDraggable.keyPress(a)}},addObserver:function(a){this.observers.push(a);this._cacheObserverCallbacks()},removeObserver:function(a){this.observers=MochiKit.Base.filter(function(b){return b.element!=a},this.observers);this._cacheObserverCallbacks()},notify:function(b,a,c){if(this[b+"Count"]>0){MochiKit.Base.map(function(d){if(d[b]){d[b](b,a,c)}},this.observers)}},_cacheObserverCallbacks:function(){var a=MochiKit.Base;var c=MochiKit.DragAndDrop.Draggables;a.map(function(b){c[b+"Count"]=a.filter(function(d){return d[b]},c.observers).length},["onStart","onEnd","onDrag"])}};MochiKit.DragAndDrop.Draggable=function(b,a){this.__init__(b,a)};MochiKit.DragAndDrop.Draggable.prototype={__class__:MochiKit.DragAndDrop.Draggable,__init__:function(g,f){var c=MochiKit.Visual;var a=MochiKit.Base;f=a.update({handle:false,starteffect:function(b){this._savedOpacity=MochiKit.DOM.getOpacity(b)||1;new c.Opacity(b,{duration:0.2,from:this._savedOpacity,to:0.7})},reverteffect:function(k,d,b){var j=Math.sqrt(Math.abs(d^2)+Math.abs(b^2))*0.02;return new c.Move(k,{x:-b,y:-d,duration:j})},endeffect:function(b){new c.Opacity(b,{duration:0.2,from:0.7,to:this._savedOpacity})},onchange:a.noop,zindex:1000,revert:false,scroll:false,scrollSensitivity:20,scrollSpeed:15,snap:false},f||{});var h=MochiKit.DOM;this.element=h.getElement(g);if(f.handle&&(typeof(f.handle)=="string")){this.handle=h.getFirstElementByTagAndClassName(null,f.handle,this.element)}if(!this.handle){this.handle=h.getElement(f.handle)}if(!this.handle){this.handle=this.element}if(f.scroll&&!f.scroll.scrollTo&&!f.scroll.outerHTML){f.scroll=h.getElement(f.scroll)}h.makePositioned(this.element);this.delta=this.currentDelta();this.options=f;this.dragging=false;this.eventMouseDown=MochiKit.Signal.connect(this.handle,"onmousedown",this,this.initDrag);MochiKit.DragAndDrop.Draggables.register(this)},destroy:function(){MochiKit.Signal.disconnect(this.eventMouseDown);MochiKit.DragAndDrop.Draggables.unregister(this)},currentDelta:function(){var a=MochiKit.DOM.getStyle;return[parseInt(a(this.element,"left")||"0"),parseInt(a(this.element,"top")||"0")]},initDrag:function(a){if(!a.mouse().button.left){return}var c=a.target;if(c.tagName&&(c.tagName=="INPUT"||c.tagName=="SELECT"||c.tagName=="OPTION"||c.tagName=="BUTTON"||c.tagName=="TEXTAREA")){return}if(this._revert){this._revert.cancel();this._revert=null}var b=a.mouse();var d=MochiKit.Position.cumulativeOffset(this.element);this.offset=[b.page.x-d.x,b.page.y-d.y];MochiKit.DragAndDrop.Draggables.activate(this);a.stop()},startDrag:function(b){this.dragging=true;if(this.options.selectclass){MochiKit.DOM.addElementClass(this.element,this.options.selectclass)}if(this.options.zindex){this.originalZ=parseInt(MochiKit.DOM.getStyle(this.element,"z-index")||"0");this.element.style.zIndex=this.options.zindex}if(this.options.ghosting){this._clone=this.element.cloneNode(true);this.ghostPosition=MochiKit.Position.absolutize(this.element);this.element.parentNode.insertBefore(this._clone,this.element)}if(this.options.scroll){if(this.options.scroll==window){var a=this._getWindowScroll(this.options.scroll);this.originalScrollLeft=a.left;this.originalScrollTop=a.top}else{this.originalScrollLeft=this.options.scroll.scrollLeft;this.originalScrollTop=this.options.scroll.scrollTop}}MochiKit.DragAndDrop.Droppables.prepare(this.element);MochiKit.DragAndDrop.Draggables.notify("onStart",this,b);if(this.options.starteffect){this.options.starteffect(this.element)}},updateDrag:function(b,g){if(!this.dragging){this.startDrag(b)}MochiKit.Position.prepare();MochiKit.DragAndDrop.Droppables.show(g,this.element);MochiKit.DragAndDrop.Draggables.notify("onDrag",this,b);this.draw(g);this.options.onchange(this);if(this.options.scroll){this.stopScrolling();var f,d;if(this.options.scroll==window){var a=this._getWindowScroll(this.options.scroll);f=new MochiKit.Style.Coordinates(a.left,a.top);d=new MochiKit.Style.Coordinates(a.left+a.width,a.top+a.height)}else{f=MochiKit.Position.page(this.options.scroll);f.x+=this.options.scroll.scrollLeft;f.y+=this.options.scroll.scrollTop;d=new MochiKit.Style.Coordinates(f.x+this.options.scroll.offsetWidth,f.y+this.options.scroll.offsetHeight)}var c=[0,0];if(g.page.x>(d.x-this.options.scrollSensitivity)){c[0]=g.page.x-(d.x-this.options.scrollSensitivity)}else{if(g.page.x<(f.x+this.options.scrollSensitivity)){c[0]=g.page.x-(f.x+this.options.scrollSensitivity)}}if(g.page.y>(d.y-this.options.scrollSensitivity)){c[1]=g.page.y-(d.y-this.options.scrollSensitivity)}else{if(g.page.y<(f.y+this.options.scrollSensitivity)){c[1]=g.page.y-(f.y+this.options.scrollSensitivity)}}this.startScrolling(c)}if(MochiKit.Base.isSafari()){window.scrollBy(0,0)}b.stop()},finishDrag:function(b,g){var c=MochiKit.DragAndDrop;this.dragging=false;if(this.options.selectclass){MochiKit.DOM.removeElementClass(this.element,this.options.selectclass)}if(this.options.ghosting){MochiKit.Position.relativize(this.element,this.ghostPosition);MochiKit.DOM.removeElement(this._clone);this._clone=null}if(g){c.Droppables.fire(b,this.element)}c.Draggables.notify("onEnd",this,b);var a=this.options.revert;if(a&&typeof(a)=="function"){a=a(this.element)}var f=this.currentDelta();if(a&&this.options.reverteffect){this._revert=this.options.reverteffect(this.element,f[1]-this.delta[1],f[0]-this.delta[0])}else{this.delta=f}if(this.options.zindex){this.element.style.zIndex=this.originalZ}if(this.options.endeffect){this.options.endeffect(this.element)}c.Draggables.deactivate();c.Droppables.reset(this.element)},keyPress:function(a){if(a.keyString!="KEY_ESCAPE"){return}this.finishDrag(a,false);a.stop()},endDrag:function(a){if(!this.dragging){return}this.stopScrolling();this.finishDrag(a,true);a.stop()},draw:function(a){var h=MochiKit.Position.cumulativeOffset(this.element);var g=this.currentDelta();h.x-=g[0];h.y-=g[1];if(this.options.scroll&&!this.options.scroll.scrollTo){h.x-=this.options.scroll.scrollLeft-this.originalScrollLeft;h.y-=this.options.scroll.scrollTop-this.originalScrollTop}var f=[a.page.x-h.x-this.offset[0],a.page.y-h.y-this.offset[1]];if(this.options.snap){if(typeof(this.options.snap)=="function"){f=this.options.snap(f[0],f[1])}else{if(this.options.snap instanceof Array){var b=-1;f=MochiKit.Base.map(MochiKit.Base.bind(function(d){b+=1;return Math.round(d/this.options.snap[b])*this.options.snap[b]},this),f)}else{f=MochiKit.Base.map(MochiKit.Base.bind(function(d){return Math.round(d/this.options.snap)*this.options.snap},this),f)}}}var c=this.element.style;if((!this.options.constraint)||(this.options.constraint=="horizontal")){c.left=f[0]+"px"}if((!this.options.constraint)||(this.options.constraint=="vertical")){c.top=f[1]+"px"}if(c.visibility=="hidden"){c.visibility=""}},stopScrolling:function(){if(this.scrollInterval){clearInterval(this.scrollInterval);this.scrollInterval=null}},startScrolling:function(a){if(!a[0]||!a[1]){return}this.scrollSpeed=[a[0]*this.options.scrollSpeed,a[1]*this.options.scrollSpeed];this.lastScrolled=new Date();this.scrollInterval=setInterval(MochiKit.Base.bind(this.scroll,this),10)},scroll:function(){var b=new Date();var f=b-this.lastScrolled;this.lastScrolled=b;if(this.options.scroll==window){var a=this._getWindowScroll(this.options.scroll);if(this.scrollSpeed[0]||this.scrollSpeed[1]){var c=f/1000;this.options.scroll.scrollTo(a.left+c*this.scrollSpeed[0],a.top+c*this.scrollSpeed[1])}}else{this.options.scroll.scrollLeft+=this.scrollSpeed[0]*f/1000;this.options.scroll.scrollTop+=this.scrollSpeed[1]*f/1000}var c=MochiKit.DragAndDrop;MochiKit.Position.prepare();c.Droppables.show(c.Draggables._lastPointer,this.element);this.draw(c.Draggables._lastPointer);this.options.onchange(this)},_getWindowScroll:function(w){var T,L,W,H;with(w.document){if(w.document.documentElement&&documentElement.scrollTop){T=documentElement.scrollTop;L=documentElement.scrollLeft}else{if(w.document.body){T=body.scrollTop;L=body.scrollLeft}}if(w.innerWidth){W=w.innerWidth;H=w.innerHeight}else{if(w.document.documentElement&&documentElement.clientWidth){W=documentElement.clientWidth;H=documentElement.clientHeight}else{W=body.offsetWidth;H=body.offsetHeight}}}return{top:T,left:L,width:W,height:H}},repr:function(){return"["+this.__class__.NAME+", options:"+MochiKit.Base.repr(this.options)+"]"}};MochiKit.DragAndDrop.__new__=function(){MochiKit.Base.nameFunctions(this);this.EXPORT_TAGS={":common":this.EXPORT,":all":MochiKit.Base.concat(this.EXPORT,this.EXPORT_OK)}};MochiKit.DragAndDrop.__new__();MochiKit.Base._exportSymbols(this,MochiKit.DragAndDrop);