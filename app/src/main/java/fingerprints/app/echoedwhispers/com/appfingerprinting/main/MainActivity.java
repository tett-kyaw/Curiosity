package fingerprints.app.echoedwhispers.com.appfingerprinting.main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.pm.Signature;

import java.util.List;

import fingerprints.app.echoedwhispers.com.appfingerprinting.R;
import fingerprints.app.echoedwhispers.com.appfingerprinting.model.AppPackageInfoCustom;
import fingerprints.app.echoedwhispers.com.appfingerprinting.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.itemHolderLinearLayout);
        getAllInstalledAppFingerprintMapping(view);
    }

    public void getAllInstalledAppFingerprintMapping(View view) {
        List<String> installedAppPackageNameList = Utils.getInstalledAppPackageNameList(view.getContext());
        LinearLayout itemHolderLinearLayout = (LinearLayout) findViewById(R.id.itemHolderLinearLayout);


        for (String packageName : installedAppPackageNameList) {
            try {
                PackageManager packageManager = getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                AppPackageInfoCustom appPackageInfoCustom = new AppPackageInfoCustom(packageInfo.packageName, packageInfo.versionCode, packageInfo.versionName, Utils.getCertificateFingerprintByType(view.getContext(), "MD5"), Utils.getCertificateFingerprintByType(view.getContext(), "SHA1"));
                View packageInfoItemView = generatePackageInfoViewItem(appPackageInfoCustom, itemHolderLinearLayout);
                itemHolderLinearLayout.addView(packageInfoItemView);
            } catch (PackageManager.NameNotFoundException e) {
                // App not installed
            }
        }
    }

    private View generatePackageInfoViewItem (AppPackageInfoCustom appPackageInfoCustom, LinearLayout itemHolderLinearLayout) {
        LayoutInflater layoutInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View packageInfoView = layoutInflater.inflate(R.layout.package_info_item, itemHolderLinearLayout, false);

//        TextView appNameTextView = (TextView) packageInfoView.findViewById(R.id.appName);
        TextView packageNameTextView = (TextView) packageInfoView.findViewById(R.id.packageName);
        TextView versionCodeTextView = (TextView) packageInfoView.findViewById(R.id.versionCode);
        TextView versionNameTextView = (TextView) packageInfoView.findViewById(R.id.versionName);
        TextView md5SignatureTextView = (TextView) packageInfoView.findViewById(R.id.md5Signature);
        TextView shaSignatureTextView = (TextView) packageInfoView.findViewById(R.id.shaSingature);

//        appNameTextView.setText("App Name : " + appPackageInfoCustom.appName);
        packageNameTextView.setText("Package Name : " + appPackageInfoCustom.packageName);
        versionCodeTextView.setText("Version Code : " + appPackageInfoCustom.versionCode);
        versionNameTextView.setText("Version Name : " + appPackageInfoCustom.versionName);
        md5SignatureTextView.setText("MD5 Signature : " + appPackageInfoCustom.md5Signature);
        shaSignatureTextView.setText("SHA Signature : " + appPackageInfoCustom.shaSignature);

        return packageInfoView;
    }


}
