package id.sandri.joborder.ApiHelper;

public class UtilsApiRegister {
    public static final String BASE_URL_API = "https://mgi-schedule.herokuapp.com/api/";

    public static BaseApiService getApiService(){
        return RetrofitRegister.getRegister(BASE_URL_API).create(BaseApiService.class);
    }

}
