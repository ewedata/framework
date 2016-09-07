package com.framework.web;

public interface IUrlDef {

    /*
     * ===== 注意： ===== 在这里定义的url千万不能以/结尾，否则当输入不以/结尾的url时无法访问。
     * 但是在urls.properties中强烈建议url以/结尾，这样可以避免服务器端重定向。 另外，对于前端开发而言，所有js请求的url也建议以/结尾，否则会收到 HTTP 307
     * 重定向的响应（虽然浏览器通常也能正常处理，但增加了请求的数量）。 ===============
     */

    public String demo_add = "/api/demo/add";

    public String login = "/";

    public String user_add="/api/user/add";
}
