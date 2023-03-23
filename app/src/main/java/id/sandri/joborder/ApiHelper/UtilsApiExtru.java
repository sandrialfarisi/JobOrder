package id.sandri.joborder.ApiHelper;

public class UtilsApiExtru {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/schedule/extru/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitMachineExtru.getDataExtru(BASE_URL_API).create(BaseApiService.class);
    }
}
