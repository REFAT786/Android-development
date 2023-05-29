package com.ibss.contactapp;

import static android.service.controls.ControlsProviderService.TAG;

import com.google.android.gms.ads.rewarded.RewardedAd;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.ibss.contactapp.ads.MyApplication;
import com.ibss.contactapp.fragment.LoginFragment;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    //............................

    private static final int LOGINFLAG = 0;
    String remoteMsg;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(this, MyFirebaseMessagingService.class);
        //startService(intent);

        //banner ad
        MobileAds.initialize(this, initializationStatus -> {

        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

         */
        //..........................
        //interstitial ad

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        Toast.makeText(MainActivity.this, "interstitial ads", Toast.LENGTH_SHORT).show();

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad dismissed fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                // Called when ad fails to show.
                                Log.e(TAG, "Ad failed to show fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });

                    }

                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        Toast.makeText(MainActivity.this, loadAdError.toString(), Toast.LENGTH_SHORT).show();
                        mInterstitialAd = null;
                    }
                });

        new Handler().postDelayed(() -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(this);
                Toast.makeText(MainActivity.this, "show add .....", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                Toast.makeText(MainActivity.this, "not show add .....", Toast.LENGTH_SHORT).show();
            }
        }, 50000);





        //...........................

        //Native ad
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(nativeAd -> {
                    // Show the ad.
                    Toast.makeText(this, "Native ads", Toast.LENGTH_SHORT).show();
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());

        //..............................

        //Rewarded ads
        //RewardedAd rewardedAd = new RewardedAd;

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        //rewardedAd = null;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        //rewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");
                        Toast.makeText(MainActivity.this, "Rewarded ads", Toast.LENGTH_SHORT).show();
                    }
                });




        //................................
        //app open ads

        Application application = getApplication();
        ((MyApplication)application).showAdIfAvailable(MainActivity.this, new MyApplication.OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                Toast.makeText(MainActivity.this, "App Open Ads", Toast.LENGTH_SHORT).show();
            }
        });


        ///....................................

        loadFrag(new LoginFragment(),LOGINFLAG);

        askNotificationPermission();
        //FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        //..........................

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(1)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetchAndActivate()
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()){
                                remoteMsg = mFirebaseRemoteConfig.getString("Event");
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("remoteMsg", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                                myEdit.putString("msg", remoteMsg);
                                myEdit.apply();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Failed to featch the data from firebase", Toast.LENGTH_SHORT).show();
                            }
                        });

        FirebaseMessaging.getInstance().subscribeToTopic("event")
                .addOnCompleteListener(task -> {
                    if(!task.isSuccessful()){
                        Log.e(TAG, "Fail" + task.getException());
                        Toast.makeText(this, "failnotification", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d(TAG,"Successful");
                        Toast.makeText(this, "Success notificatio", Toast.LENGTH_SHORT).show();
                    }
                });



        //..........................

     }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        tokenFCM();
                    }
                }  // TODO: Inform user that that your app will not show notifications.

            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
                tokenFCM();
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void tokenFCM() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
                    String msg = getString(R.string.msg_token_fmt, token);
                    Log.d(TAG, token);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


                });
    }

    public void loadFrag(Fragment fragment, int flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("reverse");

        if(flag == 0){
            ft.add(R.id.contentBody_id, fragment);
        }
        else {
            ft.replace(R.id.contentBody_id, fragment);
        }
        ft.commit();
    }




}