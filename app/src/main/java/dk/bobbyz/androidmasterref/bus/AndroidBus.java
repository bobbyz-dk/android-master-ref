package dk.bobbyz.androidmasterref.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Bobby on 01-05-2015.
 */
public class AndroidBus extends Bus {
    private final Handler mainThread = new Handler(Looper.getMainLooper());

    public AndroidBus() {
        super();
    }

    public AndroidBus(ThreadEnforcer enforcer) {
        super(enforcer);
    }

    public void postOnMain(final Object event) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                post(event);
            }
        });
    }
}
