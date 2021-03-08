package tsh.service.impl;

import tsh.service.ImportHelpService;
import tsh.util.TFileUtils;

public class ResouceHelp implements ImportHelpService {
    @Override
    public String getCode(String path) {
        return TFileUtils.readToStr("/Users/quyixiao/git/java-python/script/yijie/" + path);
    }
}
