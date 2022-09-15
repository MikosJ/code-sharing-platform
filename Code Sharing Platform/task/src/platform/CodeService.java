package platform;

public class CodeService {
    static Code home = new Code("""
            from random import*
            r=randint
            def R(w,h,n):
             M=[6]*h*w
             def m(x,y,c,d):M[y%h*w+x%w]=c;t=r(0,15)*(r(0,d)<2);t&8and m(x+1,y,c,d+1);t&4and m(x-1,y,c,d+1);t&2and m(x,y+1,c,d+1);t&1and m(x,y-1,c,d+1)
             while n:m(r(0,w),r(0,h),r(0,19),0);n-=1
             print"P3 %s %s 255 "%(w,h)+' '.join(`[0,192,255][int(x)]`for c in M for x in'0002212220200101121100'[c:c+3])""");

    static public String getHomeApi() {
        return home.getCode();
    }

    static public String getHomeWeb() {
        return """
                <html>
                <head>
                    <title>Code</title>
                </head>
                <body>
                    <pre>"""+home.getCode()+"""
                </pre>
                </body>
                </html>
                """;
    }

}
