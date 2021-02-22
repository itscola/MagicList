package top.whitecola.magiclist.mojangapi.mcpl.struct;

public class TrueProfileNoCape {



    private long timestamp;
    private String profileId;
    private String profileName;
    private TexturesBean textures;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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

    public TexturesBean getTextures() {
        return textures;
    }

    public void setTextures(TexturesBean textures) {
        this.textures = textures;
    }

    public static class TexturesBean {
        /**
         * SKIN : {"url":"http://textures.minecraft.net/texture/602ccd3a803ef390f3628f789ba58179596975f6d34bcea9aad0403a26bf2a3f"}
         */

        private SKINBean SKIN;

        public SKINBean getSKIN() {
            return SKIN;
        }

        public void setSKIN(SKINBean SKIN) {
            this.SKIN = SKIN;
        }

        public static class SKINBean {
            /**
             * url : http://textures.minecraft.net/texture/602ccd3a803ef390f3628f789ba58179596975f6d34bcea9aad0403a26bf2a3f
             */

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
