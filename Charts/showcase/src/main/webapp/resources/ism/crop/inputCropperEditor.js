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
        this.configCropper();   // configuration de this options
        this.dlg.opts.inputCropperCrop.prop('checked', false);
        this.dlg.opts.inputCropperEdition.prop('checked', false);
        this.cfg.dragCrop = this.cfg.dragCrop || true;
        this.cfg.inputZoomerValue = this.cfg.inputZoomerValue || 1;
        this.cfg.inputRoterValue = this.cfg.inputRoterValue || 180;

        //
        //
        // Build and bind
        this.configTooltip();
        this.configComponent();
        this.bindEvents();
    },
    ///
    ///
    /// Init
    ///
    ///
    configCropper: function (a) {
        // Create cropper config if not defined
        this.cfg.cropper = this.cfg.cropper || {};
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
        var thisa = this;   // current object
        this.cropper = {};  // current cropper 
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

        // Accept Canvas
//        this.dlg.apply.on('click', $.proxy(this.dlgApplyClick, this));


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
//                            // Bootstrap's Modal
//                            $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);
//
//                            if (!$('#getCroppedCanvasModal').hasClass('disabled')) {
//                                $('#getCroppedCanvasModal').attr('href', result.toDataURL(this.cropper.uploadedImageType));
//                            }
//                            if (!this.dlg.src.val() && !this.dlg.inputFile.val()) {
//                                return false;
//                            }

                            var canvas = result; //this.preview.image.cropper('getCroppedCanvas');
                            var thisa = this;
                            canvas.toBlob(function (blob) {
                                var newImg = document.createElement('img'),
                                        url = URL.createObjectURL(blob);
                                newImg.onload = function () {
                                    // no longer need to read the blob so it's revoked
                                    URL.revokeObjectURL(url);
                                };
                                newImg.src = url;
                                //console.log(url)
                                //document.body.appendChild(newImg);
                                //thisa.img.cropped.src = url;
                                thisa.img.cropped.attr('src', url);
                                thisa.input.text.val(url);
                            });
                            
                            //this.preview.image.cropper(this.cropper.options);
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

    dlgApplyClick: function (a) {
        //console.log("Apply clicked ");
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
    }

});




$(function () {



});

