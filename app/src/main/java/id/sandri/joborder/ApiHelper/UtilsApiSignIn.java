package id.sandri.joborder.ApiHelper;

public class UtilsApiSignIn {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
