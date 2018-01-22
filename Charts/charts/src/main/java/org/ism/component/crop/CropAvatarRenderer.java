package org.ism.component.crop;

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
import org.primefaces.model.CroppedImage;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = CropAvatar.COMPONENT_FAMILY,
        rendererType = CropAvatar.RENDERER_TYPE)
public class CropAvatarRenderer extends CoreRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        CropAvatar cropper = (CropAvatar) component;
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String coordsParam = cropper.getClientId(context) + "_coords";

        if (params.containsKey(coordsParam)) {
            cropper.setSubmittedValue(params.get(coordsParam));
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        CropAvatar cropper = (CropAvatar) component;

        encodeMarkup(context, cropper);
        encodeScript(context, cropper);
    }

    protected void encodeScript(FacesContext context, CropAvatar cropper) throws IOException {
        String widgetVar = cropper.resolveWidgetVar();
        String clientId = cropper.getClientId(context);
        String image = clientId + "_image";
        String select = null;

        WidgetBuilder wb = getWidgetBuilder(context); //, clientId + "_image"
        wb.initWithDomReady("CropAvatar", widgetVar, clientId)
                .attr("image", image);

        if (cropper.getMinSize() != null) {
            wb.append(",minSize:[").append(cropper.getMinSize()).append("]");
        }

        if (cropper.getMaxSize() != null) {
            wb.append(",maxSize:[").append(cropper.getMaxSize()).append("]");
        }

        wb.attr("bgColor", cropper.getBackgroundColor(), null)
                .attr("bgOpacity", cropper.getBackgroundOpacity(), 0.6)
                .attr("aspectRatio", cropper.getAspectRatio(), Double.MIN_VALUE)
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

        wb.finish();
    }

    protected void encodeMarkup(FacesContext context, CropAvatar cropper) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = cropper.getClientId(context);
        String coordsHolderId = clientId + "_coords";

        // Create Main container
        writer.startElement("div", cropper);
        writer.writeAttribute("id", clientId, null);

        // Current Avatar
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-view", "class");
        if (cropper.getAvatarTitle() != null) {
            writer.writeAttribute("title", cropper.getAvatarTitle(), null);
        }
        renderImage(context, cropper, clientId);
        writer.endElement("div"); // Close current avatar

        // Create Modal Window
        renderCropperWindow(context, cropper, clientId);

        writer.endElement("div"); // Close main div 

//        writer.startElement("div", cropper);
//        writer.writeAttribute("id", clientId, null);
//
//        renderImage(context, cropper, clientId);
//
//        writer.startElement("input", null);
//        writer.writeAttribute("type", "hidden", null);
//        writer.writeAttribute("id", coordsHolderId, null);
//        writer.writeAttribute("name", coordsHolderId, null);
//        writer.endElement("input");
//
//        writer.endElement("div");
    }

    private void renderImage(FacesContext context, CropAvatar cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String alt = cropper.getAlt() == null ? "" : cropper.getAlt();

        writer.startElement("img", null);
        writer.writeAttribute("id", clientId + "_image", null);
        writer.writeAttribute("alt", alt, null);
        writer.writeAttribute("src", getResourceURL(context, cropper.getImage()), null);
        writer.endElement("img");
    }

    private void renderCropperWindow(FacesContext context, CropAvatar cropper, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", null);
        writer.writeAttribute("id", clientId + "_avatar-modal", "id");
        writer.writeAttribute("class", "modal fade", "class");
        writer.writeAttribute("aria-hidden", true, null);
        writer.writeAttribute("aria-labelledby", "avatar-modal-label", null);
        writer.writeAttribute("role", "dialog", null);
        writer.writeAttribute("tabindex", "-1", null);
        // 
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-dialog modal-lg", "class");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-content", "class");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-form", "class");
        // Header Window
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-header", "class");
        // Title
        writer.startElement("h4", null);
        writer.writeAttribute("id", clientId + "_avatar-modal-label", "id");
        writer.writeAttribute("class", "modal-title", "class");
        if (cropper.getHeader() != null && !cropper.getHeader().isEmpty()) {
            writer.write(cropper.getHeader());
        }
        writer.endElement("h4"); // end title 
        // Close button
        if (cropper.isClosable()) {
            encodeButton(context, null, "×", cropper.getCloseTitle(), "button", "close", null, null, "modal");
        }
        writer.endElement("div"); // End header window

        // Modal Body
        writer.startElement("div", null);
        writer.writeAttribute("class", "modal-body", "class");
        // Avatar body
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-body", "class");
        //
        //===== = Upload image and data
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-upload", "class");
        encodeInput(context, "hidden", "avatar-src", "avatar_src", null);
        encodeInput(context, "hidden", "avatar-data", "avatar_data", null);
        if (cropper.getChooseLabel() != null) {
            writer.startElement("label", null);
            writer.writeAttribute("for", clientId + "_avatarInput", null);
            writer.write(cropper.getChooseLabel());
            writer.endElement("label");
        }
        encodeInput(context, "file", "avatar-input", "avatar_file", clientId + "_avatarInput");
        writer.endElement("div"); //===== = Upload image and data

        //
        // Crop and preview
        writer.startElement("div", null);
        writer.writeAttribute("class", "row", "class");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-9", "class");
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-wrapper", "class");
        writer.endElement("div");
        writer.endElement("div");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-3", "class");
        //
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-preview preview-lg", "class");
        writer.endElement("div");
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-preview preview-md", "class");
        writer.endElement("div");
        writer.startElement("div", null);
        writer.writeAttribute("class", "avatar-preview preview-sm", "class");
        writer.endElement("div");
        writer.endElement("div");
        //
        writer.endElement("div"); // End Crop and Preview

        //====== = AVATAR BUTTONS
        writer.startElement("div", null);
        writer.writeAttribute("class", "row avatar-btns", "class");
        //====== =MD-9
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-9", "class");
        //====== =BTN GROUPE 1
        writer.startElement("div", null);
        writer.writeAttribute("class", "btn-group", "class");
        encodeButton(context, null, "-90 deg", "Rotation Anti-horaire -90°", "button", "btn btn-dark small", "rotate", "-90", null);
        encodeButton(context, null, "-45 deg", "Rotation Anti-horaire -45°", "button", "btn btn-secondary small", "rotate", "-45", null);
        encodeButton(context, null, "-30 deg", "Rotation Anti-horaire -30°", "button", "btn btn-light small", "rotate", "-30", null);
        encodeButton(context, null, "-15 deg", "Rotation Anti-horaire -15°", "button", "btn btn-white small", "rotate", "-15", null);
        
        
        writer.endElement("div");   //====== =BTN GROUPE 1
        //====== =BTN GROUPE 2
        writer.startElement("div", null);
        writer.writeAttribute("class", "btn-group", "class");
        encodeButton(context, null, "90 deg", "Rotation horaire 90°", "button", "btn btn-primary", "rotate", "90", null);
        encodeButton(context, null, "15 deg", "Rotation horaire 15°", "button", "btn btn-primary", "rotate", "15", null);
        encodeButton(context, null, "30 deg", "Rotation horaire 30°", "button", "btn btn-primary", "rotate", "30", null);
        encodeButton(context, null, "45 deg", "Rotation horaire 45°", "button", "btn btn-primary", "rotate", "45", null);
        writer.endElement("div");   //====== =BTN GROUPE 2
        writer.endElement("div");   //====== =MD-9 
        //====== MD-3
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-md-3", "class");
        if (cropper.getApplyLabel() != null) {
            encodeButton(context, null, cropper.getApplyLabel(), cropper.getApplyTitle(), "button", "btn btn-primary btn-block avatar-save", null, null, null);
        }
        writer.endElement("div"); //====== MD-3

        //
        writer.endElement("div");   //====== = AVATAR BUTTONS

        writer.endElement("div"); // End Avatar body
        writer.endElement("div"); // End Modal body

        writer.endElement("div"); // 
        writer.endElement("div"); // 
        writer.endElement("div"); // 
        writer.endElement("div"); // modal window

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
    private void encodeInput(FacesContext context, String type, String styleClass, String name, String clientId) throws IOException {
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
        writer.endElement("input");
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

        CropAvatar cropper = (CropAvatar) component;
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
    private Resource getImageResource(FacesContext facesContext, CropAvatar imageCropper) {

        Resource resource = null;
        ValueExpression imageValueExpression = imageCropper.getValueExpression(CropAvatar.PropertyKeys.image.toString());

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
}
