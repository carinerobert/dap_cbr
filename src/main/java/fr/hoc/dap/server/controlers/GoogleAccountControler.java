package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;

import fr.hoc.dap.server.services.GoogleService;

/**
 * The GoogleAccountController programme implements a web application.
 * Types that carry this annotation are treated as controllers where @RequestMapping methods exists.
 * @param <ActionContext> springboot app.
 */
@Controller
public class GoogleAccountControler<ActionContext> extends GoogleService {

    /**
     * .
     */
    GoogleAccountControler() {
        super();
    }

    /** display messages for dev in a file.*/
    private static final Logger LOG = LogManager.getLogger();

    /** position of the first character stocken. **/
    private static final int SENSIBLE_DATA_FIRST_CHAR = 1;

    /** position of the last character stocken. **/
    private static final int SENSIBLE_DATA_LAST_CHAR = 7;

    /**
     * To Handle the Google response.
     * @param request handle google response by protocole HTTP.
     * @param code (encoded) use by Google, protocole & time request.
     * @param  session HTTP protocole.
     * @return the view ask to display.
     * @throws ServletException When Google account is unable to connected to DaP.
     * @throws GeneralSecurityException still treat the thread till a GeneralSecurityException occurred, treated
     * with a specified message, wich is saved for later retrieval by the #getCause() method.
     */
    @RequestMapping("/oAuth2Callback")
    public String oAuthCallbackoAuthCallback(@RequestParam final String code, final HttpServletRequest request,
            final HttpSession session) throws ServletException, GeneralSecurityException {

        final String decodedCode = extracCode(request);

        final String redirectUri = buildRedirectUri(request, getConf().getoAuth2CallbackUrl());

        final String userId = getUserid(session);

        final String loginName = getLoginName(session);

        try {
            final GoogleAuthorizationCodeFlow flow = super.getFlow();
            final TokenResponse response = flow.newTokenRequest(decodedCode).setRedirectUri(redirectUri).execute();

            final Credential credential = flow.createAndStoreCredential(response, userId);
            if (null == credential || null == credential.getAccessToken()) {
                LOG.warn("Trying to store a NULL AccessToken for user : " + userId);

                if (LOG.isDebugEnabled()) {
                    if (null != credential && null != credential.getAccessToken()) {
                        LOG.debug("New user credential stored with userId : " + userId + "partial AccessToken : "
                                + credential.getAccessToken().substring(SENSIBLE_DATA_FIRST_CHAR,
                                        SENSIBLE_DATA_LAST_CHAR));
                    }
                }
            }

            // onSuccess(request, resp, credential);
        } catch (IOException e) {
            LOG.error("Exception while trying to store user Credential", e);
            throw new ServletException("Error while trying to connect Google Account");
        }

        return "redirect:/user-added";
    }

    /**
     * Add a Google account (user will be prompt to connect and accept required
     * access).
     * @param userId to store Data on user.
     * @param loginName to call an account by this identifier.
     * @param request protocole http for request.
     * @param session activate by the HTTP protocole.
     * @return the view to Display (on Error).
     * @throws GeneralSecurityException still treat the thread till a GeneralSecurityException occurred,
     * treated with a specified message, wich is saved for later retrieval by the #getCause() method.
     * @throws ServletException .
     */
    @RequestMapping("/account/add/{userId}/{loginName}")
    public String addAccount(@PathVariable final String userId, @PathVariable final String loginName,
            final HttpServletRequest request, final HttpSession session)
            throws GeneralSecurityException, ServletException {
        LOG.info("Adding new Acount dapLogin : " + loginName + ", userKey : " + userId);
        String response = "errorOccurs";
        GoogleAuthorizationCodeFlow flow;
        Credential credential = null;
        try {
            flow = super.getFlow();
            credential = flow.loadCredential(loginName);

            if (credential != null && credential.getAccessToken() != null) {
                response = "AccountAlreadyAdded";
            } else {
                // redirect to the authorization flow
                final AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl();
                authorizationUrl.setRedirectUri(buildRedirectUri(request, getConfiguration().getoAuth2CallbackUrl()));
                // store userId in session for CallBack Access
                session.setAttribute("userId", userId);
                session.setAttribute("loginName", loginName);
                response = "redirect:" + authorizationUrl.build();
            }
        } catch (IOException e) {
            LOG.error("Error while loading credential (or Google Flow)", e);
        }
        // only when error occurs, else redirected BEFORE
        return response;
    }

    /**
     * @return the default config.
     */
    protected Configuration getConfiguration() {
        return getConf();
    }

    /**
     * set the actual config.
     * @param myConf .
     */
    public void setConfiguration(final Configuration myConf) {
    }

    /**
     * Retrieve the User ID in Session.
     * @param session the HTTP Session
     * @return the current User Id in Session
     * @throws ServletException When Google account is unable to connected to DaP.
     */
    private String getUserid(final HttpSession session) throws ServletException {
        String userId = null;
        if (null != session && null != session.getAttribute("userId")) {
            userId = (String) session.getAttribute("userId");
        }

        if (null == userId) {
            LOG.error("userId in Session is NULL in Callback");
            throw new ServletException("Error when trying to add Google account : userId is NULL is User Session");
        }
        return userId;
    }

    /**
     * .
     * @param session .
     * @return .
     * @throws ServletException .
     */
    private String getLoginName(final HttpSession session) throws ServletException {
        String loginName = null;
        if (null != session && null != session.getAttribute("loginName")) {
            loginName = (String) session.getAttribute("loginName");
        }

        if (null == loginName) {
            LOG.error("loginName in Session is NULL in Callback");
            throw new ServletException("Error when trying to add Google acocunt : loginName is NULL is User Session");
        }
        return loginName;
    }

    /**
     * @param request to connect google account with DaP.
     * @return endoded google account service.
     * @throws ServletException When Google account is unable to connected to DaP.
     */
    private String extracCode(final HttpServletRequest request) throws ServletException {
        final StringBuffer buf = request.getRequestURL();
        if (null != request.getQueryString()) {
            buf.append('?').append(request.getQueryString());
        }
        final AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
        final String decodeCode = responseUrl.getCode();

        if (decodeCode == null) {
            throw new MissingServletRequestParameterException("code", "String");
        }

        if (null != responseUrl.getError()) {
            LOG.error("Error when trying to add Google account : " + responseUrl.getError());
            throw new ServletException("Error when trying to add Google account");
            // onError(request, resp, responseUrl);
        }

        return decodeCode;
    }

    /**
     * Build a current host (and port) absolute URL.
     * @param req The current HTTP request to extract schema, host, port informations
     * @param destination the "path" to the resource
     * @return an absolute URI builded.
     */
    protected String buildRedirectUri(final HttpServletRequest req, final String destination) {
        final GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath(destination);
        return url.build();
    }

}
