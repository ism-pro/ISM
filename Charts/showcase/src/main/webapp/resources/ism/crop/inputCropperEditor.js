PrimeFaces.widget.InputCropperEditor = PrimeFaces.widget.BaseWidget.extend({
///
/// Init
    init: function (a) {
        this._super(a);
        // Main container
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
        this.dlg.inputFile = this.dlg.modal.find('.input-img-upload');
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
        this.dlg.opts.left = this.dlg.modal.find('.optRotateLeft a');
        this.dlg.opts.right = this.dlg.modal.find('.optRotateRight a');
        this.dlg.opts.inputZoomerValue = this.dlg.modal.find(".inputZoomerValue");
        this.dlg.opts.inputZoomerValueSaver = 0;
        this.dlg.opts.inputZoomer = this.dlg.modal.find(".inputZoomer");
        this.dlg.opts.inputZoomerHandler = this.dlg.modal.find(".inputZoomerHandler");
        this.dlg.opts.configs = this.dlg.modal.find('.optCropper a');
        this.dlg.opts.ratios = this.dlg.modal.find('.optApsectRatio a');
        //
        this.btns = {};
        this.btns.selector = this.dlg.modal.find('.docs-buttons');
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
        this.configCropper();   // configuration de this options
        this.cfg.dragCrop = this.cfg.dragCrop || true;
        this.cfg.inputZoomerValue = this.cfg.inputZoomerValue || 0;
        //
        //
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
    configCropper: function (a) {
        var thisa = this;
        this.cropper = {};
        this.cropper.options = {
            preview: '.img-preview',
            aspectRatio: 1 / 1,
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
            rotate: (function (e) {
                console.log('rotate')
            }),
            rotateTo: (function (e) {
                console.log('rotateTo')
            }),
            zoom: function (e) {
                thisa.zoom(e);
            }
        };
        this.cropper.originalImageURL = this.preview.image.attr('src');
        this.cropper.uploadedImageType = 'image/jpeg';
        this.cropper.uploadedImageURL = '';
        this.cropper.active = false;
    },
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
                    _this.zoomOld();
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
        // Cropper events
        this.bindEventsCropper();
    },
    bindEventsCropper: function (a) {
        // Cropper
        var thisa = this;
        console.log('bind event cropper');
        this.preview.image.cropper(this.cropper.options);



        // Input Image
        this.dlg.inputFile.change(function () {
            var files = this.files, file;

            if (!thisa.preview.image.data('cropper')) {
                return;
            }

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    thisa.cropper.uploadedImageType = file.type;

                    if (thisa.cropper.uploadedImageURL) {
                        URL.revokeObjectURL(thisa.cropper.uploadedImageURL);
                    }

                    thisa.cropper.uploadedImageURL = URL.createObjectURL(file);
                    thisa.preview.image.cropper('destroy').attr('src', thisa.cropper.uploadedImageURL).cropper(thisa.cropper.options);
                    thisa.dlg.inputFile.val('');
                    thisa.cropper.active = true;
                } else {
                    window.alert('Please choose an image file.');
                }
            }
        });

        // Options
//        $('.docs-toggles').on('change', 'input', function () {
//            var $this = $(this);
//            var name = $this.attr('name');
//            var type = $this.prop('type');
//            var cropBoxData;
//            var canvasData;
//
//            if (!$image.data('cropper')) {
//                return;
//            }
//
//            if (type === 'checkbox') {
//                options[name] = $this.prop('checked');
//                cropBoxData = $image.cropper('getCropBoxData');
//                canvasData = $image.cropper('getCanvasData');
//
//                options.ready = function () {
//                    $image.cropper('setCropBoxData', cropBoxData);
//                    $image.cropper('setCanvasData', canvasData);
//                };
//            } else if (type === 'radio') {
//                options[name] = $this.val();
//            }
//
//            $image.cropper('destroy').cropper(options);
//        });


        // Methods
        this.btns.selector.on('click', '[data-method]', function () {
            var $this = $(this);
            var data = $this.data();
            var cropper = thisa.preview.image.data('cropper');
            var cropped;
            var $target;
            var result;

            if ($this.prop('disabled') || $this.hasClass('disabled')) {
                return;
            }

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

                switch (data.method) {
                    case 'rotate':
                        if (cropped && thisa.cropper.options.viewMode > 0) {
                            thisa.preview.image.cropper('clear');
                        }

                        break;

                    case 'getCroppedCanvas':
                        if (thisa.cropper.uploadedImageType === 'image/jpeg') {
                            if (!data.option) {
                                data.option = {};
                            }

                            data.option.fillColor = '#fff';
                        }

                        break;
                }

                result = thisa.preview.image.cropper(data.method, data.option, data.secondOption);

                switch (data.method) {
                    case 'rotate':
                        if (cropped && thisa.cropper.options.viewMode > 0) {
                            thisa.preview.image.cropper('crop');
                        }

                        break;

                    case 'scaleX':
                    case 'scaleY':
                        $(this).data('option', -data.option);
                        break;

                    case 'getCroppedCanvas':
                        if (result) {
                            // Bootstrap's Modal
                            $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);

                            if (!$download.hasClass('disabled')) {
                                $download.attr('href', result.toDataURL(thisa.cropper.uploadedImageType));
                            }
                        }

                        break;

                    case 'destroy':
                        if (thisa.cropper.uploadedImageURL) {
                            URL.revokeObjectURL(thisa.cropper.uploadedImageURL);
                            thisa.cropper.uploadedImageURL = '';
                            thisa.preview.image.attr('src', thisa.cropper.originalImageURL);
                        }

                        break;
                }

                if ($.isPlainObject(result) && $target) {
                    try {
                        $target.val(JSON.stringify(result));
                    } catch (e) {
                        console.log(e.message);
                    }
                }

            }
        });

    },
    //
    //
    //
    ready: function (e) {
        console.log(e.type);
    },
    cropstart: function (e) {
        console.log(e.type, e.action);
    },
    cropmove: function (e) {
        console.log(e.type, e.action);
    },
    cropend: function (e) {
        console.log(e.type, e.action);
    },
    crop: function (e) {
        console.log('My Crop');
        if (!this.cropper.active)
            return;
        this.dlg.datas.x.val(Math.round(e.x));
        this.dlg.datas.y.val(Math.round(e.y));
        this.dlg.datas.h.val(Math.round(e.height));
        this.dlg.datas.w.val(Math.round(e.width));
        this.dlg.datas.r.val(e.rotate);
        this.dlg.datas.fx.val(e.scaleX);
        this.dlg.datas.fy.val(e.scaleY);
    },
    zoom: function (e) {
        console.log(e.type, e.ratio);
    },
    //
    //
    //
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
    inputImageChange: function (a) {
        console.log('ckucj');
        var files = this.files;
        var file;

        if (!$image.data('cropper')) {
            return;
        }

        if (files && files.length) {
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
                uploadedImageType = file.type;

                if (uploadedImageURL) {
                    URL.revokeObjectURL(uploadedImageURL);
                }

                uploadedImageURL = URL.createObjectURL(file);
                $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                $inputImage.val('');
            } else {
                window.alert('Please choose an image file.');
            }
        }
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
        this.preview.image.cropper(this.cropper.options);
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
    zoomOld: function (e) {
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
//        var _this = this;
//        if (this.active) {
//            this.$img.cropper('replace', this.url);
//        } else {
//            this.$img = $('<img src="' + this.url + '">');
//            this.preview.wrapper.empty().html(this.$img);
//            this.$img.cropper({
//                aspectRatio: 1,
//                preview: _this.preview.previews.selector,
//                crop: function (data) {
//                    var json = [
//                        '{"x":' + data.x,
//                        '"y":' + data.y,
//                        '"height":' + data.height,
//                        '"width":' + data.width,
//                        '"rotate":' + data.rotate + '}'
//                    ].join();
//                    _this.dlg.data.val(json);
//                    _this.dlg.datas.x.val(Math.round(data.x));
//                    _this.dlg.datas.y.val(Math.round(data.y));
//                    _this.dlg.datas.h.val(Math.round(data.height));
//                    _this.dlg.datas.w.val(Math.round(data.width));
//                    _this.dlg.datas.r.val(Math.round(data.rotate));
//                }
//
//            });
//            this.active = true;
//        }
//
//        this.dlg.modal.one('hidden.bs.modal', function () {
//            //_this.preview.previews.empty();
//            _this.stopCropper();
//        });
    },
    stopCropper: function () {
        if (this.active) {
            //this.$img.cropper('destroy');
            //this.$img.remove();
            //this.active = false;
        }
    },

    start: function (thisa) {

    }
});
$(function () {

    var $download = $('#download');







//
//
//    // Buttons
//    if (!$.isFunction(document.createElement('canvas').getContext)) {
//        $('button[data-method="getCroppedCanvas"]').prop('disabled', true);
//    }
//
//    if (typeof document.createElement('cropper').style.transition === 'undefined') {
//        $('button[data-method="rotate"]').prop('disabled', true);
//        $('button[data-method="scale"]').prop('disabled', true);
//    }
//
//
//    // Download
//    if (typeof $download[0].download === 'undefined') {
//        $download.addClass('disabled');
//    }
//
//
//    // Options
//    $('.docs-toggles').on('change', 'input', function () {
//        var $this = $(this);
//        var name = $this.attr('name');
//        var type = $this.prop('type');
//        var cropBoxData;
//        var canvasData;
//
//        if (!$image.data('cropper')) {
//            return;
//        }
//
//        if (type === 'checkbox') {
//            options[name] = $this.prop('checked');
//            cropBoxData = $image.cropper('getCropBoxData');
//            canvasData = $image.cropper('getCanvasData');
//
//            options.ready = function () {
//                $image.cropper('setCropBoxData', cropBoxData);
//                $image.cropper('setCanvasData', canvasData);
//            };
//        } else if (type === 'radio') {
//            options[name] = $this.val();
//        }
//
//        $image.cropper('destroy').cropper(options);
//    });
//
//
//    // Methods
//    $('.docs-buttons').on('click', '[data-method]', function () {
//        var $this = $(this);
//        var data = $this.data();
//        var cropper = $image.data('cropper');
//        var cropped;
//        var $target;
//        var result;
//
//        if ($this.prop('disabled') || $this.hasClass('disabled')) {
//            return;
//        }
//
//        if (cropper && data.method) {
//            data = $.extend({}, data); // Clone a new one
//
//            if (typeof data.target !== 'undefined') {
//                $target = $(data.target);
//
//                if (typeof data.option === 'undefined') {
//                    try {
//                        data.option = JSON.parse($target.val());
//                    } catch (e) {
//                        console.log(e.message);
//                    }
//                }
//            }
//
//            cropped = cropper.cropped;
//
//            switch (data.method) {
//                case 'rotate':
//                    if (cropped && options.viewMode > 0) {
//                        $image.cropper('clear');
//                    }
//
//                    break;
//
//                case 'getCroppedCanvas':
//                    if (uploadedImageType === 'image/jpeg') {
//                        if (!data.option) {
//                            data.option = {};
//                        }
//
//                        data.option.fillColor = '#fff';
//                    }
//
//                    break;
//            }
//
//            result = $image.cropper(data.method, data.option, data.secondOption);
//
//            switch (data.method) {
//                case 'rotate':
//                    if (cropped && options.viewMode > 0) {
//                        $image.cropper('crop');
//                    }
//
//                    break;
//
//                case 'scaleX':
//                case 'scaleY':
//                    $(this).data('option', -data.option);
//                    break;
//
//                case 'getCroppedCanvas':
//                    if (result) {
//                        // Bootstrap's Modal
//                        $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);
//
//                        if (!$download.hasClass('disabled')) {
//                            $download.attr('href', result.toDataURL(uploadedImageType));
//                        }
//                    }
//
//                    break;
//
//                case 'destroy':
//                    if (uploadedImageURL) {
//                        URL.revokeObjectURL(uploadedImageURL);
//                        uploadedImageURL = '';
//                        $image.attr('src', originalImageURL);
//                    }
//
//                    break;
//            }
//
//            if ($.isPlainObject(result) && $target) {
//                try {
//                    $target.val(JSON.stringify(result));
//                } catch (e) {
//                    console.log(e.message);
//                }
//            }
//
//        }
//    });
//
//
//    // Keyboard
//    $(document.body).on('keydown', function (e) {
//
//        if (!$image.data('cropper') || this.scrollTop > 300) {
//            return;
//        }
//
//        switch (e.which) {
//            case 37:
//                e.preventDefault();
//                $image.cropper('move', -1, 0);
//                break;
//
//            case 38:
//                e.preventDefault();
//                $image.cropper('move', 0, -1);
//                break;
//
//            case 39:
//                e.preventDefault();
//                $image.cropper('move', 1, 0);
//                break;
//
//            case 40:
//                e.preventDefault();
//                $image.cropper('move', 0, 1);
//                break;
//        }
//
//    });


//  // Import image
//  var $inputImage = $('#inputImage');
//
//  if (URL) {
//    $inputImage.change(function () {
//      var files = this.files;
//      var file;
//
//      if (!$image.data('cropper')) {
//        return;
//      }
//
//      if (files && files.length) {
//        file = files[0];
//
//        if (/^image\/\w+$/.test(file.type)) {
//          uploadedImageType = file.type;
//
//          if (uploadedImageURL) {
//            URL.revokeObjectURL(uploadedImageURL);
//          }
//
//          uploadedImageURL = URL.createObjectURL(file);
//          $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
//          $inputImage.val('');
//        } else {
//          window.alert('Please choose an image file.');
//        }
//      }
//    });
//  } else {
//    $inputImage.prop('disabled', true).parent().addClass('disabled');
//  }

});

