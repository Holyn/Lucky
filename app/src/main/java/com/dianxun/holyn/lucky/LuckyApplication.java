package com.dianxun.holyn.lucky;

import android.app.Application;

import com.dianxun.holyn.lucky.view.module.RootModule;

import org.xutils.x;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by holyn on 2015/12/9.
 */
public class LuckyApplication extends Application{

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
        initXutils();
    }

    /**
     * Inject every dependency declared in the object with the @Inject annotation if the dependency
     * has been already declared in a module and already initialized by Dagger.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Extend the dependency container graph will new dependencies provided by the modules passed as
     * arguments.
     *
     * @param modules used to populate the dependency container.
     */
    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }

    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    /**
     * 初始化xutils
     */
    private void initXutils(){
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

}
