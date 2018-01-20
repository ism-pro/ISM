/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
PrimeFaces.widget.Desk = PrimeFaces.widget.BaseWidget.extend({
    ///
    /// Init
    init: function (a) {
        this._super(a);
        this.naviconbtn = $(this.jqId + "_naviconbtn");
        this.inputs = {};
        this.inputs.menu = $(this.jqId + "_menu");
        this.collapsed = $(this.jqId + "_collapsed");
        this.closable = $(this.jqId + "_closable");
        this.collapseSpeed = $(this.jqId + "_collapseSpeed").value || 750;
        this.closeSpeed = $(this.jqId + "_closeSpeed").value || 750;
        this.headered = $(this.jqId + "_headered").value || true;
        this.closer = $(this.jqId + "_desk_closer");
        this.collapser = $(this.jqId + "_desk_collapser");
        

        // Read arriving data
        this.closable = this.cfg.closable;
        this.closeSpeed = this.cfg.closeSpeed;
        this.collapsable = this.cfg.collapsable;
        this.collapsed = this.cfg.collapsed;
        this.collapseSpeed = this.cfg.collapseSpeed;
        this.headered = this.cfg.headered;
        this.menu = this.cfg.menu;
        this.navigatorTitled = this.cfg.navigatorTitled;

        // Render
        this.desk = $(this.jqId);
        this.sidemenu = $(this.jqId + ".c-desk .c-sidemenu");
        this.menus = $(this.jqId + ".c-desk .c-menu");
        this.menus.nav = $(this.jqId + ".c-desk .c-menunav");
        this.menus.active = $(this.jqId + ".c-menu.c-active");
        this.menus.content = $(this.jqId + ".c-desk .c-contentmenu");
        this.menus.def = $(this.jqId + ".c-desk .c-menu-def");
        this.header = $(this.jqId + ".c-content-bar");
        this.titlebarText = $(this.jqId + " .c-content-barTitle > span");


        // Init renderer
        this.collapsed = false;
        this.menus.def.addClass("collapse");
        $(this.jqId + ".c-desk .c-contentmenu" + ' [data-menu="' + this.menu + '"]').removeClass("collapse");
        if (this.headered === true) {
            this.header.show();
        } else {
            this.header.hide();
        }
        if ($(this.jqId + " .c-sidemenu i").hasClass('fa-1x')) {
            this.menus.content.css('left', '0px');
            this.menus.content.css('top', '-' + this.sidemenu.height() + 'px');
            this.menus.content.css('padding-left', '42px');
        }
        if ($(this.jqId + " .c-sidemenu i").hasClass('fa-2x')) {
            this.menus.content.css('left', '0px');
            this.menus.content.css('top', '-' + this.sidemenu.height() + 'px');
            this.menus.content.css('padding-left', '58px');
        }
        this.showContentMenu();



        // Enable all boostrap tooltip with delay and close
        $(this.jqId).tooltip({selector: '[data-toggle="tooltip"]', delay: {"show": 125, "hide": 5}});


        // Event
        this.bindEvents();
    },

    ///
    /// Build
    buildDesk: function () {
    },

    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// Bind Events
    bindEvents: function () {
        var a = this;
        this.bindNavigationEvents();
        this.bindCloserEvents();
        this.bindCollapseMenuEvent();
        this.bindMenuClickEvent();


    },
    bindCollapseMenuEvent: function () {
        var thisa = this;
        // On Click
        thisa.collapser.on("click", function () {
            $(thisa.jqId + "_desk_collapser > i").toggleClass("fa-rotate-90");
            thisa.collapsed = !thisa.collapsed;
            if (!thisa.collapsed) {
                $(thisa.jqId + ' [data-toggle="tooltip"]').tooltip('enable');
                $(thisa.jqId + "_desk_collapser").tooltip('show');
            } else {
                $(thisa.jqId + "_desk_collapser").tooltip('dispose');
                $(thisa.jqId + ' [data-toggle="tooltip"]').tooltip('disable');
            }

        });

        // On mouse over desk
        $(this.jqId)
                .on("mouseover", function (e) {
                    if (thisa.collapsed === true) {
                        //if (thisa.menus.content.css("display") !== 'inline-block') {
                        thisa.menus.content.show(thisa.collapseSpeed);
                        thisa.menus.content.removeClass("collapse");
                        thisa.menus.content.css('display', 'inline-block');
                        //}

                    }
                });
        // On mouse leave desk
        $(this.jqId)
                .on("mouseleave", function (e) {
                    if (thisa.collapsed === true) {
                        //if (thisa.menus.content.css("display") !== 'none') {
                        thisa.menus.content.addClass("collapse");
                        thisa.menus.content.hide(thisa.collapseSpeed);
                        //}
                    }
                });
    },
    bindMenuClickEvent: function () {
        var thisa = this;
        this.menus
                .on("click", function () {
                    thisa.menus.removeClass("c-active");    // Remove all active class
                    thisa.menus.def.addClass("collapse");   // Collabpse all content

                    $(this).addClass("c-active"); // set new active objet
                    $('[id = "' + this.id + "_content" + '"]').removeClass("collapse"); // uncollapse corresponding content
                    thisa.titlebarText.html(this.dataset.title); // set new title

                    // Set new active id
                    thisa.menus.activeOld = thisa.menus.active;
                    thisa.menus.active = this.dataset.menu;
                    thisa.cfg.menu = this.dataset.menu;     // set new menu config
                    thisa.inputs.menu.attr("value", this.dataset.menu);
                    
                    // Apply Event
                    thisa.fireItemSelectEvent();
                });
    },
    bindNavigationEvents: function (a) {
        var thisa = this;
        this.menus.nav.on("click", function () {
            thisa.toggleContentMenu();
        });
    },
    bindCloserEvents: function () {
        var thisa = this;
        thisa.closer.on("click", function () {
            thisa.hideContentMenu();
        });
    },
    /// ////////////////////////////////////////////////////////////////////////
    ///
    // Standard Function
    showContentMenu: function (e) {
        var thisa = this;
        thisa.menus.content.show(thisa.collapseSpeed);
        this.menus.content.css('display', 'inline-block');
    },
    hideContentMenu: function (e) {
        var thisa = this;
        thisa.menus.content.hide(thisa.collapseSpeed);
    },
    toggleContentMenu: function (e) {
        var thisa = this;
        if (thisa.menus.content.css('display') === 'none') {
            thisa.showContentMenu();
        } else {
            thisa.hideContentMenu();
        }
    }

    , 
    
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// Fire Eventn
   fireItemSelectEvent: function (c) {
        if (this.cfg.behaviors) {
            var a = this.cfg.behaviors.itemSelect;
            if (a) {
                var b = {params: [{name: this.id + "_menu", value: this.menus.active}]};
                a.call(this, b);
            }
        }
    }
});


$(function () {


});





