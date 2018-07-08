(function (a) {
    if (typeof define === "function" && define.amd) {
        define(["jquery"], a)
    } else {
        if (typeof exports === "object") {
            a(require("jquery"))
        } else {
            a(window.jQuery)
        }
    }
}
(function (b) {
    var a = 0;
    b.ajaxTransport("iframe", function (d) {
        if (d.async) {
            var c = d.initialIframeSrc || "javascript:false;", f, e, g;
            return{
                send: function (h, i) {
                    f = b('<form style="display:none;"></form>');
                    f.attr("accept-charset", d.formAcceptCharset);
                    g = /\?/.test(d.url) ? "&" : "?";
                    if (d.type === "DELETE") {
                        d.url = d.url + g + "_method=DELETE";
                        d.type = "POST"
                    } else {
                        if (d.type === "PUT") {
                            d.url = d.url + g + "_method=PUT";
                            d.type = "POST"
                        } else {
                            if (d.type === "PATCH") {
                                d.url = d.url + g + "_method=PATCH";
                                d.type = "POST"
                            }
                        }
                    }
                    a += 1;
                    e = b('<iframe src="' + c + '" name="iframe-transport-' + a + '"></iframe>').bind("load", function () {
                        var j, k = b.isArray(d.paramName) ? d.paramName : [d.paramName];
                        e.unbind("load").bind("load", function () {
                            var l;
                            try {
                                l = e.contents();
                                if (!l.length || !l[0].firstChild) {
                                    throw new Error()
                                }
                            } catch (m) {
                                l = undefined
                            }
                            i(200, "success", {iframe: l});
                            b('<iframe src="' + c + '"></iframe>').appendTo(f);
                            window.setTimeout(function () {
                                f.remove()
                            }, 0)
                        });
                        f.prop("target", e.prop("name")).prop("action", d.url).prop("method", d.type);
                        if (d.formData) {
                            b.each(d.formData, function (l, m) {
                                b('<input type="hidden"/>').prop("name", m.name).val(m.value).appendTo(f)
                            })
                        }
                        if (d.fileInput && d.fileInput.length && d.type === "POST") {
                            j = d.fileInput.clone();
                            d.fileInput.after(function (l) {
                                return j[l]
                            });
                            if (d.paramName) {
                                d.fileInput.each(function (l) {
                                    b(this).prop("name", k[l] || d.paramName)
                                })
                            }
                            f.append(d.fileInput).prop("enctype", "multipart/form-data").prop("encoding", "multipart/form-data");
                            d.fileInput.removeAttr("form")
                        }
                        f.submit();
                        if (j && j.length) {
                            d.fileInput.each(function (m, l) {
                                var n = b(j[m]);
                                b(l).prop("name", n.prop("name")).attr("form", n.attr("form"));
                                n.replaceWith(l)
                            })
                        }
                    });
                    f.append(e).appendTo(document.body)
                }, 
                abort: function () {
                    if (e) {
                        e.unbind("load").prop("src", c)
                    }
                    if (f) {
                        f.remove()
                    }
                }
            }
        }
    });
    b.ajaxSetup({
        converters: {
            "iframe text": function (c) {
                return c && b(c[0].body).text()
            },
            "iframe json": function (c) {
                return c && b.parseJSON(b(c[0].body).text())
            },
            "iframe html": function (c) {
                return c && b(c[0].body).html()
            },
            "iframe xml": function (c) {
                var d = c && c[0];
                return d && b.isXMLDoc(d) ? d : b.parseXML((d.XMLDocument && d.XMLDocument.xml) || b(d.body).html())
            },
            "iframe script": function (c) {
                return c && b.globalEval(b(c[0].body).text())
            }
        }
    })
}));

(function (a) {
    if (typeof define === "function" && define.amd) {
        define(["jquery", "jquery.ui.widget"], a)
    } else {
        if (typeof exports === "object") {
            a(require("jquery"), require("./vendor/jquery.ui.widget"))
        } else {
            a(window.jQuery)
        }
    }
}
(function (b) {
    b.support.fileInput = !(new RegExp("(Android (1\\.[0156]|2\\.[01]))|(Windows Phone (OS 7|8\\.0))|(XBLWP)|(ZuneWP)|(WPDesktop)|(w(eb)?OSBrowser)|(webOS)|(Kindle/(1\\.0|2\\.[05]|3\\.0))").test(window.navigator.userAgent) || b('<input type="file">').prop("disabled"));
    b.support.xhrFileUpload = !!(window.ProgressEvent && window.FileReader);
    b.support.xhrFormDataFileUpload = !!window.FormData;
    b.support.blobSlice = window.Blob && (Blob.prototype.slice || Blob.prototype.webkitSlice || Blob.prototype.mozSlice);
    function a(c) {
        var d = c === "dragover";
        return function (g) {
            g.dataTransfer = g.originalEvent && g.originalEvent.dataTransfer;
            var f = g.dataTransfer;
            if (f && b.inArray("Files", f.types) !== -1 && this._trigger(c, b.Event(c, {delegatedEvent: g})) !== false) {
                g.preventDefault();
                if (d) {
                    f.dropEffect = "copy"
                }
            }
        }
    }
    b.widget("blueimp.fileupload", {
        options: {
            dropZone: b(document),
            pasteZone: undefined, fileInput: undefined,
            replaceFileInput: true, paramName: undefined, singleFileUploads: true, limitMultiFileUploads: undefined, limitMultiFileUploadSize: undefined, limitMultiFileUploadSizeOverhead: 512, sequentialUploads: false, limitConcurrentUploads: undefined, forceIframeTransport: false, redirect: undefined, redirectParamName: undefined, postMessage: undefined, multipart: true, maxChunkSize: undefined, uploadedBytes: undefined, recalculateProgress: true, progressInterval: 100, bitrateInterval: 500, autoUpload: true, messages: {uploadedBytes: "Uploaded bytes exceed file size"}, i18n: function (d, c) {
                d = this.messages[d] || d.toString();
                if (c) {
                    b.each(c, function (e, f) {
                        d = d.replace("{" + e + "}", f)
                    })
                }
                return d
            }, formData: function (c) {
                return c.serializeArray()
            }, add: function (d, c) {
                if (d.isDefaultPrevented()) {
                    return false
                }
                if (c.autoUpload || (c.autoUpload !== false && b(this).fileupload("option", "autoUpload"))) {
                    c.process().done(function () {
                        c.submit()
                    })
                }
            }, processData: false, contentType: false, cache: false}, _specialOptions: ["fileInput", "dropZone", "pasteZone", "multipart", "forceIframeTransport"], _blobSlice: b.support.blobSlice && function () {
            var c = this.slice || this.webkitSlice || this.mozSlice;
            return c.apply(this, arguments)
        }, _BitrateTimer: function () {
            this.timestamp = ((Date.now) ? Date.now() : (new Date()).getTime());
            this.loaded = 0;
            this.bitrate = 0;
            this.getBitrate = function (e, d, c) {
                var f = e - this.timestamp;
                if (!this.bitrate || !c || f > c) {
                    this.bitrate = (d - this.loaded) * (1000 / f) * 8;
                    this.loaded = d;
                    this.timestamp = e
                }
                return this.bitrate
            }
        }, _isXHRUpload: function (c) {
            return !c.forceIframeTransport && ((!c.multipart && b.support.xhrFileUpload) || b.support.xhrFormDataFileUpload)
        }, _getFormData: function (c) {
            var d;
            if (b.type(c.formData) === "function") {
                return c.formData(c.form)
            }
            if (b.isArray(c.formData)) {
                return c.formData
            }
            if (b.type(c.formData) === "object") {
                d = [];
                b.each(c.formData, function (e, f) {
                    d.push({name: e, value: f})
                });
                return d
            }
            return[]
        }, _getTotal: function (d) {
            var c = 0;
            b.each(d, function (e, f) {
                c += f.size || 1
            });
            return c
        }, _initProgressObject: function (d) {
            var c = {loaded: 0, total: 0, bitrate: 0};
            if (d._progress) {
                b.extend(d._progress, c)
            } else {
                d._progress = c
            }
        }, _initResponseObject: function (c) {
            var d;
            if (c._response) {
                for (d in c._response) {
                    if (c._response.hasOwnProperty(d)) {
                        delete c._response[d]
                    }
                }
            } else {
                c._response = {}
            }
        }, _onProgress: function (g, f) {
            if (g.lengthComputable) {
                var d = ((Date.now) ? Date.now() : (new Date()).getTime()), c;
                if (f._time && f.progressInterval && (d - f._time < f.progressInterval) && g.loaded !== g.total) {
                    return
                }
                f._time = d;
                c = Math.floor(g.loaded / g.total * (f.chunkSize || f._progress.total)) + (f.uploadedBytes || 0);
                this._progress.loaded += (c - f._progress.loaded);
                this._progress.bitrate = this._bitrateTimer.getBitrate(d, this._progress.loaded, f.bitrateInterval);
                f._progress.loaded = f.loaded = c;
                f._progress.bitrate = f.bitrate = f._bitrateTimer.getBitrate(d, c, f.bitrateInterval);
                this._trigger("progress", b.Event("progress", {delegatedEvent: g}), f);
                this._trigger("progressall", b.Event("progressall", {delegatedEvent: g}), this._progress)
            }
        }, _initProgressListener: function (c) {
            var d = this, e = c.xhr ? c.xhr() : b.ajaxSettings.xhr();
            if (e.upload) {
                b(e.upload).bind("progress", function (f) {
                    var g = f.originalEvent;
                    f.lengthComputable = g.lengthComputable;
                    f.loaded = g.loaded;
                    f.total = g.total;
                    d._onProgress(f, c)
                });
                c.xhr = function () {
                    return e
                }
            }
        }, _isInstanceOf: function (c, d) {
            return Object.prototype.toString.call(d) === "[object " + c + "]"
        }, _initXHRData: function (d) {
            var f = this, h, e = d.files[0], c = d.multipart || !b.support.xhrFileUpload, g = b.type(d.paramName) === "array" ? d.paramName[0] : d.paramName;
            d.headers = b.extend({}, d.headers);
            if (d.contentRange) {
                d.headers["Content-Range"] = d.contentRange
            }
            if (!c || d.blob || !this._isInstanceOf("File", e)) {
                d.headers["Content-Disposition"] = 'attachment; filename="' + encodeURI(e.name) + '"'
            }
            if (!c) {
                d.contentType = e.type || "application/octet-stream";
                d.data = d.blob || e
            } else {
                if (b.support.xhrFormDataFileUpload) {
                    if (d.postMessage) {
                        h = this._getFormData(d);
                        if (d.blob) {
                            h.push({name: g, value: d.blob})
                        } else {
                            b.each(d.files, function (i, j) {
                                h.push({name: (b.type(d.paramName) === "array" && d.paramName[i]) || g, value: j})
                            })
                        }
                    } else {
                        if (f._isInstanceOf("FormData", d.formData)) {
                            h = d.formData
                        } else {
                            h = new FormData();
                            b.each(this._getFormData(d), function (i, j) {
                                h.append(j.name, j.value)
                            })
                        }
                        if (d.blob) {
                            h.append(g, d.blob, e.name)
                        } else {
                            b.each(d.files, function (i, j) {
                                if (f._isInstanceOf("File", j) || f._isInstanceOf("Blob", j)) {
                                    h.append((b.type(d.paramName) === "array" && d.paramName[i]) || g, j, j.uploadName || j.name)
                                }
                            })
                        }
                    }
                    d.data = h
                }
            }
            d.blob = null
        }, _initIframeSettings: function (c) {
            var d = b("<a></a>").prop("href", c.url).prop("host");
            c.dataType = "iframe " + (c.dataType || "");
            c.formData = this._getFormData(c);
            if (c.redirect && d && d !== location.host) {
                c.formData.push({name: c.redirectParamName || "redirect", value: c.redirect})
            }
        }, _initDataSettings: function (c) {
            if (this._isXHRUpload(c)) {
                if (!this._chunkedUpload(c, true)) {
                    if (!c.data) {
                        this._initXHRData(c)
                    }
                    this._initProgressListener(c)
                }
                if (c.postMessage) {
                    c.dataType = "postmessage " + (c.dataType || "")
                }
            } else {
                this._initIframeSettings(c)
            }
        }, _getParamName: function (c) {
            var d = b(c.fileInput), e = c.paramName;
            if (!e) {
                e = [];
                d.each(function () {
                    var f = b(this), g = f.prop("name") || "files[]", h = (f.prop("files") || [1]).length;
                    while (h) {
                        e.push(g);
                        h -= 1
                    }
                });
                if (!e.length) {
                    e = [d.prop("name") || "files[]"]
                }
            } else {
                if (!b.isArray(e)) {
                    e = [e]
                }
            }
            return e
        }, _initFormSettings: function (c) {
            if (!c.form || !c.form.length) {
                c.form = b(c.fileInput.prop("form"));
                if (!c.form.length) {
                    c.form = b(this.options.fileInput.prop("form"))
                }
            }
            c.paramName = this._getParamName(c);
            if (!c.url) {
                c.url = c.form.prop("action") || location.href
            }
            c.type = (c.type || (b.type(c.form.prop("method")) === "string" && c.form.prop("method")) || "").toUpperCase();
            if (c.type !== "POST" && c.type !== "PUT" && c.type !== "PATCH") {
                c.type = "POST"
            }
            if (!c.formAcceptCharset) {
                c.formAcceptCharset = c.form.attr("accept-charset")
            }
        }, _getAJAXSettings: function (d) {
            var c = b.extend({}, this.options, d);
            this._initFormSettings(c);
            this._initDataSettings(c);
            return c
        }, _getDeferredState: function (c) {
            if (c.state) {
                return c.state()
            }
            if (c.isResolved()) {
                return"resolved"
            }
            if (c.isRejected()) {
                return"rejected"
            }
            return"pending"
        }, _enhancePromise: function (c) {
            c.success = c.done;
            c.error = c.fail;
            c.complete = c.always;
            return c
        }, _getXHRPromise: function (f, e, d) {
            var c = b.Deferred(), g = c.promise();
            e = e || this.options.context || g;
            if (f === true) {
                c.resolveWith(e, d)
            } else {
                if (f === false) {
                    c.rejectWith(e, d)
                }
            }
            g.abort = c.promise;
            return this._enhancePromise(g)
        }, _addConvenienceMethods: function (g, f) {
            var d = this, c = function (e) {
                return b.Deferred().resolveWith(d, e).promise()
            };
            f.process = function (h, e) {
                if (h || e) {
                    f._processQueue = this._processQueue = (this._processQueue || c([this])).pipe(function () {
                        if (f.errorThrown) {
                            return b.Deferred().rejectWith(d, [f]).promise()
                        }
                        return c(arguments)
                    }).pipe(h, e)
                }
                return this._processQueue || c([this])
            };
            f.submit = function () {
                if (this.state() !== "pending") {
                    f.jqXHR = this.jqXHR = (d._trigger("submit", b.Event("submit", {delegatedEvent: g}), this) !== false) && d._onSend(g, this)
                }
                return this.jqXHR || d._getXHRPromise()
            };
            f.abort = function () {
                if (this.jqXHR) {
                    return this.jqXHR.abort()
                }
                this.errorThrown = "abort";
                d._trigger("fail", null, this);
                return d._getXHRPromise(false)
            };
            f.state = function () {
                if (this.jqXHR) {
                    return d._getDeferredState(this.jqXHR)
                }
                if (this._processQueue) {
                    return d._getDeferredState(this._processQueue)
                }
            };
            f.processing = function () {
                return !this.jqXHR && this._processQueue && d._getDeferredState(this._processQueue) === "pending"
            };
            f.progress = function () {
                return this._progress
            };
            f.response = function () {
                return this._response
            }
        }, _getUploadedBytes: function (e) {
            var c = e.getResponseHeader("Range"), f = c && c.split("-"), d = f && f.length > 1 && parseInt(f[1], 10);
            return d && d + 1
        }, _chunkedUpload: function (n, h) {
            n.uploadedBytes = n.uploadedBytes || 0;
            var g = this, e = n.files[0], f = e.size, c = n.uploadedBytes, d = n.maxChunkSize || f, j = this._blobSlice, k = b.Deferred(), m = k.promise(), i, l;
            if (!(this._isXHRUpload(n) && j && (c || d < f)) || n.data) {
                return false
            }
            if (h) {
                return true
            }
            if (c >= f) {
                e.error = n.i18n("uploadedBytes");
                return this._getXHRPromise(false, n.context, [null, "error", e.error])
            }
            l = function () {
                var q = b.extend({}, n), p = q._progress.loaded;
                q.blob = j.call(e, c, c + d, e.type);
                q.chunkSize = q.blob.size;
                q.contentRange = "bytes " + c + "-" + (c + q.chunkSize - 1) + "/" + f;
                g._initXHRData(q);
                g._initProgressListener(q);
                i = ((g._trigger("chunksend", null, q) !== false && b.ajax(q)) || g._getXHRPromise(false, q.context)).done(function (o, s, r) {
                    c = g._getUploadedBytes(r) || (c + q.chunkSize);
                    if (p + q.chunkSize - q._progress.loaded) {
                        g._onProgress(b.Event("progress", {lengthComputable: true, loaded: c - q.uploadedBytes, total: c - q.uploadedBytes}), q)
                    }
                    n.uploadedBytes = q.uploadedBytes = c;
                    q.result = o;
                    q.textStatus = s;
                    q.jqXHR = r;
                    g._trigger("chunkdone", null, q);
                    g._trigger("chunkalways", null, q);
                    if (c < f) {
                        l()
                    } else {
                        k.resolveWith(q.context, [o, s, r])
                    }
                }).fail(function (o, s, r) {
                    q.jqXHR = o;
                    q.textStatus = s;
                    q.errorThrown = r;
                    g._trigger("chunkfail", null, q);
                    g._trigger("chunkalways", null, q);
                    k.rejectWith(q.context, [o, s, r])
                })
            };
            this._enhancePromise(m);
            m.abort = function () {
                return i.abort()
            };
            l();
            return m
        }, _beforeSend: function (d, c) {
            if (this._active === 0) {
                this._trigger("start");
                this._bitrateTimer = new this._BitrateTimer();
                this._progress.loaded = this._progress.total = 0;
                this._progress.bitrate = 0
            }
            this._initResponseObject(c);
            this._initProgressObject(c);
            c._progress.loaded = c.loaded = c.uploadedBytes || 0;
            c._progress.total = c.total = this._getTotal(c.files) || 1;
            c._progress.bitrate = c.bitrate = 0;
            this._active += 1;
            this._progress.loaded += c.loaded;
            this._progress.total += c.total
        }, _onDone: function (c, h, g, e) {
            var f = e._progress.total, d = e._response;
            if (e._progress.loaded < f) {
                this._onProgress(b.Event("progress", {lengthComputable: true, loaded: f, total: f}), e)
            }
            d.result = e.result = c;
            d.textStatus = e.textStatus = h;
            d.jqXHR = e.jqXHR = g;
            this._trigger("done", null, e)
        }, _onFail: function (e, g, f, d) {
            var c = d._response;
            if (d.recalculateProgress) {
                this._progress.loaded -= d._progress.loaded;
                this._progress.total -= d._progress.total
            }
            c.jqXHR = d.jqXHR = e;
            c.textStatus = d.textStatus = g;
            c.errorThrown = d.errorThrown = f;
            this._trigger("fail", null, d)
        }, _onAlways: function (e, f, d, c) {
            this._trigger("always", null, c)
        }, _onSend: function (i, g) {
            if (!g.submit) {
                this._addConvenienceMethods(i, g)
            }
            var h = this, k, c, j, d, l = h._getAJAXSettings(g), f = function () {
                h._sending += 1;
                l._bitrateTimer = new h._BitrateTimer();
                k = k || (((c || h._trigger("send", b.Event("send", {delegatedEvent: i}), l) === false) && h._getXHRPromise(false, l.context, c)) || h._chunkedUpload(l) || b.ajax(l)).done(function (e, n, m) {
                    h._onDone(e, n, m, l)
                }).fail(function (e, n, m) {
                    h._onFail(e, n, m, l)
                }).always(function (n, o, m) {
                    h._onAlways(n, o, m, l);
                    h._sending -= 1;
                    h._active -= 1;
                    if (l.limitConcurrentUploads && l.limitConcurrentUploads > h._sending) {
                        var e = h._slots.shift();
                        while (e) {
                            if (h._getDeferredState(e) === "pending") {
                                e.resolve();
                                break
                            }
                            e = h._slots.shift()
                        }
                    }
                    if (h._active === 0) {
                        h._trigger("stop")
                    }
                });
                return k
            };
            this._beforeSend(i, l);
            if (this.options.sequentialUploads || (this.options.limitConcurrentUploads && this.options.limitConcurrentUploads <= this._sending)) {
                if (this.options.limitConcurrentUploads > 1) {
                    j = b.Deferred();
                    this._slots.push(j);
                    d = j.pipe(f)
                } else {
                    this._sequence = this._sequence.pipe(f, f);
                    d = this._sequence
                }
                d.abort = function () {
                    c = [undefined, "abort", "abort"];
                    if (!k) {
                        if (j) {
                            j.rejectWith(l.context, c)
                        }
                        return f()
                    }
                    return k.abort()
                };
                return this._enhancePromise(d)
            }
            return f()
        }, _onAdd: function (q, m) {
            var p = this, v = true, u = b.extend({}, this.options, m), f = m.files, s = f.length, g = u.limitMultiFileUploads, k = u.limitMultiFileUploadSize, t = u.limitMultiFileUploadSizeOverhead, o = 0, n = this._getParamName(u), d, c, r, l, h = 0;
            if (k && (!s || f[0].size === undefined)) {
                k = undefined
            }
            if (!(u.singleFileUploads || g || k) || !this._isXHRUpload(u)) {
                r = [f];
                d = [n]
            } else {
                if (!(u.singleFileUploads || k) && g) {
                    r = [];
                    d = [];
                    for (l = 0; l < s; l += g) {
                        r.push(f.slice(l, l + g));
                        c = n.slice(l, l + g);
                        if (!c.length) {
                            c = n
                        }
                        d.push(c)
                    }
                } else {
                    if (!u.singleFileUploads && k) {
                        r = [];
                        d = [];
                        for (l = 0; l < s; l = l + 1) {
                            o += f[l].size + t;
                            if (l + 1 === s || ((o + f[l + 1].size + t) > k) || (g && l + 1 - h >= g)) {
                                r.push(f.slice(h, l + 1));
                                c = n.slice(h, l + 1);
                                if (!c.length) {
                                    c = n
                                }
                                d.push(c);
                                h = l + 1;
                                o = 0
                            }
                        }
                    } else {
                        d = n
                    }
                }
            }
            m.originalFiles = f;
            b.each(r || f, function (e, i) {
                var j = b.extend({}, m);
                j.files = r ? i : [i];
                j.paramName = d[e];
                p._initResponseObject(j);
                p._initProgressObject(j);
                p._addConvenienceMethods(q, j);
                v = p._trigger("add", b.Event("add", {delegatedEvent: q}), j);
                return v
            });
            return v
        }, _replaceFileInput: function (e) {
            var c = e.fileInput, d = c.clone(true);
            e.fileInputClone = d;
            b("<form></form>").append(d)[0].reset();
            c.after(d).detach();
            b.cleanData(c.unbind("remove"));
            this.options.fileInput = this.options.fileInput.map(function (f, g) {
                if (g === c[0]) {
                    return d[0]
                }
                return g
            });
            if (c[0] === this.element[0]) {
                this.element = d
            }
        }, _handleFileTreeEntry: function (h, j) {
            var e = this, i = b.Deferred(), d = function (l) {
                if (l && !l.entry) {
                    l.entry = h
                }
                i.resolve([l])
            }, f = function (l) {
                e._handleFileTreeEntries(l, j + h.name + "/").done(function (m) {
                    i.resolve(m)
                }).fail(d)
            }, g = function () {
                k.readEntries(function (l) {
                    if (!l.length) {
                        f(c)
                    } else {
                        c = c.concat(l);
                        g()
                    }
                }, d)
            }, k, c = [];
            j = j || "";
            if (h.isFile) {
                if (h._file) {
                    h._file.relativePath = j;
                    i.resolve(h._file)
                } else {
                    h.file(function (l) {
                        l.relativePath = j;
                        i.resolve(l)
                    }, d)
                }
            } else {
                if (h.isDirectory) {
                    k = h.createReader();
                    g()
                } else {
                    i.resolve([])
                }
            }
            return i.promise()
        }, _handleFileTreeEntries: function (c, e) {
            var d = this;
            return b.when.apply(b, b.map(c, function (f) {
                return d._handleFileTreeEntry(f, e)
            })).pipe(function () {
                return Array.prototype.concat.apply([], arguments)
            })
        }, _getDroppedFiles: function (d) {
            d = d || {};
            var c = d.items;
            if (c && c.length && (c[0].webkitGetAsEntry || c[0].getAsEntry)) {
                return this._handleFileTreeEntries(b.map(c, function (f) {
                    var e;
                    if (f.webkitGetAsEntry) {
                        e = f.webkitGetAsEntry();
                        if (e) {
                            e._file = f.getAsFile()
                        }
                        return e
                    }
                    return f.getAsEntry()
                }))
            }
            return b.Deferred().resolve(b.makeArray(d.files)).promise()
        }, _getSingleFileInputFiles: function (e) {
            e = b(e);
            var c = e.prop("webkitEntries") || e.prop("entries"), d, f;
            if (c && c.length) {
                return this._handleFileTreeEntries(c)
            }
            d = b.makeArray(e.prop("files"));
            if (!d.length) {
                f = e.prop("value");
                if (!f) {
                    return b.Deferred().resolve([]).promise()
                }
                d = [{name: f.replace(/^.*\\/, "")}]
            } else {
                if (d[0].name === undefined && d[0].fileName) {
                    b.each(d, function (g, h) {
                        h.name = h.fileName;
                        h.size = h.fileSize
                    })
                }
            }
            return b.Deferred().resolve(d).promise()
        }, _getFileInputFiles: function (c) {
            if (!(c instanceof b) || c.length === 1) {
                return this._getSingleFileInputFiles(c)
            }
            return b.when.apply(b, b.map(c, this._getSingleFileInputFiles)).pipe(function () {
                return Array.prototype.concat.apply([], arguments)
            })
        }, _onChange: function (f) {
            var c = this, d = {fileInput: b(f.target), form: b(f.target.form)};
            this._getFileInputFiles(d.fileInput).always(function (e) {
                d.files = e;
                if (c.options.replaceFileInput) {
                    c._replaceFileInput(d)
                }
                if (c._trigger("change", b.Event("change", {delegatedEvent: f}), d) !== false) {
                    c._onAdd(f, d)
                }
            })
        }, _onPaste: function (f) {
            var c = f.originalEvent && f.originalEvent.clipboardData && f.originalEvent.clipboardData.items, d = {files: []};
            if (c && c.length) {
                b.each(c, function (e, h) {
                    var g = h.getAsFile && h.getAsFile();
                    if (g) {
                        d.files.push(g)
                    }
                });
                if (this._trigger("paste", b.Event("paste", {delegatedEvent: f}), d) !== false) {
                    this._onAdd(f, d)
                }
            }
        }, _onDrop: function (g) {
            g.dataTransfer = g.originalEvent && g.originalEvent.dataTransfer;
            var c = this, f = g.dataTransfer, d = {};
            if (f && f.files && f.files.length) {
                g.preventDefault();
                this._getDroppedFiles(f).always(function (e) {
                    d.files = e;
                    if (c._trigger("drop", b.Event("drop", {delegatedEvent: g}), d) !== false) {
                        c._onAdd(g, d)
                    }
                })
            }
        }, _onDragOver: a("dragover"), _onDragEnter: a("dragenter"), _onDragLeave: a("dragleave"), _initEventHandlers: function () {
            if (this._isXHRUpload(this.options)) {
                this._on(this.options.dropZone, {dragover: this._onDragOver, drop: this._onDrop, dragenter: this._onDragEnter, dragleave: this._onDragLeave});
                this._on(this.options.pasteZone, {paste: this._onPaste})
            }
            if (b.support.fileInput) {
                this._on(this.options.fileInput, {change: this._onChange})
            }
        }, _destroyEventHandlers: function () {
            this._off(this.options.dropZone, "dragenter dragleave dragover drop");
            this._off(this.options.pasteZone, "paste");
            this._off(this.options.fileInput, "change")
        }, _setOption: function (c, d) {
            var e = b.inArray(c, this._specialOptions) !== -1;
            if (e) {
                this._destroyEventHandlers()
            }
            this._super(c, d);
            if (e) {
                this._initSpecialOptions();
                this._initEventHandlers()
            }
        }, _initSpecialOptions: function () {
            var c = this.options;
            if (c.fileInput === undefined) {
                c.fileInput = this.element.is('input[type="file"]') ? this.element : this.element.find('input[type="file"]')
            } else {
                if (!(c.fileInput instanceof b)) {
                    c.fileInput = b(c.fileInput)
                }
            }
            if (!(c.dropZone instanceof b)) {
                c.dropZone = b(c.dropZone)
            }
            if (!(c.pasteZone instanceof b)) {
                c.pasteZone = b(c.pasteZone)
            }
        }, _getRegExp: function (e) {
            var d = e.split("/"), c = d.pop();
            d.shift();
            return new RegExp(d.join("/"), c)
        }, _isRegExpOption: function (c, d) {
            return c !== "url" && b.type(d) === "string" && /^\/.*\/[igm]{0,3}$/.test(d)
        }, _initDataAttributes: function () {
            var d = this, c = this.options, e = this.element.data();
            b.each(this.element[0].attributes, function (g, f) {
                var h = f.name.toLowerCase(), i;
                if (/^data-/.test(h)) {
                    h = h.slice(5).replace(/-[a-z]/g, function (j) {
                        return j.charAt(1).toUpperCase()
                    });
                    i = e[h];
                    if (d._isRegExpOption(h, i)) {
                        i = d._getRegExp(i)
                    }
                    c[h] = i
                }
            })
        }, _create: function () {
            this._initDataAttributes();
            this._initSpecialOptions();
            this._slots = [];
            this._sequence = this._getXHRPromise(true);
            this._sending = this._active = 0;
            this._initProgressObject(this);
            this._initEventHandlers()
        }, active: function () {
            return this._active
        }, progress: function () {
            return this._progress
        }, add: function (d) {
            var c = this;
            if (!d || this.options.disabled) {
                return
            }
            if (d.fileInput && !d.files) {
                this._getFileInputFiles(d.fileInput).always(function (e) {
                    d.files = e;
                    c._onAdd(null, d)
                })
            } else {
                d.files = b.makeArray(d.files);
                this._onAdd(null, d)
            }
        }, send: function (g) {
            if (g && !this.options.disabled) {
                if (g.fileInput && !g.files) {
                    var e = this, c = b.Deferred(), h = c.promise(), d, f;
                    h.abort = function () {
                        f = true;
                        if (d) {
                            return d.abort()
                        }
                        c.reject(null, "abort", "abort");
                        return h
                    };
                    this._getFileInputFiles(g.fileInput).always(function (i) {
                        if (f) {
                            return
                        }
                        if (!i.length) {
                            c.reject();
                            return
                        }
                        g.files = i;
                        d = e._onSend(null, g);
                        d.then(function (j, l, k) {
                            c.resolve(j, l, k)
                        }, function (j, l, k) {
                            c.reject(j, l, k)
                        })
                    });
                    return this._enhancePromise(h)
                }
                g.files = b.makeArray(g.files);
                if (g.files.length) {
                    return this._onSend(null, g)
                }
            }
            return this._getXHRPromise(false, g && g.context)
        }})
}));

PrimeFaces.widget.FileUpload = PrimeFaces.widget.BaseWidget.extend({
    IMAGE_TYPES: /(\.|\/)(gif|jpe?g|png)$/i,
    init: function (a) {
        this._super(a);
        if (this.cfg.disabled) {
            return
        }
        this.ucfg = {};
        this.form = this.jq.closest("form");
        this.buttonBar = this.jq.children(".ui-fileupload-buttonbar");
        this.chooseButton = this.buttonBar.children(".ui-fileupload-choose");
        this.uploadButton = this.buttonBar.children(".ui-fileupload-upload");
        this.cancelButton = this.buttonBar.children(".ui-fileupload-cancel");
        this.content = this.jq.children(".ui-fileupload-content");
        this.filesTbody = this.content.find("> div.ui-fileupload-files > div");
        this.sizes = ["Bytes", "KB", "MB", "GB", "TB"];
        this.files = [];
        this.fileAddIndex = 0;
        this.cfg.invalidFileMessage = this.cfg.invalidFileMessage || "Invalid file type";
        this.cfg.invalidSizeMessage = this.cfg.invalidSizeMessage || "Invalid file size";
        this.cfg.fileLimitMessage = this.cfg.fileLimitMessage || "Maximum number of files exceeded";
        this.cfg.messageTemplate = this.cfg.messageTemplate || "{name} {size}";
        this.cfg.previewWidth = this.cfg.previewWidth || 80;
        this.uploadedFileCount = 0;
        this.renderMessages();
        this.bindEvents();
        var c = this, e = this.form.attr("action"), d = this.form.children("input[name*='javax.faces.encodedURL']");
        var b = null;
        if (d.length > 0) {
            b = 'form[action="' + e + '"]';
            e = d.val()
        }
        this.ucfg = {
            url: e, portletForms: b, paramName: this.id, dataType: "xml",
            dropZone: (this.cfg.dnd === false) ? null : this.jq,
            sequentialUploads: this.cfg.sequentialUploads,
            formData: function () {
                return c.createPostData()
            },
            beforeSend: function (g, f) {
                g.setRequestHeader("Faces-Request", "partial/ajax");
                g.pfSettings = f;
                g.pfArgs = {}
            },
            start: function (f) {
                if (c.cfg.onstart) {
                    c.cfg.onstart.call(c)
                }
            },
            add: function (l, k) {
                c.chooseButton.removeClass("ui-state-hover ui-state-focus");
                if (c.fileAddIndex === 0) {
                    c.clearMessages()
                }
                if (c.cfg.fileLimit && (c.uploadedFileCount + c.files.length + 1) > c.cfg.fileLimit) {
                    c.clearMessages();
                    c.showMessage({summary: c.cfg.fileLimitMessage});
                    return
                }
                var h = k.files ? k.files[0] : null;
                if (h) {
                    var m = c.validate(h);
                    if (m) {
                        c.showMessage({summary: m, filename: PrimeFaces.escapeHTML(h.name), filesize: h.size})
                    } else {
                        var o = $('<div class="ui-fileupload-row"></div>').append('<div class="ui-fileupload-preview"></td>').append("<div>" + PrimeFaces.escapeHTML(h.name) + "</div>").append("<div>" + c.formatSize(h.size) + "</div>").append('<div class="ui-fileupload-progress"></div>').append('<div><button class="ui-fileupload-cancel ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only"><span class="ui-button-icon-left ui-icon ui-icon ui-icon-close"></span><span class="ui-button-text">ui-button</span></button></div>').appendTo(c.filesTbody);
                        if (c.filesTbody.children(".ui-fileupload-row").length > 1) {
                            $('<div class="ui-widget-content"></div>').prependTo(o)
                        }
                        if (c.isCanvasSupported() && window.File && window.FileReader && c.IMAGE_TYPES.test(h.name)) {
                            var n = $("<canvas></canvas>").appendTo(o.children("div.ui-fileupload-preview")), g = n.get(0).getContext("2d"), i = window.URL || window.webkitURL, f = i.createObjectURL(h), j = new Image();
                            j.onload = function () {
                                var q = null, p = null, r = 1;
                                if (c.cfg.previewWidth > this.width) {
                                    q = this.width
                                } else {
                                    q = c.cfg.previewWidth;
                                    r = c.cfg.previewWidth / this.width
                                }
                                var p = parseInt(this.height * r);
                                n.attr({width: q, height: p});
                                g.drawImage(j, 0, 0, q, p)
                            };
                            j.src = f
                        }
                        o.children("div.ui-fileupload-progress").append('<div class="ui-progressbar ui-widget ui-widget-content ui-corner-all" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="ui-progressbar-value ui-widget-header ui-corner-left" style="display: none; width: 0%;"></div></div>');
                        h.row = o;
                        h.row.data("filedata", k);
                        c.files.push(h);
                        if (c.cfg.auto) {
                            c.upload()
                        }
                    }
                    if (c.files.length > 0) {
                        c.enableButton(c.uploadButton);
                        c.enableButton(c.cancelButton)
                    }
                    c.fileAddIndex++;
                    if (c.fileAddIndex === (k.originalFiles.length)) {
                        c.fileAddIndex = 0
                    }
                }
            },
            send: function (j, h) {
                if (!window.FormData) {
                    for (var g = 0; g < h.files.length; g++) {
                        var f = h.files[g];
                        if (f.row) {
                            f.row.children(".ui-fileupload-progress").find("> .ui-progressbar > .ui-progressbar-value").addClass("ui-progressbar-value-legacy").css({width: "100%", display: "block"})
                        }
                    }
                }
            },
            fail: function (g, f) {
                if (c.cfg.onerror) {
                    c.cfg.onerror.call(c)
                }
            },
            progress: function (k, j) {
                if (window.FormData) {
                    var f = parseInt(j.loaded / j.total * 100, 10);
                    for (var h = 0; h < j.files.length; h++) {
                        var g = j.files[h];
                        if (g.row) {
                            g.row.children(".ui-fileupload-progress").find("> .ui-progressbar > .ui-progressbar-value").css({width: f + "%", display: "block"})
                        }
                    }
                }
            },
            done: function (g, f) {
                c.uploadedFileCount += f.files.length;
                c.removeFiles(f.files);
                PrimeFaces.ajax.Response.handle(f.result, f.textStatus, f.jqXHR, null)
            },
            always: function (g, f) {
                if (c.cfg.oncomplete) {
                    c.cfg.oncomplete.call(c, f.jqXHR.pfArgs)
                }
            }};
        this.jq.fileupload(this.ucfg)
    },
    bindEvents: function () {
        var b = this;
        PrimeFaces.skinButton(this.buttonBar.children("button"));
        this.chooseButton.on("mouseover.fileupload", function () {
            var c = $(this);
            if (!c.prop("disabled")) {
                c.addClass("ui-state-hover")
            }
        }).on("mouseout.fileupload", function () {
            $(this).removeClass("ui-state-active ui-state-hover")
        }).on("mousedown.fileupload", function () {
            var c = $(this);
            if (!c.prop("disabled")) {
                c.addClass("ui-state-active").removeClass("ui-state-hover")
            }
        }).on("mouseup.fileupload", function () {
            $(this).removeClass("ui-state-active").addClass("ui-state-hover")
        });
        var a = false;
        this.chooseButton.on("focus.fileupload", function () {
            $(this).addClass("ui-state-focus")
        }).on("blur.fileupload", function () {
            $(this).removeClass("ui-state-focus");
            a = false
        });
        this.chooseButton.on("click.fileupload", function () {
            b.chooseButton.children("input").trigger("click")
        }).on("keydown.fileupload", function (f) {
            var d = $.ui.keyCode, c = f.which;
            if (c === d.SPACE || c === d.ENTER || c === d.NUMPAD_ENTER) {
                b.chooseButton.children("input").trigger("click");
                $(this).blur();
                f.preventDefault()
            }
        });
        this.chooseButton.children("input").on("click", function (c) {
            if (a) {
                a = false;
                c.preventDefault();
                c.stopPropagation()
            } else {
                a = true
            }
        });
        this.uploadButton.on("click.fileupload", function (c) {
            b.disableButton(b.uploadButton);
            b.disableButton(b.cancelButton);
            b.disableButton(b.filesTbody.find("> div > div:last-child").children(".ui-fileupload-cancel"));
            b.upload();
            c.preventDefault()
        });
        this.cancelButton.on("click.fileupload", function (c) {
            b.clear();
            b.disableButton(b.uploadButton);
            b.disableButton(b.cancelButton);
            c.preventDefault()
        });
        this.clearMessageLink.on("click.fileupload", function (c) {
            b.messageContainer.fadeOut(function () {
                b.messageList.children().remove()
            });
            c.preventDefault()
        });
        this.rowActionSelector = this.jqId + " .ui-fileupload-files button";
        this.rowCancelActionSelector = this.jqId + " .ui-fileupload-files .ui-fileupload-cancel";
        this.clearMessagesSelector = this.jqId + " .ui-messages .ui-messages-close";
        $(document).off("mouseover.fileupload mouseout.fileupload mousedown.fileupload mouseup.fileupload focus.fileupload blur.fileupload click.fileupload ", this.rowCancelActionSelector).on("mouseover.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).addClass("ui-state-hover")
        }).on("mouseout.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).removeClass("ui-state-hover ui-state-active")
        }).on("mousedown.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).addClass("ui-state-active").removeClass("ui-state-hover")
        }).on("mouseup.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).addClass("ui-state-hover").removeClass("ui-state-active")
        }).on("focus.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).addClass("ui-state-focus")
        }).on("blur.fileupload", this.rowCancelActionSelector, null, function (c) {
            $(this).removeClass("ui-state-focus")
        }).on("click.fileupload", this.rowCancelActionSelector, null, function (d) {
            var f = $(this).closest(".ui-fileupload-row"), c = b.files.splice(f.index(), 1);
            c[0].row = null;
            b.removeFileRow(f);
            if (b.files.length === 0) {
                b.disableButton(b.uploadButton);
                b.disableButton(b.cancelButton)
            }
            d.preventDefault()
        })
    }, upload: function () {
        for (var a = 0; a < this.files.length; a++) {
            this.files[a].row.data("filedata").submit()
        }
    }, createPostData: function () {
        var a = this.cfg.process ? this.id + " " + PrimeFaces.expressions.SearchExpressionFacade.resolveComponents(this.cfg.process).join(" ") : this.id;
        var b = this.form.serializeArray();
        var d = PrimeFaces.ajax.Request.extractParameterNamespace(this.form);
        PrimeFaces.ajax.Request.addParam(b, PrimeFaces.PARTIAL_REQUEST_PARAM, true, d);
        PrimeFaces.ajax.Request.addParam(b, PrimeFaces.PARTIAL_PROCESS_PARAM, a, d);
        PrimeFaces.ajax.Request.addParam(b, PrimeFaces.PARTIAL_SOURCE_PARAM, this.id, d);
        if (this.cfg.update) {
            var c = PrimeFaces.expressions.SearchExpressionFacade.resolveComponents(this.cfg.update).join(" ");
            PrimeFaces.ajax.Request.addParam(b, PrimeFaces.PARTIAL_UPDATE_PARAM, c, d)
        }
        return b
    }, formatSize: function (a) {
        if (a === undefined) {
            return""
        }
        if (a === 0) {
            return"N/A"
        }
        var b = parseInt(Math.floor(Math.log(a) / Math.log(1024)));
        if (b === 0) {
            return a + " " + this.sizes[b]
        } else {
            return(a / Math.pow(1024, b)).toFixed(1) + " " + this.sizes[b]
        }
    }, removeFiles: function (b) {
        for (var a = 0; a < b.length; a++) {
            this.removeFile(b[a])
        }
    }, removeFile: function (a) {
        var b = this;
        this.files = $.grep(this.files, function (c) {
            return(c.name === a.name && c.size === a.size)
        }, true);
        b.removeFileRow(a.row);
        a.row = null
    }, removeFileRow: function (a) {
        if (a) {
            a.fadeOut(function () {
                $(this).remove()
            })
        }
    }, clear: function () {
        for (var a = 0; a < this.files.length; a++) {
            this.removeFileRow(this.files[a].row);
            this.files[a].row = null
        }
        this.clearMessages();
        this.files = []
    }, validate: function (a) {
        if (this.cfg.allowTypes && !(this.cfg.allowTypes.test(a.type) || this.cfg.allowTypes.test(a.name))) {
            return this.cfg.invalidFileMessage
        }
        if (this.cfg.maxFileSize && a.size > this.cfg.maxFileSize) {
            return this.cfg.invalidSizeMessage
        }
        return null
    }, renderMessages: function () {
        var a = '<div class="ui-messages ui-widget ui-helper-hidden ui-fileupload-messages"><div class="ui-messages-error ui-corner-all"><a class="ui-messages-close" href="#"><span class="ui-icon ui-icon-close"></span></a><span class="ui-messages-error-icon"></span><ul></ul></div></div>';
        this.messageContainer = $(a).prependTo(this.content);
        this.messageList = this.messageContainer.find("> .ui-messages-error > ul");
        this.clearMessageLink = this.messageContainer.find("> .ui-messages-error > a.ui-messages-close")
    }, clearMessages: function () {
        this.messageContainer.hide();
        this.messageList.children().remove()
    }, showMessage: function (c) {
        var a = c.summary, b = "";
        if (c.filename && c.filesize) {
            b = this.cfg.messageTemplate.replace("{name}", c.filename).replace("{size}", this.formatSize(c.filesize))
        }
        this.messageList.append('<li><span class="ui-messages-error-summary">' + a + '</span><span class="ui-messages-error-detail">' + b + "</span></li>");
        this.messageContainer.show()
    }, disableButton: function (a) {
        a.prop("disabled", true).addClass("ui-state-disabled").removeClass("ui-state-hover ui-state-active ui-state-focus")
    }, enableButton: function (a) {
        a.prop("disabled", false).removeClass("ui-state-disabled")
    }, isCanvasSupported: function () {
        var a = document.createElement("canvas");
        return !!(a.getContext && a.getContext("2d"))
    }});

PrimeFaces.widget.SimpleFileUpload = PrimeFaces.widget.BaseWidget.extend({
    init: function (a) {
        this._super(a);
        if (this.cfg.skinSimple) {
            this.button = this.jq.children(".ui-button");
            this.input = $(this.jqId + "_input");
            this.display = this.jq.children(".ui-fileupload-filename");
            if (!this.input.prop("disabled")) {
                this.bindEvents()
            }
        }
    },
    bindEvents: function () {
        var a = this;
        this.button.on("mouseover.fileupload", function () {
            var b = $(this);
            if (!b.prop("disabled")) {
                b.addClass("ui-state-hover")
            }
        }).on("mouseout.fileupload", function () {
            $(this).removeClass("ui-state-active ui-state-hover")
        }).on("mousedown.fileupload", function () {
            var b = $(this);
            if (!b.prop("disabled")) {
                b.addClass("ui-state-active").removeClass("ui-state-hover")
            }
        }).on("mouseup.fileupload", function () {
            $(this).removeClass("ui-state-active").addClass("ui-state-hover")
        });
        this.input.on("change.fileupload", function () {
            var b = PrimeFaces.escapeHTML(a.input.val().replace(/\\/g, "/").replace(/.*\//, ""));
            a.display.text(b)
        }).on("focus.fileupload", function () {
            a.button.addClass("ui-state-focus")
        }).on("blur.fileupload",
                function () {
                    a.button.removeClass("ui-state-focus")
                })
    }
});












// French
PrimeFaces.locales.fr_FR = {
    closeText: 'Fermer',
    prevText: 'Précédent',
    nextText: 'Suivant',
    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    monthNamesShort: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin', 'Juil', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'],
    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    dayNamesShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
    dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
    weekHeader: 'Semaine',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Choisir l\'heure',
    timeText: 'Heure',
    hourText: 'Heures',
    minuteText: 'Minutes',
    secondText: 'Secondes',
    currentText: 'Maintenant',
    ampm: false,
    month: 'Mois',
    week: 'Semaine',
    day: 'Jour',
    allDayText: 'Toute la journée',
    backLabel: 'Précédent',
    nextLabel: 'Suivant'

};
PrimeFaces.locales.fr_FR.InputCropper = {
    name: 'Créateur d\'éditeur d\'image'
};

PrimeFaces.widget.InputCropper = PrimeFaces.widget.BaseWidget.extend({
///
/// Init
    init: function (a) {



        // Convert new parameter
        this._super(a);
        // Main container
        this.inputCropper = $(this.jqId);
        this.for = $(PrimeFaces.escapeClientId($('input[id*=' + this.cfg.for + ']').attr('id')));
        this.to = $(PrimeFaces.escapeClientId($('input[id*=' + (this.cfg.to || 'ncrLinkOn') + ']').attr('id')));
        this.croppedInfosHolder = $(this.jqId + "_croppedInfos");
        this.croppedErrorHolder = $(this.jqId + "_cropError");
        this.croppedDataHolder = $(this.jqId + "_croppedData");

        // Loading state
        this.loading = $('.loading');


        //
        this.input = {};
        this.input.name = $(this.jqId + ' .ic-crop-input-name');

        // Prepare Cropper
        this.for.detach().prependTo(this.input.name);
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
        this.dlg.modal.detach().appendTo(document.body);
        this.dlg.inputFile = this.dlg.modal.find('ncrLinkCropper_input-img-upload');
        this.dlg.labelSpan = $('#ncrLinkCropper_input-img-upload-label');
        this.to.detach().appendTo(this.dlg.labelSpan);
        var propClass = this.dlg.inputFile.prop('class');
        var propCss = this.dlg.inputFile.prop('style');
        var propAccept = this.dlg.inputFile.prop('accept');
        this.to.prop('class', propClass);
        this.to.prop('style', propCss);
        this.to.prop('accept', propAccept);
        this.dlg.inputFile.detach();
        this.dlg.inputFile = this.to;
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
        this.dlg.datas.fx = this.dlg.modal.find('.dataScaleX');
        this.dlg.datas.fy = this.dlg.modal.find('.dataScaleY');
        // Sizer Container docs-data
        this.URL = window.URL || window.webkitURL;
        //
        this.dlg.opts = {};
        this.dlg.opts.drag = this.dlg.modal.find('.optDragMode a');
        this.dlg.opts.left = this.dlg.modal.find('.optRotateBtn a');
        this.dlg.opts.inputZoomerValue = this.dlg.modal.find(".inputZoomerValue");
        this.dlg.opts.inputZoomer = this.dlg.modal.find(".inputZoomer");
        this.dlg.opts.inputZoomerHandler = this.dlg.modal.find(".inputZoomerHandler");
        this.dlg.opts.inputRoterValue = this.dlg.modal.find(".inputRoterValue");
        this.dlg.opts.inputRoter = this.dlg.modal.find(".inputRoter");
        this.dlg.opts.inputRoterHandler = this.dlg.modal.find(".inputRoterHandler");
        this.dlg.opts.inputRotateSpin = this.dlg.modal.find(".inputRotateSpin");
        this.dlg.opts.inputZoomerSpin = this.dlg.modal.find(".inputZoomerSpin");
        this.dlg.opts.inputScaleXSpin = this.dlg.modal.find(".inputScaleXSpin");
        this.dlg.opts.inputScaleYSpin = this.dlg.modal.find(".inputScaleYSpin");
        this.dlg.opts.configs = this.dlg.modal.find('.optCropper a');
        this.dlg.opts.inputCroppers = this.dlg.modal.find('input[data-toggle=toggle]');
        this.dlg.opts.inputCropperCrop = this.dlg.modal.find('.inputCropperCrop');
        this.dlg.opts.inputCropperEdition = this.dlg.modal.find('.inputCropperEdition');
        this.dlg.opts.ratios = this.dlg.modal.find('.optApsectRatio a');
        //
        this.btns = {};
        this.btns.selector = this.dlg.modal.find('.docs-buttons');
        // Input
        this.inputs = {};
        this.inputs.link = this.dlg.modal.find('.optOptions input');
        ///
        this.preview = {};
        this.preview.wrapper = this.dlg.modal.find('.img-wrapper');
        this.preview.image = this.dlg.modal.find('.img-wrapper img');
        this.preview.dragbox = this.dlg.modal.find('.cropper-drag-box');
        this.preview.previews = this.dlg.modal.find('.img-preview');
        this.preview.lg = this.dlg.modal.find('.preview-lg');
        this.preview.md = this.dlg.modal.find('.preview-md');
        this.preview.sm = this.dlg.modal.find('.preview-sm');
        this.preview.xs = this.dlg.modal.find('.preview-xs');
        //
        //
        // Config setup
        this.configCropper(); // configuration de this options
        this.dlg.opts.inputCropperCrop.prop('checked', false);
        this.dlg.opts.inputCropperEdition.prop('checked', false);
        this.cfg.dragCrop = this.cfg.dragCrop || true;
        this.cfg.inputZoomerValue = this.cfg.inputZoomerValue || 1;
        this.cfg.inputRoterValue = this.cfg.inputRoterValue || 180;
        this.cfg.uploadUrl = this.cfg.uploadUrl || '/ISM/faces/upload';
        //
        //
        // Build and bind
        this.configLang();
        this.configTooltip();
        this.configComponent();
        this.bindEvents();
    },
    //
    //
    //
    // Build

    ///
    ///
    /// Init
    ///
    ///
    configLang: function (a) {
        // Main parameter
        var lg = PrimeFaces.settings.locale;
        //console.log("Lang utilisée : " + lg);
        //console.log(PrimeFaces.locales[lg])
        //console.log(PrimeFaces)
    },
    configCropper: function (a) {
        // Create cropper config if not defined
        this.cfg.cropper = this.cfg.cropper || {};

        // Aspect ratio
        switch (this.cfg.aspectRatio) {
            case 'cinema':
                this.cfg.cropper.aspectRatio = 16 / 9;
                break
            case 'photo':
                this.cfg.cropper.aspectRatio = 4 / 3;
                break
            case 'camera':
                this.cfg.cropper.aspectRatio = 1 / 1;
                break
            case 'avatar':
                this.cfg.cropper.aspectRatio = 2 / 3;
                break
            case 'avatar':
                this.cfg.cropper.aspectRatio = NaN;
                break
        }

        this.cfg.cropper.aspectRatio = this.cfg.cropper.aspectRatio || (1);
        this.cfg.cropper.autoCrop = this.cfg.cropper.autoCrop || true;
        this.cfg.cropper.autoCropArea = this.cfg.cropper.autoCropArea || 0.8;
        this.cfg.cropper.center = this.cfg.cropper.center || true;
        this.cfg.cropper.cropBoxMovable = this.cfg.cropper.cropBoxMovable || true;
        this.cfg.cropper.cropBoxResizable = this.cfg.cropper.cropBoxResizable || true;
        //this.cfg.cropper.data = this.cfg.cropper.data || null;
        this.cfg.cropper.dragMode = this.cfg.cropper.dragMode || 'crop';
        this.cfg.cropper.guides = this.cfg.cropper.guides || true;
        this.cfg.cropper.highlight = this.cfg.cropper.highlight || true;
        this.cfg.cropper.minCanvasHeight = this.cfg.cropper.minCanvasHeight || 0;
        this.cfg.cropper.minCanvasWidth = this.cfg.cropper.minCanvasWidth || 0;
        this.cfg.cropper.minContainerHeight = this.cfg.cropper.minContainerHeight || 100;
        this.cfg.cropper.minContainerWidth = this.cfg.cropper.minContainerWidth || 200;
        this.cfg.cropper.minCropBoxHeight = this.cfg.cropper.minCropBoxHeight || 0;
        this.cfg.cropper.minCropBoxWidth = this.cfg.cropper.minCropBoxWidth || 0;
        this.cfg.cropper.movable = this.cfg.cropper.movable || true;
        this.cfg.cropper.preview = this.cfg.cropper.preview || '.img-preview';
        this.cfg.cropper.responsive = this.cfg.cropper.responsive || true;
        this.cfg.cropper.restore = this.cfg.cropper.restore || true;
        this.cfg.cropper.rotatable = this.cfg.cropper.rotatable || true;
        this.cfg.cropper.scalable = this.cfg.cropper.scalable || true;
        this.cfg.cropper.toggleDragModeOnDblclick = this.cfg.cropper.toggleDragModeOnDblclick || true;
        this.cfg.cropper.wheelZoomRatio = this.cfg.cropper.wheelZoomRatio || 0.1;
        this.cfg.cropper.zoomable = this.cfg.cropper.zoomable || true;
        this.cfg.cropper.zoomOnTouch = this.cfg.cropper.zoomOnTouch || true;
        this.cfg.cropper.zoomOnWheel = this.cfg.cropper.zoomOnWheel || true;
        ///
        var thisa = this; // current object
        this.cropper = {}; // current cropper 
        this.cropper.options = {
            aspectRatio: this.cfg.cropper.aspectRatio,
            autoCrop: this.cfg.cropper.autoCrop,
            autoCropArea: this.cfg.cropper.autoCropArea,
            center: this.cfg.cropper.center,
            cropBoxMovable: this.cfg.cropper.cropBoxMovable,
            cropBoxResizable: this.cfg.cropper.cropBoxResizable,
            //data: this.cfg.cropper.data || NaN,
            dragMode: this.cfg.cropper.dragMode,
            guides: this.cfg.cropper.guides,
            highlight: this.cfg.cropper.highlight,
            minCanvasHeight: this.cfg.cropper.minCanvasHeight,
            minCanvasWidth: this.cfg.cropper.minCanvasWidth,
            minContainerHeight: this.cfg.cropper.minContainerHeight,
            minContainerWidth: this.cfg.cropper.minContainerWidth,
            minCropBoxHeight: this.cfg.cropper.minCropBoxHeight,
            minCropBoxWidth: this.cfg.cropper.minCropBoxWidth,
            movable: this.cfg.cropper.movable,
            preview: this.cfg.cropper.preview || '.img-preview',
            responsive: this.cfg.cropper.responsive,
            restore: this.cfg.cropper.restore,
            rotatable: this.cfg.cropper.rotatable,
            scalable: this.cfg.cropper.scalable,
            toggleDragModeOnDblclick: this.cfg.cropper.toggleDragModeOnDblclick,
            wheelZoomRatio: this.cfg.cropper.wheelZoomRatio,
            zoomable: this.cfg.cropper.zoomable,
            zoomOnTouch: this.cfg.cropper.zoomOnTouch,
            zoomOnWheel: this.cfg.cropper.zoomOnWheel,
            ready: function (e) {
                thisa.ready(e);
            },
            cropstart: function (e) {
                thisa.cropstart(e);
            },
            cropmove: function (e) {
                thisa.cropmove(e);
            },
            cropend: (function (e) {
                thisa.cropend(e);
            }),
            crop: (function (e) {
                thisa.crop(e);
            }),
            zoom: function (e) {
                thisa.zoom(e);
            }
        };
        this.cropper.originalImageURL = this.preview.image.attr('src');
        this.cropper.uploadedImageType = 'image/jpeg';
        this.cropper.uploadedImageURL = '';
        this.cropper.active = false;

        // Create cropper canvas options
        this.cropper.canvas = {};
        this.cropper.canvas.options = {
            width: 1024,
            height: 1024,
            minWidth: 256,
            minHeight: 256,
            maxWidth: 4096,
            maxHeight: 4096,
            fillColor: '#fff',
            imageSmoothingEnabled: false,
            imageSmoothingQuality: 'low'
        };

        // Cropper file
        this.cropper.file = {};

        // Create cropper Error
        this.cropper.error = {};
        this.cropper.error.code = 0;
        this.cropper.error.size = {};
        this.cropper.error.size.value = 0;
        this.cropper.error.size.msg = '';
        this.cropper.error.size.min = 5;
        this.cropper.error.size.max = 10000000;
    },
    configTooltip: function () {
        // Init gloabal tooltip
        $('[data-toggle="tooltip"]').tooltip();
        // Init gloabl Popover
        $('[data-toggle="popover"]').popover();
    },
    configComponent: function () {
        this.dlg.opts.inputZoomerValue.val(this.cfg.inputZoomerValue);
        var _this = this;
        this.dlg.opts.inputZoomer.slider({
            min: 0.1, max: 10,
            value: this.cfg.inputZoomerValue,
            step: 0.001,
            slide: function (event, ui) {
                if (!_this.cropper.active)
                    return;
                _this.dlg.opts.inputZoomerValue.val(ui.value);
                _this.doZoom();
                //_this.dlg.opts.inputZoomerHandler.text(ui.value)

            }
        });
        this.dlg.opts.inputZoomer.slider('disable');
        this.dlg.opts.inputRoter.slider({
            min: -180, max: 180,
            value: this.cfg.inputRoterValue,
            step: 1,
            slide: function (event, ui) {
                if (!_this.cropper.active)
                    return;
                _this.dlg.opts.inputRoterValue.val(ui.value);
                _this.doRotate();
                //_this.dlg.opts.inputRoterHandler.text(ui.value)
            }
        });
        this.dlg.opts.inputRoter.slider('disable');
        this.dlg.opts.inputRotateSpin.TouchSpin({
            min: -180,
            max: 180,
            initval: 0,
            step: 1,
            decimals: 0,
            boostat: 5,
            maxboostedstep: 40,
            mousewheel: true,
            postfix: 'deg'
        });
        this.dlg.opts.inputZoomerSpin.TouchSpin({
            initval: this.cfg.inputZoomerValue,
            min: 0.1, max: 10,
            step: 0.001,
            decimals: 3,
            boostat: 5,
            maxboostedstep: 40,
            mousewheel: true,
            postfix: 'A/R'
        });
        this.dlg.opts.inputScaleXSpin.TouchSpin({
            min: -5, max: 5,
            initval: 1,
            step: 0.01,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 40,
            mousewheel: true,
            postfix: 'X'
        });
        this.dlg.opts.inputScaleYSpin.TouchSpin({
            min: -5, max: 5,
            initval: 1,
            step: 0.01,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 40,
            mousewheel: true,
            postfix: 'Y'
        });
    },
    ///
    ///
    /// Bind Events
    ///
    ///
    bindEvents: function () {
        //
        // Event for overlay cropped
        this.input.name.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));
        this.img.container.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));
        this.img.cropped.hover($.proxy(this.inputNameShow, this), $.proxy(this.inputNameHide, this));

        // Image wrapper
        this.preview.wrapper.hover($.proxy(this.tooltipOn, this), $.proxy(this.tooltipOff, this));
        // Cropper events
        this.bindEventsCropper();
        // Inputs
        this.dlg.opts.inputZoomerSpin.on('change', $.proxy(this.doZoom, this));
        this.dlg.opts.inputRotateSpin.on('change', $.proxy(this.doRotate, this));
        this.dlg.opts.inputScaleXSpin.on('change', $.proxy(this.doScaleX, this));
        this.dlg.opts.inputScaleYSpin.on('change', $.proxy(this.doScaleY, this));
        // Cropper
        this.dlg.opts.inputCroppers.on('change', $.proxy(this.doCropper, this));
    },
    bindEventsCropper: function (a) {
        // Cropper
        this.preview.image.cropper(this.cropper.options);
        // Input Image
        var that = this;
        console.log(this.dlg.inputFile);
        this.dlg.inputFile.change($.proxy(this.inputChange, this));
        // Options
        this.inputs.link.change('input', $.proxy(this.inputRequest, this));
        // Methods
        this.btns.selector.on('click', '[data-method]', $.proxy(this.cmdRequest, this));
    },
    //
    //
    //
    ready: function (e) {
        //console.log(e.type);
    },
    cropstart: function (e) {
        //console.log(e.type, e.action);
    },
    cropmove: function (e) {
        //console.log(e.type, e.action);
    },
    cropend: function (e) {
        //console.log(e.type, e.action);
    },
    crop: function (e) {
        if (!this.cropper.active)
            return;
        this.dlg.datas.x.val(Math.round(e.x));
        this.dlg.datas.y.val(Math.round(e.y));
        this.dlg.datas.h.val(Math.round(e.height));
        this.dlg.datas.w.val(Math.round(e.width));
        this.dlg.datas.r.val(e.rotate);
        this.dlg.datas.fx.val(e.scaleX);
        this.dlg.datas.fy.val(e.scaleY);
        // Save Input Data
        var json = [
            '{"x":' + e.x,
            '"y":' + e.y,
            '"height":' + e.height,
            '"width":' + e.width,
            '"rotate":' + e.rotate + '}'
        ].join();
        this.dlg.data.val(json);
        //console.log(json);

        rotateDegree = (e.rotate <= 180 ? (e.rotate >= -180 ? e.rotate : 360 + e.rotate) : -360 + e.rotate);
        this.dlg.opts.inputRoter.slider('value', rotateDegree);
        //this.dlg.opts.inputRoterHandler.text(rotateDegree);
        this.dlg.opts.inputRotateSpin.val(rotateDegree);
    },
    zoom: function (e) {
        //console.log(e.type, e.ratio);
        zoomRatio = e.ratio / this.cropper.zoom;
        this.dlg.opts.inputZoomer.slider('value', zoomRatio);
        //this.dlg.opts.inputZoomerHandler.text(zoomRatio);
        this.dlg.opts.inputZoomerSpin.val(zoomRatio);
    },
    //
    //
    //
    inputChange: function (e) {
        var files, file;
        files = this.dlg.inputFile.prop('files');
        if (files && files.length) {
            file = files[0];
            if (this.isImageFile(file)) {
                this.cropper.uploadedImageType = file.type;
                if (this.cropper.uploadedImageURL) {
                    URL.revokeObjectURL(this.cropper.uploadedImageURL); // Revoke the old one
                }

                this.cropper.uploadedImageURL = URL.createObjectURL(file);
                if (this.cropper.active) {
                    this.preview.image.cropper('destroy').attr('src', this.cropper.uploadedImageURL).cropper(this.cropper.options);
                } else {
                    this.preview.image = $('<img src="' + this.cropper.uploadedImageURL + '">');
                    this.preview.wrapper.empty().html(this.preview.image);
                    this.preview.image.cropper(this.cropper.options);
                }
                this.dlg.inputFile.val('');
                this.tooltipOff();
                this.cropper.active = true;
                this.cropper.zoom = null;
                this.dlg.opts.inputZoomer.slider('enable');
                this.dlg.opts.inputRoter.slider('enable');
                this.dlg.opts.inputZoomer.slider('value', 1);
                this.dlg.opts.inputRoter.slider('value', 180);
                this.dlg.opts.inputRotateSpin.val(180)
                this.dlg.opts.inputZoomerSpin.val(1)
                this.dlg.opts.inputScaleXSpin.val(1);
                this.dlg.opts.inputScaleYSpin.val(1);

                // Save file
                if (!this.cropper.file.ext) {
                    this.cropper.file.filenameOld = this.cropper.file.filename + '.' + this.cropper.file.ext;
                }
                this.cropper.file = file;
                this.cropper.file.ext = this.getImageExtension(file);
                this.cropper.file.filename = this.getOnlyFilename(file);
            } else {
                window.alert('Please choose an image file.');
            }
        }
    },
    inputRequest: function (e) {
        var name = this.inputs.link.attr('name');
        var type = this.inputs.link.prop('type');
        var cropBoxData;
        var canvasData;
        if (!this.preview.image.data('cropper')) {
            return;
        }

        //console.log('Type : ' + type);

        if (type === 'checkbox') {
            this.cropper.options[name] = this.inputs.link.prop('checked');
            cropBoxData = this.preview.image.cropper('getCropBoxData');
            canvasData = this.preview.image.cropper('getCanvasData');
            this.cropper.options.ready = function () {
                this.preview.image.cropper('setCropBoxData', cropBoxData);
                this.preview.image.cropper('setCanvasData', canvasData);
            };
        } else if (type === 'radio') {
            this.cropper.options[name] = this.inputs.link.val();
        }

        this.preview.image.cropper('destroy').cropper(this.cropper.options);
    },
    cmdRequest: function (e) {
        var $this = e.target;
        var data = $this.dataset;
        var cropper = this.preview.image.data('cropper');
        var cropped;
        var $target;
        var result;
        if (!cropper || !data.method)
            return;
        if (cropper && data.method) {
            data = $.extend({}, data); // Clone a new one

            if (typeof data.target !== 'undefined') {
                $target = $(data.target);
                if (typeof data.option === 'undefined') {
                    try {
                        data.option = JSON.parse($target.val());
                    } catch (e) {
                        console.log(e.message);
                    }
                }
            }

            cropped = cropper.cropped;
            // Manage first options
            switch (data.method) {
                case 'rotate':
                    {
                        if (cropped && this.cropper.options.viewMode > 0) {
                            this.preview.image.cropper('clear');
                        }
                    }
                    break;
                case 'getCroppedCanvas':
                    {
                        if (this.cropper.uploadedImageType === 'image/jpeg') {

                            if (!data.option) {
                                data.option = {};
                            }
                            //data.option = this.cropper.canvas.options;
                            data.option.fillColor = '#fff';
                        }
                    }
                    break;
                case 'scaleX':
                    {
                        var scaleX = -this.getScaleX();
                        $($this).attr('data-option', scaleX);
                        this.dlg.opts.inputScaleXSpin.val(scaleX);
                        data.option = scaleX;
                    }
                    break;
                case 'scaleY':
                    {
                        var scaleY = -this.getScaleY();
                        $($this).attr('data-option', scaleY);
                        this.dlg.opts.inputScaleYSpin.val(scaleY);
                        data.option = scaleY;
                    }
                    break;
                case 'zoom' :
                    {
                        // Save initial zoom
                        if (!this.cropper.zoom) {
                            this.cropper.zoom = this.getZoom();
                        }
                    }
                    break;
                case 'zoomTo':
                    {
                        // Save initial zoom
                        if (!this.cropper.zoom) {
                            this.cropper.zoom = this.getZoom();
                        }
                        if (data.option === 'reset') {
                            data.option = this.cropper.zoom;
                        }
                    }
                    break;
            }

            // Manage second options
            result = this.preview.image.cropper(data.method, data.option, data.secondOption);
            switch (data.method) {
                case 'rotate':
                    {
                        if (cropped && this.cropper.options.viewMode > 0) {
                            this.preview.image.cropper('crop');
                        }
                    }
                    break;
                case 'getCroppedCanvas':
                    {
                        if (result) {
                            $(this.loading).show();
                            // Get Blob Data
                            this.base64Str = result.toDataURL(this.cropper.file.type, "1.0");//.replace('data:' + this.cropper.file.type + ';base64,', '');


                            var datasize = this.base64Str.length;
                            //console.log("Taille cropped : " + this.toFormatted(datasize));
                            if (datasize >= this.cropper.error.size.max || datasize <= this.cropper.error.size.min) {
                                this.cropper.error.size.value = datasize;
                                this.cropper.error.size.msg = 'La taille de rognage [' + this.toFromatted(datasize) + '] est hors limite [' + this.toFromatted(this.cropper.error.size.min) + '; ' + this.toFromatted(this.cropper.error.size.max) + '] !';
                                this.fireErrorCropEvent();
                                $(this.loading).hide();
                                return;
                            }

                            // Apply Blob To display Overlay Image URL
                            this.img.cropped.prop('src', this.base64Str);
                            // Save Old File name
                            this.originalFilenameOld = (this.originalFilename + this.cropper.file.ext) || null;
                            // Create new filename
                            this.originalFilename = this.getOnlyFilename(this.cropper.file) + "_" + this.getUniqueKey(); //.name.replace('/(.*)\.(.*?)$/, "$1"').replace(".", "_") + "_" + this.getUniqueKey();
                            // Write value to Input
                            //$(this.for).prop('value', this.originalFilename);
                            $(this.for).prop('disabled', true);

                            // Fire cropped Event
                            this.fireCroppedEvent();


                            $(this.loading).hide();
                        }
                    }
                    break;
                case 'destroy':
                    {
                        if (this.cropper.uploadedImageURL) {
                            URL.revokeObjectURL(this.cropper.uploadedImageURL);
                            this.cropper.uploadedImageURL = '';
                            this.preview.image.attr('src', this.cropper.originalImageURL);
                        }
                    }
                    break;
            }

            //
            if ($.isPlainObject(result) && $target) {
                try {
                    $target.val(JSON.stringify(result));
                } catch (e) {
                    console.log(e.message);
                }
            }
        }
    },
    //
    //
    //
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
    doZoom: function (e) {
        if (!this.cropper.active)
            return;
        //console.log(e.target.value)
        if (!this.cropper.zoom) {
            this.cropper.zoom = this.getZoom();
        }
        if (e) {
            this.dlg.opts.inputZoomerValue.val(e.target.value);
            this.dlg.opts.inputZoomerSpin.val(e.target.value);
        } else {
            this.dlg.opts.inputZoomerSpin.val(this.dlg.opts.inputZoomerValue.val())
        }

        this.preview.image.cropper('zoomTo', this.cropper.zoom * this.dlg.opts.inputZoomerValue.val());
    },
    doRotate: function (e) {
        if (!this.cropper.active)
            return;
        if (e) {
            this.dlg.opts.inputRoterValue.val(e.target.value);
            this.dlg.opts.inputRotateSpin.val(e.target.value);
        } else {
            this.dlg.opts.inputRotateSpin.val(this.dlg.opts.inputRoterValue.val())
        }

        var delta = this.dlg.opts.inputRoterValue.val();
        this.preview.image.cropper('rotateTo', delta);
    },
    doScaleX: function (e) {
        if (!this.cropper.active)
            return;
        if (!this.cropper.scaleX) {
            this.cropper.scaleX = this.getScaleX();
        }
        this.preview.image.cropper('scaleX', this.cropper.scaleX * this.dlg.opts.inputScaleXSpin.val());
    },
    doScaleY: function (e) {
        if (!this.cropper.active)
            return;
        if (!this.cropper.scaleY) {
            this.cropper.scaleY = this.getScaleY();
        }
        this.preview.image.cropper('scaleY', this.cropper.scaleY * this.dlg.opts.inputScaleYSpin.val());
    },
    doCropper: function (e) {
        if (!this.cropper.active)
            return;
        var checked = $(e.target).prop('checked') === true;
        switch ($(e.target).attr('data-method')) {
            case 'crop':
                {
                    if (checked) {
                        this.preview.image.cropper('crop');
                    } else {
                        this.preview.image.cropper('clear');
                    }
                }
                break
            case 'edition':
                {
                    if (checked) {
                        this.preview.image.cropper('enable');
                    } else {
                        this.preview.image.cropper('disable');
                    }
                }
                break
        }
    },
    ///
    ///
    ///
    /// FIRE EVENTS
    fireErrorCropEvent: function () {
        if (this.cfg.behaviors) {
            var c = this.cfg.behaviors.cropError;
            if (c) {
                var source = this.cropper.error;
                this.croppedErrorHolder.prop('value', JSON.stringify(source));
                c.call(this, source);
            }
        }
    },
    fireCroppedEvent: function () {
        if (this.cfg.behaviors) {
            var c = this.cfg.behaviors.cropped;
            if (c) {
                var that = this;
                
                // Send Image to memory
                var blobIn = this.dataURItoBlob(this.base64Str); 
                var name = this.cropper.file.filename + "" + this.cropper.file.ext;
                var d = (new Date);
                var t = this.cropper.file.type;
                var file = new File([blobIn], name, {type: t, lastmModified: d});
                var formData = new FormData();
                formData.append('file', file);

                //console.log(that.cfg.uploadUrl)
//                $.ajax({
//                    type: 'POST',
//                    url: that.cfg.uploadUrl,
//                    async: true,
//                    data: formData,
//                    cache: false,
//                    contentType: false,
//                    processData: false,
//                    timeout: 60000,
//                    beforeSend: function(){console.log('before send')},
//                    success: function (data) {
//                        //console.log("Success")
//                        //$('body').html(data);
//                    },
//                    error: function (er) { console.log('Error [' + er.status + '] : ' + er.statusText)},
//                    complete: function(){ console.log('complete');  }
//                });
//                console.log(c);
                
                // Affecte la variable de sortie
//                console.log(this.dlg.inputFile);
//                console.log(this.for);
//                this.for[0] = this.dlg.inputFile[0];
//                console.log(this.for);
//                this.for.prop('file', file);
//                console.log(this.for);
                    this.dlg.inputFile.prop("file", file);
                    this.for = this.for;
//                this.for.filename = ($(this.preview.wrapper.find('img')).prop('src'));
//                var files = {0:file, length:1};
//                var sf = [];
//                sf.push(files);
//                this.for.file.push(file);
                //this.files[a].row.data("filedata").submit()
                // Send data cropped
                var json = this.getAllAsJson();
                c.call(this, json);
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
    tooltipOn: function (e) {
        if (this.cropper.active)
            return;
        $('.docs-tooltip').tooltip('show');
    },
    tooltipOff: function (e) {
        if (this.cropper.active)
            return;
        $('.docs-tooltip').tooltip('hide');
    },
    getZoom: function (e) {
        if (!this.cropper.active)
            return 0;
        var i = this.preview.image.cropper("getImageData");
        //console.log(i)
        return i.width / i.naturalWidth;
    },
    getScaleX: function (e) {
        if (!this.cropper.active)
            return 0;
        var i = this.preview.image.cropper("getImageData");
        //console.log(i)
        return i.scaleX;
    },
    getScaleY: function (e) {
        if (!this.cropper.active)
            return 0;
        var i = this.preview.image.cropper("getImageData");
        //console.log(i)
        return i.scaleY;
    },
    getImageExtension: function (file) {
        var mimeType = null;
        switch (file.type) {
            case 'image/jpeg':
                if (file.name.toLowerCase().indexOf(".jpg")) {
                    mimeType = '.jpg';
                } else if (file.name.toLowerCase().indexOf(".jpeg")) {
                    mimeType = '.jpeg';
                }
                break;
            case 'image/png':
                mimeType = '.png';
                break;
            case 'image/gif':
                mimeType = '.gif';
                break;
        }
        return mimeType;
    },
    getOnlyFilename: function (file) {
        var name = file.name;
        var mimeType = this.getImageExtension(file);
        name = name.replace(mimeType, '').replace(mimeType.toUpperCase(), '');
        return name;
    },
    getAllAsJson: function (e) {
        if (!this.cropper.active)
            return 0;
        var source = {};
        source.containerData = this.preview.image.cropper("getContainerData");
        source.imageData = this.preview.image.cropper("getImageData");
        source.croppedCanvas = this.preview.image.cropper("getCroppedCanvas", {maxWidth: 4096, maxHeight: 4096});
        source.canvasData = this.preview.image.cropper("getCanvasData");
        source.cropBoxData = this.preview.image.cropper("getCropBoxData");
        source.data = this.preview.image.cropper("getData");
        //source.blobIn = $(this.preview.wrapper.find('img')).prop('src');
        source.originalFilename = this.originalFilename;
        source.originalFilenameOld = this.originalFilenameOld;
        source.file = {};
        source.file.lastModified = this.cropper.file.lastModified;
        source.file.lastModifiedDate = this.cropper.file.lastModifiedDate;
        source.file.name = this.cropper.file.name;
        source.file.size = this.cropper.file.size;
        source.file.type = this.cropper.file.type;
        source.file.filename = this.cropper.file.filename;
        source.file.ext = this.cropper.file.ext;
        //source.blob = this.base64Str;

        //console.log(this.cropper.file)
        var json = {params: [{source}]};
        try {
            this.croppedInfosHolder.prop('value', JSON.stringify(source));
        } catch (e) {
            console.log(e.message);
        }
        return json;
    },
    getUniqueKey: function () {
        var milliseconds = (new Date).getTime();
        return (Math.random().toString(36).substr(2, 36) + "_" + milliseconds).toUpperCase();
    },
    toFormatted: function (nbr) {
        var nombre = '' + nbr;
        var retour = '';
        var count = 0;
        for (var i = nombre.length - 1; i >= 0; i--) {
            if (count !== 0 && count % 3 === 0)
                retour = nombre[i] + ' ' + retour;
            else
                retour = nombre[i] + retour;
            count++;
        }
        //alert('nb : ' + nbr + ' => ' + retour);
        return retour;
    },
    dataURItoBlob: function (dataURI) {
        // convert base64 to raw binary data held in a string
        // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
        var byteString = atob(dataURI.split(',')[1]);

        // separate out the mime component
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

        // write the bytes of the string to an ArrayBuffer
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }

        //Old Code
        //write the ArrayBuffer to a blob, and you're done
        //var bb = new BlobBuilder();
        //bb.append(ab);
        //return bb.getBlob(mimeString);

        //New Code
        var resultBlob = new Blob([ab], {type: mimeString});
        //console.log(resultBlob);
        return resultBlob;
    }
    

});
$(function () {

});





