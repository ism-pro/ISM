//(function (a) {
//    a.Jcrop = function (e, C) {
//        var J = a.extend({}, a.Jcrop.defaults),
//                ag,
//                aj = navigator.userAgent.toLowerCase(),
//                ad = /msie/.test(aj), ai = /msie [1-6]\./.test(aj);
//        function n(av) {
//            return Math.round(av) + "px"
//        }
//        function E(av) {
//            return J.baseClass + "-" + av
//        }
//        function F() {
//            return a.fx.step.hasOwnProperty("backgroundColor")
//        }
//        function G(av) {
//            var aw = a(av).offset();
//            return[aw.left, aw.top]
//        }
//        function H(av) {
//            return[(av.pageX - ag[0]), (av.pageY - ag[1])]
//        }
//        function B(av) {
//            if (typeof (av) !== "object") {
//                av = {}
//            }
//            J = a.extend(J, av);
//            a.each(["onChange", "onSelect", "onRelease", "onDblClick"], function (aw, ax) {
//                if (typeof (J[ax]) !== "function") {
//                    J[ax] = function () {}
//                }
//            })
//        }
//        function g(ax, aA, az) {
//            ag = G(at);
//            Q.setCursor(ax === "move" ? ax : ax + "-resize");
//            if (ax === "move") {
//                return Q.activateHandlers(S(aA), r, az)
//            }
//            var av = ab.getFixed();
//            var aw = t(ax);
//            var ay = ab.getCorner(t(aw));
//            ab.setPressed(ab.getCorner(aw));
//            ab.setCurrent(ay);
//            Q.activateHandlers(I(ax, av), r, az)
//        }
//        function I(aw, av) {
//            return function (ax) {
//                if (!J.aspectRatio) {
//                    switch (aw) {
//                        case"e":
//                            ax[1] = av.y2;
//                            break;
//                        case"w":
//                            ax[1] = av.y2;
//                            break;
//                        case"n":
//                            ax[0] = av.x2;
//                            break;
//                        case"s":
//                            ax[0] = av.x2;
//                            break
//                    }
//                } else {
//                    switch (aw) {
//                        case"e":
//                            ax[1] = av.y + 1;
//                            break;
//                        case"w":
//                            ax[1] = av.y + 1;
//                            break;
//                        case"n":
//                            ax[0] = av.x + 1;
//                            break;
//                        case"s":
//                            ax[0] = av.x + 1;
//                            break
//                    }
//                }
//                ab.setCurrent(ax);
//                X.update()
//            }
//        }
//        function S(aw) {
//            var av = aw;
//            ar.watchKeys();
//            return function (ax) {
//                ab.moveOffset([ax[0] - av[0], ax[1] - av[1]]);
//                av = ax;
//                X.update()
//            }
//        }
//        function t(av) {
//            switch (av) {
//                case"n":
//                    return"sw";
//                case"s":
//                    return"nw";
//                case"e":
//                    return"nw";
//                case"w":
//                    return"ne";
//                case"ne":
//                    return"sw";
//                case"nw":
//                    return"se";
//                case"se":
//                    return"nw";
//                case"sw":
//                    return"ne"
//            }
//        }
//        function c(av) {
//            return function (aw) {
//                if (J.disabled) {
//                    return false
//                }
//                if ((av === "move") && !J.allowMove) {
//                    return false
//                }
//                ag = G(at);
//                s = true;
//                g(av, H(aw));
//                aw.stopPropagation();
//                aw.preventDefault();
//                return false
//            }
//        }
//        function V(az, aw, ay) {
//            var av = az.width(), ax = az.height();
//            if ((av > aw) && aw > 0) {
//                av = aw;
//                ax = (aw / az.width()) * az.height()
//            }
//            if ((ax > ay) && ay > 0) {
//                ax = ay;
//                av = (ay / az.height()) * az.width()
//            }
//            N = az.width() / av;
//            f = az.height() / ax;
//            az.width(av).height(ax)
//        }
//        function Z(av) {
//            return{x: av.x * N, y: av.y * f, x2: av.x2 * N, y2: av.y2 * f, w: av.w * N, h: av.h * f}
//        }
//        function r(aw) {
//            var av = ab.getFixed();
//            if ((av.w > J.minSelect[0]) && (av.h > J.minSelect[1])) {
//                X.enableHandles();
//                X.done()
//            } else {
//                X.release()
//            }
//            Q.setCursor(J.allowSelect ? "crosshair" : "default")
//        }
//        function af(av) {
//            if (J.disabled) {
//                return
//            }
//            if (!J.allowSelect) {
//                return
//            }
//            s = true;
//            ag = G(at);
//            X.disableHandles();
//            Q.setCursor("crosshair");
//            var aw = H(av);
//            ab.setPressed(aw);
//            X.update();
//            Q.activateHandlers(aq, r, av.type.substring(0, 5) === "touch");
//            ar.watchKeys();
//            av.stopPropagation();
//            av.preventDefault();
//            return false
//        }
//        function aq(av) {
//            ab.setCurrent(av);
//            X.update()
//        }
//        function ah() {
//            var av = a("<div></div>").addClass(E("tracker"));
//            if (ad) {
//                av.css({opacity: 0, backgroundColor: "white"})
//            }
//            return av
//        }
//        if (typeof (e) !== "object") {
//            e = a(e)[0]
//        }
//        if (typeof (C) !== "object") {
//            C = {}
//        }
//        B(C);
//        var j = {border: "none", visibility: "visible", margin: 0, padding: 0, position: "absolute", top: 0, left: 0};
//        var Y = a(e), al = true;
//        if (e.tagName == "IMG") {
//            if (Y[0].width != 0 && Y[0].height != 0) {
//                Y.width(Y[0].width);
//                Y.height(Y[0].height)
//            } else {
//                var w = new Image();
//                w.src = Y[0].src;
//                Y.width(w.width);
//                Y.height(w.height)
//            }
//            var at = Y.clone().removeAttr("id").css(j).show();
//            at.width(Y.width());
//            at.height(Y.height());
//            Y.after(at).hide()
//        } else {
//            at = Y.css(j).show();
//            al = false;
//            if (J.shade === null) {
//                J.shade = true
//            }
//        }
//        V(at, J.boxWidth, J.boxHeight);
//        var R = at.width(), P = at.height(), aa = a("<div />").width(R).height(P).addClass(E("holder")).css({position: "relative", backgroundColor: J.bgColor}).insertAfter(Y).append(at);
//        if (J.addClass) {
//            aa.addClass(J.addClass)
//        }
//        var K = a("<div />"), m = a("<div />").width("100%").height("100%").css({zIndex: 310, position: "absolute", overflow: "hidden"}), M = a("<div />").width("100%").height("100%").css("zIndex", 320), A = a("<div />").css({position: "absolute", zIndex: 600}).dblclick(function () {
//            var av = ab.getFixed();
//            J.onDblClick.call(i, av)
//        }).insertBefore(at).append(m, M);
//        if (al) {
//            K = a("<img />").attr("src", at.attr("src")).css(j).width(R).height(P), m.append(K)
//        }
//        if (ai) {
//            A.css({overflowY: "hidden"})
//        }
//        var v = J.boundary;
//        var b = ah().width(R + (v * 2)).height(P + (v * 2)).css({position: "absolute", top: n(-v), left: n(-v), zIndex: 290}).mousedown(af);
//        var ap = J.bgColor, ac = J.bgOpacity, z, an, q, U, N, f, p = true, s, D, ae;
//        ag = G(at);
//        var T = (function () {
//            function av() {
//                var aC = {}, aA = ["touchstart", "touchmove", "touchend"], aB = document.createElement("div"), az;
//                try {
//                    for (az = 0; az < aA.length; az++) {
//                        var ax = aA[az];
//                        ax = "on" + ax;
//                        var ay = (ax in aB);
//                        if (!ay) {
//                            aB.setAttribute(ax, "return;");
//                            ay = typeof aB[ax] == "function"
//                        }
//                        aC[aA[az]] = ay
//                    }
//                    return aC.touchstart && aC.touchend && aC.touchmove
//                } catch (aD) {
//                    return false
//                }
//            }
//            function aw() {
//                if ((J.touchSupport === true) || (J.touchSupport === false)) {
//                    return J.touchSupport
//                } else {
//                    return av()
//                }
//            }
//            return{createDragger: function (ax) {
//                    return function (ay) {
//                        if (J.disabled) {
//                            return false
//                        }
//                        if ((ax === "move") && !J.allowMove) {
//                            return false
//                        }
//                        ag = G(at);
//                        s = true;
//                        g(ax, H(T.cfilter(ay)), true);
//                        ay.stopPropagation();
//                        ay.preventDefault();
//                        return false
//                    }
//                }, newSelection: function (ax) {
//                    return af(T.cfilter(ax))
//                }, cfilter: function (ax) {
//                    ax.pageX = ax.originalEvent.changedTouches[0].pageX;
//                    ax.pageY = ax.originalEvent.changedTouches[0].pageY;
//                    return ax
//                }, isSupported: av, support: aw()}
//        }());
//        var ab = (function () {
//            var ax = 0, aI = 0, aw = 0, aH = 0, aA, ay;
//            function aC(aL) {
//                aL = az(aL);
//                aw = ax = aL[0];
//                aH = aI = aL[1]
//            }
//            function aB(aL) {
//                aL = az(aL);
//                aA = aL[0] - aw;
//                ay = aL[1] - aH;
//                aw = aL[0];
//                aH = aL[1]
//            }
//            function aK() {
//                return[aA, ay]
//            }
//            function av(aN) {
//                var aM = aN[0], aL = aN[1];
//                if (0 > ax + aM) {
//                    aM -= aM + ax
//                }
//                if (0 > aI + aL) {
//                    aL -= aL + aI
//                }
//                if (P < aH + aL) {
//                    aL += P - (aH + aL)
//                }
//                if (R < aw + aM) {
//                    aM += R - (aw + aM)
//                }
//                ax += aM;
//                aw += aM;
//                aI += aL;
//                aH += aL
//            }
//            function aD(aL) {
//                var aM = aJ();
//                switch (aL) {
//                    case"ne":
//                        return[aM.x2, aM.y];
//                    case"nw":
//                        return[aM.x, aM.y];
//                    case"se":
//                        return[aM.x2, aM.y2];
//                    case"sw":
//                        return[aM.x, aM.y2]
//                }
//            }
//            function aJ() {
//                if (!J.aspectRatio) {
//                    return aG()
//                }
//                var aN = J.aspectRatio, aU = J.minSize[0] / N, aM = J.maxSize[0] / N, aX = J.maxSize[1] / f, aO = aw - ax, aW = aH - aI, aP = Math.abs(aO), aQ = Math.abs(aW), aS = aP / aQ, aL, aT, aV, aR;
//                if (aM === 0) {
//                    aM = R * 10
//                }
//                if (aX === 0) {
//                    aX = P * 10
//                }
//                if (aS < aN) {
//                    aT = aH;
//                    aV = aQ * aN;
//                    aL = aO < 0 ? ax - aV : aV + ax;
//                    if (aL < 0) {
//                        aL = 0;
//                        aR = Math.abs((aL - ax) / aN);
//                        aT = aW < 0 ? aI - aR : aR + aI
//                    } else {
//                        if (aL > R) {
//                            aL = R;
//                            aR = Math.abs((aL - ax) / aN);
//                            aT = aW < 0 ? aI - aR : aR + aI
//                        }
//                    }
//                } else {
//                    aL = aw;
//                    aR = aP / aN;
//                    aT = aW < 0 ? aI - aR : aI + aR;
//                    if (aT < 0) {
//                        aT = 0;
//                        aV = Math.abs((aT - aI) * aN);
//                        aL = aO < 0 ? ax - aV : aV + ax
//                    } else {
//                        if (aT > P) {
//                            aT = P;
//                            aV = Math.abs(aT - aI) * aN;
//                            aL = aO < 0 ? ax - aV : aV + ax
//                        }
//                    }
//                }
//                if (aL > ax) {
//                    if (aL - ax < aU) {
//                        aL = ax + aU
//                    } else {
//                        if (aL - ax > aM) {
//                            aL = ax + aM
//                        }
//                    }
//                    if (aT > aI) {
//                        aT = aI + (aL - ax) / aN
//                    } else {
//                        aT = aI - (aL - ax) / aN
//                    }
//                } else {
//                    if (aL < ax) {
//                        if (ax - aL < aU) {
//                            aL = ax - aU
//                        } else {
//                            if (ax - aL > aM) {
//                                aL = ax - aM
//                            }
//                        }
//                        if (aT > aI) {
//                            aT = aI + (ax - aL) / aN
//                        } else {
//                            aT = aI - (ax - aL) / aN
//                        }
//                    }
//                }
//                if (aL < 0) {
//                    ax -= aL;
//                    aL = 0
//                } else {
//                    if (aL > R) {
//                        ax -= aL - R;
//                        aL = R
//                    }
//                }
//                if (aT < 0) {
//                    aI -= aT;
//                    aT = 0
//                } else {
//                    if (aT > P) {
//                        aI -= aT - P;
//                        aT = P
//                    }
//                }
//                return aF(aE(ax, aI, aL, aT))
//            }
//            function az(aL) {
//                if (aL[0] < 0) {
//                    aL[0] = 0
//                }
//                if (aL[1] < 0) {
//                    aL[1] = 0
//                }
//                if (aL[0] > R) {
//                    aL[0] = R
//                }
//                if (aL[1] > P) {
//                    aL[1] = P
//                }
//                return[Math.round(aL[0]), Math.round(aL[1])]
//            }
//            function aE(aO, aQ, aN, aP) {
//                var aS = aO, aR = aN, aM = aQ, aL = aP;
//                if (aN < aO) {
//                    aS = aN;
//                    aR = aO
//                }
//                if (aP < aQ) {
//                    aM = aP;
//                    aL = aQ
//                }
//                return[aS, aM, aR, aL]
//            }
//            function aG() {
//                var aM = aw - ax, aL = aH - aI, aN;
//                if (z && (Math.abs(aM) > z)) {
//                    aw = (aM > 0) ? (ax + z) : (ax - z)
//                }
//                if (an && (Math.abs(aL) > an)) {
//                    aH = (aL > 0) ? (aI + an) : (aI - an)
//                }
//                if (U / f && (Math.abs(aL) < U / f)) {
//                    aH = (aL > 0) ? (aI + U / f) : (aI - U / f)
//                }
//                if (q / N && (Math.abs(aM) < q / N)) {
//                    aw = (aM > 0) ? (ax + q / N) : (ax - q / N)
//                }
//                if (ax < 0) {
//                    aw -= ax;
//                    ax -= ax
//                }
//                if (aI < 0) {
//                    aH -= aI;
//                    aI -= aI
//                }
//                if (aw < 0) {
//                    ax -= aw;
//                    aw -= aw
//                }
//                if (aH < 0) {
//                    aI -= aH;
//                    aH -= aH
//                }
//                if (aw > R) {
//                    aN = aw - R;
//                    ax -= aN;
//                    aw -= aN
//                }
//                if (aH > P) {
//                    aN = aH - P;
//                    aI -= aN;
//                    aH -= aN
//                }
//                if (ax > R) {
//                    aN = ax - P;
//                    aH -= aN;
//                    aI -= aN
//                }
//                if (aI > P) {
//                    aN = aI - P;
//                    aH -= aN;
//                    aI -= aN
//                }
//                return aF(aE(ax, aI, aw, aH))
//            }
//            function aF(aL) {
//                return{x: aL[0], y: aL[1], x2: aL[2], y2: aL[3], w: aL[2] - aL[0], h: aL[3] - aL[1]}
//            }
//            return{flipCoords: aE, setPressed: aC, setCurrent: aB, getOffset: aK, moveOffset: av, getCorner: aD, getFixed: aJ}
//        }());
//        var d = (function () {
//            var aA = false, aF = a("<div />").css({position: "absolute", zIndex: 240, opacity: 0}), az = {top: aB(), left: aB().height(P), right: aB().height(P), bottom: aB()};
//            function aH(aI, aJ) {
//                az.left.css({height: n(aJ)});
//                az.right.css({height: n(aJ)})
//            }
//            function ax() {
//                return aC(ab.getFixed())
//            }
//            function aC(aI) {
//                az.top.css({left: n(aI.x), width: n(aI.w), height: n(aI.y)});
//                az.bottom.css({top: n(aI.y2), left: n(aI.x), width: n(aI.w), height: n(P - aI.y2)});
//                az.right.css({left: n(aI.x2), width: n(R - aI.x2)});
//                az.left.css({width: n(aI.x)})
//            }
//            function aB() {
//                return a("<div />").css({position: "absolute", backgroundColor: J.shadeColor || J.bgColor}).appendTo(aF)
//            }
//            function ay() {
//                if (!aA) {
//                    aA = true;
//                    aF.insertBefore(at);
//                    ax();
//                    X.setBgOpacity(1, 0, 1);
//                    K.hide();
//                    aE(J.shadeColor || J.bgColor, 1);
//                    if (X.isAwake()) {
//                        aw(J.bgOpacity, 1)
//                    } else {
//                        aw(1, 1)
//                    }
//                }
//            }
//            function aE(aI, aJ) {
//                h(av(), aI, aJ)
//            }
//            function aG() {
//                if (aA) {
//                    aF.remove();
//                    K.show();
//                    aA = false;
//                    if (X.isAwake()) {
//                        X.setBgOpacity(J.bgOpacity, 1, 1)
//                    } else {
//                        X.setBgOpacity(1, 1, 1);
//                        X.disableHandles()
//                    }
//                    h(aa, 0, 1)
//                }
//            }
//            function aw(aJ, aI) {
//                if (aA) {
//                    if (J.bgFade && !aI) {
//                        aF.animate({opacity: 1 - aJ}, {queue: false, duration: J.fadeTime})
//                    } else {
//                        aF.css({opacity: 1 - aJ})
//                    }
//                }
//            }
//            function aD() {
//                J.shade ? ay() : aG();
//                if (X.isAwake()) {
//                    aw(J.bgOpacity)
//                }
//            }
//            function av() {
//                return aF.children()
//            }
//            return{update: ax, updateRaw: aC, getShades: av, setBgColor: aE, enable: ay, disable: aG, resize: aH, refresh: aD, opacity: aw}
//        }());
//        var X = (function () {
//            var aG, aP = 370, aC = {}, aS = {}, ax = {}, az = false;
//            function aD(aW) {
//                var aX = a("<div />").css({position: "absolute", opacity: J.borderOpacity}).addClass(E(aW));
//                m.append(aX);
//                return aX
//            }
//            function ay(aW, aX) {
//                var aY = a("<div />").mousedown(c(aW)).css({cursor: aW + "-resize", position: "absolute", zIndex: aX}).addClass("ord-" + aW);
//                if (T.support) {
//                    aY.bind("touchstart.jcrop", T.createDragger(aW))
//                }
//                M.append(aY);
//                return aY
//            }
//            function aH(aW) {
//                var aX = J.handleSize, aY = ay(aW, aP++).css({opacity: J.handleOpacity}).addClass(E("handle"));
//                if (aX) {
//                    aY.width(aX).height(aX)
//                }
//                return aY
//            }
//            function aN(aW) {
//                return ay(aW, aP++).addClass("jcrop-dragbar")
//            }
//            function aK(aW) {
//                var aX;
//                for (aX = 0; aX < aW.length; aX++) {
//                    ax[aW[aX]] = aN(aW[aX])
//                }
//            }
//            function aO(aW) {
//                var aX, aY;
//                for (aY = 0; aY < aW.length; aY++) {
//                    switch (aW[aY]) {
//                        case"n":
//                            aX = "hline";
//                            break;
//                        case"s":
//                            aX = "hline bottom";
//                            break;
//                        case"e":
//                            aX = "vline right";
//                            break;
//                        case"w":
//                            aX = "vline";
//                            break
//                    }
//                    aC[aW[aY]] = aD(aX)
//                }
//            }
//            function aJ(aW) {
//                var aX;
//                for (aX = 0; aX < aW.length; aX++) {
//                    aS[aW[aX]] = aH(aW[aX])
//                }
//            }
//            function aF(aW, aX) {
//                if (!J.shade) {
//                    K.css({top: n(-aX), left: n(-aW)})
//                }
//                A.css({top: n(aX), left: n(aW)})
//            }
//            function aV(aW, aX) {
//                A.width(Math.round(aW)).height(Math.round(aX))
//            }
//            function aA() {
//                var aW = ab.getFixed();
//                ab.setPressed([aW.x, aW.y]);
//                ab.setCurrent([aW.x2, aW.y2]);
//                aT()
//            }
//            function aT(aW) {
//                if (aG) {
//                    return aE(aW)
//                }
//            }
//            function aE(aW) {
//                var aX = ab.getFixed();
//                aV(aX.w, aX.h);
//                aF(aX.x, aX.y);
//                if (J.shade) {
//                    d.updateRaw(aX)
//                }
//                aG || aU();
//                if (aW) {
//                    J.onSelect.call(i, Z(aX))
//                } else {
//                    J.onChange.call(i, Z(aX))
//                }
//            }
//            function aw(aX, aY, aW) {
//                if (!aG && !aY) {
//                    return
//                }
//                if (J.bgFade && !aW) {
//                    at.animate({opacity: aX}, {queue: false, duration: J.fadeTime})
//                } else {
//                    at.css("opacity", aX)
//                }
//            }
//            function aU() {
//                A.show();
//                if (J.shade) {
//                    d.opacity(ac)
//                } else {
//                    aw(ac, true)
//                }
//                aG = true
//            }
//            function aQ() {
//                aR();
//                A.hide();
//                if (J.shade) {
//                    d.opacity(1)
//                } else {
//                    aw(1)
//                }
//                aG = false;
//                J.onRelease.call(i)
//            }
//            function av() {
//                if (az) {
//                    M.show()
//                }
//            }
//            function aL() {
//                az = true;
//                if (J.allowResize) {
//                    M.show();
//                    return true
//                }
//            }
//            function aR() {
//                az = false;
//                M.hide()
//            }
//            function aM(aW) {
//                if (aW) {
//                    D = true;
//                    aR()
//                } else {
//                    D = false;
//                    aL()
//                }
//            }
//            function aI() {
//                aM(false);
//                aA()
//            }
//            if (J.dragEdges && a.isArray(J.createDragbars)) {
//                aK(J.createDragbars)
//            }
//            if (a.isArray(J.createHandles)) {
//                aJ(J.createHandles)
//            }
//            if (J.drawBorders && a.isArray(J.createBorders)) {
//                aO(J.createBorders)
//            }
//            a(document).bind("touchstart.jcrop-ios", function (aW) {
//                if (a(aW.currentTarget).hasClass("jcrop-tracker")) {
//                    aW.stopPropagation()
//                }
//            });
//            var aB = ah().mousedown(c("move")).css({cursor: "move", position: "absolute", zIndex: 360});
//            if (T.support) {
//                aB.bind("touchstart.jcrop", T.createDragger("move"))
//            }
//            m.append(aB);
//            aR();
//            return{updateVisible: aT, update: aE, release: aQ, refresh: aA, isAwake: function () {
//                    return aG
//                }, setCursor: function (aW) {
//                    aB.css("cursor", aW)
//                }, enableHandles: aL, enableOnly: function () {
//                    az = true
//                }, showHandles: av, disableHandles: aR, animMode: aM, setBgOpacity: aw, done: aI}
//        }());
//        var Q = (function () {
//            var aw = function () {}, ay = function () {}, ax = J.trackDocument;
//            function aF(aG) {
//                b.css({zIndex: 450});
//                if (aG) {
//                    a(document).bind("touchmove.jcrop", aE).bind("touchend.jcrop", aB)
//                } else {
//                    if (ax) {
//                        a(document).bind("mousemove.jcrop", av).bind("mouseup.jcrop", az)
//                    }
//                }
//            }
//            function aD() {
//                b.css({zIndex: 290});
//                a(document).unbind(".jcrop")
//            }
//            function av(aG) {
//                aw(H(aG));
//                return false
//            }
//            function az(aG) {
//                aG.preventDefault();
//                aG.stopPropagation();
//                if (s) {
//                    s = false;
//                    ay(H(aG));
//                    if (X.isAwake()) {
//                        J.onSelect.call(i, Z(ab.getFixed()))
//                    }
//                    aD();
//                    aw = function () {};
//                    ay = function () {}
//                }
//                return false
//            }
//            function aA(aH, aG, aI) {
//                s = true;
//                aw = aH;
//                ay = aG;
//                aF(aI);
//                return false
//            }
//            function aE(aG) {
//                aw(H(T.cfilter(aG)));
//                return false
//            }
//            function aB(aG) {
//                return az(T.cfilter(aG))
//            }
//            function aC(aG) {
//                b.css("cursor", aG)
//            }
//            if (!ax) {
//                b.mousemove(av).mouseup(az).mouseout(az)
//            }
//            at.before(b);
//            return{activateHandlers: aA, setCursor: aC}
//        }());
//        var ar = (function () {
//            var ay = a('<input type="radio" />').css({position: "fixed", left: "-120px", width: "12px"}).addClass("jcrop-keymgr"), aA = a("<div />").css({position: "absolute", overflow: "hidden"}).append(ay);
//            function aw() {
//                if (J.keySupport) {
//                    ay.show();
//                    ay.focus()
//                }
//            }
//            function az(aB) {
//                ay.hide()
//            }
//            function ax(aC, aB, aD) {
//                if (J.allowMove) {
//                    ab.moveOffset([aB, aD]);
//                    X.updateVisible(true)
//                }
//                aC.preventDefault();
//                aC.stopPropagation()
//            }
//            function av(aC) {
//                if (aC.ctrlKey || aC.metaKey) {
//                    return true
//                }
//                ae = aC.shiftKey ? true : false;
//                var aB = ae ? 10 : 1;
//                switch (aC.which) {
//                    case 37:
//                        ax(aC, -aB, 0);
//                        break;
//                    case 39:
//                        ax(aC, aB, 0);
//                        break;
//                    case 38:
//                        ax(aC, 0, -aB);
//                        break;
//                    case 40:
//                        ax(aC, 0, aB);
//                        break;
//                    case 27:
//                        if (J.allowSelect) {
//                            X.release()
//                        }
//                        break;
//                    case 9:
//                        return true
//                }
//                return false
//            }
//            if (J.keySupport) {
//                ay.keydown(av).blur(az);
//                if (ai || !J.fixedSupport) {
//                    ay.css({position: "absolute", left: "-20px"});
//                    aA.append(ay).insertBefore(at)
//                } else {
//                    ay.insertBefore(at)
//                }
//            }
//            return{watchKeys: aw}
//        }());
//        function l(av) {
//            aa.removeClass().addClass(E("holder")).addClass(av)
//        }
//        function u(aO, aC) {
//            var aI = aO[0] / N, ax = aO[1] / f, aH = aO[2] / N, aw = aO[3] / f;
//            if (D) {
//                return
//            }
//            var aG = ab.flipCoords(aI, ax, aH, aw), aM = ab.getFixed(), aJ = [aM.x, aM.y, aM.x2, aM.y2], az = aJ, ay = J.animationDelay, aL = aG[0] - aJ[0], aB = aG[1] - aJ[1], aK = aG[2] - aJ[2], aA = aG[3] - aJ[3], aF = 0, aD = J.swingSpeed;
//            aI = az[0];
//            ax = az[1];
//            aH = az[2];
//            aw = az[3];
//            X.animMode(true);
//            var av;
//            function aE() {
//                window.setTimeout(aN, ay)
//            }
//            var aN = (function () {
//                return function () {
//                    aF += (100 - aF) / aD;
//                    az[0] = Math.round(aI + ((aF / 100) * aL));
//                    az[1] = Math.round(ax + ((aF / 100) * aB));
//                    az[2] = Math.round(aH + ((aF / 100) * aK));
//                    az[3] = Math.round(aw + ((aF / 100) * aA));
//                    if (aF >= 99.8) {
//                        aF = 100
//                    }
//                    if (aF < 100) {
//                        ao(az);
//                        aE()
//                    } else {
//                        X.done();
//                        X.animMode(false);
//                        if (typeof (aC) === "function") {
//                            aC.call(i)
//                        }
//                    }
//                }
//            }());
//            aE()
//        }
//        function L(av) {
//            ao([av[0] / N, av[1] / f, av[2] / N, av[3] / f]);
//            J.onSelect.call(i, Z(ab.getFixed()));
//            X.enableHandles()
//        }
//        function ao(av) {
//            ab.setPressed([av[0], av[1]]);
//            ab.setCurrent([av[2], av[3]]);
//            X.update()
//        }
//        function k() {
//            return Z(ab.getFixed())
//        }
//        function am() {
//            return ab.getFixed()
//        }
//        function x(av) {
//            B(av);
//            O()
//        }
//        function y() {
//            J.disabled = true;
//            X.disableHandles();
//            X.setCursor("default");
//            Q.setCursor("default")
//        }
//        function W() {
//            J.disabled = false;
//            O()
//        }
//        function o() {
//            X.done();
//            Q.activateHandlers(null, null)
//        }
//        function ak() {
//            aa.remove();
//            Y.show();
//            Y.css("visibility", "visible");
//            a(e).removeData("Jcrop")
//        }
//        function au(aw, ax) {
//            X.release();
//            y();
//            var av = new Image();
//            av.onload = function () {
//                var ay = av.width;
//                var aA = av.height;
//                var aB = J.boxWidth;
//                var az = J.boxHeight;
//                at.width(ay).height(aA);
//                at.attr("src", aw);
//                K.attr("src", aw);
//                V(at, aB, az);
//                R = at.width();
//                P = at.height();
//                K.width(R).height(P);
//                b.width(R + (v * 2)).height(P + (v * 2));
//                aa.width(R).height(P);
//                d.resize(R, P);
//                W();
//                if (typeof (ax) === "function") {
//                    ax.call(i)
//                }
//            };
//            av.src = aw
//        }
//        function h(ay, av, aw) {
//            var ax = av || J.bgColor;
//            if (J.bgFade && F() && J.fadeTime && !aw) {
//                ay.animate({backgroundColor: ax}, {queue: false, duration: J.fadeTime})
//            } else {
//                ay.css("backgroundColor", ax)
//            }
//        }
//        function O(av) {
//            if (J.allowResize) {
//                if (av) {
//                    X.enableOnly()
//                } else {
//                    X.enableHandles()
//                }
//            } else {
//                X.disableHandles()
//            }
//            Q.setCursor(J.allowSelect ? "crosshair" : "default");
//            X.setCursor(J.allowMove ? "move" : "default");
//            if (J.hasOwnProperty("trueSize")) {
//                N = J.trueSize[0] / R;
//                f = J.trueSize[1] / P
//            }
//            if (J.hasOwnProperty("setSelect")) {
//                L(J.setSelect);
//                X.done();
//                delete (J.setSelect)
//            }
//            d.refresh();
//            if (J.bgColor != ap) {
//                h(J.shade ? d.getShades() : aa, J.shade ? (J.shadeColor || J.bgColor) : J.bgColor);
//                ap = J.bgColor
//            }
//            if (ac != J.bgOpacity) {
//                ac = J.bgOpacity;
//                if (J.shade) {
//                    d.refresh()
//                } else {
//                    X.setBgOpacity(ac)
//                }
//            }
//            z = J.maxSize[0] || 0;
//            an = J.maxSize[1] || 0;
//            q = J.minSize[0] || 0;
//            U = J.minSize[1] || 0;
//            if (J.hasOwnProperty("outerImage")) {
//                at.attr("src", J.outerImage);
//                delete (J.outerImage)
//            }
//            X.refresh()
//        }
//        if (T.support) {
//            b.bind("touchstart.jcrop", T.newSelection)
//        }
//        M.hide();
//        O(true);
//        var i = {setImage: au, animateTo: u, setSelect: L, setOptions: x, tellSelect: k, tellScaled: am, setClass: l, disable: y, enable: W, cancel: o, release: X.release, destroy: ak, focus: ar.watchKeys, getBounds: function () {
//                return[R * N, P * f]
//            }, getWidgetSize: function () {
//                return[R, P]
//            }, getScaleFactor: function () {
//                return[N, f]
//            }, getOptions: function () {
//                return J
//            }, ui: {holder: aa, selection: A}};
//        if (ad) {
//            aa.bind("selectstart", function () {
//                return false
//            })
//        }
//        Y.data("Jcrop", i);
//        return i
//    };
//    a.fn.Jcrop = function (b, d) {
//        var c;
//        this.each(function () {
//            if (a(this).data("Jcrop")) {
//                if (b === "api") {
//                    return a(this).data("Jcrop")
//                } else {
//                    a(this).data("Jcrop").setOptions(b)
//                }
//            } else {
//                if (this.tagName == "IMG") {
//                    a.Jcrop.Loader(this, function () {
//                        a(this).css({display: "block", visibility: "hidden"});
//                        c = a.Jcrop(this, b);
//                        if (a.isFunction(d)) {
//                            d.call(c)
//                        }
//                    })
//                } else {
//                    a(this).css({display: "block", visibility: "hidden"});
//                    c = a.Jcrop(this, b);
//                    if (a.isFunction(d)) {
//                        d.call(c)
//                    }
//                }
//            }
//        });
//        return this
//    };
//    a.Jcrop.Loader = function (f, g, c) {
//        var d = a(f), b = d[0];
//        function e() {
//            if (b.complete) {
//                d.unbind(".jcloader");
//                if (a.isFunction(g)) {
//                    g.call(b)
//                }
//            } else {
//                window.setTimeout(e, 50)
//            }
//        }
//        d.bind("load.jcloader", e).bind("error.jcloader", function (h) {
//            d.unbind(".jcloader");
//            if (a.isFunction(c)) {
//                c.call(b)
//            }
//        });
//        if (b.complete && a.isFunction(g)) {
//            d.unbind(".jcloader");
//            g.call(b)
//        }
//    };
//    a.Jcrop.defaults = {allowSelect: true, allowMove: true, allowResize: true, trackDocument: true, baseClass: "jcrop", addClass: null, bgColor: "black", bgOpacity: 0.6, bgFade: false, borderOpacity: 0.4, handleOpacity: 0.5, handleSize: null, aspectRatio: 0, keySupport: true, createHandles: ["n", "s", "e", "w", "nw", "ne", "se", "sw"], createDragbars: ["n", "s", "e", "w"], createBorders: ["n", "s", "e", "w"], drawBorders: true, dragEdges: true, fixedSupport: true, touchSupport: null, shade: null, boxWidth: 0, boxHeight: 0, boundary: 2, fadeTime: 400, animationDelay: 20, swingSpeed: 3, minSelect: [0, 0], maxSize: [0, 0], minSize: [0, 0], onChange: function () {}, onSelect: function () {}, onDblClick: function () {}, onRelease: function () {}}
//}(jQuery));
//



//PrimeFaces.widget.CropAvatar = PrimeFaces.widget.DeferredWidget.extend({
//    init: function (a) {
//        this._super(a);
//        this.image = $(PrimeFaces.escapeClientId(this.cfg.image));
//        this.jqCoords = $(this.jqId + "_coords");
//        var b = this;
//        this.cfg.onSelect = function (d) {
//            b.saveCoords(d)
//        };
//        this.cfg.onChange = function (d) {
//            b.saveCoords(d)
//        };
//        this.renderDeferred()
//    }, _render: function () {
//        this.image.Jcrop(this.cfg)
//    }, saveCoords: function (b) {
//        var a = b.x + "_" + b.y + "_" + b.w + "_" + b.h;
//        this.jqCoords.val(a)
//    }});



//PrimeFaces.widget.CropAvatar = PrimeFaces.widget.BaseWidget.extend({
//    ///
//    /// Init
//    init: function (a) {
//
//        this._super(a);
//        this.$container = $(this.jqId);
//
//        /// ///////////////////////////
//        // Get convenient object
//        this.$avatarView = this.$container.find('.avatar-view');
//        this.$avatar = this.$avatarView.find('img');
//        this.$avatarModal = this.$container.find(this.jqId + '_avatar-modal');
//        this.$loading = this.$container.find('.loading');
//
//        this.$avatarForm = this.$avatar.closest("form"); //this.$avatarModal.find('.avatar-form');
//        this.$avatarUpload = this.$avatarForm.find('.avatar-upload');
//        this.$avatarSrc = this.$avatarForm.find('.avatar-src');
//        this.$avatarData = this.$avatarForm.find('.avatar-data');
//        this.$avatarInput = this.$avatarForm.find('.avatar-input');
//        this.$avatarSave = this.$avatarForm.find('.avatar-save');
//        this.$avatarBtns = this.$avatarForm.find('.avatar-btns');
//
//        this.$avatarWrapper = this.$avatarModal.find('.avatar-wrapper');
//        this.$avatarPreview = this.$avatarModal.find('.avatar-preview');
//
//        // Construction
//        this.build(this);
//
//    },
//
//    ///
//    /// Build
//    support: {
//        fileList: !!$('<input type="file">').prop('files'),
//        blobURLs: !!window.URL && URL.createObjectURL,
//        formData: !!window.FormData
//    },
//
//    build: function () {
//        /// ///////////////////////////
//        // Setup object
//        this.support.datauri = this.support.fileList && this.support.blobURLs;
//        if (!this.support.formData) {
//            this.initIframe();
//        }
//
//        this.initTooltip();
//        this.initModal();
//        this.bindEvents();
//
//        // Config
//        this.$avatarView.css('width', this.cfg.boxWidth + 'px')
//                .css('height', this.cfg.boxHeight + 'px');
//
//    },
//    initTooltip: function () {
//        this.$avatarView.tooltip({
//            placement: 'bottom'
//        });
//    },
//    initModal: function () {
//        this.$avatarModal.modal({
//            show: false
//        });
//    },
//    initPreview: function () {
//        var url = this.$avatar.attr('src');
//        this.$avatarPreview.html('<img src="' + url + '">');
//    },
//
//    initIframe: function () {
//        console.log("Init Frame")
//        var target = 'upload-iframe-' + (new Date()).getTime();
//        var $iframe = $('<iframe>').attr({
//            name: target,
//            src: ''
//        });
//        var _this = this;
//
//        // Ready ifrmae
//        $iframe.one('load', function () {
//
//            // respond response
//            $iframe.on('load', function () {
//                var data;
//
//                try {
//                    data = $(this).contents().find('body').text();
//                } catch (e) {
//                    console.log(e.message);
//                }
//
//                if (data) {
//                    try {
//                        data = $.parseJSON(data);
//                    } catch (e) {
//                        console.log(e.message);
//                    }
//
//                    _this.submitDone(data);
//                } else {
//                    _this.submitFail('Image upload failed!');
//                }
//
//                _this.submitEnd();
//
//            });
//        });
//
//        this.$iframe = $iframe;
//        this.$avatarForm.attr('target', target).after($iframe.hide());
//    },
//
//    /// ////////////////////////////////////////////////////////////////////////
//    ///
//    /// Bind Events
//    bindEvents: function () {
//        var thisa = this;
//        this.$avatarView.on('click', $.proxy(this.click, this));
//        this.$avatarInput.on('change', $.proxy(this.change, this));
//        this.$avatarForm.on('submit', $.proxy(this.submit, this));
//        this.$avatarBtns.on('click', $.proxy(this.rotate, this));
//        this.$avatarSave.click(function (e) {
//            thisa.submit();
//        });
//    },
//    click: function () {
//        this.$avatarModal.modal('show');
//        this.initPreview();
//    },
//    change: function () {
//        var files, file;
//
//        if (this.support.datauri) {
//            files = this.$avatarInput.prop('files');
//            if (files.length > 0) {
//                file = files[0];
//                if (this.isImageFile(file)) {
//                    if (this.url) {
//                        URL.revokeObjectURL(this.url); // Revoke the old one
//                    }
//                    this.url = URL.createObjectURL(file);
//                    this.startCropper();
//                }
//            }
//        } else {
//            file = this.$avatarInput.val();
//
//            if (this.isImageFile(file)) {
//                this.syncUpload();
//            }
//        }
//    },
//    submit: function () {
//        console.log("submit")
//        if (!this.$avatarSrc.val() && !this.$avatarInput.val()) {
//            return false;
//        }
//
//        if (this.support.formData) {
//            //this.fireUploadEvent();
//            this.ajaxUpload();
//            //this.cropDone();
//            return false;
//        }
//    },
//    rotate: function (e) {
//        var data;
//
//        if (this.active) {
//            data = $(e.target).data();
//
//            if (data.method) {
//                this.$img.cropper(data.method, data.option);
//            }
//        }
//    },
//
//    /// ////////////////////////////////////////////////////////////////////////
//    ///
//    // Standard Function
//    isImageFile: function (file) {
//        console.log("isImageFile")
//        if (file.type) {
//            return /^image\/\w+$/.test(file.type);
//        } else {
//            return /\.(jpg|jpeg|png|gif)$/.test(file);
//        }
//    },
//    startCropper: function () {
//        console.log("start Cropper")
//        var _this = this;
//
//        if (this.active) {
//            console.log("start cropper : url : " + this.url)
//            this.$img.cropper('replace', this.url);
//        } else {
//            this.$img = $('<img src="' + this.url + '">');
//            this.$avatarWrapper.empty().html(this.$img);
//            this.$img.cropper({
//                aspectRatio: 1,
//                preview: this.$avatarPreview.selector,
//                crop: function (e) {
//                    var json = [
//                        '{"x":' + e.x,
//                        '"y":' + e.y,
//                        '"height":' + e.height,
//                        '"width":' + e.width,
//                        '"rotate":' + e.rotate + '}'
//                    ].join();
//
//                    _this.$avatarData.val(json);
//                }
//            });
//
//            this.active = true;
//        }
//
//        this.$avatarModal.one('hidden.bs.modal', function () {
//            _this.$avatarPreview.empty();
//            _this.stopCropper();
//        });
//    },
//    stopCropper: function () {
//
//        console.log("stop cropper")
//        if (this.active) {
//            this.$img.cropper('destroy');
//            this.$img.remove();
//            this.active = false;
//        }
//    },
//
//    /// ////////////////////////////////////////////////////////////////////////
//    ///
//    /// Fire Eventn
//    fireUploadEvent: function (c) {
//        console.log("fireUploadEvent")
//        var url = this.$avatarForm.attr('action');
//        var data = new FormData(this.$avatarForm[0]);
//        var _this = this;
//
//        if (this.cfg.behaviors) {
//            var a = this.cfg.behaviors.itemSelect;
//
//            if (a) {
//                var b = {params: [{name: this.id + "_newTab", value: a.attr("id")}, {name: this.id + "_tabindex", value: parseInt(a.index() / 2)}]};
//                a.call(this, b);
//            }
//        }
//    },
//
//    /// ////////////////////////////////////////////////////////////////////////
//    /// ////////////////////////////////////////////////////////////////////////
//    /// ////////////////////////////////////////////////////////////////////////
//    /// ////////////////////////////////////////////////////////////////////////
//
//    /// ////////////////////////////////////////////////////////////////////////
//    /// ////////////////////////////////////////////////////////////////////////
//    /// ////////////////////////////////////////////////////////////////////////
//
//
//
//
//
//
//    ajaxUpload: function () {
//
//        console.log("ajax upload")
//        var url = "/"; //this.$avatarForm.attr('action');
//        var data = new FormData(this.$avatarForm[0]);
//        var _this = this;
//        console.log(data)
//        $.ajax(url, {
//            type: 'post',
//            data: data,
//            dataType: 'json',
//            processData: false,
//            contentType: false,
//
//            beforeSend: function () {
//                _this.submitStart();
//            },
//
//            success: function (data) {
//                _this.submitDone(data);
//            },
//
//            error: function (XMLHttpRequest, textStatus, errorThrown) {
//                _this.submitFail(textStatus || errorThrown);
//            },
//
//            complete: function () {
//                _this.submitEnd();
//            }
//        });
//    },
//
//    syncUpload: function () {
//        console.log("syncUpload")
//        this.$avatarSave.click();
//    },
//
//    submitStart: function () {
//        console.log("submitStart")
//        this.$loading.fadeIn();
//    },
//
//    submitDone: function (data) {
//        console.log("submitDone")
//        console.log(data);
//
//        if ($.isPlainObject(data) && data.state === 200) {
//            if (data.result) {
//                this.url = data.result;
//
//                if (this.support.datauri || this.uploaded) {
//                    this.uploaded = false;
//                    this.cropDone();
//                } else {
//                    this.uploaded = true;
//                    this.$avatarSrc.val(this.url);
//                    this.startCropper();
//                }
//
//                this.$avatarInput.val('');
//            } else if (data.message) {
//                this.alert(data.message);
//            }
//        } else {
//            this.alert('Failed to response');
//        }
//    },
//
//    submitFail: function (msg) {
//        console.log("submitFail")
//        this.alert(msg);
//    },
//
//    submitEnd: function () {
//        console.log("submitFail")
//        this.$loading.fadeOut();
//    },
//
//    cropDone: function () {
//        //this.$avatarForm.get(0).reset();
//        this.$avatar.attr('src', this.url);
//        console.log(this.$avatar)
//        this.$avatar.attr('src', this.$img.cropper('getCroppedCanvas'));
//        this.stopCropper();
//        this.$avatarModal.modal('hide');
//        console.log(this.$img.cropper('getCroppedCanvas'))
//    },
//
//    alert: function (msg) {
//        var $alert = [
//            '<div class="alert alert-danger avatar-alert alert-dismissable">',
//            '<button type="button" class="close" data-dismiss="alert">&times;</button>',
//            msg,
//            '</div>'
//        ].join('');
//
//        this.$avatarUpload.after($alert);
//    }
//
//
//});




PrimeFaces.widget.InputCropper = PrimeFaces.widget.BaseWidget.extend({
///
/// Init
    init: function (a) {

        this._super(a);
        this.inputCropper = $(this.jqId);
        //
        this.input = {};
        this.input.name = $(this.jqId + ' .ic-crop-input-name');
        this.input.text = $(this.jqId + ' .ic-crop-input-name-text');
        this.input.in = $(this.jqId + ' .ic-crop-input-name-in');
        //
        this.img = {};
        this.img.dropdown = $(this.jqId + ' .ic-crop-dropdown-img');
        this.img.container = this.img.dropdown.find('.ic-crop-container');
        this.img.cropped = this.img.dropdown.find('.ic-crop-img-cropped');
        //
        this.dlg = {}
        this.dlg.modal = $(this.jqId).find('.modalDialog');
        this.dlg.input = this.dlg.modal.find('.form-control-file-sm.input-img');
        this.dlg.data = this.dlg.modal.find('.input-data');
        this.dlg.src = this.dlg.modal.find('.input-src');
        this.dlg.apply = this.dlg.modal.find('.btn-apply');
        this.dlg.loading = this.dlg.modal.find('.loading');
        //
        this.dlg.datas = {};
        this.dlg.datas.x = this.dlg.modal.find('.dataX');
        this.dlg.datas.y = this.dlg.modal.find('.dataY');
        this.dlg.datas.w = this.dlg.modal.find('.dataWidth');
        this.dlg.datas.h = this.dlg.modal.find('.dataHeight');
        this.dlg.datas.r = this.dlg.modal.find('.dataRotate');
        //
        this.dlg.opts = {};
        this.dlg.opts.drag = this.dlg.modal.find('.optDragMode a');
        this.dlg.opts.left = this.dlg.modal.find('.optRotateLeft a');
        this.dlg.opts.right = this.dlg.modal.find('.optRotateRight a');
        this.dlg.opts.inputZoomerValue = this.dlg.modal.find(".inputZoomerValue");
        this.dlg.opts.inputZoomerValueSaver = 0;
        this.dlg.opts.inputZoomer = this.dlg.modal.find(".inputZoomer");
        this.dlg.opts.inputZoomerHandler = this.dlg.modal.find(".inputZoomerHandler");
        this.dlg.opts.configs = this.dlg.modal.find('.optCropper a');
        this.dlg.opts.ratios = this.dlg.modal.find('.optApsectRatio a');
        this.preview = {}
        this.preview.wrapper = this.dlg.modal.find('.img-wrapper');
        this.preview.dragbox = this.dlg.modal.find('.cropper-drag-box');
        this.preview.previews = this.dlg.modal.find('.img-preview');
        this.preview.lg = this.dlg.modal.find('.preview-lg');
        this.preview.md = this.dlg.modal.find('.preview-md');
        this.preview.sm = this.dlg.modal.find('.preview-sm');
        // Config setup
        this.cfg.dragCrop = this.cfg.dragCrop || true;
        this.cfg.inputZoomerValue = this.cfg.inputZoomerValue || 0;
        // Build and bind
        this.initTooltip();
        this.initModal();
        this.initConfig();
        this.bindEvents();
    },
    ///
    ///
    /// Init
    ///
    ///
    initTooltip: function () {
        // Init gloabal tooltip
        $('[data-toggle="tooltip"]').tooltip();
        this.img.cropped.tooltip({
            placement: 'bottom'
        });
    },
    initModal: function () {
        this.dlg.modal.modal({
            show: false
        });
    },
    initPreview: function () {
//        var url = this.img.cropped.attr('src');
//        //console.log("init preview url : " + url)
//        if (url !== '') {
//            //this.dlgInputChange();
//            //$(this.jqId).find('.avatar-preview').html('<img src="' + this.url + '">');
//            //console.log(this.$img.cropper('data'));
//        }
//        console.log(this.dlg.opts..find('button'))
//        if(this.url!='undefined'){
//            this.dlg.rotors.find('button').attr('css', "disabled");
//        }else{
//            this.dlg.rotors.find('button').attr('css', "disabled");
//        }
    },
    initConfig: function () {
        this.dlg.opts.inputZoomerValue.val(this.cfg.inputZoomerValue);
        var _this = this;
        this.dlg.opts.inputZoomer.slider({
            min: -10, max: 10,
            value: this.cfg.inputZoomerValue,
            step: 0.01,
            slide: function (event, ui) {
                if (_this.active) {
                    _this.dlg.opts.inputZoomerValue.val(ui.value);
                    _this.zoom();
                }
            }
        });
        this.dlg.opts.inputZoomer.slider('disable');
    },
    ///
    ///
    /// Bind Events
    ///
    ///
    bindEvents: function () {
        // Event for input click to open window
        this.input.name.on('click', $.proxy(this.inputNameClick, this))
        // Event for overlay cropped
        this.input.name.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));
        this.img.container.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));
        this.img.cropped.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));
        // Dialog event
        this.dlg.input.change($.proxy(this.dlgInputChange, this));
        this.dlg.apply.on('click', $.proxy(this.dlgApplyClick, this));
        // Options
        this.dlg.opts.drag.on('click', $.proxy(this.setDragMode, this));
        this.dlg.opts.left.on('click', $.proxy(this.rotate, this));
        this.dlg.opts.right.on('click', $.proxy(this.rotate, this));
        this.dlg.opts.configs.on('click', $.proxy(this.configure, this));
        this.dlg.opts.ratios.on('click', $.proxy(this.setAspectRatio, this));
    },
    inputNameClick: function (a) {
        this.initPreview();
    },
    inputNameShow: function (a) {
        this.img.dropdown.addClass('ic-block');
    },
    inputNameHide: function (a) {
        if (this.input.name.is(':hover'))
            return;
        if (this.img.container.is(':hover'))
            return;
        if (this.img.cropped.is(':hover'))
            return;
        this.img.dropdown.removeClass('ic-block');
    },
    dlgInputChange: function (a) {
        var files, file;
        files = this.dlg.input.prop('files');
        if (files.length > 0) {
            file = files[0];
            if (this.isImageFile(file)) {
                if (this.url) {
                    URL.revokeObjectURL(this.url); // Revoke the old one
                }
                this.url = URL.createObjectURL(file);
                this.startCropper();
                this.dlg.opts.inputZoomer.slider('enable');
            }
        }
    },
    dlgApplyClick: function (a) {
        console.log("Apply clicked ");
        if (!this.dlg.src.val() && !this.dlg.input.val()) {
            return false;
        }


        var canvas = this.$img.cropper('getCroppedCanvas');
        var thisa = this;
        canvas.toBlob(function (blob) {
            var newImg = document.createElement('img'),
                    url = URL.createObjectURL(blob);
            newImg.onload = function () {
                // no longer need to read the blob so it's revoked
                URL.revokeObjectURL(url);
            };
            newImg.src = url;
            console.log(url)
            //document.body.appendChild(newImg);
            //thisa.img.cropped.src = url;
            thisa.img.cropped.attr('src', url);
        });
    },
    setDragMode: function (e) {
        var data;
        if (this.active) {
            data = $(e.target).data();
            if (data.method) {
                this.$img.cropper(data.method, data.option);
            }
        }
    },
    rotate: function (e) {
        var data;
        if (this.active) {
            data = $(e.target).data();
            if (data.method) {
                this.$img.cropper(data.method, data.option);
            }
        }
    },
    configure: function (e) {
        var data;
        if (this.active) {
            data = $(e.target).data();
            if (data.method) {
                this.$img.cropper(data.method);
            }
        }
    },
    zoom: function (e) {
        if (this.active) {
            var delta = this.dlg.opts.inputZoomerValue.val() - this.dlg.opts.inputZoomerValueSaver;
            this.$img.cropper('zoom', delta);
            this.dlg.opts.inputZoomerValueSaver = this.dlg.opts.inputZoomerValue.val();
        }
    },
    setAspectRatio: function (e) {
        var data;
        if (this.active) {
            data = $(e.target).data();
            if (data.method) {
                if (data.option != 'null') {
                    this.$img.cropper(data.method, data.option);
                    this.$img.cropper('crop');
                } else {
                    this.$img.cropper(data.method, this.$img.cropper(data.method));
                }
            }
        }
    },
    ///
    /// Standard function
    ///
    ///
    isImageFile: function (file) {
        if (file.type) {
            return /^image\/\w+$/.test(file.type);
        } else {
            return /\.(jpg|jpeg|png|gif)$/.test(file);
        }
    },
    startCropper: function () {
        var _this = this;
        if (this.active) {
            this.$img.cropper('replace', this.url);
        } else {
            this.$img = $('<img src="' + this.url + '">');
            this.preview.wrapper.empty().html(this.$img);
            this.$img.cropper({
                aspectRatio: 1,
                preview: _this.preview.previews.selector,
                crop: function (data) {
                    var json = [
                        '{"x":' + data.x,
                        '"y":' + data.y,
                        '"height":' + data.height,
                        '"width":' + data.width,
                        '"rotate":' + data.rotate + '}'
                    ].join();
                    _this.dlg.data.val(json);
                    _this.dlg.datas.x.val(Math.round(data.x));
                    _this.dlg.datas.y.val(Math.round(data.y));
                    _this.dlg.datas.h.val(Math.round(data.height));
                    _this.dlg.datas.w.val(Math.round(data.width));
                    _this.dlg.datas.r.val(Math.round(data.rotate));
                }

            });
            this.active = true;
        }

        this.dlg.modal.one('hidden.bs.modal', function () {
            //_this.preview.previews.empty();
            _this.stopCropper();
        });
    },
    stopCropper: function () {
        if (this.active) {
            //this.$img.cropper('destroy');
            //this.$img.remove();
            //this.active = false;
        }
    }

});
$(function () {
    $('[data-toggle="popover"]').popover();
    $('.sizerPopper').popover({
        container: 'body',
        html: true,
        content: $('.sizerContainer'),
        toggle: 'popover'
    });

});
