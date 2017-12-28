/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.util;

/**
 *
 * @author r.hendrick
 */
public class Constants {

    public static class ContextParams {
        // JSF context params
        public static final String INTERPRET_EMPTY_STRING_AS_NULL = "javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL";
        
        // PF context params
        public static final String THEME = "ichartsfaces.THEME";
        public static final String MOBILE_THEME = "ichartsfaces.mobile.THEME";
        public static final String FONT_AWESOME = "ichartsfaces.FONT_AWESOME";
        public static final String AUTO_UPDATE = "ichartsfaces.AUTO_UPDATE";
        public static final String PUSH_SERVER_URL = "ichartsfaces.PUSH_SERVER_URL";
        public static final String SUBMIT = "ichartsfaces.SUBMIT";
        public static final String DIRECTION = "ichartsfaces.DIR";
        public static final String RESET_VALUES = "ichartsfaces.RESET_VALUES";
        public static final String SECRET_KEY = "ichartsfaces.SECRET";
        public static final String PFV_KEY = "ichartsfaces.CLIENT_SIDE_VALIDATION";
        public static final String UPLOADER = "ichartsfaces.UPLOADER";
        public static final String CACHE_PROVIDER = "ichartsfaces.CACHE_PROVIDER";
        public static final String TRANSFORM_METADATA = "ichartsfaces.TRANSFORM_METADATA";
        public static final String LEGACY_WIDGET_NAMESPACE = "ichartsfaces.LEGACY_WIDGET_NAMESPACE";
        public static final String BEAN_VALIDATION_DISABLED = "javax.faces.validator.DISABLE_DEFAULT_BEAN_VALIDATOR";
        public static final String INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES = "ichartsfaces.INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES";
        public static final String EARLY_POST_PARAM_EVALUATION = "ichartsfaces.EARLY_POST_PARAM_EVALUATION";
    }

    public static class RequestParams {
        // JSF request params
        public static final String PARTIAL_REQUEST_PARAM = "javax.faces.partial.ajax";
        public static final String PARTIAL_UPDATE_PARAM = "javax.faces.partial.render";
        public static final String PARTIAL_PROCESS_PARAM = "javax.faces.partial.execute";
        public static final String PARTIAL_SOURCE_PARAM = "javax.faces.source";
        public static final String PARTIAL_BEHAVIOR_EVENT_PARAM = "javax.faces.behavior.event";

        // PF request params
        public static final String RESET_VALUES_PARAM = "ichartsfaces.resetvalues";
        public static final String IGNORE_AUTO_UPDATE_PARAM = "ichartsfaces.ignoreautoupdate";
        public static final String SKIP_CHILDREN_PARAM = "ichartsfaces.skipchildren";
    }

    public static final String DOWNLOAD_COOKIE = "ichartsfaces.download";

    public final static String LIBRARY = "ichartsfaces";
    
    public final static String PUSH_PATH = "/ichartspush";
    
    public static final String DYNAMIC_CONTENT_PARAM = "pfdrid";
    public static final String DYNAMIC_CONTENT_CACHE_PARAM = "pfdrid_c";
    public static final String DYNAMIC_CONTENT_TYPE_PARAM = "pfdrt";
    public static final String DYNAMIC_RESOURCES_MAPPING = "ichartsfaces.dynamicResourcesMapping";
    
    public static final String BARCODE_MAPPING = "ichartsfaces.barcodeMapping";
    
    public final static String FRAGMENT_ID = "ichartsfaces.fragment";
    public final static String FRAGMENT_AUTO_RENDERED = "ichartsfaces.fragment.autorendered";
    
    public static class DIALOG_FRAMEWORK {
        public final static String OUTCOME = "dialog.outcome";
        public final static String OPTIONS = "dialog.options";
        public final static String PARAMS = "dialog.params";
        public final static String SOURCE_COMPONENT = "dialog.source.component";
        public final static String SOURCE_WIDGET = "dialog.source.widget";
        public final static String CONVERSATION_PARAM = "pfdlgcid";
        public final static String INCLUDE_VIEW_PARAMS = "includeViewParams";
    }
    
    public static final String EMPTY_STRING = "";
    
    public final static String CLIENT_BEHAVIOR_RENDERING_MODE = "CLIENT_BEHAVIOR_RENDERING_MODE";
    
    public final static String DEFAULT_CACHE_REGION = "ichartsfaces.DEFAULT_CACHE_REGION";
    
    public final static String MOBILE_RENDER_KIT_ID = "ICHARTSFACES_MOBILE";
    
    public static final String HELPER_RENDERER = "org.ichartsfaces.HELPER_RENDERER";
    
    public static final String TABLE_STATE = "ichartsfaces.TABLE_STATE";
}
