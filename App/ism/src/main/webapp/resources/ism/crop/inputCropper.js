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
                            $(this.for).prop('value', this.originalFilename);
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
                var name = this.cropper.file.filename + "." + this.cropper.file.ext;
                var d = (new Date);
                var t = this.cropper.file.type;
                var file = new File([blobIn], name, {type: t, lastmModified: d});
                var formData = new FormData();
                formData.append('file', file);

                //console.log(that.cfg.uploadUrl)
                $.ajax({
                    type: 'POST',
                    url: that.cfg.uploadUrl,
                    async: true,
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    timeout: 60000,
                   
                    success: function (data) {
                        // your callback here
                        //console.log("Success")
                        //$('body').html(data);
                    },
                    error: function (er) {
                        // handle error
                        console.log('Error [' + er.status + '] : ' + er.statusText)
                    }
                });


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





