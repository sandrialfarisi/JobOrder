package id.sandri.joborder.ApiHelper;

public class UtilsApiDryLaminasi {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/schedule/drylaminasi/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitMachineDry.getDataDry(BASE_URL_API).create(BaseApiService.class);
    }
}
