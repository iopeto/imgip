package org.iopeto.imgip.web.dump;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Service
public class DumpServiceImpl implements DumpService {

    @Override
    public String dumpRequest(HttpServletRequest request){
        String dump = "";

        dump += "request.getCharacterEncoding(): " + request.getCharacterEncoding() + "\n";
        dump += "request.getLocale(): " + request.getLocale() + "\n";
        dump += "request.getRemoteAddr(): " + request.getRemoteAddr() + "\n";
        dump += "request.getRemotePort(): " + request.getRemotePort() + "\n\n";

        dump += "headers:\n";
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            dump += "header: " + headerName + "=";
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while (headerValues.hasMoreElements()){
                String headerValue = headerValues.nextElement();
                dump += headerValue +"; ";
            }
            dump += "\n";
        }

        return dump;
    }
}
