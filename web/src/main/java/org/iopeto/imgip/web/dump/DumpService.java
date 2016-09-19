package org.iopeto.imgip.web.dump;

import javax.servlet.http.HttpServletRequest;

public interface DumpService {
    String dumpRequest(HttpServletRequest request);
}
