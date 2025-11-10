package com.kume.kume.configuration.security;

import org.springframework.stereotype.Component;

@Component
public class CspNonceFilter extends org.springframework.web.filter.OncePerRequestFilter {
    private static final java.security.SecureRandom RNG = new java.security.SecureRandom();

    private static String nonce() {
        byte[] b = new byte[16];
        RNG.nextBytes(b);
        return java.util.Base64.getEncoder().encodeToString(b);
    }

    @Override
    protected void doFilterInternal(
            jakarta.servlet.http.HttpServletRequest req,
            jakarta.servlet.http.HttpServletResponse res,
            jakarta.servlet.FilterChain chain) throws java.io.IOException, jakarta.servlet.ServletException {

        String n = nonce();
        req.setAttribute("cspNonce", n);

        String csp = String.join(" ",
                "default-src 'self';",
                "base-uri 'self';",
                "object-src 'none';",
                "frame-ancestors 'none';",
                "img-src 'self' data:;",
                "font-src 'self' https://fonts.gstatic.com;",
                "script-src 'self' https://cdn.jsdelivr.net 'nonce-" + n + "';",
                "style-src 'self' https://fonts.googleapis.com 'nonce-" + n + "';",
                "connect-src 'self';",
                "form-action 'self';",
                "upgrade-insecure-requests");

        res.setHeader("Content-Security-Policy", csp);
        chain.doFilter(req, res);
    }
}
