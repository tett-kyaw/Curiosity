package fingerprints.app.echoedwhispers.com.appfingerprinting.model;

/**
 * Created by tett on 3/2/16.
 */
public class AppPackageInfoCustom {
//    public final String appName;
    public final String packageName;
    public final Integer versionCode;
    public final String versionName;
    public final String md5Signature;
    public final String shaSignature;

    public AppPackageInfoCustom(String packageName, Integer versionCode, String versionName, String md5Signature, String shaSignature) {
//        this.appName = appName;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.md5Signature = md5Signature;
        this.shaSignature = shaSignature;
    }
}
