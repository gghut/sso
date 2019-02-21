package space.ggh.sso;

import space.ggh.sso.listener.PublicUrlsListener;

/**
 * @author by ggh on 18-12-4.
 */
public class GrantConfigBuilder {

    private PublicUrlsListener publicUrlsListener;

    public GrantConfigBuilder setPublicUrlsListener(PublicUrlsListener publicUrlsListener){
        this.publicUrlsListener = publicUrlsListener;
        return this;
    }

    public void build(){

    }
}
