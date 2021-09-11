package com.dg.jsontools.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils<J extends JsonUtils<?>> implements Runnable {

    boolean onSyntaxException0;

    String JSON0;
    onJSONSyntaxException onJSONSyntaxException0;
    onJSONExport onJSONExport0;

    @Override
    public void run() {
    }

    public J JSONimput(String json) {

        JSON0 = json;

        return (J)this;

    }

    public String JSONexport() {
        if (isJSON(JSON0)) {
            onJSONExport0.onExportSucceed();
        }
        return JSON0;
    }

    public J  escape() {
        String JSONString=JSON0;
        JSON.parse(JSONString);

        Boolean inQuotation=false;
        String result=new String();

        for (int i=0;i < JSONString.length();i++) {
            String stringtoEscape=JSONString.substring(i, i + 1);

            if (stringtoEscape.hashCode() == 34) {
                inQuotation = !inQuotation;
                result = result + stringtoEscape;
                continue;
            }

            if (stringtoEscape.hashCode() == 39) {
                inQuotation = !inQuotation;
                result = result + stringtoEscape;
                continue;
            }

            if (inQuotation) {
                result = result + escapeChar(stringtoEscape);
            }

            if (!inQuotation) {
                result = result + stringtoEscape;
            }

            System.out.println(inQuotation);
        }
        JSON0 = result;
        return (J)this;
    }

    public static String  escape(String JSONString) {
        JSON.parse(JSONString);

        Boolean inQuotation=false;
        String result=new String();

        for (int i=0;i < JSONString.length();i++) {
            String stringtoEscape=JSONString.substring(i, i + 1);

            if (stringtoEscape.hashCode() == 34) {
                inQuotation = !inQuotation;
                result = result + stringtoEscape;
                continue;
            }

            if (stringtoEscape.hashCode() == 39) {
                inQuotation = !inQuotation;
                result = result + stringtoEscape;
                continue;
            }

            if (inQuotation) {
                result = result + escapeChar(stringtoEscape);
            }

            if (!inQuotation) {
                result = result + stringtoEscape;
            }

            System.out.println(inQuotation);
        }
        return result;
    }

    public static String escapeChar(String a) {

        int hashCode=a.hashCode();

        String result=new String();

        result = ConvertUtils.decToHexString(hashCode);

        switch (result.length()) {
            case 1:
                result = "\\u000" + result;
                break;
            case 2:
                result = "\\u00" + result;
                break;    
            case 3:
                result = "\\u0" + result;
                break;   
            case 4:
                result = "\\u" + result;
                break;    

            default:

        }

        return result;
    }

    public J format() {

        String JSONString=JSON0;

        JSONObject a=(JSONObject) JSON.parse(JSONString, 2);
        JsonUtils result=new JsonUtils();
        String b=result.formatJson((a.toJSONString()));

        JSON0 = b;

        return (J)this;

    }

    public static String format(String JSONString) {

        JSONObject a=(JSONObject) JSON.parse(JSONString, 2);
        JsonUtils result=new JsonUtils();
        String b=result.formatJson((a.toJSONString()));

        return b;

    }

    /**
     * 单位缩进字符串。
     */
    private static String SPACE = "   ";

    /**
     * 返回格式化JSON字符串。
     * 
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public  String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;
        for (int i = 0; i < length; i++) {
            key = json.charAt(i);
            if ((key == '[') || (key == '{')) {
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }
                result.append(key);
                result.append('\n');
                number++;
                result.append(indent(number));
                continue;
            }
            if ((key == ']') || (key == '}')) {
                result.append('\n');
                number--;
                result.append(indent(number));
                result.append(key);
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }
                continue;
            }
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            result.append(key);
        }


        return result.toString();
    }
    
    private String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }


    public static boolean isJSON(String JSONString) {
        try {
            JSONObject a=(JSONObject) JSON.parse(JSONString);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public J zip() {

        String JSONString=JSON0;

        JSONObject a=(JSONObject) JSON.parse(JSONString);
        String b=(a.toJSONString());

        JSON0 = b;

        return (J)this;

    }

    public static String zip(String JSONString) {

        JSONObject a=(JSONObject) JSON.parse(JSONString);
        String b=(a.toJSONString());

        return b;

    }

    public J handle(String handle) {
        if (isJSON(JSON0)) {
            switch (handle) {
                case "escape":
                    JSON0 = escape(JSON0);
                    break;
                case "format":
                    JSON0 = formatJson(zip(JSON0));
                    break;
                case "zip":
                    JSON0 = zip(JSON0);
                    break;
            }
        } else {
            if (!onSyntaxException0) {
                onSyntaxException0 = true;
                onJSONSyntaxException0.onSyntaxException();
            }
        }

        return (J)this;
    }

    public J setOnJSONExport(onJSONExport onJSONExport) {
        onJSONExport0 = onJSONExport;
        return (J)this;
    }

    public J setOnJSONSyntaxException(onJSONSyntaxException onJSONSyntaxException) {
        onJSONSyntaxException0 = onJSONSyntaxException;
        return (J)this;
    }

    public interface onJSONExport {
        void onExportSucceed();
    }


    public interface onJSONSyntaxException {
        void onSyntaxException();
    }

}
