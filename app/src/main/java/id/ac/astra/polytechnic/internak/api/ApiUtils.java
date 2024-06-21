package id.ac.astra.polytechnic.internak.api;

public class ApiUtils {

    //this is your server API server ip Address
    // (if you use localhost, look for your computer ip address)
    public static final String API_URL = "http://10.1.9.219:8080/";
    // if You use emulator/AVD use this:
    //public static final String API_URL = "http://10.0.2.2:8080/";
    private ApiUtils(){
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient (API_URL).create(UserService.class);
    }
}
