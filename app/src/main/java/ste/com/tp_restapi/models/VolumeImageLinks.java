package ste.com.tp_restapi.models;

public class VolumeImageLinks {

    private String thumbnail;

    public String getThumbnail() {
        if(thumbnail.isEmpty()){
            String sinThumbnail = "";
            return sinThumbnail;
        }else{
            return thumbnail;
        }

    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
