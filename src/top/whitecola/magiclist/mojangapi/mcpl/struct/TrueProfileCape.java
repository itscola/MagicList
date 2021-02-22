package top.whitecola.magiclist.mojangapi.mcpl.struct;

public class TrueProfileCape {


    private String timestamp;
    private String profileId;
    private String profileName;
    private boolean signatureRequired;
    private TexturesBean textures;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public boolean isSignatureRequired() {
        return signatureRequired;
    }

    public void setSignatureRequired(boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    public TexturesBean getTextures() {
        return textures;
    }

    public void setTextures(TexturesBean textures) {
        this.textures = textures;
    }

    public static class TexturesBean {


        private SKINBean SKIN;
        private CAPEBean CAPE;

        public SKINBean getSKIN() {
            return SKIN;
        }

        public void setSKIN(SKINBean SKIN) {
            this.SKIN = SKIN;
        }

        public CAPEBean getCAPE() {
            return CAPE;
        }

        public void setCAPE(CAPEBean CAPE) {
            this.CAPE = CAPE;
        }

        public static class SKINBean {


            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class CAPEBean {


            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
