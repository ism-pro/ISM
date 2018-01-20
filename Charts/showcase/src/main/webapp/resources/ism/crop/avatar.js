
PrimeFaces.widget.CropAvatar = PrimeFaces.widget.BaseWidget.extend({
    ///
    /// Init
    init: function (a) {

        this._super(a);
        this.$container = $(this.jqId);

        /// ///////////////////////////
        // Get convenient object
        this.$avatarView = this.$container.find('.avatar-view');
        this.$avatar = this.$avatarView.find('img');
        this.$avatarModal = this.$container.find('#avatar-modal');
        this.$loading = this.$container.find('.loading');

        this.$avatarForm = this.$avatarModal.find('#avatar-form');//.closest("form");//
        this.$avatarUpload = this.$avatarForm.find('.avatar-upload');
        this.$avatarSrc = this.$avatarForm.find('.avatar-src');
        this.$avatarData = this.$avatarForm.find('.avatar-data');
        this.$avatarInput = this.$avatarForm.find('.avatar-input');
        this.$avatarSave = this.$avatarForm.find('.avatar-save');
        this.$avatarBtns = this.$avatarForm.find('.avatar-btns');

        this.$avatarWrapper = this.$avatarModal.find('.avatar-wrapper');
        this.$avatarPreview = this.$avatarModal.find('.avatar-preview');

        // Construction
        this.build(this);

    },

    ///
    /// Build
    support: {
        fileList: !!$('<input type="file">').prop('files'),
        blobURLs: !!window.URL && URL.createObjectURL,
        formData: !!window.FormData
    },

    build: function () {
        /// ///////////////////////////
        // Setup object
        this.support.datauri = this.support.fileList && this.support.blobURLs;
        if (!this.support.formData) {
            this.initIframe();
        }

        this.initTooltip();
        this.initModal();
        this.bindEvents();
    },
    initTooltip: function () {
        this.$avatarView.tooltip({
            placement: 'bottom'
        });
    },
    initModal: function () {
        this.$avatarModal.modal({
            show: false
        });
    },
    initPreview: function () {
        var url = this.$avatar.attr('src');
        this.$avatarPreview.html('<img src="' + url + '">');
    },
    initIframe: function () {
        console.log("Init Frame")
        var target = 'upload-iframe-' + (new Date()).getTime();
        var $iframe = $('<iframe>').attr({
            name: target,
            src: ''
        });
        var _this = this;

        // Ready ifrmae
        $iframe.one('load', function () {

            // respond response
            $iframe.on('load', function () {
                var data;

                try {
                    data = $(this).contents().find('body').text();
                } catch (e) {
                    console.log(e.message);
                }

                if (data) {
                    try {
                        data = $.parseJSON(data);
                    } catch (e) {
                        console.log(e.message);
                    }

                    _this.submitDone(data);
                } else {
                    _this.submitFail('Image upload failed!');
                }

                _this.submitEnd();

            });
        });

        this.$iframe = $iframe;
        this.$avatarForm.attr('target', target).after($iframe.hide());
    },

    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// Bind Events
    bindEvents: function () {
        var thisa = this;
        this.$avatarView.on('click', $.proxy(this.click, this));
        this.$avatarInput.on('change', $.proxy(this.change, this));
        this.$avatarForm.on('submit', $.proxy(this.submit, this));
        this.$avatarBtns.on('click', $.proxy(this.rotate, this));
        this.$avatarSave.click(function (e) {
            thisa.submit();
        });
    },
    click: function () {
        this.$avatarModal.modal('show');
        this.initPreview();
    },
    change: function () {
        var files, file;

        if (this.support.datauri) {
            files = this.$avatarInput.prop('files');
            if (files.length > 0) {
                file = files[0];
                if (this.isImageFile(file)) {
                    if (this.url) {
                        URL.revokeObjectURL(this.url); // Revoke the old one
                    }
                    this.url = URL.createObjectURL(file);
                    this.startCropper();
                }
            }
        } else {
            file = this.$avatarInput.val();

            if (this.isImageFile(file)) {
                this.syncUpload();
            }
        }
    },
    submit: function () {
        console.log("submit")
        if (!this.$avatarSrc.val() && !this.$avatarInput.val()) {
            return false;
        }

        if (this.support.formData) {
            //this.fireUploadEvent();
            this.ajaxUpload();
            return false;
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

    /// ////////////////////////////////////////////////////////////////////////
    ///
    // Standard Function
    isImageFile: function (file) {
        console.log("isImageFile")
        if (file.type) {
            return /^image\/\w+$/.test(file.type);
        } else {
            return /\.(jpg|jpeg|png|gif)$/.test(file);
        }
    },
    startCropper: function () {
        console.log("start Cropper")
        var _this = this;

        if (this.active) {
            console.log("start cropper : url : " + this.url)
            this.$img.cropper('replace', this.url);
        } else {
            this.$img = $('<img src="' + this.url + '">');
            this.$avatarWrapper.empty().html(this.$img);
            this.$img.cropper({
                aspectRatio: 1,
                preview: this.$avatarPreview.selector,
                crop: function (e) {
                    var json = [
                        '{"x":' + e.x,
                        '"y":' + e.y,
                        '"height":' + e.height,
                        '"width":' + e.width,
                        '"rotate":' + e.rotate + '}'
                    ].join();

                    _this.$avatarData.val(json);
                }
            });

            this.active = true;
        }

        this.$avatarModal.one('hidden.bs.modal', function () {
            _this.$avatarPreview.empty();
            _this.stopCropper();
        });
    },
    stopCropper: function () {

        console.log("stop cropper")
        if (this.active) {
            this.$img.cropper('destroy');
            this.$img.remove();
            this.active = false;
        }
    },

    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// Fire Eventn
    fireUploadEvent: function (c) {
        console.log("fireUploadEvent")
        var url = this.$avatarForm.attr('action');
        var data = new FormData(this.$avatarForm[0]);
        var _this = this;
        
        if (this.cfg.behaviors) {
            var a = this.cfg.behaviors.itemSelect;

            if (a) {
                var b = {params: [{name: this.id + "_newTab", value: a.attr("id")}, {name: this.id + "_tabindex", value: parseInt(a.index() / 2)}]};
                a.call(this, b);
            }
        }
    },

    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////

    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////






    ajaxUpload: function () {

        console.log("ajax upload")
        console.log(this.$avatarForm)
        var url = this.$avatarForm.attr('action');
        var data = new FormData(this.$avatarForm[0]);
        var _this = this;

        $.ajax(url, {
            type: 'post',
            data: data,
            dataType: 'json',
            processData: false,
            contentType: false,

            beforeSend: function () {
                _this.submitStart();
            },

            success: function (data) {
                _this.submitDone(data);
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                _this.submitFail(textStatus || errorThrown);
            },

            complete: function () {
                _this.submitEnd();
            }
        });
    },

    syncUpload: function () {
        console.log("syncUpload")
        this.$avatarSave.click();
    },

    submitStart: function () {
        console.log("submitStart")
        this.$loading.fadeIn();
    },

    submitDone: function (data) {
        console.log("submitDone")
        console.log(data);

        if ($.isPlainObject(data) && data.state === 200) {
            if (data.result) {
                this.url = data.result;

                if (this.support.datauri || this.uploaded) {
                    this.uploaded = false;
                    this.cropDone();
                } else {
                    this.uploaded = true;
                    this.$avatarSrc.val(this.url);
                    this.startCropper();
                }

                this.$avatarInput.val('');
            } else if (data.message) {
                this.alert(data.message);
            }
        } else {
            this.alert('Failed to response');
        }
    },

    submitFail: function (msg) {
        console.log("submitFail")
        this.alert(msg);
    },

    submitEnd: function () {
        console.log("submitFail")
        this.$loading.fadeOut();
    },

    cropDone: function () {
        this.$avatarForm.get(0).reset();
        this.$avatar.attr('src', this.url);
        this.stopCropper();
        this.$avatarModal.modal('hide');
    },

    alert: function (msg) {
        var $alert = [
            '<div class="alert alert-danger avatar-alert alert-dismissable">',
            '<button type="button" class="close" data-dismiss="alert">&times;</button>',
            msg,
            '</div>'
        ].join('');

        this.$avatarUpload.after($alert);
    }


});