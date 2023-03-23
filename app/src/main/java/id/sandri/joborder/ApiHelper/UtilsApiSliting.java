package id.sandri.joborder.ApiHelper;

public class UtilsApiSliting {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/schedule/sliting/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitMachineSliting.getDataSliting(BASE_URL_API).create(BaseApiService.class);
    }
}
