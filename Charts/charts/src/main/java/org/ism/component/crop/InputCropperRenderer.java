package org.ism.component.crop;

import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;
import javax.imageio.ImageIO;
import org.ism.model.cropper.CropError;
import org.ism.util.Util;
import org.primefaces.model.CroppedImage;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = InputCropper.COMPONENT_FAMILY,
        rendererType = InputCropper.RENDERER_TYPE)
public class InputCropperRenderer extends CoreRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        InputCropper cropper = (InputCropper) component;
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        String croppedInfos = cropper.getClientId(context) + "_croppedInfos";
        String cropError = cropper.getClientId(context) + "_cropError";
        String croppedData = cropper.getClientId(context) + "_croppedData";

        if (params.containsKey(croppedInfos)) {
            String croppedInfosValue = params.get(croppedInfos);
            // parse
            org.ism.model.cropper.CroppedImage croppedImage = org.ism.model.cropper.CroppedImage.unparse(croppedInfosValue);
            if(croppedImage!=null){
                cropper.setImageCropped(croppedImage);
            }
            
        }
        
        if (params.containsKey(cropError)) {
            // convert to iamge
            String cropErrorValue = params.get(cropError);
            cropper.setCropError(CropError.unparse(cropErrorValue));
        }
        
        
        if (params.containsKey(croppedData)) {
            // convert to iamge
            String base64Data = params.get(croppedData);
        }


        decodeBehaviors(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        InputCropper cropper = (InputCropper) component;

        encodeMarkup(context, cropper);
        
        encodeScript(context, cropper);
    }

    protected void encodeScript(FacesContext context, InputCropper cropper) throws IOException {
        String widgetVar = cropper.resolveWidgetVar();
        String clientId = cropper.getClientId(context);
        String image = clientId + "_image";
        String select = null;

        WidgetBuilder wb = getWidgetBuilder(context); //, clientId + "_image"
        wb.initWithDomReady("InputCropper", widgetVar, clientId)
                .attr("image", image)
                .attr("for", cropper.getFor())
                .attr("aspectRatio", cropper.getAspectRatio());

        if (cropper.getMinSize() != null) {
            wb.append(",minSize:[").append(cropper.getMinSize()).append("]");
        }

        if (cropper.getMaxSize() != null) {
            wb.append(",maxSize:[").append(cropper.getMaxSize()).append("]");
        }

        wb.attr("bgColor", cropper.getBackgroundColor(), null)
                .attr("bgOpacity", cropper.getBackgroundOpacity(), 0.6)
                .attr("boxWidth", cropper.getBoxWidth(), 0)
                .attr("boxHeight", cropper.getBoxHeight(), 0);

        Object value = cropper.getValue();
        if (value != null) {
            CroppedImage croppedImage = (CroppedImage) value;

            int x = croppedImage.getLeft();
            int y = croppedImage.getTop();
            int x2 = x + croppedImage.getWidth();
            int y2 = y + croppedImage.getHeight();

            select = "[" + x + "," + y + "," + x2 + "," + y2 + "]";
        } else if (cropper.getInitialCoords() != null) {
            select = "[" + cropper.getInitialCoords() + "]";
        }

        wb.append(",setSelect:").append(select);
        encodeClientBehaviors(context, cropper);
        wb.finish();
    }

    protected void encodeMarkup(FacesContext context, InputCropper cropper) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = cropper.getClientId(context);
        String inputCropperHolderId = clientId + "_holder";

        // Div InputCropper
        writer.startElement("div", cropper);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("class", "inputCropper", "class");

        // Input Field Text
        encodeInputField(context, cropper, clientId);

        // Input Field Overlay Image
        encodeInputOverlayImage(context, cropper, clientId);

        // Modal Window Start
        writer.startElement("div", null);
        writer.writeAttribute("id", clientId + "_modalDialog", null);
        writer.writeAttribute("class", "modal  modalDialog", null);
        writer.writeAttribute("aria-hidden", "true", null);
        writer.writeAttribute("aria-labelledby", "modalDialog-label", null);
        writer.writeAttribute("role", "dialog", null);
        writer.writeAttribute("tabindex", "-1", null);
        // == Build Large Modal dialog
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-dialog modal-lg", null);
        // == Content of Moadal dialog
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-content", null);
        // == Inside Content
        renderCropperContent(context, cropper, clientId);
        writer.endElement("div");   // == Content of Moadal dialog
        writer.endElement("div");   // == Large Modal dialog
        writer.endElement("div");   // == Modal Window

        writer.startElement("input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("id", inputCropperHolderId, null);
        writer.writeAttribute("name", inputCropperHolderId, null);
        writer.endElement("input");
        
        // Loading
        writer.startElement("div", null);
        writer.writeAttribute("class", "loading", null);
        writer.writeAttribute("aria-label", "Loading", null);
        writer.writeAttribute("role", "img", null);
        writer.writeAttribute("tabindex", "-1", null);
        // == Loading image
        writer.startElement("img", null);
        writer.writeAttribute("src", getResourceURL(context, cropper.getLoadingGif()), null);
        writer.endElement("img");   // == Loading image
        writer.endElement("div");   // == Loading end

        
        //
        Util.encodeStateHolder(context, clientId + "_croppedInfos",  "");
        Util.encodeStateHolder(context, clientId + "_cropError",  "");
        Util.encodeStateHolder(context, clientId + "_croppedData", "");
    

    }

    private void encodeInputField(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        // Input Field Text
        writer.startElement("div", null);
        writer.writeAttribute("class", "ic-crop-input-name", null);
        writer.writeAttribute("data-toggle", "modal", null);
        writer.writeAttribute("data-target", "#" + clientId.replace(":", "\\:") + "_modalDialog", null);
        //== input field name in
        writer.startElement("div", null);
        writer.writeAttribute("class", "ic-crop-input-name-in", null);
        writer.endElement("div"); // == input field name in
        writer.endElement("div"); // == input field
    }

    private void encodeInputOverlayImage(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String alt = cropper.getAlt() == null ? "" : cropper.getAlt();

        // Input Field Text
        writer.startElement("div", null);
        writer.writeAttribute("class", "ic-crop-dropdown-img", null);
        writer.writeAttribute("data-toggle", "modal", null);
        writer.writeAttribute("data-target", "#" + clientId.replace(":", "\\:") + "_modalDialog", null);
        //== input field name in
        writer.startElement("div", null);
        writer.writeAttribute("class", "ic-crop-container", null);
        //== Image
        writer.startElement("img", null);
        writer.writeAttribute("id", clientId + "_cropImg", null);
        writer.writeAttribute("alt", alt, null);
        writer.writeAttribute("class", "ic-crop-img-cropped", null);
        writer.endElement("img"); // == image
        writer.endElement("div"); // == input field name in
        writer.endElement("div"); // == input field
    }

    private void renderImage(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String alt = cropper.getAlt() == null ? "" : cropper.getAlt();

        writer.startElement("img", null);
        writer.writeAttribute("id", clientId + "_image", null);
        writer.writeAttribute("alt", alt, null);
        writer.writeAttribute("src", getResourceURL(context, cropper.getImage()), null);
        writer.endElement("img");
    }

    private void encodeInputViewer(FacesContext context, InputCropper cropper, String forData, String invite, String unit) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        // Input Group
        writer.startElement("div", null);
        writer.writeAttribute("class", "input-group input-group-sm mb-1", null);
        //== Label
        writer.startElement("label", null);
        writer.writeAttribute("class", "input-group-addon", null);
        writer.writeAttribute("for", forData, null);
        writer.writeText(invite, null);
        writer.endElement("label"); //== Label
        //== Input
        writer.startElement("input", null);
        writer.writeAttribute("id", cropper.getClientId() + "_" + forData, null);
        writer.writeAttribute("class", "form-control " + forData, null);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("placeholder", invite.toLowerCase(), null);
        writer.endElement("input"); //== Input
        //== SPAN
        writer.startElement("span", null);
        writer.writeAttribute("class", "input-group-addon", null);
        writer.writeText(unit, null);
        writer.endElement("span"); //== SPAN

        writer.endElement("div"); // Input Group
    }

    private void encodeBtnUpload(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        //== Uploader Button
        writer.startElement("label", null);
        writer.writeAttribute("class", "btn btn-sm btn-primary btn-upload", null);
        writer.writeAttribute("for", cropper.getClientId() + "_input-img-upload", null);
        writer.writeAttribute("title", cropper.getAlt(), null);
        //== span label
        writer.startElement("span", null);  //== span label
        writer.writeAttribute("id", cropper.getClientId() + "_input-img-upload-label", "id");
        writer.writeAttribute("class", "docs-tooltip", null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-animation", "true", null);
        writer.writeAttribute("data-placement", "top", null);
        writer.writeAttribute("title", "Charger une image depuis une source", null);
        //== input src
        encodeInput(context, "hidden", "input-src", "input-src", null);
        //== input data
        encodeInput(context, "hidden", "input-data", "input-data", null);
        //== input file uplaod
        encodeInput(context, "file", "sr-only input-img-upload", "file", clientId + "_input-img-upload", ".jpg,.jpeg,.png,.gif,.bmp,.tiff");
        //== span icon upload
        writer.startElement("span", null);
        writer.writeAttribute("class", "fa fa-upload", null);
        writer.endElement("span"); //== span icon upload
        writer.endElement("span"); //== Uploader Button
        writer.endElement("label"); //== Uploader Button
    }

    private void encodeDropDownStart(FacesContext context, String classGrouper, String title, String btnIcon) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        // Crop Options
        writer.startElement("div", null);
        writer.writeAttribute("class", "btn-group dropup " + classGrouper, null);
        writer.writeAttribute("data-container", "." + classGrouper + "Btn", null);
        writer.writeAttribute("data-placement", "bottom", null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("title", title, null);
        // Container
        writer.startElement("div", null);
        writer.writeAttribute("class", "btn-group dropup ", null);
        // Encode Btn
        writer.startElement("button", null);
        writer.writeAttribute("type", "utton", null);
        writer.writeAttribute("class", "btn btn-outline-dark btn-sm dropdown-toggle " + classGrouper + "Btn", null);
        writer.writeAttribute("aria-haspopup", "true", null);
        writer.writeAttribute("aria-expanded", "false", null);
        writer.writeAttribute("data-toggle", "dropdown", null);
        writer.startElement("span", null);
        writer.writeAttribute("class", btnIcon, null);
        writer.endElement("span");   //== Icon
        writer.endElement("button");   //== Btn
        // Start Drop down menu
        writer.startElement("div", null);
        writer.writeAttribute("class", "dropdown-menu", null);
    }

    private void encodeDropDownEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.endElement("div");   //== dropdown menu
        writer.endElement("div");   //== Container
        writer.endElement("div");   //== Crop Options
    }

    private void encodeDropDownHeader(FacesContext context, String header) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("h6", null);
        writer.writeAttribute("class", "dropdown-header", null);
        writer.write(header);
        writer.endElement("h6");
    }

    private void encodeDropDownHeaderIcon(FacesContext context, String icon) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("h6", null);
        writer.writeAttribute("class", "dropdown-header", null);
        writer.startElement("span", null);
        writer.writeAttribute("class", icon, null);
        writer.endElement("span");
        writer.endElement("h6");
    }

    private void encodeDropDownItemLabelInput(FacesContext context, String type, String name, String value, String title, String text, String icon, String placement) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("label", null);
        writer.writeAttribute("class", "dropdown-item", null);
        writer.writeAttribute("title", title, null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", placement, null);
        //
        writer.startElement("input", null);
        writer.writeAttribute("type", type, null);
        writer.writeAttribute("class", "sr-only", null);
        writer.writeAttribute("name", name, null);
        writer.writeAttribute("value", value, null);
        writer.endElement("input");
        //
        writer.write("[");
        writer.startElement("span", null);
        writer.writeAttribute("class", icon, null);
        writer.endElement("span");   //== Icon
        writer.write("] " + text);
        writer.endElement("label");
    }

    private void encodeDropDownItemLink(FacesContext context, String method, String option, String title, String text, String icon, String placement) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("a", null);
        writer.writeAttribute("class", "dropdown-item", null);
        writer.writeAttribute("title", title, null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", placement, null);
        writer.writeAttribute("data-method", method, null);
        writer.writeAttribute("data-option", option, null);
        //
        if (icon != null) {
            writer.write("[");
            writer.startElement("span", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("span");   //== Icon
            writer.write("] ");
        }
        if (title != null) {
            writer.write(text);
        }
        writer.endElement("a");
    }

    private void encodeDropDownDivider(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", null);
        writer.writeAttribute("class", "dropdown-divider", null);
        writer.endElement("div");
    }

    private void encodeInputSpin(FacesContext context, String method, String name, String value, String title, String classId, String placement) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("label", null);
        writer.writeAttribute("title", title, null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", placement, null);
        //
        writer.startElement("input", null);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("class", "input-sm inputSpin " + classId, null);
        writer.writeAttribute("data-method", method, null);
        writer.writeAttribute("name", name, null);
        writer.writeAttribute("value", value, null);
        writer.endElement("input");
        //
        writer.endElement("label");
    }

    private void encodeInputSlider(FacesContext context, String title, String classId, String placement) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", null);
        writer.writeAttribute("class", classId, null);
        writer.writeAttribute("title", title, null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", placement, null);
        //
        writer.startElement("input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("disabled", "true", null);
        writer.writeAttribute("class", "smaller " + classId + "Value", null);
        writer.writeAttribute("name", classId + "Value", null);
        writer.endElement("input");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "ui-slider-handle " + classId + "Handler", null);
        writer.endElement("div");
        //
        writer.endElement("div");
    }

    private void encodeInviteInputToggle(FacesContext context, String invite, String classId, String method, String size, String width, String height, String onstyle, String offstyle) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        //== Colomn 1
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol txt-md txt-al", null);
        writer.write(invite);
        writer.endElement("div");   //== Column 1
        //== Colomn 2
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", null);
        //== Input Toggle
        writer.startElement("input", null);
        writer.writeAttribute("type", "checkbox", null);
        writer.writeAttribute("checked", "true", null);
        writer.writeAttribute("class", "form-control " + classId, null);
        writer.writeAttribute("data-method", method, null);
        writer.writeAttribute("data-toggle", "toggle", null);
        writer.writeAttribute("data-size", size, null);
        writer.writeAttribute("data-width", width, null);
        writer.writeAttribute("data-height", height, null);
        writer.writeAttribute("data-onstyle", onstyle, null);
        writer.writeAttribute("data-offstyle", offstyle, null);
        writer.endElement("input"); //== Input Toggle
        //
        writer.endElement("div");
    }

    private void encodeDropDownItemButton(FacesContext context, String method, String option1, String option2, String title, String icon, String placement) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        //== Button
        writer.startElement("button", null);
        writer.writeAttribute("type", "button", null);
        writer.writeAttribute("class", "dropdown-item moveOption " + icon, null);
        writer.writeAttribute("data-method", method, null);
        writer.writeAttribute("data-option", option1, null);
        writer.writeAttribute("data-second-option", option2, null);
        writer.writeAttribute("title", title, null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", placement, null);
        writer.endElement("button");   //== Button
    }

    private void renderCropperRowViewers(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        //
        // Crop and preview
        writer.startElement("div", null);
        writer.writeAttribute("class", "row", null);
        //== Wrapper
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-9", null);
        writer.startElement("div", null);
        writer.writeAttribute("class", "img-wrapper", null);
        writer.endElement("div");
        writer.endElement("div");   //== Wrapper
        //== Viewers
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-3", "class");
        //== ClearFix
        writer.startElement("div", null);
        writer.writeAttribute("class", "docs-preview clearfix", null);
        //== Previews
        writer.startElement("div", null);
        writer.writeAttribute("class", "img-preview preview-lg", null);
        writer.endElement("div");
        writer.startElement("div", null);
        writer.writeAttribute("class", "img-preview preview-md", null);
        writer.endElement("div");
        writer.startElement("div", null);
        writer.writeAttribute("class", "img-preview preview-sm", null);
        writer.endElement("div");
        writer.startElement("div", null);
        writer.writeAttribute("class", "img-preview preview-xs", null);
        writer.endElement("div");
        //== End Previews
        writer.endElement("div");   //== ClearFix

        //
        //
        //== Size Container
        writer.startElement("div", null);
        writer.writeAttribute("class", "docs-data container sizerContainer", null);
        writer.writeAttribute("style", "float: right;", null);
        //== Size Container row
        writer.startElement("div", null);
        writer.writeAttribute("class", "row sizerContainerSpacer", null);
        //
        encodeInputViewer(context, cropper, "dataX", "X", "px");
        encodeInputViewer(context, cropper, "dataY", "Y", "px");
        encodeInputViewer(context, cropper, "dataWidth", "W", "px");
        encodeInputViewer(context, cropper, "dataHeight", "H", "px");
        encodeInputViewer(context, cropper, "dataHeight", "H", "px");
        encodeInputViewer(context, cropper, "dataRotate", "R", "°");
        encodeInputViewer(context, cropper, "dataScaleX", "FX", "%");
        encodeInputViewer(context, cropper, "dataScaleY", "FY", "%");
        //
        writer.endElement("div");   //== Size Container row
        writer.endElement("div");   //== Size Container

        //
        writer.endElement("div");   //== Viewers
        writer.endElement("div");   // Crop and preview
    }

    private void renderCropperContent(FacesContext context, InputCropper cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        // Window Title and Buttons
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-header", null);
        //== H4 Title
        writer.startElement("h4", null);
        writer.writeAttribute("id", clientId + "_modalDialog-label", "id");
        writer.writeAttribute("class", "modal-title", null);
        if (cropper.getHeader() != null && !cropper.getHeader().isEmpty()) {
            writer.write(cropper.getHeader());
        }
        writer.endElement("h4");    //== H4 Title
        //== Close Button
        if (cropper.isClosable()) {
            encodeButton(context, null, "×", cropper.getCloseTitle(), "button", "close", null, null, "modal");
        }//== Close Button
        writer.endElement("div");   // Window Title and Buttons

        //
        //
        // Window Body
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-body", null);
        // Render first row with Wrapper and Previews
        renderCropperRowViewers(context, cropper, clientId);

        //
        //
        // BUILD CONTROLLERS
        writer.startElement("div", null);
        writer.writeAttribute("class", "row ic-btn-control", "class");
        //== MD-9 Column
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-9 docs-buttons", "class");

        //
        //
        //== Uploader Button
        encodeBtnUpload(context, cropper, clientId);

        //
        //
        //== Crop Options
        encodeDropDownStart(context, "optOptions", "Contrôle des modes vue/image", "fa fa-eye");
        encodeDropDownHeader(context, "Contrôle Vue");
        encodeDropDownItemLabelInput(context, "radio", "viewMode", "0", "Permet un rognage libre", "Rognage Libre", "fa fa-eye-slash", "right");
        encodeDropDownItemLabelInput(context, "radio", "viewMode", "1", "Permet le rognage sur l'image", "Rognage à l'image", "fa fa-circle-thin", "right");
        encodeDropDownItemLabelInput(context, "radio", "viewMode", "2", "Permet le rognage dans l'espace", "Rognage à l'espace", "fa fa-circle-o", "right");
        encodeDropDownItemLabelInput(context, "radio", "viewMode", "3", "Permet le rognage dans les limtes", "Rognage au limite", "fa fa-circle", "right");
        encodeDropDownDivider(context);
        encodeDropDownHeader(context, "Contrôle sur image");
        encodeDropDownItemLink(context, "setDragMode", "none", "Désactiver les modes", "Désactiver", "fa fa-toggle-off", "right");
        encodeDropDownItemLink(context, "setDragMode", "crop", "Activer le mode par rognage", "Rogner", "fa fa-crop", "right");
        encodeDropDownItemLink(context, "setDragMode", "move", "Activer le mode par placement", "Placer", "fa fa-arrows", "right");
        encodeDropDownEnd(context); //== Crop Options

        //
        //
        //== Rotate Options
        encodeDropDownStart(context, "optRotate", "Contrôleur de rotation de l'image", "fa fa-rotate-left");
        encodeDropDownHeader(context, "Rotation Prédéfinie");
        //== Row split context
        writer.startElement("div", null);
        writer.writeAttribute("class", "row splitContextRow", "class");
        //== Colomn 1
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownHeaderIcon(context, "fa fa-rotate-right");
        encodeDropDownItemLink(context, "rotate", "90", "Rotation anti-horaire de +90°", "+90° degré", null, "left");
        encodeDropDownItemLink(context, "rotate", "45", "Rotation anti-horaire de +45°", "+45° degré", null, "left");
        encodeDropDownItemLink(context, "rotate", "30", "Rotation anti-horaire de +30°", "+30° degré", null, "left");
        encodeDropDownItemLink(context, "rotate", "15", "Rotation anti-horaire de +15°", "+15° degré", null, "left");
        writer.endElement("div");   //== Column 1
        //== Column 2
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownHeaderIcon(context, "fa fa-rotate-left");
        encodeDropDownItemLink(context, "rotate", "-90", "Rotation anti-horaire de -90°", "-90° degré", null, "right");
        encodeDropDownItemLink(context, "rotate", "-45", "Rotation anti-horaire de -45°", "-45° degré", null, "right");
        encodeDropDownItemLink(context, "rotate", "-30", "Rotation anti-horaire de -30°", "-30° degré", null, "right");
        encodeDropDownItemLink(context, "rotate", "-15", "Rotation anti-horaire de -15°", "-15° degré", null, "right");
        writer.endElement("div");   //== Column 2
        writer.endElement("div");   //== Row split context
        encodeDropDownDivider(context);
        encodeDropDownHeader(context, "Rotation Glissante");
        encodeInputSpin(context, "rotate", "rotate", "0", "Activer la cellule. Tourner l'image avec la roulette de la souris", " inputRotateSpin", "right");
        encodeInputSlider(context, "Exécuter une rotation de l'image", "inputRoter", "right");
        encodeDropDownEnd(context); //== Rotate Options

        //
        //
        //== Options ZOOMER
        encodeDropDownStart(context, "optZoomer", "Contrôleur du zoom de l'image", "fa fa-search-plus");
        //== Row split context
        writer.startElement("div", null);
        writer.writeAttribute("class", "row splitContextRow", "class");
        //== Colomn 1
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownHeaderIcon(context, "fa fa-search-plus");
        encodeDropDownItemLink(context, "zoom", "0.75", "Zoomer de 75%", "+75%", null, "left");
        encodeDropDownItemLink(context, "zoom", "0.50", "Zoomer de 50%", "+50%", null, "left");
        encodeDropDownItemLink(context, "zoom", "0.25", "Zoomer de 25%", "+25%", null, "left");
        encodeDropDownItemLink(context, "zoom", "0.10", "Zoomer de 10%", "+10%", null, "left");
        writer.endElement("div");   //== Column 1
        //== Column 2
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownHeaderIcon(context, "fa fa-search-minus");
        encodeDropDownItemLink(context, "zoom", "-0.75", "Zoomer de -75%", "-75%", null, "right");
        encodeDropDownItemLink(context, "zoom", "-0.50", "Zoomer de -50%", "-50%", null, "right");
        encodeDropDownItemLink(context, "zoom", "-0.25", "Zoomer de -25%", "-25%", null, "right");
        encodeDropDownItemLink(context, "zoom", "-0.10", "Zoomer de -10%", "-10%", null, "right");
        writer.endElement("div");   //== Column 2
        writer.endElement("div");   //== Row split context
        //== Reset
        encodeDropDownItemLink(context, "zoomTo", "reset", "Zoomer à l'initiale", "Réinitialiser", "fa fa-eraser", "right");
        encodeDropDownDivider(context);
        encodeDropDownHeader(context, "Zoom");
        encodeInputSpin(context, "zoom", "zoom", "0", "Activer la cellule. Zoomer l'image avec la roulette de la souris", " inputZoomerSpin", "right");
        encodeInputSlider(context, "Exécuter un zoom de l'image", "inputZoomer", "right");
        encodeDropDownEnd(context); //== Rotate Options

        //
        //
        //== Options Cropper
        encodeDropDownStart(context, "optCropper", "Contrôleur d'édition image", "fa fa-gear");
        //== Row split context Cadrage
        writer.startElement("div", null);
        writer.writeAttribute("class", "row splitContextRow", null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", "right", null);
        writer.writeAttribute("title", "Affiche ou non le cadre de rognage", null);
        encodeInviteInputToggle(context, "Cadrage", "inputCropperCrop", "crop", "mini", "75%", "24", "success", "danger");
        writer.endElement("div");   //== Row Split Context Cadrage
        //== Row split context Edition
        writer.startElement("div", null);
        writer.writeAttribute("class", "row splitContextRow", null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", "right", null);
        writer.writeAttribute("title", "Permet ou non l'editon de l'image", null);
        encodeInviteInputToggle(context, "Edition", "inputCropperEdition", "edition", "mini", "75%", "24", "success", "danger");
        writer.endElement("div");   //== Row Split Context Edition
        encodeDropDownDivider(context);
        encodeDropDownItemLink(context, "reset", "null", "Réinitialiser l'image en cours", "Réinitialiser", "fa fa-refresh", "right");
        encodeDropDownItemLink(context, "destroy", "null", "Supprimer l'édition en cours", "Supprimer l'image", "fa fa-trash-o", "right");
        encodeDropDownEnd(context); //== Options Cropper

        //
        //
        //== Options Ratio
        encodeDropDownStart(context, "optApsectRatio", "Contrôleur du cadrage de l'image", "fa fa-window-restore");
        encodeDropDownItemLink(context, "setAspectRatio", "1.7777777777777777777777777777778", "Rognage CINEMA", "Cinéma [16:9]", "fa fa-camera", "right");
        encodeDropDownItemLink(context, "setAspectRatio", "1.3333333333333333333333333333333", "Rognage PHOTO", "Photographie [4:3]", "fa fa-photo", "right");
        encodeDropDownItemLink(context, "setAspectRatio", "1", "Rognage CAMERA", "Caméra [1:1]", "fa fa-camera-retro", "right");
        encodeDropDownItemLink(context, "setAspectRatio", "0.66666666666666666666666666666667", "Rognage BADGE", "BADGE [2:3]", "fa fa-id-badge", "right");
        encodeDropDownItemLink(context, "setAspectRatio", "null", "Rognage LIBRE", "Cadrage LIBRE", "fa fa-delicious", "right");
        encodeDropDownEnd(context); //== Options Ratio

        //
        //
        //== Options Flip
        encodeDropDownStart(context, "optFlip", "Contrôleur Taille/Place Image", "fa fa-arrows-alt");
        encodeDropDownHeader(context, "Mirroire");
        //== Row split context
        writer.startElement("div", null);
        writer.writeAttribute("class", "row splitContextRow", "class");
        //== Colomn 1
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownItemLink(context, "scaleX", "-1", "Miroire horizontale", "Horiz.", "fa fa-angle-double-left", "left");
        writer.endElement("div");   //== Column 1
        //== Column 2
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-sm-6 splitContextCol", "class");
        encodeDropDownItemLink(context, "scaleY", "-1", "Miroire Verticale", "Vertic.", "fa fa-angle-double-right", "right");
        writer.endElement("div");   //== Column 2
        writer.endElement("div");   //== Row split context
        encodeDropDownDivider(context);
        encodeDropDownHeader(context, "Mouvement");
        //== Row Button
        writer.startElement("div", null);
        writer.writeAttribute("class", "btn-group btn", null);
        encodeDropDownItemButton(context, "move", "-10", "0", "Déplacement à gauche de 10 unit", "fa fa-arrow-left", "left");
        encodeDropDownItemButton(context, "move", "10", "0", "Déplacement à droite de 10 unit", "fa fa-arrow-right", "left");
        encodeDropDownItemButton(context, "move", "0", "-10", "Déplacement en dessous de 10 unit", "fa fa-arrow-up", "right");
        encodeDropDownItemButton(context, "move", "0", "10", "Déplacement au dessus de 10 unit", "fa fa-arrow-down", "right");
        writer.endElement("div");   //== Row Button
        encodeDropDownDivider(context);
        encodeDropDownHeader(context, "Mise à l'échelle");
        encodeInputSpin(context, "scaleX", "scaleX", "1", "Activer la cellule. Etirer horizontallement l'image avec la roulette de la souris", " inputScaleXSpin", "right");
        encodeInputSpin(context, "scaleX", "scaleY", "1", "Activer la cellule. Etirer verticalement l'image avec la roulette de la souris", " inputScaleYSpin", "right");
        encodeDropDownEnd(context); //== Options Flip

        //
        //
        // Apply Button
        writer.startElement("button", null);
        writer.writeAttribute("class", "btn btn-primary btn-apply btn-sm", null);
        writer.writeAttribute("style", "float: right", null);
        writer.writeAttribute("data-dismiss", "modal", null);
        writer.writeAttribute("data-method", "getCroppedCanvas", null);
        writer.writeAttribute("data-option", "{ &quot;maxWidth&quot;: 4096, &quot;maxHeight&quot;: 4096 }", null);
        writer.writeAttribute("data-toggle", "tooltip", null);
        writer.writeAttribute("data-placement", "top", null);
        writer.writeAttribute("title", cropper.getApplyTitle(), null);
        writer.write(cropper.getApplyLabel());
        writer.endElement("div");   //== Apply Button

        writer.endElement("div");   //====== =MD-9 
        //====== MD-3
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-3", "class");
        // Empty
        writer.endElement("div"); //====== MD-3

        //
        writer.endElement("div");   //== Build controlleur
        writer.endElement("div");   //== Window Body

    }

    /**
     * Encode an input markup
     *
     * @param context
     * @param type
     * @param styleClass
     * @param name
     * @throws IOException
     */
    private void encodeInput(FacesContext context, String type, String styleClass, String name, String clientId, String accept) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", null);
        if (clientId != null) {
            writer.writeAttribute("id", clientId, "id");
        }
        if (type != null) {
            writer.writeAttribute("type", type, "type");
        }
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "class");
        }
        if (name != null) {
            writer.writeAttribute("name", name, "name");
        }
        if (accept != null) {
            writer.writeAttribute("accept", accept, "accept");
        }

        writer.endElement("input");
    }

    private void encodeInput(FacesContext context, String type, String styleClass, String name, String clientId) throws IOException {
        encodeInput(context, type, styleClass, name, clientId, null);
    }

    /**
     * Encode a button markup
     *
     * @param context current context
     * @param clientId current id to be used
     * @param type standar button type like submit, button, is mendatory
     * @param styleClass current button class
     * @param dataMethod special obtion data method, null to disable
     * @param dataOption special option data option null to disable
     * @param title information of the button
     * @param text button content
     * @throws IOException
     */
    private void encodeButton(FacesContext context, String clientId, String text, String title,
            String type, String styleClass, String dataMethod, String dataOption, String dataDismiss) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("button", null);
        if (clientId != null) {
            writer.writeAttribute("id", clientId, "id");
        }
        writer.writeAttribute("type", type, "type");
        writer.writeAttribute("class", styleClass, "class");
        if (dataMethod != null) {
            writer.writeAttribute("data-method", dataMethod, null);
        }
        if (dataOption != null) {
            writer.writeAttribute("data-option", dataOption, null);
        }
        if (dataDismiss != null) {
            writer.writeAttribute("data-dismiss", dataDismiss, null);
        }
        if (title != null) {
            writer.writeAttribute("title", title, null);
        }
        if (text != null) {
            writer.write(text);
        }
        writer.endElement("button"); // end title 
    }

    @Override
    public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue) throws ConverterException {
        String coords = (String) submittedValue;

        if (isValueBlank(coords)) {
            return null;
        }

        String[] cropCoords = coords.split("_");

        int x = (int) Double.parseDouble(cropCoords[0]);
        int y = (int) Double.parseDouble(cropCoords[1]);
        int w = (int) Double.parseDouble(cropCoords[2]);
        int h = (int) Double.parseDouble(cropCoords[3]);

        if (w <= 0 || h <= 0) {
            return null;
        }

        InputCropper cropper = (InputCropper) component;
        Resource resource = getImageResource(context, cropper);
        InputStream inputStream;
        String imagePath = cropper.getImage();
        String contentType = null;

        try {

            if (resource != null && !"RES_NOT_FOUND".equals(resource.toString())) {
                inputStream = resource.getInputStream();
                contentType = resource.getContentType();
            } else {

                boolean isExternal = imagePath.startsWith("http");

                if (isExternal) {
                    URL url = new URL(imagePath);
                    URLConnection urlConnection = url.openConnection();
                    inputStream = urlConnection.getInputStream();
                    contentType = urlConnection.getContentType();
                } else {
                    ExternalContext externalContext = context.getExternalContext();
                    File file = new File(externalContext.getRealPath("") + imagePath);
                    if (!file.isFile()) {
                        System.out.println("File absolute path : \n"
                                + file.getAbsolutePath() + "\nFile path :\n"
                                + file.getPath() + "\nDoes not exist !");
                    }
                    inputStream = new FileInputStream(file);
                }
            }

            BufferedImage outputImage = ImageIO.read(inputStream);
            inputStream.close();
            BufferedImage cropped = outputImage.getSubimage(x, y, w, h);
            ByteArrayOutputStream croppedOutImage = new ByteArrayOutputStream();
            String format = guessImageFormat(contentType, imagePath);
            ImageIO.write(cropped, format, croppedOutImage);

            return new CroppedImage(cropper.getImage(), croppedOutImage.toByteArray(), x, y, w, h);
        } catch (IOException e) {
            throw new ConverterException(e);
        }
    }

    /**
     * Attempt to obtain the resource from the server by parsing the
     * valueExpression of the image attribute. Returns null if the
     * valueExpression is not of the form #{resource['path/to/resource']} or
     * #{resource['library:name']}. Otherwise returns the value obtained by
     * ResourceHandler.createResource().
     */
    private Resource getImageResource(FacesContext facesContext, InputCropper imageCropper) {

        Resource resource = null;
        ValueExpression imageValueExpression = imageCropper.getValueExpression(InputCropper.PropertyKeys.image.toString());

        if (imageValueExpression != null) {
            String imageValueExpressionString = imageValueExpression.getExpressionString();

            if (imageValueExpressionString.matches("^[#][{]resource\\['[^']+'\\][}]$")) {

                imageValueExpressionString = imageValueExpressionString.replaceFirst("^[#][{]resource\\['", "");
                imageValueExpressionString = imageValueExpressionString.replaceFirst("'\\][}]$", "");
                String resourceLibrary = null;
                String resourceName;
                String[] resourceInfo = imageValueExpressionString.split(":");

                if (resourceInfo.length == 2) {
                    resourceLibrary = resourceInfo[0];
                    resourceName = resourceInfo[1];
                } else {
                    resourceName = resourceInfo[0];
                }

                if (resourceName != null) {
                    Application application = facesContext.getApplication();
                    ResourceHandler resourceHandler = application.getResourceHandler();

                    if (resourceLibrary != null) {
                        resource = resourceHandler.createResource(resourceName, resourceLibrary);
                    } else {
                        resource = resourceHandler.createResource(resourceName);
                    }
                }
            }
        }

        return resource;
    }

    /**
     * Attempt to obtain the image format used to write the image from the
     * contentType or the image's file extension.
     */
    private String guessImageFormat(String contentType, String imagePath) throws IOException {

        String format = "png";

        if (contentType == null) {
            contentType = URLConnection.guessContentTypeFromName(imagePath);
        }

        if (contentType != null) {
            format = contentType.replaceFirst("^image/([^;]+)[;]?.*$", "$1");
        } else {
            int queryStringIndex = imagePath.indexOf('?');

            if (queryStringIndex != -1) {
                imagePath = imagePath.substring(0, queryStringIndex);
            }

            String[] pathTokens = imagePath.split("\\.");

            if (pathTokens.length > 1) {
                format = pathTokens[pathTokens.length - 1];
            }
        }

        return format;
    }
    
    
    public org.ism.model.cropper.CroppedImage unparseObj(InputCropper cropper, String croppedInfos) {
        Gson g = new Gson();
        //Util.out("Serie : " + selectedSerie);
        org.ism.model.cropper.CroppedImage croppedImg = g.fromJson(croppedInfos, org.ism.model.cropper.CroppedImage.class);
        return croppedImg;
    }
}
