package com.goforer.github_clean_architecture_mvp.presentation.caller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;

import com.goforer.base.presentation.customtabsclient.shared.CustomTabsHelper;
import com.goforer.base.presentation.customtabsclient.shared.ServiceConnection;
import com.goforer.base.presentation.customtabsclient.shared.ServiceConnectionCallback;
import com.goforer.github_clean_architecture_mvp.R;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;
import com.goforer.github_clean_architecture_mvp.presentation.view.activity.RepositoryActivity;

public enum Caller {
    INSTANCE;

    public static final String EXTRA_PROFILE = "github:profile";
    
    private ServiceConnection mServiceConnection = new ServiceConnection(new ServiceConnectionCallback() {
        @Override
        public void onServiceConnected(CustomTabsClient client) {
            client.warmup(0);

            CustomTabsSession session = client.newSession(new CustomTabsCallback());
            session.mayLaunchUrl(Uri.parse(mUrl), null, null);
        }

        @Override
        public void onServiceDisconnected() {
        }

    });

    private Intent createIntent(Context context, Class<?> cls, boolean isNewTask) {
        Intent intent = new Intent(context, cls);

        if (isNewTask && !(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return intent;
    }

    @SuppressWarnings("unused")
    private Intent createIntent(String action) {
        Intent intent = new Intent(action);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    public void callRepository(Context context, User userProfile) {
        Intent intent = createIntent(context, RepositoryActivity.class, true);
        intent.putExtra(EXTRA_PROFILE, userProfile);
        context.startActivity(intent);
    }

    public void callChromeCustomTabs(Context context, final String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ic_close));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setToolbarColor(context.getResources()
                    .getColor(R.color.colorZimgo, null)).setShowTitle(true);
        } else {
            builder.setToolbarColor(context.getResources()
                    .getColor(R.color.colorZimgo)).setShowTitle(true);
        }

        builder.enableUrlBarHiding();
        CustomTabsIntent customTabsIntent = builder.build();
        String packageName = CustomTabsHelper.getPackageNameToUse(context);
        CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent);
        CustomTabsClient.bindCustomTabsService(context, packageName, mServiceConnection);

        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
    
    public void unBindService(Context context) {
        context.unbindService(mServiceConnection);
    }
}
