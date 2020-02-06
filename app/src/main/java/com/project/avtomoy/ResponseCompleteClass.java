package com.project.avtomoy;

public class ResponseCompleteClass {
    private GetRecordModel recordModel;
    private AdvertClass advert;

    public ResponseCompleteClass(GetRecordModel recordModel, AdvertClass advert) {
        this.recordModel = recordModel;
        this.advert = advert;
    }

    public GetRecordModel getRecordModel() {
        return recordModel;
    }

    public void setRecordModel(GetRecordModel recordModel) {
        this.recordModel = recordModel;
    }

    public AdvertClass getAdvert() {
        return advert;
    }

    public void setAdvert(AdvertClass advert) {
        this.advert = advert;
    }
}
