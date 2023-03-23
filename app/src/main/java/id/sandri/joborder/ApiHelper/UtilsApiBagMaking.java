package id.sandri.joborder.ApiHelper;

public class UtilsApiBagMaking {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/schedule/bagmaking/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitMachineBag.getDataBag(BASE_URL_API).create(BaseApiService.class);
    }
}
