package cn.qyd.blogroom.website.ueditor;

import cn.qyd.blogroom.website.ueditor.define.ActionMap;
import cn.qyd.blogroom.website.ueditor.define.BaseState;
import cn.qyd.blogroom.website.ueditor.define.State;
import cn.qyd.blogroom.website.ueditor.hunter.FileManager;
import cn.qyd.blogroom.website.ueditor.hunter.ImageHunter;
import cn.qyd.blogroom.website.ueditor.upload.Uploader;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ActionEnter {
    private HttpServletRequest request = null;
    private String rootPath = null;
    private String contextPath = null;
    private String actionType = null;
    private ConfigManager configManager = null;

    public ActionEnter(HttpServletRequest request, String rootPath) {
        this.request = request;
        this.rootPath = rootPath;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
    }

    public String exec() {
        String callbackName = this.request.getParameter("callback");
        if (callbackName != null) {
            return !this.validCallbackName(callbackName) ? (new BaseState(false, 401)).toJSONString() : callbackName + "(" + this.invoke() + ");";
        } else {
            return this.invoke();
        }
    }

    public String invoke() {
        if (this.actionType != null && ActionMap.mapping.containsKey(this.actionType)) {
            if (this.configManager != null && this.configManager.valid()) {
                State state = null;
                int actionCode = ActionMap.getType(this.actionType);
                Map<String, Object> conf = null;
                switch(actionCode) {
                    case 0:
                        return this.configManager.getAllConfig().toString();
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        conf = this.configManager.getConfig(actionCode);
                        state = (new Uploader(this.request, conf)).doExec();
                        break;
                    case 5:
                        conf = this.configManager.getConfig(actionCode);
                        String[] list = this.request.getParameterValues((String)conf.get("fieldName"));
                        state = (new ImageHunter(conf)).capture(list);
                        break;
                    case 6:
                    case 7:
                        conf = this.configManager.getConfig(actionCode);
                        int start = this.getStartIndex();
                        state = (new FileManager(conf)).listFile(start);
                }

                return state.toJSONString();
            } else {
                return (new BaseState(false, 102)).toJSONString();
            }
        } else {
            return (new BaseState(false, 101)).toJSONString();
        }
    }

    public int getStartIndex() {
        String start = this.request.getParameter("start");

        try {
            return Integer.parseInt(start);
        } catch (Exception var3) {
            return 0;
        }
    }

    public boolean validCallbackName(String name) {
        return name.matches("^[a-zA-Z_]+[\\w0-9_]*$");
    }
}

