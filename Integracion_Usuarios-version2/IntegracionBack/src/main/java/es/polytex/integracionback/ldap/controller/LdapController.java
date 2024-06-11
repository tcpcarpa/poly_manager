package es.polytex.integracionback.ldap.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.ldap.manager.ldapManager;

public class LdapController extends Controller {
    private static final ldapManager menuManager = new ldapManager();
    private static LdapController controller;
    public static LdapController getInstance() {
        if (controller == null) {
            controller = new LdapController();
        }
        return controller;
    }
}
